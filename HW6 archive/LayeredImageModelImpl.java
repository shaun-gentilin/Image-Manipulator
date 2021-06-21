package model;

import image.IImage;
import image.ILayeredImage;
import manipulation.IManipulation;
import java.util.ArrayList;
import java.util.List;

/**
 * Class extending ILayeredImageModel interface that represents the LayeredImageModel
 * implementation.
 */
public class LayeredImageModelImpl implements ILayeredImageModel {

  List<ILayeredImage> layeredImages;

  /**
   * Constructor for a LayeredImageModel.
   *
   * @param layeredImages a list of layered images.
   */
  public LayeredImageModelImpl(List<ILayeredImage> layeredImages) {
    if (layeredImages == null) {
      throw new IllegalArgumentException("Layered Image list cannot be null.");
    }

    this.layeredImages = new ArrayList<>();
    for (ILayeredImage i : layeredImages) {
      this.layeredImages.add(i);
    }
  }


  /**
   * Apply the given manipulation on the specified image in the specified layered image.
   *
   * @param manip      the manipulation to be applied to the specified image.
   * @param layerImage the number of the layered image to be manipulated on.
   * @param layer      the image of the layered image to be manipulated on.
   * @return a layered image after the manipulation.
   * @throws IllegalArgumentException if the manipulation is null, or if layerImage or layer is
   *                                  invalid.
   */
  @Override
  public ILayeredImage applyManipulation(IManipulation manip, int layerImage, int layer)
      throws IllegalArgumentException {
    if (manip == null) {
      throw new IllegalArgumentException("Manipulation cannot be null.");
    } else if (layerImage < 0 || layerImage >= this.layeredImages.size()) {
      throw new IllegalArgumentException("That is not a valid layered image.");
    } else if (layer < 0 || layer >= this.layeredImages.get(layerImage).getAmountLayers()) {
      throw new IllegalArgumentException("That is not a valid image.");
    }
    IImage image = manip.apply(layeredImages.get(layerImage).getLayer(layer));
    this.layeredImages.get(layerImage).replaceLayer(image, layer);
    return this.layeredImages.get(layerImage);
  }


  /**
   * Exports a specific layered image.
   *
   * @param layerImage number of layered image to be exported.
   * @return String of the output file name.
   * @throws IllegalArgumentException if the layerImage is invalid.
   */
  @Override
  public String exportImage(int layerImage) throws IllegalArgumentException {
    if (layerImage < 0 || layerImage >= this.layeredImages.size()) {
      throw new IllegalArgumentException("That is not a valid layered image.");
    }
    return this.layeredImages.get(layerImage).exportImage();
  }

  /**
   * Add the given layered image to the list of layered images in the model.
   *
   * @param layeredImage the layered image to be added to the model.
   * @return the number of the layered image that has just been added.
   * @throws IllegalArgumentException if the layered image is not valid.
   */
  @Override
  public int addLayeredImage(ILayeredImage layeredImage) throws IllegalArgumentException {
    if (layeredImage == null) {
      throw new IllegalArgumentException("Layered Image cannot be null.");
    }
    this.layeredImages.add(layeredImage);
    return this.layeredImages.size() - 1;
  }

  /**
   * Remove specified layered image from the model.
   *
   * @param layerImage layered image to be remove from model.
   * @throws IllegalArgumentException if layerImage is invalid.
   */
  @Override
  public void removeLayeredImage(int layerImage) throws IllegalArgumentException {
    if (layerImage < 0 || layerImage >= this.layeredImages.size()) {
      throw new IllegalArgumentException("That is not a valid layered image.");
    }
    this.layeredImages.remove(layerImage);
  }

  /**
   * Observes a specific image in a specific layer of a layered image in the model.
   *
   * @param layerImage index of layered image in model.
   * @param layerNum   index of image in layered image.
   * @return specified image.
   * @throws IllegalArgumentException if layerImage or layerNum are invalid.
   */
  @Override
  public IImage getImage(int layerImage, int layerNum) throws IllegalArgumentException {
    if (layerImage < 0 || layerImage >= this.layeredImages.size()) {
      throw new IllegalArgumentException("That is not a valid layered image.");
    } else if (layerNum < 0 || layerNum >= this.layeredImages.get(layerImage).getAmountLayers()) {
      throw new IllegalArgumentException("That is not a valid image.");
    }
    return this.layeredImages.get(layerImage).getLayer(layerNum);
  }

  /**
   * Observes a specific layered image in the model.
   *
   * @param layerImage index of layered image in model.
   * @return specified layered image.
   * @throws IllegalArgumentException if layerImage is invalid.
   */
  @Override
  public ILayeredImage getLayerImage(int layerImage) throws IllegalArgumentException {
    if (layerImage < 0 || layerImage >= this.layeredImages.size()) {
      throw new IllegalArgumentException("That is not a valid layered image.");
    }
    return this.layeredImages.get(layerImage);
  }
}
