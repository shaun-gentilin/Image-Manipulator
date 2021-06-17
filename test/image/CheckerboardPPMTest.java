package image;

import static org.junit.Assert.assertEquals;

import image.hw5.CheckerboardPPM;
import image.hw5.IImage;
import image.hw5.PPMImage;
import org.junit.Test;

/**
 * Test class for CheckerboardPPM.
 */
public class CheckerboardPPMTest {

  @Test(expected = IllegalArgumentException.class)
  public void constructorTestEmptyFilePath() {
    IImage emptyFile = new CheckerboardPPM("", 1, 4,
        new int[]{1, 1, 1}, new int[]{2, 2, 2}, 50);
  }


  @Test(expected = IllegalArgumentException.class)
  public void constructorTestInvalidWidth() {
    IImage emptyFile = new CheckerboardPPM("TestName", -4, 4,
        new int[]{1, 1, 1}, new int[]{2, 2, 2}, 50);
  }


  @Test(expected = IllegalArgumentException.class)
  public void constructorTestInvalidTiles() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, -4,
        new int[]{1, 1, 1}, new int[]{2, 2, 2}, 50);
  }


  @Test(expected = IllegalArgumentException.class)
  public void constructorTestInvalidColor1Null() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, 4,
        null, new int[]{2, 2, 2}, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorTestInvalidColor2Null() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, 4,
        new int[]{1, 1, 1}, null, 50);
  }


  @Test(expected = IllegalArgumentException.class)
  public void constructorTestInvalidColor1Size() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, 4,
        new int[]{1, 1, 1, 1}, new int[]{2, 1, 1}, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorTestInvalidColor2Size() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, 4,
        new int[]{1, 1, 1}, new int[]{2, 1, 1, 3}, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorTestInvalidMaxColorValueLowerBound() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, 4,
        new int[]{1, 1, 1}, new int[]{2, 1, 1}, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorTestInvalidMaxColorValueUpperBound() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, 4,
        new int[]{1, 1, 1}, new int[]{2, 1, 1}, 1000000000);
  }


  @Test
  public void testLoadImage() {
    IImage board = new CheckerboardPPM("Game", 1, 5, new int[]{1, 1, 1},
        new int[]{2, 2, 2}, 25);
    board.loadImage("Test Game");
    assertEquals(2, board.getPixel(3, 2)[0]);
    assertEquals(1, board.getPixel(0, 0)[0]);
    assertEquals(1, board.getPixel(0, 0)[1]);
    assertEquals(1, board.getPixel(0, 0)[2]);
    assertEquals(2, board.getPixel(1, 0)[0]);
    assertEquals(2, board.getPixel(1, 0)[1]);
    assertEquals(2, board.getPixel(1, 0)[2]);
    assertEquals(1, board.getPixel(1, 1)[0]);
    assertEquals(1, board.getPixel(1, 1)[1]);
    assertEquals(1, board.getPixel(1, 1)[2]);
    assertEquals(2, board.getPixel(0, 1)[0]);
    assertEquals(2, board.getPixel(0, 1)[1]);
    assertEquals(2, board.getPixel(0, 1)[2]);

  }


  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidFileName() {
    IImage invalid = new CheckerboardPPM("", 4, 4, new int[]{1, 1, 1},
        new int[]{1, 2, 1}, 50);
    invalid.loadImage("");
  }


  @Test
  public void testGetWidth() {
    IImage board = new CheckerboardPPM("Game", 4, 4, new int[]{1, 1, 1},
        new int[]{2, 2, 2}, 50);
    assertEquals(4, board.getWidth());
  }


  @Test
  public void testGetHeightCorrectTiles() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int[]{1, 1, 1},
        new int[]{2, 2, 2}, 50);
    assertEquals(16, board.getHeight());
  }

  @Test
  public void testSetPixel() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int[]{1, 2, 3},
        new int[]{2, 2, 2}, 50);
    int[] pixel = {4, 5, 6};
    board.setPixel(0, 0, pixel);
    assertEquals(4, board.getPixel(0, 0)[0]);
    assertEquals(5, board.getPixel(0, 0)[1]);
    assertEquals(6, board.getPixel(0, 0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidWidthLowerBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int[]{1, 1, 1},
        new int[]{2, 2, 2}, 50);
    board.setPixel(-1, 1, new int[]{4, 4, 4});
  }


  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidWidthUpperBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int[]{1, 1, 1},
        new int[]{2, 2, 2}, 50);
    board.setPixel(board.getHeight() + 1, 1, new int[]{4, 4, 4});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidHeightLowerBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int[]{1, 1, 1},
        new int[]{2, 2, 2}, 50);
    board.setPixel(1, -1, new int[]{4, 4, 4});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidHeightUpperBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int[]{1, 1, 1},
        new int[]{2, 2, 2}, 50);
    board.setPixel(1, board.getHeight() + 1, new int[]{4, 4, 4});
  }


  @Test
  public void testGetPixel() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int[]{1, 2, 3},
        new int[]{2, 2, 2}, 50);
    board.loadImage("Game");
    assertEquals(1, board.getPixel(0, 0)[0]);
    assertEquals(2, board.getPixel(0, 0)[1]);
    assertEquals(3, board.getPixel(0, 0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidWidthLowerBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int[]{1, 2, 3},
        new int[]{2, 2, 2}, 50);
    board.getPixel(-1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidWidthUpperBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int[]{1, 2, 3},
        new int[]{2, 2, 2}, 50);
    board.getPixel(board.getHeight() + 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidHeightLowerBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int[]{1, 2, 3},
        new int[]{2, 2, 2}, 50);
    board.getPixel(1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInvalidHeightUpperBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int[]{1, 2, 3},
        new int[]{2, 2, 2}, 50);
    board.getPixel(1, board.getHeight() + 1);
  }


  @Test
  public void testGetMaxColorValue() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int[]{1, 2, 3},
        new int[]{2, 2, 2}, 50);
    assertEquals(50, board.getMaxColorValue());
  }

  @Test
  public void testExportImageValidImageFile() {
    IImage image = new CheckerboardPPM("C:\\Users\\1235k\\IdeaProjects\\merlino_homework5"
        + "\\TestImages\\checkerboard.ppm", 1, 2,
        new int[]{44, 44, 44}, new int[]{76, 76, 76}, 100);

    image.exportImage();
    IImage outputImage = new PPMImage("C:\\Users\\1235k\\IdeaProjects\\merlino_homework5\\"
        + "TestImages\\checkerboard-output.ppm");
    assertEquals(2, outputImage.getWidth());
    assertEquals(2, outputImage.getHeight());
    assertEquals(100, outputImage.getMaxColorValue());

  }
}