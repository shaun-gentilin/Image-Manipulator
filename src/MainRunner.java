import controller.Controller;
import controller.IController;
import java.io.StringReader;

/**
 * A class to hold the main function that will be used in the jar to run the program.
 */
public class MainRunner {

  /**
   * A main function to run the program from the command line.
   * @param args - the arguments passed from the command line.
   */
  public static void main(String[] args) {
    for (String i : args) {
      Readable reader = new StringReader(i);
      IController controller = new Controller(reader);
      controller.runScript();
    }
  }
}
