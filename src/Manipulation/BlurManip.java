package Manipulation;

public class BlurManip extends AbstractFilterManip {
  public BlurManip() {
    //pass the specific 3x3 blur kernel to the abstract class and let that class do the work.
    super(new double [][] {{1.0/16, 1.0/8, 1.0/16}, {1.0/8, 1.0/4, 1.0/8}, {1.0/16, 1.0/8, 1.0/16}},
        3, 3);
  }
}
