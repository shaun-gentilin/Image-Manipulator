package Manipulation;

import Image.IImage;

public class SepiatoneManip implements IManipulation {

  /**
   * Apply method for a Sepia tone manipulation according to the following matrix:
   * {{.393, .769, .189}, {.349, .686, .168}, {.272, .534, .131}}
   * @param   image Image to be manipulated.
   * @return  manipulated image.
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

        int newR = (int)((0.393 * colors[0]) + (0.769 * colors[1]) + (0.189 * colors[2]));
        int newG = (int)((0.349 * colors[0]) + (0.686 * colors[1]) + (0.168 * colors[3]));
        int newB = (int)((0.272 * colors[0]) + (0.534 * colors[1]) + (0.131 * colors[3]));

        int [] newColors = {newR, newG, newB};
        image.setPixel(j, i, newColors);
      }
    }
    return image;
  }
}
