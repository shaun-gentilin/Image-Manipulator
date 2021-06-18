package image;

import image.hw5.IImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public abstract class AbstractLayeredImage implements ILayeredImage {

  String filename;
  List<IImage> layers;
  List<IImage> transparentLayers;
  int width;
  int height;
  int maxColorValue;

  public AbstractLayeredImage(String filename, String imgType) {
    String type = this.getImageFormat(filename);
    this.filename = filename;
    if (type != imgType) {
      throw new IllegalArgumentException("The image type is invalid.");
    }
    this.layers = new ArrayList<>();
    this.transparentLayers = new ArrayList<>();
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
   * Add a new layer to this image at the top which is a copy of the primary layer (layer 0).
   * Change the pathname to include information about the layer (e.g. the number of the layer) so
   * that the path name will be unique to this layer (i.e. no repeated path names if there are
   * multiple copies).
   */
  @Override
  public abstract void addLayer(int layerNum) throws IllegalArgumentException;


  @Override
  public void removeLayer(int layerNum) throws IllegalArgumentException {
    IImage layer = this.layers.get(layerNum);
    if (layerNum >= this.layers.size()) {
      throw new IllegalArgumentException("Not a valid layer.");
    }
    else if (this.transparentLayers.contains(layer)) {
      this.transparentLayers.remove(layer);
    }
    this.layers.remove(layerNum);
  }

  @Override
  public void toggleLayerTransparency(int layerNum) throws IllegalArgumentException {
    IImage layer = this.layers.get(layerNum);
    if (layerNum >= this.layers.size()) {
      throw new IllegalArgumentException("Not a valid layer.");
    }
    else if (this.transparentLayers.contains(layer)) {
      this.transparentLayers.remove(layer);
    }
    else {
      this.transparentLayers.add(layer);
    }
  }
}
