package controller;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

/**
 * Test class for Controller.
 */
public class ControllerTest {

  /*
  Make sure the controller does not break when running commands.
   */
  @Test
  public void testController() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    Readable in = new StringReader("new jpeg " + path + " current 0 blur sepia sharpen gray "
        + "invisible remove save saveas ppm export quit");
    IController controller = new Controller(in);
    controller.runScript();
    File imageFile = new File("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image-output.txt");

    //the new file should be created when the save command is used
    boolean created = true;
    try {
      created = imageFile.createNewFile();
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    assertFalse(created);
  }
}