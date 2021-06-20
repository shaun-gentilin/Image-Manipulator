package image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * Class representing a layered image in the PNG image format.  A png layered image consists of many
 * layers that are each their own individual png image.
 */
public class PNGLayeredImage implements ILayeredImage {

  private final String filename;
  private List<PNGImage> layers;
  private List<PNGImage> transparentLayers;
  private int width;
  private int height;
  private int maxColorValue;

  /**
   * A constructor for the PNGLayeredImage class.
   * @param filename - the file path representing the text file that specifies where the layer
   *                 images are being stored.
   */
  public PNGLayeredImage(String filename) {
    this.filename = filename;
    this.transparentLayers = new ArrayList<>();
    String type = this.getImageFormat(filename);
    if (!(type.equalsIgnoreCase("png"))) {
      throw new IllegalArgumentException("The image type is invalid.");
    }
    loadImageLayers(filename);
  }

  /**
   * Constructor that allows for fields to be initialize from parameters.  Allows for conversion
   * from one layered image type to another.
   * @param filename - the file path for this image.
   * @param layers - the layers for this image.
   * @param transparentLayers - the layers that are transparent in this image.
   * @param width - the width for this image.
   * @param height - the height for this image.
   * @param maxColorValue - the maximum color value for this image.
   */
  public PNGLayeredImage(String filename, List<PNGImage> layers,
      List<PNGImage> transparentLayers, int width, int height, int maxColorValue) {
    this.transparentLayers = transparentLayers;
    this.layers = layers;
    this.filename = filename;
    this.width = width;
    this.height = height;
    this.maxColorValue = maxColorValue;
  }

