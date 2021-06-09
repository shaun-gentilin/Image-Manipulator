package Manipulation;

import Image.IImage;

public class BlurManip implements IManipulation {

  /**
   * Get the new color that is a result of applying the blur on the specified channel.  The blur is
   * a result of applying the following matrix on the given pixel:
   * {{1/16, 1/8, 1/16}, {1/8, 1/4, 1/8}, {1/16, 1/8, 1/16}}
   * This is applied by putting the middle of the blur array on the specified pixel, and lining the
   * rest of the values up with the surrounding pixels.  Each pixel value is then multiplied by its
   * corresponding kernel value and each of the results is added together.  This becomes the new
   * color for the specified channel of the specified pixel.
   * @param image - the image that is being blurred.
   * @param channel - an int representing the color channel that is currently being blurred.
   *     0 represents red, 1 represents green, 2 represents blue (corresponding to how they are
   *     represented in the pixel array of image).
   * @param width - the width of the pixel that is being blurred.
   * @param height - the height of the pixel that is being blurred.
   * @return an int representing the blurred color for this pixel in the specified channel.
   */
  private int getBlurColor(IImage image, int channel, int width, int height) {
    //set up the matrix of kernel values
    double [] kernel = {1.0/16, 1.0/8, 1.0/16, 1.0/8, 1.0/4, 1.0/8, 1.0/16, 1.0/8, 1.0/16};

    //get the values of the pixels that will correspond to cells in the kernel and store them,
    //ensuring that no pixel values are out of bounds
    int [][] pixelValues = {{width - 1, height - 1}, {width, height - 1}, {width + 1, height - 1},
                            {width - 1, height},     {width, height},     {width + 1, height},
                            {width - 1, height + 1}, {width, height + 1}, {width + 1, height + 1}};

    //Check that none of the values are negative or over the max width or height.
    //Ff any of the values violate this, set them to null so that we will not try to use them as
    //pixels for the blurring process.
    int maxWidth = image.getWidth();
    int maxHeight = image.getHeight();
    for (int i = 0; i < 9; i++) {
      if (pixelValues[i][0] < 0 || pixelValues[i][0] > maxWidth
          || pixelValues[i][1] < 0 || pixelValues[i][1] > maxHeight) {
        pixelValues[i] = null;
      }
    }

    //Apply the blur, matching up the kernel with the new array of pixelValues.
    int sum = 0;
    for (int i = 0; i < 9; i++) {
      if (pixelValues[i] == null) {
        continue;
      }
      int color = image.getPixel(pixelValues[i][0], pixelValues[i][1])[channel];
      int blurColor = (int)(color * kernel[i]);

      //Check that the value falls within the proper range (i.e. 0 and 255 for 8 bits).  If not,
      //clamp the value.
      if (blurColor < 0) {
        blurColor = 0;
      }
      else if (blurColor > image.getMaxColorValue()) {
        blurColor = image.getMaxColorValue();
      }

      sum += blurColor;
    }

    return sum;
  }

  @Override
  public IImage apply(IImage image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }

    //create new pixel array to hold the new values after blurring (this way the new values will
    //not interfere with calculating the rest)
    int [][][] newPixels = null;

    //get the blurred value for each channel of each pixel, create a new pixel and add it to the new
    //pixel array
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int[] colors = image.getPixel(j, i);
        int newR = this.getBlurColor(image,0, j, i);
        int newG = this.getBlurColor(image,1, j, i);
        int newB = this.getBlurColor(image,2, j, i);

        int [] newColors = {newR, newG, newB};
        newPixels[j][i] = newColors;
      }
    }

    //transfer all of the new pixel values to the pixel array of the image
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        image.setPixel(j, i, newPixels[j][i]);
      }
    }

    return image;
  }
}
