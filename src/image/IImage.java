package image;

import java.awt.image.BufferedImage;

/**
 * Interface to represent different types of images in different formats (e.g. PPM) and outline what
 * methods they should support.
 */
public interface IImage {

  /**
   * Load the image at filename into the class.  Should initialize parameters for image width,
   * height, maxColorValue, and pixels.
   * @param filename - the path name of the image to be loaded into the image class.
   * @throws IllegalArgumentException if the file does not exist or is in the wrong format.
   */
  void loadImage(String filename) throws IllegalArgumentException;

  /**
   * Get the width for the image.
   *
   * @return an int representing the width of the image in question.
   */
  int getWidth();

  /**
   * Get the height for the image.
   *
   * @return an int representing the height of the image in question.
   */
  int getHeight();

  /**
   * Set the colors of the pixel at the specified width and height to the given pixel colors.
   *
   * @param width  - the width of the pixel to be set.
   * @param height - the height of the pixel to be set.
   * @param pixel  - an array of integers representing the colors that this pixel is to be set to.
   * @throws IllegalArgumentException if width or height are too low or too high,or if the pixel is
   *                                  not valid for the type of image.
   */
  void setPixel(int width, int height, int[] pixel) throws IllegalArgumentException;

  /**
   * Get the pixel at the specified width and height.
   *
   * @param width  - the width of the pixel to be obtained.
   * @param height - the height of the pixel to be obtained.
   * @return an array of integers representing the r, g, and b values of the pixel.
   * @throws IllegalArgumentException if the width or height are too low or too high.
   */
  int[] getPixel(int width, int height);

  /**
   * Export this image to a new output file path.
   * @return the file path for the exported image.
   */
  String exportImage() throws IllegalArgumentException;

  /**
   * Get the max color value for this image.
   *
   * @return an int representing the max color value for this image.
   */
  int getMaxColorValue();

  /**
   * Convert this image to the given type and return the new image.
   * @param type - the type for this image to be converted to.
   * @return an IImage representing the newly converted image.
   * @throws IllegalArgumentException if the ImageType is null.
   */
  IImage convertTo(ImageType type) throws IllegalArgumentException;

  /**
   * Export the image and return a buffered image.
   * @return a buffered image representing the image that has been exported.
   */
  BufferedImage exportBufferedImage();
}
