package manipulation;

import image.IImage;

/**
 * Class representing a Grayscale Manipulation.
 */
public class GrayscaleManip implements IManipulation {

  /**
   * Apply method for a Grayscale manipulation according to the following formula:
   * r′=g′=b′=0.2126r+0.7152g+0.0722b.
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
        int newColor = (int) (0.2126 * colors[0] + 0.7152 * colors[1] + 0.0722 * colors[2]);
        if (newColor < 0) {
          newColor = 0;
        } else if (newColor > image.getMaxColorValue()) {
          newColor = image.getMaxColorValue();
        }
        int[] newColors = {newColor, newColor, newColor};
        image.setPixel(j, i, newColors);
      }
    }
    return image;
  }
}
