package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Class to represent a generic image that extends the AbstractImage class and used ImageIO to load
 * and export images from the class.
 */
public abstract class AbstractIOImage extends AbstractImage {

  /**
   * Constructor for the JPEGImage class. Initializes the file path and loads the rest of the
   * values using the parent constructor.
   * @param filePath - the file path where the picture is being stored.
   */
  public AbstractIOImage(String filePath) {
    super(filePath);
  }

  /**
   * Convenience constructor for a JPEGImage.  Specifies whether the image at the given file path
   * should be loaded or not (if this parameter is left out,
   * the image will be loaded automatically).  Useful for making copies of images and giving them
   * unique file paths.
   * @param filePath - the file path that this image should possess.
   * @param dontLoadImage - a boolean flag to signify that the image at the given path should not
   *                      be loaded.
   */
  public AbstractIOImage(String filePath, boolean dontLoadImage) {
    super(filePath, dontLoadImage);
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
  public AbstractIOImage(String filePath, int width, int height, int maxColorValue, int [][][] pixels) {
    super(filePath, width, height, maxColorValue, pixels);
  }

  /**
   * Load the image at filename into the class.  Should initialize parameters for image width,
   * height, maxColorValue, and pixels.
   *
   * @param filename - the path name of the image to be loaded into the image class.
   * @throws IllegalArgumentException if the file does not exist or is in the wrong format.
   */
  @Override
  public void loadImage(String filename) throws IllegalArgumentException {
    BufferedImage img;
    try {
      img = ImageIO.read(new File(filename));
      this.width = img.getWidth();
      this.height = img.getHeight();
      this.pixels = new int[width][height][3];
      for (int x = 0; x < this.width; x++) {
        for (int y = 0; y < this.height; y++) {
          int pixel = img.getRGB(x, y);
          Color color = new Color(pixel, true);
          int red = color.getRed();
          int green = color.getGreen();
          int blue = color.getBlue();
          int [] singlePixel = new int [] {red, green, blue};
          this.pixels[x][y] = singlePixel;
        }
      }
      this.maxColorValue = 255;
    } catch (IOException error) {
      throw new IllegalArgumentException("File does not exist.");
    }
  }

  /**
   * Export this image to a new output file path.
   *
   * @return the file path for the exported image.
   */
  @Override
  public String exportImage() throws IllegalArgumentException {
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
    return exportHelp(img);
  }

  /**
   * Help the export by creating the specific output file path based on the image type.  For
   * example, a png will end with -output.png.  After this path is created export the image with
   * the specific type to that path.
   * @param img - the image to be exported.
   * @return a string representing the path that the image was exported to.
   * @throws IllegalArgumentException if the file could not be written to.
   */
  protected abstract String exportHelp(BufferedImage img) throws IllegalArgumentException;
}
