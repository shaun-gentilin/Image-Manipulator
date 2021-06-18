package image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Color;

public class JPEGImage extends AbstractImage {

  private final String filePath;
  private int width;
  private int height;
  private int maxColorValue;
  private int[][][] pixels;

  public JPEGImage(String filePath) {
    super(filePath);
    this.filePath = filePath;
  }

  /**
   * Load the image at filename into the class.
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
    String output = this.filePath.substring(0, this.filePath.length()-5) + "-output.jpeg";
    File outputPath = new File(output);
    try {
      ImageIO.write(img, "jpeg", outputPath);
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    return output;
  }
}
