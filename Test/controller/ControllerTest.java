package controller;

import static org.junit.Assert.*;
import java.io.StringReader;
import org.junit.Test;

public class ControllerTest {

  @Test
  public void testController() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    Readable in = new StringReader("new jpeg " + path + " current 0 blur sepia sharpen gray "
        + "invisible remove save saveas ppm export quit");
    IController controller = new Controller(in);
    controller.runScript();
  }

}