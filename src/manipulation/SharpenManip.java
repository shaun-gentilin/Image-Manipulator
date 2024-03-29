package manipulation;

/**
 * Class representing a Sharpen Manipulation.
 */
public class SharpenManip extends AbstractFilterManip {

  /**
   * Constructor for a Sharpen Manipulation.
   */
  public SharpenManip() {
    //pass the specific 5x5 blur kernel to the abstract class and let that class do the work.
    super(new double[][]{{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
                         {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
                         {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
                         {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
                         {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}},
        5, 5);
  }
}
