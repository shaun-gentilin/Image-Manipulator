package image;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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

  @Test
  public void testGetWidth() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    assertEquals(5, jpeg.getWidth());
  }

  @Test
  public void testGetHeight() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    assertEquals(5, jpeg.getHeight());
  }

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

  @Test
  public void testSetPixel() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    int [] pixel = new int [] {1, 1, 1};
    jpeg.setPixel(0, 0, pixel);
    assertEquals(pixel[0], jpeg.getPixel(0, 0)[0]);
    assertEquals(pixel[1], jpeg.getPixel(0, 0)[1]);
    assertEquals(pixel[2], jpeg.getPixel(0, 0)[2]);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetPixelInvalidWidthLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    int [] pixel = new int [] {1, 1, 1};
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
    int [] pixel = new int [] {1, 1, 1};
    jpeg.setPixel(0, 0, pixel);
    assertEquals(pixel[0], jpeg.getPixel(jpeg.getWidth()+1, 0)[0]);
    assertEquals(pixel[1], jpeg.getPixel(jpeg.getWidth()+1, 0)[1]);
    assertEquals(pixel[2], jpeg.getPixel(jpeg.getWidth()+1, 0)[2]);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetPixelInvalidHeightLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    int [] pixel = new int [] {1, 1, 1};
    jpeg.setPixel(0, 0, pixel);
    assertEquals(pixel[0], jpeg.getPixel(0, -1)[0]);
    assertEquals(pixel[1], jpeg.getPixel(0, -1)[1]);
    assertEquals(pixel[2], jpeg.getPixel(0, -1)[2]);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetPixelInvalidHeightUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    int [] pixel = new int [] {1, 1, 1};
    jpeg.setPixel(0, 0, pixel);
    assertEquals(pixel[0], jpeg.getPixel(0, jpeg.getHeight()+1)[0]);
    assertEquals(pixel[1], jpeg.getPixel(0, jpeg.getHeight()+1)[1]);
    assertEquals(pixel[2], jpeg.getPixel(0, jpeg.getHeight()+1)[2]);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetPixelInvalidPixelSize() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg";
    IImage jpeg = new JPEGImage(path);
    int [] pixel = new int [] {1, 1, 1, 1};
    jpeg.setPixel(0, 0, pixel);
    assertEquals(pixel[0], jpeg.getPixel(0, 0)[0]);
    assertEquals(pixel[1], jpeg.getPixel(0, 0)[1]);
    assertEquals(pixel[2], jpeg.getPixel(0, 0)[2]);
  }
}