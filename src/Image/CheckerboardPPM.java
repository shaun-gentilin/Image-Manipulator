package Image;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CheckerboardPPM extends AbstractCheckerboardImage {
  private final String filePath;
  private final int maxColorValue; // max Color value of the checkerboard
  private final int width; //width of square in pixels
  private final int tiles; //amount of tiles on top row

  public CheckerboardPPM(String filePath, int width, int tiles, int[] color1,
      int[] color2, int maxColorValue) {
    super(filePath, width, tiles, color1, color2, maxColorValue);
    this.filePath = filePath;
    this.width = width;
    this.tiles = tiles;
    this.maxColorValue = maxColorValue;
  }

  @Override
  public void exportImage() throws IOException {
    File output = new File("output" + this.filePath);
    try {
      if (output.createNewFile()) {
        System.out.println("File created: " + output.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException error) {
      System.out.println("Error occurred.");
    }
    FileWriter writer = new FileWriter(output.getName());
    try {
      writer.write("P3\n");
      writer.write(tiles + " " + tiles + "\n");
      writer.write(this.maxColorValue + "\n");
      for (int i = 0; i < tiles; i++) {
        for (int j = 0; j < tiles; j++) {
          for(int x = 0; x < width; x++) {
            for(int y = 0; y < width; y++) {
              int [] pixel = super.getPixel(i, j);
              writer.write(" " + pixel[0] + "  " + pixel[1] + "  " + pixel[2] + "\n");
            }
          }
          writer.write("\t");
        }
        writer.write("\n");
      }
      writer.close();
    } catch (IOException error) {
      System.out.println("Error occurred.");
    }
  }
}
