package manipulation;


import static org.junit.Assert.assertEquals;

import image.hw5.IImage;
import image.hw5.PPMImage;
import org.junit.Test;

/**
 * Test class for a Sepiatone Manipulation.
 */
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

  @Test
  public void testApplyCheckerBoardImage() {
    IImage outputImage = new PPMImage("C:\\Users\\1235k\\IdeaProjects\\merlino_homework5\\"
        + "TestImages\\checkerboard-output.ppm");
    IManipulation sepia = new SepiatoneManip();
    sepia.apply(outputImage);
    assertEquals(59, outputImage.getPixel(0, 0)[0]);
    assertEquals(52, outputImage.getPixel(0, 0)[1]);
    assertEquals(41, outputImage.getPixel(0, 0)[2]);
    assertEquals(100, outputImage.getPixel(1, 0)[0]);
    assertEquals(91, outputImage.getPixel(1, 0)[1]);
    assertEquals(71, outputImage.getPixel(1, 0)[2]);
    assertEquals(59, outputImage.getPixel(1, 1)[0]);
    assertEquals(52, outputImage.getPixel(1, 1)[1]);
    assertEquals(41, outputImage.getPixel(1, 1)[2]);
    assertEquals(100, outputImage.getPixel(0, 1)[0]);
    assertEquals(91, outputImage.getPixel(0, 1)[1]);
    assertEquals(71, outputImage.getPixel(0, 1)[2]);
  }
}