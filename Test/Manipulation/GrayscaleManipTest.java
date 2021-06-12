package manipulation;

import static org.junit.Assert.assertEquals;

import image.IImage;
import image.PPMImage;
import org.junit.Test;

/**
 * Test class for a Gray Scale Manipulation.
 */
public class GrayscaleManipTest {


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

  @Test
  public void testApplyCheckerBoardImage() {
    IImage outputImage = new PPMImage("C:\\Users\\1235k\\IdeaProjects\\merlino_homework5\\"
        + "TestImages\\checkerboard-output.ppm");
    IManipulation gray = new GrayscaleManip();
    gray.apply(outputImage);
    assertEquals(44, outputImage.getPixel(0, 0)[0]);
    assertEquals(44, outputImage.getPixel(0, 0)[1]);
    assertEquals(44, outputImage.getPixel(0, 0)[2]);
    assertEquals(76, outputImage.getPixel(1, 0)[0]);
    assertEquals(76, outputImage.getPixel(1, 0)[1]);
    assertEquals(76, outputImage.getPixel(1, 0)[2]);
    assertEquals(44, outputImage.getPixel(1, 1)[0]);
    assertEquals(44, outputImage.getPixel(1, 1)[1]);
    assertEquals(44, outputImage.getPixel(1, 1)[2]);
    assertEquals(76, outputImage.getPixel(0, 1)[0]);
    assertEquals(76, outputImage.getPixel(0, 1)[1]);
    assertEquals(76, outputImage.getPixel(0, 1)[2]);
  }
}