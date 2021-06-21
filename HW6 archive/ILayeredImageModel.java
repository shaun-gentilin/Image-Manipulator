package model;

import image.IImage;
import manipulation.IManipulation;
import image.ILayeredImage;

/**
 * Interface to represent the model to be used to manipulate images.
 * This model implementation represents a list of layered images.
 */
public interface ILayeredImageModel {

  /**
   * Apply the given manipulation on the specified image in the specified layered image.
   * @param manip         the manipulation to be applied to the specified image.
   * @param layerImage    the number of the layered image to be manipulated on.
   * @param layer         the image of the layered image to be manipulated on.
   * @return              a layered image after the manipulation.
   * @throws IllegalArgumentException   if the manipulation is null, or if layerImage or
   *                                    layer is invalid.
   */
  ILayeredImage applyManipulation(IManipulation manip, int layerImage, int layer)
      throws IllegalArgumentException;

  /**
   * Exports a specific layered image.
   *
   * @param layerImage    number of layered image to be exported.
   * @return              String of the output file name.
   * @throws IllegalArgumentException  if the layerImage is invalid.
   */
  String exportImage(int layerImage) throws IllegalArgumentException;

  /**
   * Add the given layered image to the list of layered images in the model.
   *
   * @param layeredImage     the layered image to be added to the model.
   * @return                  the number of the layered image that has just been added.
   * @throws IllegalArgumentException if the layered image is not valid.
   */
  int addLayeredImage(ILayeredImage layeredImage) throws IllegalArgumentException;

  /**
   * Remove specified layered image from the model.
   * @param layerImage      layered image to be remove from model.
   * @throws IllegalArgumentException   if layerImage is invalid.
   */
  void removeLayeredImage(int layerImage) throws IllegalArgumentException;

  /**
   * Observes a specific image in a specific layer of a layered image in the model.
   * @param layerImage    index of layered image in model.
   * @param layerNum      index of image in layered image.
   * @return              specified image.
   * @throws IllegalArgumentException   if layerImage or layerNum are invalid.
   */
  IImage getImage(int layerImage, int layerNum) throws IllegalArgumentException;

  /**
   * Observes a specific layered image in the model.
   * @param layerImage     index of layered image in model.
   * @return                specified layered image.
   * @throws IllegalArgumentException   if layerImage is invalid.
   */
  ILayeredImage getLayerImage(int layerImage) throws IllegalArgumentException;

}
