package image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

/**
 * Test class for JPEGImage
 */
public class JPEGImageTest {

  //loadImage TESTS
  @Test
  public void testLoadImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    assertEquals(5, jpeg.getHeight());
    assertEquals(5, jpeg.getWidth());
    assertEquals(0, jpeg.getMaxColorValue());
    assertEquals(0, jpeg.getPixel(0, 0)[0]);
    assertEquals(0, jpeg.getPixel(0, 0)[1]);
    assertEquals(0, jpeg.getPixel(0, 0)[2]);
    assertEquals(0, jpeg.getPixel(0, 1)[0]);
    assertEquals(0, jpeg.getPixel(0, 1)[1]);
    assertEquals(0, jpeg.getPixel(0, 1)[2]);
    assertEquals(0, jpeg.getPixel(0, 2)[0]);
    assertEquals(0, jpeg.getPixel(0, 2)[1]);
    assertEquals(0, jpeg.getPixel(0, 2)[2]);
    assertEquals(0, jpeg.getPixel(0, 3)[0]);
    assertEquals(0, jpeg.getPixel(0, 3)[1]);
    assertEquals(0, jpeg.getPixel(0, 3)[2]);
    assertEquals(0, jpeg.getPixel(0, 4)[0]);
    assertEquals(0, jpeg.getPixel(0, 4)[1]);
    assertEquals(0, jpeg.getPixel(0, 4)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidFile() {
    IImage badFile = new JPEGImage("badFile");
  }

  //getWidth TESTS


  @Test
  public void testGetWidth() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    assertEquals(5, jpeg.getWidth());
  }

  //getHeight TESTS


