package Image;

import static org.junit.Assert.*;

public class PPMImageTest {

  //loadImage TESTS


  /*
   * TODO: Test the method with a valid filename (ensure that fields are set properly).
   */

  //exception tests

  /*
   * TODO: Test method with an invalid filename.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInValidImageFile() {
    IImage invalidFile = new PPMImage("invalidFile");
    invalidFile.loadImage("invalidFile");
  }

  /*
   * TODO: Test with file that has invalid format (does not start with P3).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidFormat() {
    IImage notP3 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\no-p3-start.ppm");
    notP3.loadImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\no-p3-start.ppm");
  }

  /*
   * TODO: Test with a max color value that is less than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidMaxColorLowerBound() {
    IImage lowMC = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\negative-max-color-value.ppm");
    lowMC.loadImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\negative-max-color-value.ppm");
  }

  /*
   * Test with a width that is less than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidWidth() {
    IImage lowWidth = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\negative-width.ppm");
    lowWidth.loadImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\negative-width.ppm");
  }

  /*
   * Test with a height that is less than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidHeight() {
    IImage lowHeight = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\negative-height.ppm");
    lowHeight.loadImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\negative-height.ppm");
  }

  /*
   * Test with a max color value that is greater than 65536.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidMaxColorUpperBound() {
    IImage greatMC = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\max-color-value-too-large.ppm");
    greatMC.loadImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\max-color-value-too-large.ppm");
  }

  //getWidth TESTS


  /*
  TODO: Test that it returns the proper width from the image.
   */

  //getHeight TESTS


  /*
  Test that it returns the proper height from the image.
   */
  @Test
  public void testGetHeight() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    assertEquals(768, image.getHeight());
  }

  //setPixel TESTS


  /*
  TODO: Test that the correct pixel is set if all inputs are valid.
   */

  //exception tests

  /*
  TODO: Test an invalid pixel (not an integer array of size 3).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidPixel() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(1, 1, new int[]{0, 1, 2, 3});
  }

  /*
  TODO: Test an invalid width (negative).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidWidthNegative() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(-1, 1, new int[]{1, 1, 1});
  }

  /*
  Test an invalid width (too large).
 */
  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidWidthUpperBound() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(image.getWidth() + 1, 1, new int[]{1, 1, 1});
  }
    /*
  TODO: Test an invalid height (negative).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidHeightNegative() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(1, -1, new int [] {1, 1, 1});
  }
    /*
  TODO: Test an invalid height (too large).
   */
    @Test(expected = IllegalArgumentException.class)
    public void testSetPixelInvalidHeightUpperbound() {
      IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
          + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
      image.setPixel(1, image.getHeight() + 1, new int [] {1, 1, 1});
    }


  //getPixel TESTS


  /*
  TODO: Test that the proper pixel is returned if given the correct width and height.
   */

  //exception tests

  /*
  TODO: Test an invalid width (negative).
   */
  @Test(expected=IllegalArgumentException.class)
  public void testGetPixelInvalidWidthNegative() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.getPixel(-1, 1);
  }
    /*
  TODO: Test an invalid width (too large).
   */
    @Test(expected=IllegalArgumentException.class)
    public void testGetPixelInvalidWidthUpperBound() {
      IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
          + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
      image.getPixel(image.getWidth()+1, 1);
    }
    /*
  TODO: Test an invalid height (negative).
   */
    @Test(expected=IllegalArgumentException.class)
    public void testGetPixelInvalidHeightNegative() {
      IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
          + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
      image.getPixel(1, -1);
    }
    /*
  TODO: Test an invalid height (too large).
   */
    @Test(expected=IllegalArgumentException.class)
    public void testGetPixelInvalidHeightUpperBound() {
      IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
          + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
      image.getPixel(1, image.getHeight()+1);
    }
  //exportImage TESTS

  /*
  TODO: Test that data is properly exported to the proper file if a valid file path is given for the
  image.
   */
}