import controller.Controller;
import controller.IController;
import java.io.StringReader;
import model.ILayeredImageModel;
import model.LayeredImageModelImpl;
import view.IView;
import view.View;

/**
 * A class to hold the main function that will be used in the jar to run the program.
 */
public class Main {

  /**
   * A main function to run the program from the command line.
   * @param args - the arguments passed from the command line.
   */
  public static void main(String[] args) {
    ILayeredImageModel model = new LayeredImageModelImpl();
    View view = new View();
    IController controller = new Controller(model, view, null);

    switch (args[0]) {
      case "-script":
        String path = "";
        int ctr = 1;
        while (!path.contains(".")) {
          path += " " + args[ctr];
          ctr++;
        }
        path = path.substring(1); //take out the space at the beginning
        Readable readerScript = new StringReader("run " + path);
        controller.run(readerScript);
        break;
      case "-text":
        //TODO: (system.in)
        Readable readerText = new StringReader(args[1]);
        controller.run(readerText);
        break;
      case "-interactive":
        view.setVisible(true);
    }
  }
}