  @Test
  public void testGetHeight() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    assertEquals(5, jpeg.getHeight());
  }

  //getPixel TESTS


  @Test
  public void testGetPixel() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    assertEquals(0, jpeg.getPixel(0, 0)[0]);
    assertEquals(0, jpeg.getPixel(0, 0)[1]);
    assertEquals(0, jpeg.getPixel(0, 0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidHeightLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    assertEquals(0, jpeg.getPixel(0, -1)[0]);
    assertEquals(0, jpeg.getPixel(0, -1)[1]);
    assertEquals(0, jpeg.getPixel(0, -1)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidWidthLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    assertEquals(0, jpeg.getPixel(-1, 0)[0]);
    assertEquals(0, jpeg.getPixel(-1, 0)[1]);
    assertEquals(0, jpeg.getPixel(-1, 0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidWidthUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    assertEquals(0, jpeg.getPixel(jpeg.getWidth() + 1, 0)[0]);
    assertEquals(0, jpeg.getPixel(jpeg.getWidth() + 1, 0)[1]);
    assertEquals(0, jpeg.getPixel(jpeg.getWidth() + 1, 0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidHeightUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    assertEquals(0, jpeg.getPixel(0, jpeg.getHeight() + 1)[0]);
    assertEquals(0, jpeg.getPixel(0, jpeg.getHeight() + 1)[1]);
    assertEquals(0, jpeg.getPixel(0, jpeg.getHeight() + 1)[2]);
  }

  //setPixel TESTS


  @Test
  public void testSetPixel() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    int[] pixel = new int[]{1, 1, 1};
    jpeg.setPixel(0, 0, pixel);
    assertEquals(pixel[0], jpeg.getPixel(0, 0)[0]);
    assertEquals(pixel[1], jpeg.getPixel(0, 0)[1]);
    assertEquals(pixel[2], jpeg.getPixel(0, 0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidWidthLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    int[] pixel = new int[]{1, 1, 1};
    jpeg.setPixel(0, 0, pixel);
    assertEquals(pixel[0], jpeg.getPixel(-1, 0)[0]);
    assertEquals(pixel[1], jpeg.getPixel(-1, 0)[1]);
    assertEquals(pixel[2], jpeg.getPixel(-1, 0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidWidthUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    int[] pixel = new int[]{1, 1, 1};
    jpeg.setPixel(0, 0, pixel);
    assertEquals(pixel[0], jpeg.getPixel(jpeg.getWidth() + 1, 0)[0]);
    assertEquals(pixel[1], jpeg.getPixel(jpeg.getWidth() + 1, 0)[1]);
    assertEquals(pixel[2], jpeg.getPixel(jpeg.getWidth() + 1, 0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidHeightLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    int[] pixel = new int[]{1, 1, 1};
    jpeg.setPixel(0, 0, pixel);
    assertEquals(pixel[0], jpeg.getPixel(0, -1)[0]);
    assertEquals(pixel[1], jpeg.getPixel(0, -1)[1]);
    assertEquals(pixel[2], jpeg.getPixel(0, -1)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidHeightUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    int[] pixel = new int[]{1, 1, 1};
    jpeg.setPixel(0, 0, pixel);
    assertEquals(pixel[0], jpeg.getPixel(0, jpeg.getHeight() + 1)[0]);
    assertEquals(pixel[1], jpeg.getPixel(0, jpeg.getHeight() + 1)[1]);
    assertEquals(pixel[2], jpeg.getPixel(0, jpeg.getHeight() + 1)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidPixelSize() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    int[] pixel = new int[]{1, 1, 1, 1};
    jpeg.setPixel(0, 0, pixel);
    assertEquals(pixel[0], jpeg.getPixel(0, 0)[0]);
    assertEquals(pixel[1], jpeg.getPixel(0, 0)[1]);
    assertEquals(pixel[2], jpeg.getPixel(0, 0)[2]);
  }

  //getMaxColorValue TESTS


  /*
  Test the method works on a valid image.
   */
  @Test
  public void testGetMaxColorValue() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    assertEquals(0, jpeg.getMaxColorValue());
  }

  //exportImage TESTS


  /*
  Test exporting a valid image.
   */
  @Test
  public void testExportImageValidImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage image = new JPEGImage(path);
    String outputPath = image.exportImage();
    assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0-output.jpg", outputPath);

    IImage outputImage = new JPEGImage(outputPath);
    assertEquals(5, outputImage.getHeight());
    assertEquals(5, outputImage.getWidth());
    assertEquals(0, outputImage.getMaxColorValue());
    assertEquals(0, outputImage.getPixel(0, 0)[0]);
    assertEquals(0, outputImage.getPixel(0, 0)[1]);
    assertEquals(0, outputImage.getPixel(0, 0)[2]);
    assertEquals(0, outputImage.getPixel(0, 1)[0]);
    assertEquals(0, outputImage.getPixel(0, 1)[1]);
    assertEquals(0, outputImage.getPixel(0, 1)[2]);
    assertEquals(0, outputImage.getPixel(0, 2)[0]);
    assertEquals(0, outputImage.getPixel(0, 2)[1]);
    assertEquals(0, outputImage.getPixel(0, 2)[2]);
    assertEquals(0, outputImage.getPixel(0, 3)[0]);
    assertEquals(0, outputImage.getPixel(0, 3)[1]);
    assertEquals(0, outputImage.getPixel(0, 3)[2]);
    assertEquals(0, outputImage.getPixel(0, 4)[0]);
    assertEquals(0, outputImage.getPixel(0, 4)[1]);
    assertEquals(0, outputImage.getPixel(0, 4)[2]);
  }

  /*
  Test exporting an invalid file.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExportImageInvalidFile() {
    IImage image = new JPEGImage("C:\\Users\\Shaun\\Clege\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.exportImage();
  }

  //convertTo TESTS


  /*
  Test converting the image to a JPEG.
   */
  @Test
  public void testConvertToJPEG() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage image = new JPEGImage(path);
    IImage newImage = image.convertTo(ImageType.JPEG);
    assertTrue(newImage instanceof JPEGImage);
    assertEquals(image.getWidth(), newImage.getWidth());
    assertEquals(image.getHeight(), newImage.getHeight());
    assertEquals(image.getMaxColorValue(), newImage.getMaxColorValue());
    assertEquals(image.getPixel(0, 0)[0], newImage.getPixel(0, 0)[0]);
    assertEquals(image.getPixel(0, 0)[1], newImage.getPixel(0, 0)[1]);
    assertEquals(image.getPixel(0, 0)[2], newImage.getPixel(0, 0)[2]);
  }

  /*
  Test converting the image to a PNG.
   */
  @Test
  public void testConvertToPNG() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage image = new JPEGImage(path);
    IImage newImage = image.convertTo(ImageType.PNG);
    assertTrue(newImage instanceof PNGImage);
    assertEquals(image.getWidth(), newImage.getWidth());
    assertEquals(image.getHeight(), newImage.getHeight());
    assertEquals(image.getMaxColorValue(), newImage.getMaxColorValue());
    assertEquals(image.getPixel(0, 0)[0], newImage.getPixel(0, 0)[0]);
    assertEquals(image.getPixel(0, 0)[1], newImage.getPixel(0, 0)[1]);
    assertEquals(image.getPixel(0, 0)[2], newImage.getPixel(0, 0)[2]);
  }

  /*
  Test converting the image to a PPM.
   */
  @Test
  public void testConvertToPPM() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage image = new JPEGImage(path);
    IImage newImage = image.convertTo(ImageType.PPM);
    assertTrue(newImage instanceof PPMImage);
    assertEquals(image.getWidth(), newImage.getWidth());
    assertEquals(image.getHeight(), newImage.getHeight());
    assertEquals(image.getMaxColorValue(), newImage.getMaxColorValue());
    assertEquals(image.getPixel(0, 0)[0], newImage.getPixel(0, 0)[0]);
    assertEquals(image.getPixel(0, 0)[1], newImage.getPixel(0, 0)[1]);
    assertEquals(image.getPixel(0, 0)[2], newImage.getPixel(0, 0)[2]);
  }
}