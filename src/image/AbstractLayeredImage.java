package image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Abstract class to represent a generic layered image for the image manipulator.
 */
public abstract class AbstractLayeredImage implements ILayeredImage {

  protected final String filename;
  protected List<IImage> layers;
  protected List<IImage> transparentLayers;
  protected int width;
  protected int height;
  protected int maxColorValue;

  /**
   * The constructor for the JPEGLayeredImage class.
   *
   * @param filename - the file path where the image is being stored.
   */
  public AbstractLayeredImage(String filename) {
    this.filename = filename;
    this.transparentLayers = new ArrayList<>();
    this.getImageFormat(filename);
    loadImageLayers(filename);
  }

  /**
   * Constructor that allows for fields to be initialize from parameters.  Allows for conversion
   * from one layered image type to another.
   *
   * @param filename          - the file path for this image.
   * @param layers            - the layers for this image.
   * @param transparentLayers - the layers that are transparent in this image.
   * @param width             - the width for this image.
   * @param height            - the height for this image.
   * @param maxColorValue     - the maximum color value for this image.
   */
  public AbstractLayeredImage(String filename, List<IImage> layers,
      List<IImage> transparentLayers, int width, int height, int maxColorValue) {
    this.transparentLayers = transparentLayers;
    this.layers = layers;
    this.filename = filename;
    this.width = width;
    this.height = height;
    this.maxColorValue = maxColorValue;
  }

  /**
   * Get the specified layer for the image.
   *
   * @param layerNum - the number of the layer to be retrieved.
   * @return an image representing a single layer in the layered image.
   */
  @Override
  public IImage getLayer(int layerNum) {
    if (layerNum >= this.layers.size() || layerNum < 0) {
      throw new IllegalArgumentException("Not a valid layer.");
    }
    return layers.get(layerNum);
  }

