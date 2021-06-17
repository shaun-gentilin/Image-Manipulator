package image;

import image.hw5.IImage;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.PixelGrabber;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class JPEGImage  implements IImage {

  private final String filePath;
  private int width;
  private int height;
  private int maxColorValue;
  private int[][][] pixels;

  JPEGImage(String filePath) {
    this.filePath = filePath;
    loadImage(this.filePath);
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
   * Get the width for the image.
   *
   * @return an int representing the width of the image in question.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Get the height for the image.
   *
   * @return an int representing the height of the image in question.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Set the colors of the pixel at the specified width and height to the given pixel colors.
   *
   * @param width  - the width of the pixel to be set.
   * @param height - the height of the pixel to be set.
   * @param pixel  - an array of integers representing the colors that this pixel is to be set to.
   * @throws IllegalArgumentException if width or height are too low or too high,or if the pixel is
   *                                  not valid for the type of image.
   */
  @Override
  public void setPixel(int width, int height, int[] pixel) throws IllegalArgumentException {
    this.pixels[width][height] = pixel;
  }

  /**
   * Get the pixel at the specified width and height.
   *
   * @param width  - the width of the pixel to be obtained.
   * @param height - the height of the pixel to be obtained.
   * @return an array of integers representing the r, g, and b values of the pixel.
   * @throws IllegalArgumentException if the width or height are too low or too high.
   */
  @Override
  public int[] getPixel(int width, int height) {
    return this.pixels[width][height];
  }

  /**
   * Export this image to the existing file path.
   */
  @Override
  public void exportImage() throws IllegalArgumentException {
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
  }

  /**
   * Get the max color value for this image.
   *
   * @return an int representing the max color value for this image.
   */
  @Override
  public int getMaxColorValue() {
    return this.maxColorValue;
  }
}
