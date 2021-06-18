package image;

import image.hw5.IImage;

public interface ILayeredImage {

  /**
   * Get the specified layer for the image.
   * @param layerNum - the number of the layer to be retrieved.
   * @return an image representing a single layer in the layered image.
   */
  IImage getLayer(int layerNum);

  /**
   * Loads a layered image based on the filename.
   * @param filename - filename of the text file specifying where the images are stored.
   */
  void loadImageLayers(String filename);

  /**
   * Get the width for the image.
   * @return an int representing the width of the layered image in question.
   */
  int getWidth();

  /**
   * Get the height for the image.
   * @return an int representing the height of the layered image in question.
   */
  int getHeight();

  /**
   * Export the top-most layer as an image to a new output file path.
   * @return a string representing the filename of the layer that was exported.
   */
  void exportImage() throws IllegalArgumentException;

  /**
   * Get the max color value for this layered image.
   * @return an int representing the max color value for this image.
   */
  int getMaxColorValue();

  /**
   * Analyses and returns the formot of an input image file.
   * @param filename      image file.
   * @return              format of filename.
   */
  String getImageFormat(String filename);

  /**
   * Add a new layer to this image at the top which is a copy of the primary layer (layer 0).
   * Change the pathname to include information about the layer (e.g. the number of the layer) so
   * that the path name will be unique to this layer (i.e. no repeated path names if there are
   * multiple copies).
   */
  void addLayer(int layerNum) throws IllegalArgumentException;

  /**
   * Remove the specified layer from the layered image.
   * @param layerNum - the integer number of the layer to be removed.
   */
  void removeLayer(int layerNum);

  /**
   * Save all layered images to text file.
   */
  void saveImage();
}
