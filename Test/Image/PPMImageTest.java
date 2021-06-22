package image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

/**
 * Test class for PPMImage.
 */
public class PPMImageTest {

  //loadImage TESTS

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

  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInValidImageFile() {
    IImage invalidFile = new PPMImage("invalidFile");
    invalidFile.loadImage("invalidFile");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidFormat() {
    IImage notP3 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\no-p3-start.ppm");
    notP3.loadImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\no-p3-start.ppm");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidMaxColorLowerBound() {
    IImage lowMC = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\negative-max-color-value.ppm");
    lowMC.loadImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\negative-max-color-value.ppm");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidWidth() {
    IImage lowWidth = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\negative-width.ppm");
    lowWidth.loadImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\negative-width.ppm");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidHeight() {
    IImage lowHeight = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\negative-height.ppm");
    lowHeight.loadImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\negative-height.ppm");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidMaxColorUpperBound() {
    IImage greatMC = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\max-color-value-too-large.ppm");
    greatMC.loadImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\max-color-value-too-large.ppm");
  }


  //getWidth TESTS


  @Test
  public void testGetWidth() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    assertEquals(640, image.getWidth());
  }


  //getHeight TESTS


  @Test
  public void testGetHeight() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    assertEquals(427, image.getHeight());
  }

  //setPixel TESTS


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
    image.setPixel(0,0, new int [] {101, 90, 58});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidPixelTooManyValues() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(1, 1, new int[]{0, 1, 2, 3});
  }

  /*
  Test setting the pixel with a color value that is too low.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidPixelTooLow() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(1, 1, new int[]{0, 1, -5});
  }

  /*
Test setting the pixel with a color value that is too high.
 */
  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidPixelTooHigh() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(1, 1, new int[]{0, 1, 300});
  }


  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidWidthNegative() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(-1, 1, new int[]{1, 1, 1});
  }


  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidWidthUpperBound() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(image.getWidth() + 1, 1, new int[]{1, 1, 1});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidHeightNegative() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(1, -1, new int[]{1, 1, 1});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidHeightUpperbound() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(1, image.getHeight() + 1, new int[]{1, 1, 1});
  }


  //getPixel TESTS


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


  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidWidthNegative() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.getPixel(-1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidWidthUpperBound() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.getPixel(image.getWidth() + 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidHeightNegative() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.getPixel(1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidHeightUpperBound() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.getPixel(1, image.getHeight() + 1);
  }

  //exportImage TESTS

  @Test
  public void testExportImageValidImageFileNotModified() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.exportImage();
    IImage outputImage = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
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


  /*
  Test export after changing a pixel.
   */
  @Test
  public void testExportImageValidImageFileAfterModification() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.setPixel(0,0, new int [] {0, 0, 0});
    image.exportImage();
    IImage outputImage = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    assertEquals(2, outputImage.getWidth());
    assertEquals(2, outputImage.getHeight());
    assertEquals(255, outputImage.getMaxColorValue());
    assertEquals(0, outputImage.getPixel(0, 0)[0]);
    assertEquals(0, outputImage.getPixel(0, 0)[1]);
    assertEquals(0, outputImage.getPixel(0, 0)[2]);
    assertEquals(200, outputImage.getPixel(1, 0)[0]);
    assertEquals(44, outputImage.getPixel(1, 0)[1]);
    assertEquals(87, outputImage.getPixel(1, 0)[2]);
    assertEquals(56, outputImage.getPixel(0, 1)[0]);
    assertEquals(120, outputImage.getPixel(0, 1)[1]);
    assertEquals(43, outputImage.getPixel(0, 1)[2]);
    assertEquals(24, outputImage.getPixel(1, 1)[0]);
    assertEquals(68, outputImage.getPixel(1, 1)[1]);
    assertEquals(255, outputImage.getPixel(1, 1)[2]);

    //reset the image for other tests
    image.setPixel(0,0, new int [] {101, 90, 58});
    image.exportImage();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExportImageInvalidFile() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\Clege\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.exportImage();
  }


  //getMaxColorValueTests


  @Test
  public void testGetMaxColorValue() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    assertEquals(255, image.getMaxColorValue());
  }


  //convertTo TESTS


  /*
  Test converting the image to a JPEG.
   */
  @Test
  public void testConvertToJPEG() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    IImage newImage = image.convertTo(ImageType.JPEG);
    assertTrue(newImage instanceof JPEGImage);
    assertEquals(image.getWidth(), newImage.getWidth());
    assertEquals(image.getHeight(), newImage.getHeight());
    assertEquals(image.getMaxColorValue(), newImage.getMaxColorValue());
    assertEquals(image.getPixel(0,0)[0], newImage.getPixel(0,0)[0]);
    assertEquals(image.getPixel(0,0)[1], newImage.getPixel(0,0)[1]);
    assertEquals(image.getPixel(0,0)[2], newImage.getPixel(0,0)[2]);
  }

  /*
  Test converting the image to a PNG.
   */
  @Test
  public void testConvertToPNG() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    IImage newImage = image.convertTo(ImageType.PNG);
    assertTrue(newImage instanceof PNGImage);
    assertEquals(image.getWidth(), newImage.getWidth());
    assertEquals(image.getHeight(), newImage.getHeight());
    assertEquals(image.getMaxColorValue(), newImage.getMaxColorValue());
    assertEquals(image.getPixel(0,0)[0], newImage.getPixel(0,0)[0]);
    assertEquals(image.getPixel(0,0)[1], newImage.getPixel(0,0)[1]);
    assertEquals(image.getPixel(0,0)[2], newImage.getPixel(0,0)[2]);
  }

  /*
  Test converting the image to a PPM.
   */
  @Test
  public void testConvertToPPM() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    IImage newImage = image.convertTo(ImageType.PPM);
    assertTrue(newImage instanceof PPMImage);
    assertEquals(image.getWidth(), newImage.getWidth());
    assertEquals(image.getHeight(), newImage.getHeight());
    assertEquals(image.getMaxColorValue(), newImage.getMaxColorValue());
    assertEquals(image.getPixel(0,0)[0], newImage.getPixel(0,0)[0]);
    assertEquals(image.getPixel(0,0)[1], newImage.getPixel(0,0)[1]);
    assertEquals(image.getPixel(0,0)[2], newImage.getPixel(0,0)[2]);
  }
}