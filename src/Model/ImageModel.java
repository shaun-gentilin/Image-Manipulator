package Model;

import Image.IImage;
import Manipulation.IManipulation;
/**
 * Interface to represent the model to be used to manipulate images.
 */
public interface ImageModel {

  /**
   * Apply the given manipulation on the specified image.
   * @param manip - the manipulation to be applied to the specified image.
   * @param imageNum - the number of the image that is to be manipulated.
   * @return an image that is the result of the manipulation.
   * @throws IllegalArgumentException if the manipulation is null, or the image number is invalid.
   */
  IImage applyManipulation(IManipulation manip, int imageNum) throws IllegalArgumentException;

  /**
   * Export the specified image.
   * @param imageNum - the number of the image to be exported.
   * @throws IllegalArgumentException if the imageNum is invalid.
   */
  void exportImage(int imageNum) throws IllegalArgumentException;

  /**
   * Add the given image to the list of images in the model.
   * @param image - the image to be added to the model.
   * @return the number of the image that has just been added.
   * @throws IllegalArgumentException if the image is not valid (i.e. if it is null).
   */
  int addImage(IImage image) throws IllegalArgumentException;

  /**
   * Remove the specified image from the model.
   * @param imageNum - the image to be removed from the model.
   * @throws IllegalArgumentException if the number did not correspond to a valid image.
   */
  void removeImage(int imageNum) throws IllegalArgumentException;

  /**
   * Get the specified image from the list of images for this model.
   * @param imageNum - the image to be returned.
   * @return the specified image.
   */
  IImage getImage(int imageNum) throws IllegalArgumentException;
}
