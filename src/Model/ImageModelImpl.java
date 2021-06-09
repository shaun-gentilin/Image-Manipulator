package Model;

import Image.*;
import Manipulation.IManipulation;

/**
 * CLass to represent a concrete implementation of the Model.ImageModel interface.
 */
public class ImageModelImpl implements ImageModel {
  IImage image;

  public ImageModelImpl(IImage image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    this.image = image;
  }

  @Override
  public IImage applyManipulation(IManipulation manip) {
    return manip.apply(this.image);
  }
}
