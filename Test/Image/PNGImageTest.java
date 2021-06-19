package image;

import static org.junit.Assert.*;

import org.junit.Test;

public class PNGImageTest {
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

  @Test
  public void testGetWidth() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    assertEquals(5, png.getWidth());
  }

  @Test
  public void testGetHeight() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\black0.png";
    IImage png = new PNGImage(path);
    assertEquals(5, png.getHeight());
  }

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
}