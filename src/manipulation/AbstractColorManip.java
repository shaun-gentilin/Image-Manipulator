package manipulation;

import image.IImage;

public abstract class AbstractColorManip implements IManipulation {
  double [][] colorMatrix; //a 3x3 color matrix to multiply each pixel with

  public AbstractColorManip(double [][] colorMatrix) {
    if (colorMatrix == null || colorMatrix.length != 3
    || colorMatrix[0].length != 3
        || colorMatrix[1].length != 3
        || colorMatrix[2].length != 3) {
      throw new IllegalArgumentException("Not a valid color matrix.");
    }
    this.colorMatrix = colorMatrix;
  }

  /**
   * Clamp the given pixel color values to the given maxColorValue.
   * @param color - the colors to be clamped.
   * @param maxColorValue - the value the colors are to be clamped to.
   * @return a new color array with clamped values.
   */
  private int clamp (int color, int maxColorValue) {
    if (color < 0) {
      color = 0;
    }
    else if (color > maxColorValue) {
      color = maxColorValue;
    }
    return color;
  }

  /**
   * Apply the matrix for this manipulation to the pixel array.
   * @param colors - the array of ints representing the colors of the given pixel.
   * @return a new int array representing the new colors with the manipulation applied.
   */
  private int [] applyColorMatrix(int [] colors, int maxColorValue) {
    int newR = (int) ((this.colorMatrix[0][0] * colors[0]) + (this.colorMatrix[0][1] * colors[1])
        + (this.colorMatrix[0][2] * colors[2]));
    int newG = (int) ((this.colorMatrix[1][0] * colors[0]) + (this.colorMatrix[1][1] * colors[1])
        + (this.colorMatrix[1][2] * colors[2]));
    int newB = (int) ((this.colorMatrix[2][0] * colors[0]) + (this.colorMatrix[2][1] * colors[1])
        + (this.colorMatrix[2][2] * colors[2]));
    newR = this.clamp(newR, maxColorValue);
    newG = this.clamp(newG, maxColorValue);
    newB = this.clamp(newB, maxColorValue);
    return new int [] {newR, newG, newB};
  }

  /**
   * Apply method for a color manipulation using the given matrix.
   *
   * @param image Image to be manipulated.
   * @return manipulated image.
   * @throws IllegalArgumentException if given image is null.
   */
  @Override
  public IImage apply(IImage image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int[] colors = image.getPixel(j, i);
        int[] newColors = this.applyColorMatrix(colors, image.getMaxColorValue());
        image.setPixel(j, i, newColors);
      }
    }
    return image;
  }
}
