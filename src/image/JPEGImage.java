package image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Class to represent an image in the JPEG format.  An image has a width, height, maxColorValue,
 * and consists of pixels that make up the image.
 */
public class JPEGImage extends AbstractIOImage {

  /**
   * Constructor for the JPEGImage class. Initializes the file path and loads the rest of the
   * values using the parent constructor.
   * @param filePath - the file path where the picture is being stored.
   */
  public JPEGImage(String filePath) { super(filePath); }

  /**
   * Convenience constructor for a JPEGImage.  Specifies whether the image at the given file path
   * should be loaded or not (if this parameter is left out,
   * the image will be loaded automatically).  Useful for making copies of images and giving them
   * unique file paths.
   * @param filePath - the file path that this image should possess.
   * @param dontLoadImage - a boolean flag to signify that the image at the given path should not
   *                      be loaded.
   */
  public JPEGImage(String filePath, boolean dontLoadImage) {
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
  public JPEGImage(String filePath, int width, int height, int maxColorValue, int [][][] pixels) {
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
  protected void exportHelp(BufferedImage img) throws IllegalArgumentException {
    File output = new File(super.filePath);
    try {
      ImageIO.write(img, "jpg", output);
    } catch (IOException ioException) {
      throw new IllegalArgumentException("Could not write the new Image.");
    }
  }
}
