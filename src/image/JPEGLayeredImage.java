package image;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;
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
 * A class to represent a layered image in the JPEG format.  All layers of this image must be in
 * this format and have the same dimensions.
 */
public class JPEGLayeredImage implements ILayeredImage {

  private final String filename;
  private List<JPEGImage> layers;
  private List<JPEGImage> transparentLayers;
  private int width;
  private int height;
  private int maxColorValue;

  /**
   * The constructor for the JPEGLayeredImage class.
   * @param filename - the file path where the image is being stored.
   */
  public JPEGLayeredImage(String filename) {
    this.filename = filename;
    this.transparentLayers = new ArrayList<>();
    String type = this.getImageFormat(filename);
    if (!(type.equalsIgnoreCase("jpeg"))) {
      throw new IllegalArgumentException("The image type is invalid.");
    }
    loadImageLayers(filename);
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
        layers.add(new JPEGImage(reader.nextLine()));
      }
      reader.close();
    } catch(FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }

    //make sure all of the images have the same parameters
    this.height = this.layers.get(0).getHeight();
    this.width = this.layers.get(0).getWidth();
    this.maxColorValue = this.layers.get(0).getMaxColorValue();

    for (JPEGImage i : this.layers) {
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

  /**
   * Add a new layer to this image at the top which is a copy of the primary layer (layer 0).
   * Change the pathname to include information about the layer (e.g. the number of the layer) so
   * that the path name will be unique to this layer (i.e. no repeated path names if there are
   * multiple copies).
   */
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
          + "-layer" + (this.layers.size() - 1) + ".jpg";

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
      JPEGImage newLayerImage = new JPEGImage(newPath, true);
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
  public void saveImage() {
    String path = this.filename.substring(0, this.filename.length() - 4) + "-output.txt";
    File newFile = new File(path);
    try {
      newFile.createNewFile();
      FileWriter writer = new FileWriter(newFile);
      for(int i = 0; i < this.layers.size(); i++) {
        String outPath = this.layers.get(i).exportImage();
        writer.append(outPath + "\n");
      }
      writer.close();
    } catch (IOException error) {
      throw new IllegalStateException("Cannot write to text file.");
    }
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

    JPEGImage layer = this.layers.get(layerNum);
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
    if(image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    if(!(image instanceof JPEGImage)) {
      throw new IllegalArgumentException("Image has to be a JPEG.");
    }
    if(layer < 0 || layer >= this.layers.size()) {
      throw new IllegalArgumentException("Layer is invalid.");
    }
    if(image.getHeight() != this.height || image.getWidth() != this.width
    || image.getMaxColorValue() != this.maxColorValue) {
      throw new IllegalArgumentException("Image does not have same height / width.");
    }
    this.layers.set(layer, (JPEGImage) image);
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
