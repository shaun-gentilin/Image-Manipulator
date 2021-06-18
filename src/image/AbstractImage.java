package image;

public abstract class AbstractImage implements IImage {

  private final String filePath;
  private int width;
  private int height;
  private int maxColorValue;
  private int[][][] pixels;

  public AbstractImage(String filename) {
    this.filePath = filename;
    loadImage(filename);
  }

  @Override
  public abstract void loadImage(String filename) throws IllegalArgumentException;

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

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

  @Override
  public int[] getPixel(int width, int height) {
    if (width < 0 || height < 0
        || width > this.width
        || height > this.height) {
      throw new IllegalArgumentException("Invalid width or height.");
    }
    return pixels[width][height];
  }

  @Override
  public abstract String exportImage() throws IllegalArgumentException;

  @Override
  public int getMaxColorValue() {
    return this.maxColorValue;
  }
}
