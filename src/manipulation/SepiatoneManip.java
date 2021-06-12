package manipulation;

import image.IImage;

/**
 * Class representing a Sepiatone Manipulation.
 */
public class SepiatoneManip extends AbstractColorManip {

  /**
   * Constructor for a sepiatone manipulation to pass in the sepiatone matrix.
   */
  public SepiatoneManip() {
    super(new double [][] {{0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}});
  }
}

