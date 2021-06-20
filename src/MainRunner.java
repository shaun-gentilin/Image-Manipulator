import controller.Controller;
import controller.IController;
import java.io.StringReader;

public class MainRunner {

   public static void main(String[] args) {
      for (String i : args) {
         Readable reader = new StringReader(i);
         IController controller = new Controller(reader);
         controller.runScript();
      }
   }
}
