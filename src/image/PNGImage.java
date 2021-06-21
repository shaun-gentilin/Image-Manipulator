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
    super(filePath, width, height, maxColorValue, pixels);
  }

  /**
   * Help the export by creating the specific output file path based on the image type.  For
   * example, a png will end with -output.png.  After this path is created export the image with the
   * specific type to that path.
   *
   * @param img - the image to be exported.
   * @return a string representing the path that the image was exported to.
   * @throws IllegalArgumentException if the file could not be written to.
   */
  @Override
  protected String exportHelp(BufferedImage img) throws IllegalArgumentException {
    String output = this.filePath.substring(0, this.filePath.length() - 4) + "-output.png";
    File outputPath = new File(output);
    try {
      ImageIO.write(img, "png", outputPath);
    } catch (IOException ioException) {
      throw new IllegalArgumentException("Could not write the new Image.");
    }
    return output;
  }
}
