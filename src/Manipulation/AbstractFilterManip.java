package Manipulation;

import Image.IImage;

public abstract class AbstractFilterManip implements IManipulation {
  double [][] kernel;
  int kernelWidth;
  int kernelHeight;

  public AbstractFilterManip(double [][] kernel, int kernelWidth, int kernelHeight) {
    if (kernel == null) {
      throw new IllegalArgumentException("Kernel cannot be null");
    }
    else if (kernelWidth <= 0 || kernelHeight <= 0
    || kernelWidth % 2 == 0 || kernelHeight % 2 == 0) {
      throw new IllegalArgumentException("Kernel dimensions must be positive and odd.");
    }

    this.kernel = kernel;
    this.kernelWidth = kernelWidth;
    this.kernelHeight = kernelHeight;
  }

  /**
   * Get the new color that is a result of applying the blur on the specified channel.  The blur is
   * a result of applying the kernel matrix (provided by the class) on the given pixel.
   * This is applied by putting the middle of the kernel on the specified pixel, and lining the
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
  private int getFilterColor(IImage image, int channel, int width, int height) {
    //get the values of the pixels (width and height) that will correspond to cells in the kernel and store them,
    //ensuring that no pixel values are out of bounds
    int [][][] pixelValues;
    pixelValues = new int [this.kernelWidth][this.kernelHeight][2];

    //get the mid values for the kernel to do some math
    int midWidth = (int)Math.ceil(this.kernelWidth/2.0);
    int midHeight = (int)Math.ceil(this.kernelHeight/2.0);

    for (int i = 0; i < this.kernelHeight; i++) {
      for (int j = 0; j < this.kernelWidth; j++) {
        //get the width or height value of the pixel based on the kernel width or height
        //(e.g. if the kernel was 3x3, the top left value should be width - 1 because the width
        //is for this middle pixel).
        pixelValues[j][i][0] = width + (i - (midWidth - 1));
        pixelValues[j][i][1] = height + (i - (midHeight - 1));
      }
    }

    //Check that none of the values are negative or over the max width or height.
    //Ff any of the values violate this, set them to null so that we will not try to use them as
    //pixels for the blurring process.
    int maxWidth = image.getWidth();
    int maxHeight = image.getHeight();
    for (int i = 0; i < this.kernelHeight; i++) {
      for (int j = 0; j < this.kernelWidth; j++) {
        if (pixelValues[j][i][0] < 0 || pixelValues[j][i][0] > maxWidth
            || pixelValues[j][i][1] < 0 || pixelValues[j][i][1] > maxHeight) {
          pixelValues[j][i] = null;
        }
      }
    }

    //Apply the filter, matching up the kernel with the new array of pixelValues.
    int sum = 0;
    for (int i = 0; i < this.kernelHeight; i++) {
      for (int j = 0; j < this.kernelWidth; j++) {
        if (pixelValues[j][i] == null) {
          continue;
        }
        int color = image.getPixel(pixelValues[j][i][0], pixelValues[j][i][1])[channel];
        int filterColor = (int)(color * this.kernel[j][i]);

        sum += filterColor;
      }
    }

    //Check that the value falls within the proper range (i.e. 0 and 255 for 8 bits).  If not,
    //clamp the value.
    if (sum < 0) {
      sum = 0;
    }
    else if (sum > image.getMaxColorValue()) {
      sum = image.getMaxColorValue();
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
    int [][][] newPixels;
    newPixels = new int [image.getWidth()][image.getHeight()][3];

    //get the blurred value for each channel of each pixel, create a new pixel and add it to the new
    //pixel array
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int newR = this.getFilterColor(image,0, j, i);
        int newG = this.getFilterColor(image,1, j, i);
        int newB = this.getFilterColor(image,2, j, i);
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
