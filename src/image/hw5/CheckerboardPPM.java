package image.hw5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class representing a Checkerboard as a PPM image.
 */
public class CheckerboardPPM extends AbstractCheckerboardImage {

  private final String filePath;
  private final int maxColorValue; // max Color value of the checkerboard
  private final int width; //width of square in pixels
  private final int tiles; //amount of tiles on top row

  /**
   * Constructo for Checkerboard PPM.
   *
   * @param filePath      filePath
   * @param width         width of each tile in pixels
   * @param tiles         width of checkerboard in tiles
   * @param color1        one color of the tiles
   * @param color2        second color of the tiles
   * @param maxColorValue max Color Value
   */
  public CheckerboardPPM(String filePath, int width, int tiles, int[] color1,
      int[] color2, int maxColorValue) {
    super(filePath, width, tiles, color1, color2, maxColorValue);
    this.filePath = filePath;
    this.width = width;
    this.tiles = tiles;
    this.maxColorValue = maxColorValue;
  }

  /**
   * Exports an image as a PPM.
   *
   * @throws IllegalArgumentException for any IOExceptions.
   */
  @Override
  public String exportImage() throws IllegalArgumentException {
    String path = this.filePath.substring(0, this.filePath.length() - 4) + "-output.ppm";
    File output = new File(path);
    boolean isFileCreated = false;
    try {
      isFileCreated = output.createNewFile();
    } catch (IOException ioException) {
      throw new IllegalArgumentException("Bad filename.");
    }
    if (isFileCreated) {
      System.out.println("File was created.");
    } else {
      System.out.println("File already existed and is being overwritten.");
    }
    FileWriter writer = null;
    try {
      writer = new FileWriter(path, false);
    } catch (IOException ioException) {
      throw new IllegalArgumentException("Bad filename.");
    }
    try {
      writer.write("P3\n");
      writer.write(tiles * width + " " + tiles * width + "\n");
      writer.write(maxColorValue + "\n");
      int[] color1 = this.getPixel(0, 0);
      int[] color2 = this.getPixel(0, 1);
      int count = 0;
      for (int x = 0; x < tiles * width; x++) {
        for (int y = 0; y < tiles * width; y++) {
          for (int t = 0; t < width; t++) {
            int[] pixel = new int[3];
            if (count % 2 == 0) {
              pixel = color1;
            }
            if (count % 2 == 1) {
              pixel = color2;
            }
            writer.write(" " + pixel[0] + " " + pixel[1] + " " + pixel[2] + "\t");
          }
          count++;
        }
        count++;
      }
      writer.close();
    } catch (IOException error) {
      System.out.println("Error occurred.");
    }
    return path;
  }
}
