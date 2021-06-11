package Manipulation;

import static org.junit.Assert.*;

import Image.IImage;
import Image.PPMImage;
import org.junit.Test;

public class SepiatoneManipTest {

  //apply TESTS

  /*
  Test where the method is given valid image to be sure that the pixel was set to the proper
  sepiatone values.
   */
  @Test
  public void testApplyValidImage() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    IManipulation sep = new SepiatoneManip();
    sep.apply(image);
    assertEquals(119, image.getPixel(0, 0)[0]);
    assertEquals(106, image.getPixel(0, 0)[1]);
    assertEquals(83, image.getPixel(0, 0)[2]);
    assertEquals(128, image.getPixel(1, 0)[0]);
    assertEquals(114, image.getPixel(1, 0)[1]);
    assertEquals(89, image.getPixel(1, 0)[2]);
    assertEquals(122, image.getPixel(0, 1)[0]);
    assertEquals(109, image.getPixel(0, 1)[1]);
    assertEquals(84, image.getPixel(0, 1)[2]);
    assertEquals(109, image.getPixel(1, 1)[0]);
    assertEquals(97, image.getPixel(1, 1)[1]);
    assertEquals(76, image.getPixel(1, 1)[2]);
  }

  //exception tests
  /*
  Test that exception is thrown if a null image is passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testApplyNullImage() {
    IManipulation sep = new SepiatoneManip();
    sep.apply(null);
  }
}