  /**
   * Loads a layered image based on the filename.
   *
   * @param filename - filename of the text file specifying where the images are stored.
   */
  @Override
  public void loadImageLayers(String filename) {
    this.layers = new ArrayList<>();
    try {
      File input = new File(filename);
      Scanner reader = new Scanner(input);
      while (reader.hasNextLine()) {
        this.layers.add(new PNGImage(reader.nextLine()));
      }
      reader.close();
    } catch(FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }

    //make sure all of the images have the same parameters
    this.height = this.layers.get(0).getHeight();
    this.width = this.layers.get(0).getWidth();
    this.maxColorValue = this.layers.get(0).getMaxColorValue();

    for (PNGImage i : this.layers) {
      if (i.getHeight() != this.height
          || i.getWidth() != this.width
          || i.getMaxColorValue() != this.maxColorValue) {
        throw new IllegalArgumentException("Images did not have the same dimensions.");
      }
    }
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getMaxColorValue() {
    return this.maxColorValue;
  }

  @Override
  public void addLayer() throws IllegalArgumentException {
    //initialize the reader
    Scanner reader;
    try {
      File input = new File(this.filename);
      reader = new Scanner(input);
    } catch(FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }

    //make the new layer as a copy of the original layer (layer 0).  This will be the first entry
    //in the given text file (filename)
    if (!(reader.hasNextLine())) {
      throw new IllegalArgumentException("No main file to copy.");
    }
    else {
      String primaryLayerPath = reader.nextLine();
      //take the primary path without the .ppm at the end and add stuff to it so that we know
      //this will be a valid file path.  Add in information about the layer so that the path will
      //be unique to the new layer.
      String newPath = primaryLayerPath.substring(0, primaryLayerPath.length() - 4)
          + "-layer" + (this.layers.size() - 1) + ".png";

      //create the new file so that we can create a new PPMImage object with the new name.
      File newLayer = new File(newPath);
      try {
        newLayer.createNewFile();
      } catch (IOException e) {
        throw new IllegalStateException("Cannot create new file.");
      }

      //create the new image object and load the contents of the original image into the new object.
      //This will create a new image object with a new file path, but the same properties as the
      //original layer.
      PNGImage newLayerImage = new PNGImage(newPath, true);
      newLayerImage.loadImage(primaryLayerPath);

      //add the new layer to the list of layers
      this.layers.add(newLayerImage);
    }
    reader.close();
  }

  @Override
  public IImage getLayer(int layerNum) {
    if (layerNum >= this.layers.size() || layerNum < 0) {
      throw new IllegalArgumentException("Not a valid layer.");
    }
    return layers.get(layerNum);
  }

  /**
   * Save all layered images to text file.
   */
  @Override
  public String saveImage() {
    String path = this.filename.substring(0, this.filename.length() - 4) + "-output-png.txt";
    File newFile = new File(path);
    try {
      newFile.createNewFile();
      FileWriter writer = new FileWriter(newFile);
      for(int i = 0; i < this.layers.size(); i++) {
        String outPath = this.layers.get(i).exportImage();
        writer.write(outPath + "\n");
      }
      writer.close();
    } catch (IOException error) {
      throw new IllegalStateException("Cannot write to text file.");
    }
    return path;
  }

  /**
   * Export the top-most layer as an image to a new output file path.
   */
  @Override
  public String exportImage() throws IllegalArgumentException, IllegalStateException {
    for (int i = this.layers.size() - 1; i >= 0; i--) {
      if (!(this.transparentLayers.contains(this.layers.get(i)))) {
        return this.layers.get(i).exportImage();
      }
    }
    throw new IllegalStateException("All layers were transparent.");
  }

  @Override
  public void removeLayer(int layerNum) throws IllegalArgumentException {
    if (layerNum >= this.layers.size() || layerNum < 0) {
      throw new IllegalArgumentException("Not a valid layer.");
    }

    this.transparentLayers.remove(this.layers.get(layerNum));
    this.layers.remove(layerNum);
  }

  @Override
  public void toggleLayerTransparency(int layerNum) throws IllegalArgumentException {
    if (layerNum >= this.layers.size() || layerNum < 0) {
      throw new IllegalArgumentException("Not a valid layer.");
    }

    PNGImage layer = this.layers.get(layerNum);
    if (this.transparentLayers.contains(layer)) {
      this.transparentLayers.remove(layer);
    }
    else {
      this.transparentLayers.add(layer);
    }
  }

  /**
   * Replaces given layer with given image.
   *
   * @param image Image to be replaced.
   * @param layer Layer in which the Image shall be replaced.
   * @throws IllegalArgumentException If the image is null, or if the layer is invalid.
   */
  @Override
  public void replaceLayer(IImage image, int layer) throws IllegalArgumentException {
    if (!(image instanceof PNGImage)) {
      throw new IllegalArgumentException("Images must be in the correct format.");
    }
    else if (layer < 0 || layer > this.layers.size() - 1) {
      throw new IllegalArgumentException("Not a valid layer number to replace.");
    }
    else if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    else if (image.getHeight() != this.height
    || image.getWidth() != this.width
    || image.getMaxColorValue() != this.maxColorValue) {
      throw new IllegalArgumentException("New image must have the same dimensions as the existing "
          + "layered image.");
    }

    this.layers.set(layer, (PNGImage) image);
  }

  /**
   * Observes the amount of layers in a layered Image.
   *
   * @returns the amount of layers in a layered Image as an int.
   */
  @Override
  public int getAmountLayers() {
    return this.layers.size();
  }

  /**
   * Save the current image in a different type specified by type.  If the type is the type that the
   * current image already is, the normal saveImage method will be used.
   *
   * @param type - the type to convert this image to.
   * @throws IllegalArgumentException if the type was invalid.
   */
  @Override
  public String saveImageAs(ImageType type) throws IllegalArgumentException {
    switch (type) {
      case PNG:
        return this.saveImage();
      case PPM:
        List<PPMImage> newLayersPPM = new ArrayList<>();
        List<PPMImage> newTransLayersPPM = new ArrayList<>();
        for (PNGImage i : this.layers) {
          newLayersPPM.add((PPMImage) i.convertTo(ImageType.PPM));
        }
        for (PNGImage i : this.transparentLayers) {
          newTransLayersPPM.add((PPMImage) i.convertTo(ImageType.PPM));
        }

        ILayeredImage newLayeredImagePPM = new PPMLayeredImage(this.filename, newLayersPPM,
            newTransLayersPPM, this.width, this.height, this.maxColorValue);
        return newLayeredImagePPM.saveImage();
      case JPEG:
        List<JPEGImage> newLayersJPEG = new ArrayList<>();
        List<JPEGImage> newTransLayersJPEG = new ArrayList<>();
        for (PNGImage i : this.layers) {
          newLayersJPEG.add((JPEGImage) i.convertTo(ImageType.JPEG));
        }
        for (PNGImage i : this.transparentLayers) {
          newTransLayersJPEG.add((JPEGImage) i.convertTo(ImageType.JPEG));
        }

        ILayeredImage newLayeredImageJPEG = new JPEGLayeredImage(this.filename, newLayersJPEG,
            newTransLayersJPEG, this.width, this.height, this.maxColorValue);
        return newLayeredImageJPEG.saveImage();
      default:
        throw new IllegalArgumentException("The image type was invalid.");
    }
  }

  /**
   * Analyses and returns the format of an input image file.
   *
   * @param filePath image file.
   * @return format of filename.
   */

  @Override
  public String getImageFormat(String filePath) {
    String format = "";
    int ctr = 0;
    try {
      File inputFile = new File(filePath);
      Scanner scan = new Scanner(inputFile);
      while (scan.hasNextLine()) {
        String data = scan.nextLine();
        ImageInputStream iis = ImageIO.createImageInputStream(new File(data));
        Iterator<ImageReader> imageReader = ImageIO.getImageReaders(iis);
        if (!imageReader.hasNext()) {
          throw new IllegalArgumentException("There is no image reader that can read this.");
        }
        if (ctr == 0) {
          format = imageReader.next().getFormatName();
        }
        else {
          if (!(format.equalsIgnoreCase(imageReader.next().getFormatName()))) {
            throw new IllegalArgumentException("All image types must be the same.");
          }
        }
        ctr++;
      }
      return format;
    } catch (IOException error) {
      throw new IllegalArgumentException(error);
    }
  }
}
