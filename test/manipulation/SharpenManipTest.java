package manipulation;

import static org.junit.Assert.assertEquals;

import image.hw5.IImage;
import image.hw5.PPMImage;
import org.junit.Test;

/**
 * Test class for a Sharpen Manipulation.
 */
public class SharpenManipTest {

  //apply TESTS

  /*
  Test a case where a valid image is given, and the values are properly changed to blurred values.
   */
  @Test
  public void testApplyValidImage() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    IManipulation sharpen = new SharpenManip();
    sharpen.apply(image);
    assertEquals(171, image.getPixel(0, 0)[0]);
    assertEquals(148, image.getPixel(0, 0)[1]);
    assertEquals(152, image.getPixel(0, 0)[2]);
    assertEquals(245, image.getPixel(1, 0)[0]);
    assertEquals(113, image.getPixel(1, 0)[1]);
    assertEquals(174, image.getPixel(1, 0)[2]);
    assertEquals(137, image.getPixel(0, 1)[0]);
    assertEquals(170, image.getPixel(0, 1)[1]);
    assertEquals(141, image.getPixel(0, 1)[2]);
    assertEquals(113, image.getPixel(1, 1)[0]);
    assertEquals(131, image.getPixel(1, 1)[1]);
    assertEquals(255, image.getPixel(1, 1)[2]);
  }

  //exception tests

  /*
  Test a case where the image passed to the method is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testApplyNullImage() {
    IManipulation sharpen = new SharpenManip();
    sharpen.apply(null);
  }

  @Test
  public void testApplyCheckerBoardImage() {
    IImage outputImage = new PPMImage("C:\\Users\\1235k\\IdeaProjects\\merlino_homework5\\"
        + "TestImages\\checkerboard-output.ppm");
    IManipulation sharpen = new SharpenManip();
    sharpen.apply(outputImage);
    assertEquals(93, outputImage.getPixel(0, 0)[0]);
    assertEquals(93, outputImage.getPixel(0, 0)[1]);
    assertEquals(93, outputImage.getPixel(0, 0)[2]);
    assertEquals(100, outputImage.getPixel(1, 0)[0]);
    assertEquals(100, outputImage.getPixel(1, 0)[1]);
    assertEquals(100, outputImage.getPixel(1, 0)[2]);
    assertEquals(93, outputImage.getPixel(1, 1)[0]);
    assertEquals(93, outputImage.getPixel(1, 1)[1]);
    assertEquals(93, outputImage.getPixel(1, 1)[2]);
    assertEquals(100, outputImage.getPixel(0, 1)[0]);
    assertEquals(100, outputImage.getPixel(0, 1)[1]);
    assertEquals(100, outputImage.getPixel(0, 1)[2]);
  }
}