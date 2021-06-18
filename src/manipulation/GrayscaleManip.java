package manipulation;

/**
 * Class representing a Grayscale Manipulation.
 */
public class GrayscaleManip extends AbstractColorManip {
  /**
   * Constructor for a grayscale manipulation to pass the grayscale matrix to super.
   */
  public GrayscaleManip() {
    super(new double [][] {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}});
  }
}
