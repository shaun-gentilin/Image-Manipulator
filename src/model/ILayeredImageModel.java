package model;

import manipulation.IManipulation;

/**
 * Model to handle LayeredImage data and handle the manipulation of these images.  The model should
 * consist of one LayeredImage with many layers that operations can be performed on.
 */
public interface ILayeredImageModel {

  /**
   * Load the new image specified by path in the specified format to the model.
   * @param format - the format of the image to be loaded.
   * @param path - the path of the image to be loaded.
   * @throws IllegalArgumentException if the path or format are invalid.
   */
  void loadNewImage(String format, String path) throws IllegalArgumentException;

  /**
   * Creates a new layer as the copy of the first layer in the image.
   */
  void addNewLayer();

  /**
   * Remove the specified layer from the layeredImage.
   * @param layerNum - the number of the layer to be removed.
   * @throws IllegalArgumentException if the layer number is not valid.
   */
  void removeLayer(int layerNum) throws IllegalArgumentException;

  /**
   * Apply the specified manipulation to the specified layer of the image.
   * @param layerNum - the number of the layer to be manipulated.
   * @param manip - the manipulation to apply to the layer.
   * @throws IllegalArgumentException if the layer number or manipulation are not valid.
   */
  void applyManipulation(int layerNum, IManipulation manip) throws IllegalArgumentException;

  /**
   * Save the layered image from this model to a new ouput path.
   * @return the new output path that the image is saved to.
   */
  String saveLayeredImage();

  /**
   * Export the topmost visible layer of the image.
   * @return the string representing where the new image was stored.
   * @throws IllegalStateException if all layers are invisible.
   */
  String exportTopLayer() throws IllegalStateException;

  /**
   * Saves the image at a new file path in the new format.
   * @param format - the format to save the image in.
   * @return a string representing the path to the new saved image.
   * @throws IllegalArgumentException if the format is invalid.
   */
  String saveAsFormat(String format) throws IllegalArgumentException;

  /**
   * Toggle the transparency of the specified layer.  That is, if the layer was visible, it will
   * become invisible and vise versa.
   * @param layerNum - the number of the layer to be toggled.
   * @throws IllegalArgumentException if the layer number is invalid.
   */
  void toggleTransparent(int layerNum) throws IllegalArgumentException;
}
