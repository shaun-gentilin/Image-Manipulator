package image;

import image.hw5.IImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public abstract class AbstractLayeredImage implements ILayeredImage {

  String filename;
  List<IImage> layers;
  int width;
  int height;
  int maxColorValue;

  public AbstractLayeredImage(String filename, String imgType) {
    String type = this.getImageFormat(filename);
    this.filename = filename;
    if (type != imgType) {
      throw new IllegalArgumentException("The image type is invalid.");
    }
    loadImageLayers(this.filename);
  }

  /**
   * Get the specified layer for the image.
   *
   * @param layerNum - the number of the layer to be retrieved.
   * @return an image representing a single layer in the layered image.
   */
  @Override
  public IImage getLayer(int layerNum) {
    return layers.get(layerNum);
  }

  /**
   * Loads a layered image based on the filename.
   *
   * @param filename - filename of the text file specifying where the images are stored.
   */
  @Override
  public abstract void loadImageLayers(String filename);

  /**
   * Analyses and returns the formot of an input image file.
   *
   * @param filename image file.
   * @return format of filename.
   */
  @Override
  public String getImageFormat(String filename) {
    File inputFile = new File(filename);
    try {
      ImageInputStream reader = ImageIO.createImageInputStream(inputFile);
      Iterator<ImageReader> iter = ImageIO.getImageReaders(reader);
      if (!iter.hasNext()) {
        throw new IllegalArgumentException("No readers found.");
      }
      ImageReader temp = iter.next();
      String format = temp.getFormatName();
      reader.close();
      return format;
    } catch (IOException error) {
      throw new IllegalArgumentException("Failure.");
    }
  }

  /**
   * Get the width for the image.
   *
   * @return an int representing the width of the layered image in question.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Get the height for the image.
   *
   * @return an int representing the height of the layered image in question.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Save all layered images to text file.
   */
  @Override
  public void saveImage() {
    File newFile = new File(this.filename);
    try {
      newFile.createNewFile();
      FileWriter write = new FileWriter(newFile);
      for(int x = 0; x < layers.size()-1; x++) {
        write.write(layers.get(x).exportImage() + "\n");
      }
    } catch (IOException error) {
    }
  }

  /**
   * Export the top-most layer as an image to a new output file path.
   */
  @Override
  public void exportImage() throws IllegalArgumentException {
    this.layers.get(this.layers.size() - 1).exportImage();
  }

  /**
   * Get the max color value for this image.
   *
   * @return an int representing the max color value for this image.
   */
  @Override
  public int getMaxColorValue() {
    return this.maxColorValue;
  }

  /**
   * Add a new layer to this image at the top which is a copy of the primary layer.
   */
  @Override
  public abstract void addLayer() throws IllegalArgumentException;

  /**
   * Remove the specified layer from the layered image.
   * @param layerNum - the integer number of the layer to be removed.
   */
  @Override
  public void removeLayer(int layerNum) {
    this.layers.remove(layerNum);
  }
}
