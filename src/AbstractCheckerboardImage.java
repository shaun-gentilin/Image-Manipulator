package Image;

import java.io.IOException;

public abstract class AbstractCheckerboardImage implements IImage {
  private final int width; //width of square in pixels
  private final int tiles; //amount of tiles on top row
  private final int[] color1; // color1 given in rgb array
  private final int[] color2; // color2 given in rgb array
  private int[][][][][] pixels; //pixels in entire checkerboard
  private final int maxColorValue; // max Color value of the checkerboard

  public AbstractCheckerboardImage(String filePath, int width, int tiles, int[] color1,
      int[] color2, int maxColorValue) {
    if(filePath.equals("")) {
      throw new IllegalArgumentException("Filepath name cannot be empty.");
    }
    if (width < 1) {
      throw new IllegalArgumentException("Width of Squares cannot be negative or 0");
    }
    if (tiles < 1) {
      throw new IllegalArgumentException("Tiles in checkerboard height cannot be negative or 0.");
    }
    if (color1 == null || color2 == null || color1.length != 3 || color2.length != 3) {
      throw new IllegalArgumentException("Colors are invalid.");
    }
    if(maxColorValue <= 0 || maxColorValue >= 65536) {
      throw new IllegalArgumentException("Maximum color value is invalid.");
    }
    this.width = width;
    this.tiles = tiles;
    this.color1 = color1;
    this.color2 = color2;
    this.maxColorValue = maxColorValue;
    loadImage(filePath);
  }

  @Override
  public void loadImage(String filename) throws IllegalArgumentException {
    int[][][][][] board = new int[tiles][tiles][width][width][3];
    int[][][] square1 = new int[width][width][3];
    int[][][] square2 = new int[width][width][3];
    for (int i = 0; i < width; i++) {//populate both squares
      for (int z = 0; z < width; z++) {
        square1[i][z] = this.color1;
        square2[i][z] = this.color2;
      }
    }
        for(int i = 0; i < tiles; i=i+2) { // populate even rows
      for(int z = 0; z < tiles; z++) {
        if(z % 2 == 0) {
          board[i][z] = square1;
        }
        else {
          board[i][z] = square2;
        }
      }
    }
    for(int i = 1; i < tiles; i = i + 2) { // populate odd rows
      for(int z = 0; z < tiles; z++) {
        if(z % 2 == 0) {
          board[i][z] = square2;
        }
        else {
          board[i][z] = square1;
        }
      }
    }
    this.pixels = board.clone();
  }

  /**
   * Observes width of square.
   * @return width of square
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Observes height of board in squares
   * @return height of board
   */
  @Override
  public int getHeight() {
    return tiles;
  }

  /**
   * Setting method for pixel colors.
   *
   * @param width  - the width of the pixels to be set in checkerboard.
   * @param height - the height of the pixels to be set in checkerboard.
   * @param pixel  - an array of 3 integers representing the colors that this pixel is to be set
   *               to.
   * @throws IllegalArgumentException If the width / height / or given pixel are invalid.
   */
  @Override
  public void setPixel(int width, int height, int[] pixel) throws IllegalArgumentException {
    if (width < 0 || height < 0
        || width > tiles
        || height > tiles) {
      throw new IllegalArgumentException("Invalid width or height.");
    } else if (pixel.length != 3) {
      throw new IllegalArgumentException("Not a valid pixel to set.");
    }
    for(int i = 0; i < getWidth(); i++) {
      for(int z = 0; z < getWidth(); z++) {
        this.pixels[width][height][i][z] = pixel.clone();
      }
    }
  }

  /**
   * Observer method for pixel colors.
   *
   * @param width  - the width of the pixel to be obtained in checkerboard.
   * @param height - the height of the pixel to be obtained in checkerboard.
   * @return Pixels at specific input position.
   * @throws IllegalArgumentException if the width or height are too low or too high.
   */
  @Override
  public int[] getPixel(int width, int height) {
    if (width < 0 || height < 0
        || width > tiles
        || height > tiles) {
      throw new IllegalArgumentException("Invalid width or height.");
    }
    return this.pixels[width][height][0][0];
  }

  /**
   * Exports checkerboard image to PPM format.
   *
   * @throws IOException if creating file / writing to file fails.
   */
  @Override
  public abstract void exportImage() throws IllegalArgumentException;

  /**
   * Observer method to obtain maximum pixel color value.
   * @return Maximum pixel color value.
   */
  @Override
  public int getMaxColorValue() {
    return maxColorValue;
  }
}
