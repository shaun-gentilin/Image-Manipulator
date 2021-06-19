package image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * Abstract class to represent an abstract version of a layered image.  A layered image consists of
 * many image layers that are each themselves an image.  These layers can be manipulated, exported,
 * and saved just like any individual image.
 */
public abstract class AbstractLayeredImage implements ILayeredImage {

  private final String filename;

  /**
   * Constructor for the AbstractLayeredImage class.  Checks if the type of the image is correct
   * then initializes and loads the correct values into the fields.
   * @param filename - the file path representing where the image is stored.
   * @param imgType - the type of the image to be loaded (each layer of this image has the same
   *                type.
   */
  public AbstractLayeredImage(String filename, String imgType) {
    String type = this.getImageFormat(filename);
    this.filename = filename;
    if (!(type.equals(imgType))) {
      throw new IllegalArgumentException("The image type is invalid.");
    }
    loadImageLayers(this.filename);
  }

  /**
   * Analyses and returns the format of an input image file.
   *
   * @param filePath image file.
   * @return format of filename.
   */

  @Override
  public String getImageFormat(String filename) {
    File inputFile = new File(filename);
    try {
      ImageInputStream reader = ImageIO.createImageInputStream(new FileInputStream(inputFile));
      Iterator<ImageReader> iter = ImageIO.getImageReaders(reader);
      if (!iter.hasNext()) {
        throw new IllegalArgumentException("No readers found.");
      }
      ImageReader temp = iter.next();
      String format = temp.getFormatName();
      reader.close();
      return format;
    } catch (IOException error) {
      throw new IllegalArgumentException(error);
    }
  }
}
