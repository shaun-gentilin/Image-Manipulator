package Image;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CheckerboardPPMTest {
//Constructor

  //EXCEPTIONS

  /*
  emptyFilePath
   */
  @Test(expected=IllegalArgumentException.class)
  public void constructorTestEmptyFilePath() {
    IImage emptyFile = new CheckerboardPPM("", 1, 4,
        new int [] {1, 1, 1}, new int [] {2, 2, 2}, 50);
  }



  /*
  Width less than 1
   */
  @Test(expected=IllegalArgumentException.class)
  public void constructorTestInvalidWidth() {
    IImage emptyFile = new CheckerboardPPM("TestName", -4, 4,
        new int [] {1, 1, 1}, new int [] {2, 2, 2}, 50);
  }



  /*
  Tiles less than 1
   */
  @Test(expected=IllegalArgumentException.class)
  public void constructorTestInvalidTiles() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, -4,
        new int [] {1, 1, 1}, new int [] {2, 2, 2}, 50);
  }

  /*
  color1 null
   */
  @Test(expected=IllegalArgumentException.class)
  public void constructorTestInvalidColor1Null() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, 4,
        null, new int [] {2, 2, 2}, 50);
  }
  /*
  color2 null
   */
  @Test(expected=IllegalArgumentException.class)
  public void constructorTestInvalidColor2Null() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, 4,
        new int [] {1, 1, 1},null, 50);
  }

  /*
   color1 length != 3
   */
  @Test(expected=IllegalArgumentException.class)
  public void constructorTestInvalidColor1Size() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, 4,
        new int [] {1, 1, 1, 1},new int [] {2, 1, 1}, 50);
  }
  /*
   color2 length !=3
   */
  @Test(expected=IllegalArgumentException.class)
  public void constructorTestInvalidColor2Size() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, 4,
        new int [] {1, 1, 1},new int [] {2, 1, 1, 3}, 50);
  }

  /*
  maxColorValue less than 0
   */
  @Test(expected=IllegalArgumentException.class)
  public void constructorTestInvalidMaxColorValueLowerBound() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, 4,
        new int [] {1, 1, 1},new int [] {2, 1, 1}, -2);
  }
    /*
 maxColorValue greater than 65536
   */
    @Test(expected=IllegalArgumentException.class)
    public void constructorTestInvalidMaxColorValueUpperBound() {
      IImage emptyFile = new CheckerboardPPM("TestName", 4, 4,
          new int [] {1, 1, 1},new int [] {2, 1, 1}, 1000000000);
    }

  //LOADIMAGE
  /*
 Ensure that it works properly
   */
  @Test
  public void testLoadImage() {
    IImage board = new CheckerboardPPM("Game", 1, 2, new int [] {1, 1, 1},
        new int [] {2, 2, 2}, 25);
    board.loadImage("Test Game");
    assertEquals(1, board.getPixel(0, 0)[0]);
    assertEquals(1, board.getPixel(0, 0)[1]);
    assertEquals(1, board.getPixel(0, 0)[2]);
   // assertEquals(2, board.getPixel(1, 0)[0]);
    //assertEquals(2, board.getPixel(1, 0)[1]);
    //assertEquals(2, board.getPixel(1, 0)[2]);
    assertEquals(1, board.getPixel(1, 1)[0]);
    assertEquals(1, board.getPixel(1, 1)[1]);
    assertEquals(1, board.getPixel(1, 1)[2]);
    assertEquals(2, board.getPixel(0, 1)[0]);
    assertEquals(2, board.getPixel(0, 1)[1]);
    assertEquals(2, board.getPixel(0, 1)[2]);

  }

  //EXCEPTIONS
  /*
   invalid filename
   */
  @Test(expected=IllegalArgumentException.class)
  public void testLoadImageInvalidFileName() {
    IImage invalid = new CheckerboardPPM("", 4, 4, new int [] {1, 1, 1},
        new int [] {1, 2, 1}, 50);
    invalid.loadImage("");
  }

  //GETWIDTH
  /*
   get correct width
   */
  @Test
  public void testGetWidth() {
    IImage board = new CheckerboardPPM("Game", 4, 4, new int [] {1, 1, 1},
        new int [] {2, 2, 2}, 50);
    assertEquals(4, board.getWidth());
  }

  //GETHEIGHT
  /*
   get correct tiles
   */
  @Test
  public void testGetHeightCorrectTiles() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int [] {1, 1, 1},
        new int [] {2, 2, 2}, 50);
    assertEquals(16, board.getHeight());
  }

  //SETPIXEL
  /*
 set correct pixel
   */
  @Test
  public void testSetPixel() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int [] {1, 2, 3},
        new int [] {2, 2, 2}, 50);
    int [] pixel = {4, 5, 6};
    board.setPixel(0, 0, pixel);
    assertEquals(4, board.getPixel(0, 0)[0]);
    assertEquals(5, board.getPixel(0, 0)[1]);
    assertEquals(6, board.getPixel(0, 0)[2]);
  }

  //EXCEPTIONS

  /*
   width less than 1
   */
  @Test(expected=IllegalArgumentException.class)
  public void testSetPixelInvalidWidthLowerBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int [] {1, 1, 1},
        new int [] {2, 2, 2}, 50);
    board.setPixel(-1, 1, new int [] {4, 4, 4});
  }

  /*
   width greater than tiles
   */
  @Test(expected=IllegalArgumentException.class)
  public void testSetPixelInvalidWidthUpperBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int [] {1, 1, 1},
        new int [] {2, 2, 2}, 50);
    board.setPixel(board.getHeight()+1, 1, new int [] {4, 4, 4});
  }

  /*
 height less than 1
   */
  @Test(expected=IllegalArgumentException.class)
  public void testSetPixelInvalidHeightLowerBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int [] {1, 1, 1},
        new int [] {2, 2, 2}, 50);
    board.setPixel(1, -1, new int [] {4, 4, 4});
  }

  /*
 height greater than tiles
   */
  @Test(expected=IllegalArgumentException.class)
  public void testSetPixelInvalidHeightUpperBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int [] {1, 1, 1},
        new int [] {2, 2, 2}, 50);
    board.setPixel(1, board.getHeight()+1, new int [] {4, 4, 4});
  }

  //GETPIXEL
  /*
   ensure it gets correct pixel
   */
  @Test
  public void testGetPixel() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int [] {1, 2, 3},
        new int [] {2, 2, 2}, 50);
    board.loadImage("Game");
    assertEquals(1, board.getPixel(0, 0)[0]);
    assertEquals(2, board.getPixel(0, 0)[1]);
    assertEquals(3, board.getPixel(0, 0)[2]);
  }

  //EXCEPTIONS
  /*
   width less than 1
   */
  @Test(expected=IllegalArgumentException.class)
  public void testGetPixelInvalidWidthLowerBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int [] {1, 2, 3},
        new int [] {2, 2, 2}, 50);
    board.getPixel(-1, 1);
  }
  /*
   width greater than tiles
   */
  @Test(expected=IllegalArgumentException.class)
  public void testGetPixelInvalidWidthUpperBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int [] {1, 2, 3},
        new int [] {2, 2, 2}, 50);
    board.getPixel(board.getHeight()+1, 1);
  }
  /*
   height less than 1
   */
  @Test(expected=IllegalArgumentException.class)
  public void testGetPixelInvalidHeightLowerBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int [] {1, 2, 3},
        new int [] {2, 2, 2}, 50);
    board.getPixel(1, -1);
  }
  /*
 height greater than tiles
   */
  @Test(expected=IllegalArgumentException.class)
  public void testGetPixelInvalidHeightUpperBound() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int [] {1, 2, 3},
        new int [] {2, 2, 2}, 50);
    board.getPixel(1, board.getHeight()+1);
  }

  //GETMAXCOLORVALUE
  /*
  ensure it gets max color value
   */
  @Test
  public void testGetMaxColorValue() {
    IImage board = new CheckerboardPPM("Game", 4, 16, new int [] {1, 2, 3},
        new int [] {2, 2, 2}, 50);
    assertEquals(50, board.getMaxColorValue());
  }

  @Test
  public void testExportImageValidImageFile() {
    IImage image = new CheckerboardPPM("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\checkerboard.ppm", 2, 5,
        new int [] {1, 1, 1}, new int [] {2, 2, 2}, 25);

    image.exportImage();
    IImage outputImage = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\checkerboard-output.ppm");

    assertEquals(1, outputImage.getPixel(2, 2)[0]);
    assertEquals(1, outputImage.getPixel(2, 2)[1]);
    assertEquals(1, outputImage.getPixel(2, 2)[2]);
    /*
    assertEquals(2, outputImage.getWidth());
    assertEquals(2, outputImage.getHeight());
    assertEquals(25, outputImage.getMaxColorValue());
    assertEquals(1, outputImage.getPixel(0, 0)[0]);
    assertEquals(1, outputImage.getPixel(0, 0)[1]);
    assertEquals(1, outputImage.getPixel(0, 0)[2]);
    assertEquals(2, outputImage.getPixel(1, 0)[0]);
    assertEquals(2, outputImage.getPixel(1, 0)[1]);
    assertEquals(2, outputImage.getPixel(1, 0)[2]);
    assertEquals(2, outputImage.getPixel(0, 1)[0]);
    assertEquals(2, outputImage.getPixel(0, 1)[1]);
    assertEquals(2, outputImage.getPixel(0, 1)[2]);
    assertEquals(1, outputImage.getPixel(1, 1)[0]);
    assertEquals(1, outputImage.getPixel(1, 1)[1]);
    assertEquals(1, outputImage.getPixel(1, 1)[2]);
  */
  }
}