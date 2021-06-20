package image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class representing an image in the PPM image format.  A PPM layered image consists of many layers
 * that are each an individual ppm image.
 */
public class PPMLayeredImage extends AbstractLayeredImage {

  private final String filename;
  private List<PPMImage> layers;
  private List<PPMImage> transparentLayers;
  private int width;
  private int height;
  private int maxColorValue;

  /**
   * A constructor for the PPMLayeredImage class.
   * @param filename - the file path representing the text file that specifies where the layer
   *                  images are being stored.
   */
  public PPMLayeredImage(String filename) {
    super(filename, "PPM");
    this.filename = filename;
    this.layers = new ArrayList<>();
    this.transparentLayers = new ArrayList<>();
  }

  /**
   * Loads a layered image based on the filename.
   *
   * @param filename - filename of the text file specifying where the images are stored.
   */
  @Override
  public void loadImageLayers(String filename) {
    try {
      File input = new File(filename);
      Scanner reader = new Scanner(input);
      while (reader.hasNextLine()) {
        this.layers.add(new PPMImage(reader.nextLine()));
      }
      reader.close();
    } catch(FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }

    //make sure all of the images have the same parameters
    this.height = this.layers.get(0).getHeight();
    this.width = this.layers.get(0).getWidth();
    this.maxColorValue = this.layers.get(0).getMaxColorValue();

    for (PPMImage i : this.layers) {
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
          + "-layer" + (this.layers.size() - 1) + ".ppm";

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
      PPMImage newLayerImage = new PPMImage(newPath, true);
      newLayerImage.loadImage(primaryLayerPath);

      //add the new layer to the list of layers
      this.layers.add(newLayerImage);
    }
    reader.close();
  }

  @Override
  public IImage getLayer(int layerNum) {
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
      for(int i = 0; i < this.layers.size() - 1; i++) {
        String outPath = this.layers.get(i).exportImage();
        writer.write(outPath + "\n");
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
    if (layerNum >= this.layers.size()) {
      throw new IllegalArgumentException("Not a valid layer.");
    }

    this.transparentLayers.remove(this.layers.get(layerNum));
    this.layers.remove(layerNum);
  }

  @Override
  public void toggleLayerTransparency(int layerNum) throws IllegalArgumentException {
    if (layerNum >= this.layers.size()) {
      throw new IllegalArgumentException("Not a valid layer.");
    }

    PPMImage layer = this.layers.get(layerNum);
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
    if (!(image instanceof PPMImage)) {
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

    this.layers.set(layer, (PPMImage) image);
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
}
