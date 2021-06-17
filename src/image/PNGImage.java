package image;

public class PNGImage implements IImage {
  private final String filePath;
  private int width;
  private int height;
  private int maxColorValue;
  private int[][][] pixels;

  PNGImage(String filePath) {
    this.filePath = filePath;
    loadImage(this.filePath);
  }

  /**
   * Load the image at filename into the class.
   *
   * @param filename - the path name of the image to be loaded into the image class.
   * @throws IllegalArgumentException if the file does not exist or is in the wrong format.
   */
  @Override
  public void loadImage(String filename) throws IllegalArgumentException {

  }

  /**
   * Get the width for the image.
   *
   * @return an int representing the width of the image in question.
   */
  @Override
  public int getWidth() {
    return 0;
  }

  /**
   * Get the height for the image.
   *
   * @return an int representing the height of the image in question.
   */
  @Override
  public int getHeight() {
    return 0;
  }

  /**
   * Set the colors of the pixel at the specified width and height to the given pixel colors.
   *
   * @param width  - the width of the pixel to be set.
   * @param height - the height of the pixel to be set.
   * @param pixel  - an array of integers representing the colors that this pixel is to be set to.
   * @throws IllegalArgumentException if width or height are too low or too high,or if the pixel is
   *                                  not valid for the type of image.
   */
  @Override
  public void setPixel(int width, int height, int[] pixel) throws IllegalArgumentException {

  }

  /**
   * Get the pixel at the specified width and height.
   *
   * @param width  - the width of the pixel to be obtained.
   * @param height - the height of the pixel to be obtained.
   * @return an array of integers representing the r, g, and b values of the pixel.
   * @throws IllegalArgumentException if the width or height are too low or too high.
   */
  @Override
  public int[] getPixel(int width, int height) {
    return new int[0];
  }

  /**
   * Export this image to the existing file path.
   */
  @Override
  public void exportImage() throws IllegalArgumentException {

  }

  /**
   * Get the max color value for this image.
   *
   * @return an int representing the max color value for this image.
   */
  @Override
  public int getMaxColorValue() {
    return 0;
  }
}
