package Model;

import Image.IImage;
import Manipulation.IManipulation;
/**
 * Interface to represent the model to be used to manipulate images.
 */
public interface ImageModel {
  IImage applyManipulation(IManipulation manip);
}
