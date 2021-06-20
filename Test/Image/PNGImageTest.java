package image;

import static org.junit.Assert.*;

import org.junit.Test;

public class PNGImageTest {

  //loadImage TESTS


  @Test
  public void testLoadImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    assertEquals(5, png.getHeight());
    assertEquals(5, png.getWidth());
    assertEquals(0, png.getMaxColorValue());
    assertEquals(0, png.getPixel(0, 0)[0]);
    assertEquals(0, png.getPixel(0, 0)[1]);
    assertEquals(0, png.getPixel(0, 0)[2]);
    assertEquals(0, png.getPixel(0, 1)[0]);
    assertEquals(0, png.getPixel(0, 1)[1]);
    assertEquals(0, png.getPixel(0, 1)[2]);
    assertEquals(0, png.getPixel(0, 2)[0]);
    assertEquals(0, png.getPixel(0, 2)[1]);
    assertEquals(0, png.getPixel(0, 2)[2]);
    assertEquals(0, png.getPixel(0, 3)[0]);
    assertEquals(0, png.getPixel(0, 3)[1]);
    assertEquals(0, png.getPixel(0, 3)[2]);
    assertEquals(0, png.getPixel(0, 4)[0]);
    assertEquals(0, png.getPixel(0, 4)[1]);
    assertEquals(0, png.getPixel(0, 4)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidFile() {
    IImage badFile = new PNGImage("badFile");
  }


  //getWidth TESTS
  @Test
  public void testGetWidth() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    assertEquals(5, png.getWidth());
  }


  //getHeight TESTS


  @Test
  public void testGetHeight() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    assertEquals(5, png.getHeight());
  }


  //getPixel TESTS
  @Test
  public void testGetPixel() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    assertEquals(0, png.getPixel(0, 0)[0]);
    assertEquals(0, png.getPixel(0, 0)[1]);
    assertEquals(0, png.getPixel(0, 0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidHeightLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    assertEquals(0, png.getPixel(0, -1)[0]);
    assertEquals(0, png.getPixel(0, -1)[1]);
    assertEquals(0, png.getPixel(0, -1)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidWidthLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    assertEquals(0, png.getPixel(-1, 0)[0]);
    assertEquals(0, png.getPixel(-1, 0)[1]);
    assertEquals(0, png.getPixel(-1, 0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidWidthUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    assertEquals(0, png.getPixel(png.getWidth() + 1, 0)[0]);
    assertEquals(0, png.getPixel(png.getWidth() + 1, 0)[1]);
    assertEquals(0, png.getPixel(png.getWidth() + 1, 0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidHeightUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    assertEquals(0, png.getPixel(0, png.getHeight() + 1)[0]);
    assertEquals(0, png.getPixel(0, png.getHeight() + 1)[1]);
    assertEquals(0, png.getPixel(0, png.getHeight() + 1)[2]);
  }


  //setPixel TESTS


  @Test
  public void testSetPixel() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    int [] pixel = new int [] {1, 1, 1};
    png.setPixel(0, 0, pixel);
    assertEquals(pixel[0], png.getPixel(0, 0)[0]);
    assertEquals(pixel[1], png.getPixel(0, 0)[1]);
    assertEquals(pixel[2], png.getPixel(0, 0)[2]);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetPixelInvalidWidthLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    int [] pixel = new int [] {1, 1, 1};
    png.setPixel(0, 0, pixel);
    assertEquals(pixel[0], png.getPixel(-1, 0)[0]);
    assertEquals(pixel[1], png.getPixel(-1, 0)[1]);
    assertEquals(pixel[2], png.getPixel(-1, 0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidWidthUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    int [] pixel = new int [] {1, 1, 1};
    png.setPixel(0, 0, pixel);
    assertEquals(pixel[0], png.getPixel(png.getWidth()+1, 0)[0]);
    assertEquals(pixel[1], png.getPixel(png.getWidth()+1, 0)[1]);
    assertEquals(pixel[2], png.getPixel(png.getWidth()+1, 0)[2]);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetPixelInvalidHeightLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    int [] pixel = new int [] {1, 1, 1};
    png.setPixel(0, 0, pixel);
    assertEquals(pixel[0], png.getPixel(0, -1)[0]);
    assertEquals(pixel[1], png.getPixel(0, -1)[1]);
    assertEquals(pixel[2], png.getPixel(0, -1)[2]);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetPixelInvalidHeightUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    int [] pixel = new int [] {1, 1, 1};
    png.setPixel(0, 0, pixel);
    assertEquals(pixel[0], png.getPixel(0, png.getHeight()+1)[0]);
    assertEquals(pixel[1], png.getPixel(0, png.getHeight()+1)[1]);
    assertEquals(pixel[2], png.getPixel(0, png.getHeight()+1)[2]);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetPixelInvalidPixelSize() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    int [] pixel = new int [] {1, 1, 1, 1};
    png.setPixel(0, 0, pixel);
    assertEquals(pixel[0], png.getPixel(0, 0)[0]);
    assertEquals(pixel[1], png.getPixel(0, 0)[1]);
    assertEquals(pixel[2], png.getPixel(0, 0)[2]);
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
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage image = new PNGImage(path);
    String outputPath = image.exportImage();
    assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0-output.png", outputPath);

    IImage outputImage = new PNGImage(outputPath);
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
    IImage image = new PNGImage("C:\\Users\\Shaun\\Clege\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    image.exportImage();
  }
}