  /**
   * Helper to the loadImageLayers function that allows for the correct type of image to be added
   * to the list of layers.
   * @param path - the file path of the image to be added to the list of layers.
   */
  protected abstract IImage loadImageLayersHelp(String path);

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
        this.layers.add(loadImageLayersHelp(reader.nextLine()));
      }
      reader.close();
    } catch (FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }

    if (this.layers.size() == 0) {
      throw new IllegalArgumentException("There must be at least one layer in the layered image, "
          + "but the text file did not have any.");
    }

    //make sure all of the images have the same parameters
    this.height = this.layers.get(0).getHeight();
    this.width = this.layers.get(0).getWidth();
    this.maxColorValue = this.layers.get(0).getMaxColorValue();

    for (IImage i : this.layers) {
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
   * Export the top-most layer as an image to a new output file path.
   *
   * @return a string representing the filename of the layer that was exported.
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

  /**
   * Analyses and returns the formot of an input image file.
   * @param filename      image file.
   * @return              format of filename.
   * @throws IllegalArgumentException if all images do not have the same type or if the input file
   *     cannot be read.
   */
  @Override
  public String getImageFormat(String filename) throws IllegalArgumentException {
    String format = "";
    int ctr = 0;
    try {
      File inputFile = new File(filename);
      Scanner scan = new Scanner(inputFile);
      while (scan.hasNextLine()) {
        String data = scan.nextLine();
        String type = data.substring(data.length() - 3);
        if (ctr == 0) {
          format = type;
        } else {
          if (!(format.equalsIgnoreCase(type))) {
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

  /**
   * Helper for the addLayer method.  This method will allow concrete implementations of this
   * generic class to create images from the given path that have their own unique paths.
   * @param primaryLayerPath - the path of the original image in this layered image.  To be used to
   *                         copy to the new image (will be loaded in).
   * @return an image that is a copy of the primary layer with a new unique file path.
   */
  protected abstract IImage addLayerHelp(String primaryLayerPath);

  /**
   * Add a new layer to this image at the top which is a copy of the primary layer (layer 0). Change
   * the pathname to include information about the layer (e.g. the number of the layer) so that the
   * path name will be unique to this layer (i.e. no repeated path names if there are multiple
   * copies).  New layers are automatically visible.
   */
  @Override
  public void addLayer() throws IllegalArgumentException {
    //initialize the reader
    Scanner reader;
    try {
      File input = new File(this.filename);
      reader = new Scanner(input);
    } catch (FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }

    //make the new layer as a copy of the original layer (layer 0).  This will be the first entry
    //in the given text file (filename)
    if (!(reader.hasNextLine())) {
      throw new IllegalArgumentException("No main file to copy.");
    } else {
      String primaryLayerPath = reader.nextLine();
      IImage newLayerImage = addLayerHelp(primaryLayerPath);
      newLayerImage.loadImage(primaryLayerPath);

      //add the new layer to the list of layers
      this.layers.add(newLayerImage);
    }
    reader.close();
  }

  /**
   * Remove the specified layer from the layered image.
   *
   * @param layerNum - the integer number of the layer to be removed.
   * @throws IllegalArgumentException if the layer number given is invlaid.
   */
  @Override
  public void removeLayer(int layerNum) throws IllegalArgumentException {
    if (layerNum >= this.layers.size() || layerNum < 0) {
      throw new IllegalArgumentException("Not a valid layer.");
    }

    this.transparentLayers.remove(this.layers.get(layerNum));
    this.layers.remove(layerNum);
  }

  /**
   * Give the add-on for the file output path for the specific image type that we are working with.
   * For example, for a ppm image, the addition should be "-output-ppm.txt".
   * @return the String add-on.
   */
  protected abstract String saveImagePathAddition();

  /**
   * Save all layered images to text file.
   */
  @Override
  public String saveImage() {
    String path = this.filename.substring(0, this.filename.length() - 4) + saveImagePathAddition();
    File newFile = new File(path);
    try {
      newFile.createNewFile();
      FileWriter writer = new FileWriter(newFile);
      for (int i = 0; i < this.layers.size(); i++) {
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
   * Make the specified layer transparent (will not be visible for exportation purposes) if it was
   * already visible, and make the layer visible if it was transparent..
   *
   * @param layerNum - the number of the layer to be toggled.
   * @throws IllegalArgumentException if the layer number given is not valid.
   */
  @Override
  public void toggleLayerTransparency(int layerNum) throws IllegalArgumentException {
    if (layerNum >= this.layers.size() || layerNum < 0) {
      throw new IllegalArgumentException("Not a valid layer.");
    }
    IImage layer = this.layers.get(layerNum);
    if (this.transparentLayers.contains(layer)) {
      this.transparentLayers.remove(layer);
    } else {
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
    if (layer < 0 || layer > this.layers.size() - 1) {
      throw new IllegalArgumentException("Not a valid layer number to replace.");
    } else if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    } else if (image.getHeight() != this.height
        || image.getWidth() != this.width
        || image.getMaxColorValue() != this.maxColorValue) {
      throw new IllegalArgumentException("New image must have the same dimensions as the existing "
          + "layered image.");
    } else if (!(image.getClass().equals(this.layers.get(layer).getClass()))) {
      throw new IllegalArgumentException("Images must be in the correct format.");
    }
    this.layers.set(layer, image);
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
    List<IImage> newLayers = new ArrayList<>();
    List<IImage> newTransLayers = new ArrayList<>();
    for (IImage i : this.layers) {
      newLayers.add(i.convertTo(type));
    }
    for (IImage i : this.transparentLayers) {
      newLayers.add(i.convertTo(type));
    }

    ILayeredImage newLayeredImage;
    switch (type) {
      case PPM:
        newLayeredImage = new PPMLayeredImage(this.filename, newLayers,
            newTransLayers, this.width, this.height, this.maxColorValue);
        break;

      case PNG:
        newLayeredImage = new PNGLayeredImage(this.filename, newLayers,
            newTransLayers, this.width, this.height, this.maxColorValue);
        break;

      case JPEG:
        newLayeredImage = new JPEGLayeredImage(this.filename, newLayers,
            newTransLayers, this.width, this.height, this.maxColorValue);
        break;

      default:
        throw new IllegalArgumentException("The image type was invalid.");
    }

    return newLayeredImage.saveImage();
  }

  /**
   * Export the topmost visible layer of this image and return a buffered image.
   * @return a buffered image representing the exported topmost visible layer of this image.
   */
  @Override
  public BufferedImage exportTopVisibleBufferedImage() {
    for (int i = this.layers.size() - 1; i >= 0; i--) {
      if (!(this.transparentLayers.contains(this.layers.get(i)))) {
        return this.layers.get(i).exportBufferedImage();
      }
    }
    throw new IllegalStateException("All layers were transparent.");
  }
}
