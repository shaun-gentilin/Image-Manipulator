package image;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PPMLayeredImageTest {

  //loadImage TESTS

  @Test
  public void testLoadImageValidImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PPM\\valid image\\simple-ppm-two-images.txt";
    ILayeredImage image = new PPMLayeredImage(path);
    image.loadImageLayers(path);
    assertEquals(2, image.getHeight());
    assertEquals(2, image.getWidth());
    assertEquals(255, image.getMaxColorValue());
    assertEquals(101, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(90, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(58, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(200, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(44, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(87, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(56, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(120, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(43, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(24, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(68, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(255, image.getLayer(0).getPixel(0, 0)[0]);
  }


}