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
   Colors are same
   */
  @Test(expected=IllegalArgumentException.class)
  public void constructorTestInvalidColorSame() {
    IImage emptyFile = new CheckerboardPPM("TestName", 4, 4,
        new int [] {1, 1, 1},new int [] {1, 1, 1}, 50);
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
  TODO: Ensure that it works properly
   */

  //EXCEPTIONS
  /*
  TODO: invalid filename
   */

  //GETWIDTH
  /*
  TODO: get correct width
   */

  //GETHEIGHT
  /*
  TODO: get correct tiles
   */

  //SETPIXEL
  /*
  TODO: set correct pixel
   */

  //EXCEPTIONS

  /*
  TODO: width less than 1
   */

  /*
  TODO: width greater than tiles
   */

  /*
  TODO: height less than 1
   */

  /*
  TODO: height greater than tiles
   */

  //GETPIXEL
  /*
  TODO: ensure it gets correct pixel
   */

  //EXCEPTIONS
  /*
  TODO: width less than 1
   */
  /*
  TODO: width greater than tiles
   */
  /*
  TODO: height less than 1
   */
  /*
  TODO: height greater than tiles
   */

  //GETMAXCOLORVALUE
  /*
  TODO: ensure it gets max color value
   */
}