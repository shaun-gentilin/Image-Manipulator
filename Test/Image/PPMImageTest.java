package Image;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;

public class PPMImageTest {

  //loadImage TESTS


  /*
   * Test the method with a valid filename (ensure that fields are set properly).
   */
  @Test
  public void testLoadImageValidImageFile() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.loadImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    assertEquals(2, image.getWidth());
    assertEquals(2, image.getHeight());
    assertEquals(255, image.getMaxColorValue());
    assertEquals(101, image.getPixel(0, 0)[0]);
    assertEquals(90, image.getPixel(0, 0)[1]);
    assertEquals(58, image.getPixel(0, 0)[2]);
    assertEquals(200, image.getPixel(1, 0)[0]);
    assertEquals(44, image.getPixel(1, 0)[1]);
    assertEquals(87, image.getPixel(1, 0)[2]);
    assertEquals(56, image.getPixel(0, 1)[0]);
    assertEquals(120, image.getPixel(0, 1)[1]);
    assertEquals(43, image.getPixel(0, 1)[2]);
    assertEquals(24, image.getPixel(1, 1)[0]);
    assertEquals(68, image.getPixel(1, 1)[1]);
    assertEquals(255, image.getPixel(1, 1)[2]);
  }

  /*
  Test loading a different image after one has been loaded.
   */
  @Test
  public void testLoadImageValidImageFileReload() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    image.loadImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    assertEquals(2, image.getWidth());
    assertEquals(2, image.getHeight());
    assertEquals(255, image.getMaxColorValue());
    assertEquals(101, image.getPixel(0, 0)[0]);
    assertEquals(90, image.getPixel(0, 0)[1]);
    assertEquals(58, image.getPixel(0, 0)[2]);
    assertEquals(200, image.getPixel(1, 0)[0]);
    assertEquals(44, image.getPixel(1, 0)[1]);
    assertEquals(87, image.getPixel(1, 0)[2]);
    assertEquals(56, image.getPixel(0, 1)[0]);
    assertEquals(120, image.getPixel(0, 1)[1]);
    assertEquals(43, image.getPixel(0, 1)[2]);
    assertEquals(24, image.getPixel(1, 1)[0]);
    assertEquals(68, image.getPixel(1, 1)[1]);
    assertEquals(255, image.getPixel(1, 1)[2]);
  }

  //exception tests

  /*
   * Test method with an invalid filename.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInValidImageFile() {
    IImage invalidFile = new PPMImage("invalidFile");
    invalidFile.loadImage("invalidFile");
  }

  /*
   * Test with file that has invalid format (does not start with P3).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidFormat() {
    IImage notP3 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\no-p3-start.ppm");
    notP3.loadImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\no-p3-start.ppm");
  }

  /*
   * Test with a max color value that is less than 0.
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
  Test that it returns the proper width from the image.
   */
  @Test
  public void testGetWidth() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    assertEquals(1024, image.getWidth());
  }

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
  Test that the correct pixel is set if all inputs are valid.
   */
  @Test
  public void testSetPixelValidInputs() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(0, 0, new int[]{0, 0, 0});
    assertEquals(0, image.getPixel(0, 0)[0]);
    assertEquals(0, image.getPixel(0, 0)[1]);
    assertEquals(0, image.getPixel(0, 0)[2]);
    assertEquals(200, image.getPixel(1, 0)[0]);
    assertEquals(44, image.getPixel(1, 0)[1]);
    assertEquals(87, image.getPixel(1, 0)[2]);
    assertEquals(56, image.getPixel(0, 1)[0]);
    assertEquals(120, image.getPixel(0, 1)[1]);
    assertEquals(43, image.getPixel(0, 1)[2]);
    assertEquals(24, image.getPixel(1, 1)[0]);
    assertEquals(68, image.getPixel(1, 1)[1]);
    assertEquals(255, image.getPixel(1, 1)[2]);
  }

  //exception tests

  /*
  Test an invalid pixel (not an integer array of size 3).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidPixel() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(1, 1, new int[]{0, 1, 2, 3});
  }

  /*
  Test an invalid width (negative).
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
  Test an invalid height (negative).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidHeightNegative() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(1, -1, new int [] {1, 1, 1});
  }
    /*
  Test an invalid height (too large).
   */
    @Test(expected = IllegalArgumentException.class)
    public void testSetPixelInvalidHeightUpperbound() {
      IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
          + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
      image.setPixel(1, image.getHeight() + 1, new int [] {1, 1, 1});
    }


  //getPixel TESTS


  /*
  Test that the proper pixel is returned if given the correct width and height.
   */
  @Test
  public void testGetPixelValidInputs() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    assertEquals(101, image.getPixel(0, 0)[0]);
    assertEquals(90, image.getPixel(0, 0)[1]);
    assertEquals(58, image.getPixel(0, 0)[2]);
    assertEquals(200, image.getPixel(1, 0)[0]);
    assertEquals(44, image.getPixel(1, 0)[1]);
    assertEquals(87, image.getPixel(1, 0)[2]);
    assertEquals(56, image.getPixel(0, 1)[0]);
    assertEquals(120, image.getPixel(0, 1)[1]);
    assertEquals(43, image.getPixel(0, 1)[2]);
    assertEquals(24, image.getPixel(1, 1)[0]);
    assertEquals(68, image.getPixel(1, 1)[1]);
    assertEquals(255, image.getPixel(1, 1)[2]);
  }

  //exception tests

  /*
   Test an invalid width (negative).
   */
  @Test(expected=IllegalArgumentException.class)
  public void testGetPixelInvalidWidthNegative() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.getPixel(-1, 1);
  }
    /*
    Test an invalid width (too large).
   */
    @Test(expected=IllegalArgumentException.class)
    public void testGetPixelInvalidWidthUpperBound() {
      IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
          + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
      image.getPixel(image.getWidth()+1, 1);
    }
    /*
    Test an invalid height (negative).
   */
    @Test(expected=IllegalArgumentException.class)
    public void testGetPixelInvalidHeightNegative() {
      IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
          + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
      image.getPixel(1, -1);
    }
    /*
  Test an invalid height (too large).
   */
    @Test(expected=IllegalArgumentException.class)
    public void testGetPixelInvalidHeightUpperBound() {
      IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
          + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
      image.getPixel(1, image.getHeight()+1);
    }
  //exportImage TESTS

  /*
  Test that data is properly exported to the proper file if a valid file path is given for
  the image.
   */
  @Test
  public void testExportImageValidImageFile() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.exportImage();
    IImage outputImage = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels-output.ppm");
    assertEquals(2, outputImage.getWidth());
    assertEquals(2, outputImage.getHeight());
    assertEquals(255, outputImage.getMaxColorValue());
    assertEquals(101, outputImage.getPixel(0, 0)[0]);
    assertEquals(90, outputImage.getPixel(0, 0)[1]);
    assertEquals(58, outputImage.getPixel(0, 0)[2]);
    assertEquals(200, outputImage.getPixel(1, 0)[0]);
    assertEquals(44, outputImage.getPixel(1, 0)[1]);
    assertEquals(87, outputImage.getPixel(1, 0)[2]);
    assertEquals(56, outputImage.getPixel(0, 1)[0]);
    assertEquals(120, outputImage.getPixel(0, 1)[1]);
    assertEquals(43, outputImage.getPixel(0, 1)[2]);
    assertEquals(24, outputImage.getPixel(1, 1)[0]);
    assertEquals(68, outputImage.getPixel(1, 1)[1]);
    assertEquals(255, outputImage.getPixel(1, 1)[2]);
  }

  //exception tests

  /*
  Test a case where an IllegalArgumentException is thrown for a bad filename.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExportImageInvalidFile() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\Clege\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.exportImage();
  }


  //getMaxColorValue TESTS


  /*
  Test that it returns the correct max color value.
   */
  @Test
  public void testGetMaxColorValue() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    assertEquals(255, image.getMaxColorValue());
  }
}