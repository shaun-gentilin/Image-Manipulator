package image;

import java.awt.image.BufferedImage;

/**
 * Class to represent a generic image that implements the IImage interface.  Implements methods
 * that should be the same for every image object.
 */
public abstract class AbstractImage implements IImage {
  protected final String filePath;
  protected int width;
  protected int height;
  protected int maxColorValue;
  protected int[][][] pixels;

  public AbstractImage(String filename) {
    this.filePath = filename;
    loadImage(filename);
  }

  /**
   * Convenience constructor for a PPMImage.  Specifies whether the image at the given file path
   * should be loaded or not (if this parameter is left out,
   * the image will be loaded automatically).  Useful for making copies of images and giving them
   * unique file paths.
   * @param filePath - the file path that this image should possess.
   * @param dontLoadImage - a boolean flag to signify that the image at the given path should not
   *                      be loaded.
   */
  public AbstractImage(String filePath, boolean dontLoadImage) {
    this.filePath = filePath;
  }

  /**
   * Constructor that allows all parameters needed for the class to be loaded in without loading the
   * image manually.  This allows for different image type to be converted by passing in all of
   * their parameters.
   * @param filePath - the file path of the image.
   * @param width - the width of the image.
   * @param height - the height of the image.
   * @param maxColorValue - the maximum color value for the image.
   * @param pixels - the list of pixels present in the image.
   */
  public AbstractImage(String filePath, int width, int height, int maxColorValue,
      int [][][] pixels) {
    this.filePath = filePath;
    this.width = width;
    this.height = height;
    this.maxColorValue = maxColorValue;
    this.pixels = pixels;
  }


  /**
   * Load the image at filename into the class.  Should initialize parameters for image width,
   * height, maxColorValue, and pixels.
   *
   * @param filename - the path name of the image to be loaded into the image class.
   * @throws IllegalArgumentException if the file does not exist or is in the wrong format.
   */
  @Override
  public abstract void loadImage(String filename) throws IllegalArgumentException;

  /**
   * Get the width for the image.
   *
   * @return an int representing the width of the image in question.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Get the height for the image.
   *
   * @return an int representing the height of the image in question.
   */
  @Override
  public int getHeight() {
    return this.height;
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
    if (width < 0 || height < 0
        || width > this.width
        || height > this.height) {
      throw new IllegalArgumentException("Invalid width or height.");
    } else if (pixel.length != 3) {
      throw new IllegalArgumentException("Not a valid pixel to set.");
    }

    for (int i : pixel) {
      if (i < 0 || i > this.maxColorValue) {
        throw new IllegalArgumentException("Pixel does not have valid color values.");
      }
    }
    this.pixels[width][height] = pixel.clone();
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
    if (width < 0 || height < 0
        || width > this.width
        || height > this.height) {
      throw new IllegalArgumentException("Invalid width or height.");
    }
    return pixels[width][height];
  }


  /**
   * Export this image to a new output file path.
   *
   * @return the file path for the exported image.
   */
  @Override
  public abstract String exportImage() throws IllegalArgumentException;

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
   * Convert this image to the given type and return the new image.
   *
   * @param type - the type for this image to be converted to.
   * @return an IImage representing the newly converted image.
   * @throws IllegalArgumentException if the ImageType is null.
   */
  @Override
  public IImage convertTo(ImageType type) throws IllegalArgumentException {
    switch (type) {
      case PPM:
        return new PPMImage(this.filePath, this.width, this.height,
            this.maxColorValue, this.pixels);
      case PNG:
        return new PNGImage(this.filePath, this.width, this.height,
            this.maxColorValue, this.pixels);
      case JPEG:
        return new JPEGImage(this.filePath, this.width, this.height,
            this.maxColorValue, this.pixels);
      default:
        throw new IllegalArgumentException("The image type was invalid.");
    }
  }

  @Override
  public BufferedImage exportBufferedImage() {
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < this.width; x++) {
      for (int y = 0; y < this.height; y++) {
        int r = this.pixels[x][y][0];
        int g = this.pixels[x][y][1];
        int b = this.pixels[x][y][2];
        int color = (r << 16) | (g << 8) | b;
        img.setRGB(x, y, color);
      }
    }
    return img;
  }
}
