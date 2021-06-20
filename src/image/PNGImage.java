package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A class to represent an image in the PNG format.  An image has a width, height, maxColorvalue,
 * and a list of pixels that compose the image.
 */
public class PNGImage implements IImage {
  private final String filePath;
  private int width;
  private int height;
  private int maxColorValue;
  private int[][][] pixels;

  /**
   * Constructor for the PNGImage class.  Initializes the filepath and loads the image using the
   * super constructor.
   * @param filePath - the file path representing where the image came from.
   */
  public PNGImage(String filePath) {
    this.filePath = filePath;
    this.loadImage(filePath);
  }

  /**
   * Convenience constructor for a PNGImage.  Specifies whether the image at the given file path
   * should be loaded or not (if this parameter is left out,
   * the image will be loaded automatically).  Useful for making copies of images and giving them
   * unique file paths.
   * @param filePath - the file path that this image should possess.
   * @param dontLoadImage - a boolean flag to signify that the image at the given path should not
   *                      be loaded.
   */
  public PNGImage(String filePath, boolean dontLoadImage) {
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
  public PNGImage(String filePath, int width, int height, int maxColorValue, int [][][] pixels) {
    this.filePath = filePath;
    this.width = width;
    this.height = height;
    this.maxColorValue = maxColorValue;
    this.pixels = pixels;
  }

  /**
   * Load the image at filename into the class.
   *
   * @param filename - the path name of the image to be loaded into the image class.
   * @throws IllegalArgumentException if the file does not exist or is in the wrong format.
   */
  @Override
  public void loadImage(String filename) throws IllegalArgumentException {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(filename));
      this.width = img.getWidth();
      this.height = img.getHeight();
      this.pixels = new int[width][height][3];
      for(int x = 0; x < this.width; x++) {
        for(int y = 0; y < this.height; y++) {
          int pixel = img.getRGB(x, y);
          Color color = new Color(pixel, true);
          int red = color.getRed();
          int green = color.getGreen();
          int blue = color.getBlue();
          int [] singlePixel = new int[] {red, green, blue};
          this.pixels[x][y] = singlePixel;
        }
      }
      int max = 0;
      for(int x = 0; x < this.width; x++) {
        for(int y = 0; y < this.height; y++) {
          for(int z = 0; z < 3; z++) {
            if(max < this.pixels[x][y][z]) {
              max = this.pixels[x][y][z];
            }
          }
        }
      }
      this.maxColorValue = max;
    } catch (IOException error) {
      throw new IllegalArgumentException("File does not exist.");
    }
  }

  /**
   * Export this image to the existing file path.
   */
  @Override
  public String exportImage() throws IllegalArgumentException {
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for(int x = 0; x < this.width; x++) {
      for(int y = 0; y < this.height; y++) {
        int r = this.pixels[x][y][0];
        int g = this.pixels[x][y][1];
        int b = this.pixels[x][y][2];
        int color = (r << 16) | (g << 8) | b;
        img.setRGB(x, y, color);
      }
    }
    String output = this.filePath.substring(0, this.filePath.length()-4) + "-output.png";
    File outputPath = new File(output);
    try {
      ImageIO.write(img, "png", outputPath);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    return output;
  }

  @Override
  public int getMaxColorValue() {
    return this.maxColorValue;
  }

  /**
   * Convert this image to the given type and return the new image.
   *
   * @param type - the type for this image to be converted to.
   * @return an IImage representing the newly converted image.
   * @throws IllegalArgumentException if the ImageType is invalid.
   */
  @Override
  public IImage convertTo(ImageType type) throws IllegalArgumentException {
    switch (type) {
      case PNG:
        return this;
      case PPM:
        return new PPMImage(this.filePath, this.width, this.height,
            this.maxColorValue, this.pixels);
      case JPEG:
        return new JPEGImage(this.filePath, this.width, this.height,
            this.maxColorValue, this.pixels);
      default:
        throw new IllegalArgumentException("The image type was invalid.");
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
  public void setPixel(int width, int height, int[] pixel) throws IllegalArgumentException {
    if (width < 0 || height < 0
        || width > this.width
        || height > this.height) {
      throw new IllegalArgumentException("Invalid width or height.");
    } else if (pixel.length != 3) {
      throw new IllegalArgumentException("Not a valid pixel to set.");
    }
    this.pixels[width][height] = pixel.clone();
  }

  @Override
  public int[] getPixel(int width, int height) {
    if (width < 0 || height < 0
        || width > this.width
        || height > this.height) {
      throw new IllegalArgumentException("Invalid width or height.");
    }
    return pixels[width][height];
  }
}
