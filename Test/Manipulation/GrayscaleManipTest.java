package Manipulation;

import static org.junit.Assert.*;

import Image.IImage;
import Image.PPMImage;
import org.junit.Test;

public class GrayscaleManipTest {

  //apply TESTS

  /*
  Test where the method is given valid image to be sure that the pixel was set to the proper
  grayscale value.
   */
  @Test
  public void testApplyValidImage() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    IManipulation gray = new GrayscaleManip();
    gray.apply(image);
    assertEquals(90, image.getPixel(0, 0)[0]);
    assertEquals(90, image.getPixel(0, 0)[1]);
    assertEquals(90, image.getPixel(0, 0)[2]);
    assertEquals(80, image.getPixel(1, 0)[0]);
    assertEquals(80, image.getPixel(1, 0)[1]);
    assertEquals(80, image.getPixel(1, 0)[2]);
    assertEquals(100, image.getPixel(0, 1)[0]);
    assertEquals(100, image.getPixel(0, 1)[1]);
    assertEquals(100, image.getPixel(0, 1)[2]);
    assertEquals(72, image.getPixel(1, 1)[0]);
    assertEquals(72, image.getPixel(1, 1)[1]);
    assertEquals(72, image.getPixel(1, 1)[2]);
  }

  //exception tests

  /*
  Test that exception is thrown if a null image is passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testApplyNullImage() {
    IManipulation gray = new GrayscaleManip();
    gray.apply(null);
  }
}