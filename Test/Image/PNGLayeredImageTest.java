package image;

import static org.junit.Assert.*;

import org.junit.Test;

public class PNGLayeredImageTest {

  String badDimensions = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
      + "TestImagesHW06\\PNG\\valid image\\invalid-image-mismatched-dimensions.txt";

  String badImageTypes = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
      + "TestImagesHW06\\bad layered image mismatched image types\\mismatched-types.txt";

  @Test
  public void testLoadImageLayersValidImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    assertEquals(5, image.getHeight());
    assertEquals(5, image.getWidth());
    assertEquals(0, image.getMaxColorValue());
    assertEquals(0, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 1)[0]);

    assertEquals(0, image.getLayer(1).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 1)[0]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageLayersInvalidFile() {
    String path = "badPath";
    ILayeredImage image = new PNGLayeredImage(path);
    assertEquals(5, image.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageLayersMismatchedDimensions() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW0"
        + "6\\JPG\\invalid image (not same dimensions)\\non-valid-image-mismatched-dimensions.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
  }


  @Test
  public void testGetWidthLayers() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    assertEquals(5, image.getWidth());
  }

  @Test
  public void testGetHeightLayers() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    assertEquals(5, image.getHeight());
  }

  @Test
  public void testGetMaxColor() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    assertEquals(0, image.getMaxColorValue());
  }

  @Test
  public void testRemoveLayer() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    IImage layer = image.getLayer(0);
    image.removeLayer(0);
    assertNotEquals(layer, image.getLayer(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLayerInvalidLayerLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.removeLayer(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLayerInvalidLayerUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.removeLayer(100);
  }

  @Test
  public void testToggleTransparency() {

  }

  @Test(expected = IllegalArgumentException.class)
  public void testToggleTransparencyInvalidLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.toggleLayerTransparency(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testToggleTransparencyInvalidUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.toggleLayerTransparency(100);
  }

  @Test
  public void testExportImage() {

  }

  @Test (expected = IllegalArgumentException.class)
  public void testExportImageAllTransparent() {

  }

  @Test
  public void testSaveImage() {

  }

  @Test
  public void testGetLayer() {

  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetLayerInvalidLayerLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    IImage layer = image.getLayer(-1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetLayerInvalidLayerUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    IImage layer = image.getLayer(100);
  }

  @Test
  public void testGetAmountLayers() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    assertEquals(2, image.getAmountLayers());
  }

  @Test
  public void testReplaceLayer() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage images = new PNGLayeredImage(path);
    IImage image = images.getLayer(0);
    images.replaceLayer(image, 1);
    assertEquals(image.getPixel(0, 0)[0], images.getLayer(1).getPixel(0, 0)[0]);
    assertEquals(image.getPixel(0, 0)[1], images.getLayer(1).getPixel(0, 0)[1]);
    assertEquals(image.getPixel(0, 0)[2], images.getLayer(1).getPixel(0, 0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerNullImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage images = new PNGLayeredImage(path);
    images.replaceLayer(null, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerNotPNGImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage images = new PNGLayeredImage(path);
    images.replaceLayer(new JPEGImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\black0.jpg"), 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerInvalidLayerUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage images = new PNGLayeredImage(path);
    IImage image = images.getLayer(0);
    images.replaceLayer(image, images.getAmountLayers()+1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerInvalidLayerLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage images = new PNGLayeredImage(path);
    IImage image = images.getLayer(0);
    images.replaceLayer(image, -1);
  }


}