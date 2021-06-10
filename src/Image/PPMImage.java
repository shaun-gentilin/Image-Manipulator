package Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to represent the specific PPM format for Images.
 */
public class PPMImage implements IImage {

  private final String filePath;
  private int width;
  private int height;
  private int maxColorValue;
  private int[][][] pixels;

  public PPMImage(String filename) {
    this.filePath = filename;
    loadImage(filename);
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
   * Observer method for width of image.
   *
   * @return max width of Image.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Observer method for height of image.
   *
   * @return max height of Image.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Setter method for pixel colors.
   *
   * @param width  - the width of the pixel to be set.
   * @param height - the height of the pixel to be set.
   * @param pixel  - an array of 3 integers representing the colors that this pixel is to be set
   *               to.
   * @throws IllegalArgumentException If the width / height / or given pixel are invalid.
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
    this.pixels[width][height] = pixel.clone();
  }

  /**
   * Observer method for pixel colors.
   *
   * @param width  - the width of the pixel to be obtained.
   * @param height - the height of the pixel to be obtained.
   * @return Pixels at specific input position.
   * @throws IllegalArgumentException if the width or height are too low or too high.
   */
  @Override
  public int[] getPixel(int width, int height) throws IllegalArgumentException {
    if (width < 0 || height < 0
        || width > this.width
        || height > this.height) {
      throw new IllegalArgumentException("Invalid width or height.");
    }
    return pixels[width][height];
  }

  /**
   * Exports image to PPM format.
   * @throws IOException if creating file / writing to file fails.
   */
  @Override
  public void exportImage() throws IOException {
    String path = this.filePath.substring(0, this.filePath.length() - 4) + "-output.ppm";
    File output = new File(path);
    boolean isFileCreated = output.createNewFile();

    if (isFileCreated) {
      System.out.println("File was created.");
    }
    else {
      System.out.println("File already existed and is being overwritten.");
    }

    FileWriter writer = new FileWriter(path, false);
    try {
      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write(this.maxColorValue + "\n");
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int[] pixel = pixels[j][i];
          writer.write(" " + pixel[0] + "  " + pixel[1] + "  " +
              pixel[2] + "\t");
        }
        writer.write("\n");
      }
      writer.close();
    } catch (IOException error) {
      System.out.println("Error occurred.");
    }
  }

  /**
   * Observer method for maxColorValue.
   * @return returns MaxColorValue of image
   */
  @Override
  public int getMaxColorValue() {
    return this.maxColorValue;
  }
}
