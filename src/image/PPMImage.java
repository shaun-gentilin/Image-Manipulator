package image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to represent the specific PPM format for Images.
 */
public class PPMImage extends AbstractImage {

  /**
   * Constructor for the JPEGImage class. Initializes the file path and loads the rest of the
   * values using the parent constructor.
   * @param filePath - the file path where the picture is being stored.
   */
  public PPMImage(String filePath) {
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
  public PPMImage(String filePath, boolean dontLoadImage) {
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
  public PPMImage(String filePath, int width, int height, int maxColorValue, int [][][] pixels) {
    super(filePath, width, height, maxColorValue, pixels);
  }

  /**
   * Read an image file in the PPM format and store image information in the fields for the class.
   *
   * @param filename the path of the file.
   * @throws IllegalArgumentException if the file does not exist or is in the wrong format.
   */
  @Override
  public void loadImage(String filename) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Filename not valid.");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s);
        builder.append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Not a valid format for a PPM image.");
    }

    int width = sc.nextInt();
    if (width < 0) {
      throw new IllegalArgumentException("Width must not be negative.");
    }
    this.width = width;

    int height = sc.nextInt();
    if (height < 0) {
      throw new IllegalArgumentException("Height must not be negative.");
    }
    this.height = height;

    int maxValue = sc.nextInt();
    if (maxValue <= 0 || maxValue >= 65536) {
      throw new IllegalArgumentException("Maximum color value should not be negative.");
    }
    this.maxColorValue = maxValue;

    int[][][] pixels = new int[width][height][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        int[] pixel = {r, g, b};
        pixels[j][i] = pixel;
      }
    }
    this.pixels = pixels;
  }

  /**
   * Exports image to PPM format.
   * @return the file path for the exported image.
   * @throws IllegalArgumentException if creating file / writing to file fails.
   */
  @Override
  public String exportImage() throws IllegalArgumentException {
    String path = this.filePath.substring(0, this.filePath.length() - 4) + "-output.ppm";
    File output = new File(path);

    boolean isFileCreated;

    try {
      isFileCreated = output.createNewFile();
    } catch (IOException e) {
      throw new IllegalArgumentException("Bad filename.");
    }

    if (isFileCreated) {
      System.out.println("File was created.");
    } else {
      System.out.println("File already existed and is being overwritten.");
    }

    FileWriter writer;
    try {
      writer = new FileWriter(path, false);
    } catch (IOException ioException) {
      throw new IllegalArgumentException("Bad file.");
    }
    try {
      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write(this.maxColorValue + "\n");
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int[] pixel = pixels[j][i];
          writer.write(" " + pixel[0] + "  " + pixel[1] + "  "
              + pixel[2] + "\t");
        }
        writer.write("\n");
      }
      writer.close();
    } catch (IOException error) {
      System.out.println("Error occurred.");
    }
    return path;
  }
}
