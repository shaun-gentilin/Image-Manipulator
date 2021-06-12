package model;


import image.IImage;
import java.util.ArrayList;
import java.util.List;
import manipulation.IManipulation;

/**
 * CLass to represent a concrete implementation of the Model.ImageModel interface.
 */
public class ImageModelImpl implements ImageModel {

  List<IImage> images;

  /**
   * Constructor for an ImageModel.
   *
   * @param images A list of images
   */
  public ImageModelImpl(List<IImage> images) {
    if (images == null) {
      throw new IllegalArgumentException("Image list cannot be null.");
    }

    this.images = new ArrayList<>();
    for (IImage i : images) {
      this.images.add(i);
    }
  }

  @Override
  public IImage applyManipulation(IManipulation manip, int imageNum)
      throws IllegalArgumentException {
    if (manip == null) {
      throw new IllegalArgumentException("Manipulation cannot be null.");
    } else if (imageNum < 0 || imageNum >= images.size()) {
      throw new IllegalArgumentException("That is not a valid image.");
    }

    return manip.apply(this.images.get(imageNum));
  }

  @Override
  public void exportImage(int imageNum) throws IllegalArgumentException {
    if (imageNum < 0 || imageNum >= images.size()) {
      throw new IllegalArgumentException("That is not a valid image.");
    }
    IImage image = this.images.get(imageNum);
    image.exportImage();
  }

  @Override
  public int addImage(IImage image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    this.images.add(image);
    return this.images.size() - 1;
  }

  @Override
  public void removeImage(int imageNum) throws IllegalArgumentException {
    if (imageNum < 0 || imageNum >= images.size()) {
      throw new IllegalArgumentException("That is not a valid image.");
    }
    this.images.remove(imageNum);
  }

  @Override
  public IImage getImage(int imageNum) throws IllegalArgumentException {
    if (imageNum < 0 || imageNum >= images.size()) {
      throw new IllegalArgumentException("That is not a valid image.");
    }
    return this.images.get(imageNum);
  }
}
