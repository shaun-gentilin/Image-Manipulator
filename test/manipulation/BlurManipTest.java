package manipulation;

import static org.junit.Assert.assertEquals;

import image.hw5.IImage;
import image.hw5.PPMImage;
import org.junit.Test;

/**
 * Test class for a Blur Manipulation.
 */
public class BlurManipTest {

  //apply TESTS

  /*
  Test a case where a valid image is given, and the values are properly changed to blurred values.
   */
  @Test
  public void testApplyValidImage() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    IManipulation blur = new BlurManip();
    blur.apply(image);
    assertEquals(58, image.getPixel(0, 0)[0]);
    assertEquals(46, image.getPixel(0, 0)[1]);
    assertEquals(44, image.getPixel(0, 0)[2]);
    assertEquals(68, image.getPixel(1, 0)[0]);
    assertEquals(37, image.getPixel(1, 0)[1]);
    assertEquals(61, image.getPixel(1, 0)[2]);
    assertEquals(41, image.getPixel(0, 1)[0]);
    assertEquals(51, image.getPixel(0, 1)[1]);
    assertEquals(53, image.getPixel(0, 1)[2]);
    assertEquals(44, image.getPixel(1, 1)[0]);
    assertEquals(42, image.getPixel(1, 1)[1]);
    assertEquals(81, image.getPixel(1, 1)[2]);
  }

  //exception tests

  /*
  Test a case where the image passed to the method is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testApplyNullImage() {
    IManipulation blur = new BlurManip();
    blur.apply(null);
  }

  @Test
  public void testApplyCheckerBoardImage() {
    IImage outputImage = new PPMImage("C:\\Users\\1235k\\IdeaProjects\\merlino_homework5\\"
        + "TestImages\\checkerboard-output.ppm");
    IManipulation blur = new BlurManip();
    blur.apply(outputImage);
    assertEquals(31, outputImage.getPixel(0, 0)[0]);
    assertEquals(31, outputImage.getPixel(0, 0)[1]);
    assertEquals(31, outputImage.getPixel(0, 0)[2]);
    assertEquals(33, outputImage.getPixel(1, 0)[0]);
    assertEquals(33, outputImage.getPixel(1, 0)[1]);
    assertEquals(33, outputImage.getPixel(1, 0)[2]);
    assertEquals(31, outputImage.getPixel(1, 1)[0]);
    assertEquals(31, outputImage.getPixel(1, 1)[1]);
    assertEquals(31, outputImage.getPixel(1, 1)[2]);
    assertEquals(33, outputImage.getPixel(0, 1)[0]);
    assertEquals(33, outputImage.getPixel(0, 1)[1]);
    assertEquals(33, outputImage.getPixel(0, 1)[2]);
  }
}