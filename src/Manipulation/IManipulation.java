package Manipulation;

import Image.*;

public interface IManipulation {

  /**
   * Applys the given manipulation to the image.
   * @param image - the image to be modified.
   * @return the modified image.
   * @throws IllegalArgumentException if the image passed was null.
   */
  IImage apply(IImage image) throws IllegalArgumentException;
}
