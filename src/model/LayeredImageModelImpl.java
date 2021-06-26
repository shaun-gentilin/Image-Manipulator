package model;

import image.ILayeredImage;
import image.ImageType;
import image.JPEGLayeredImage;
import image.PNGLayeredImage;
import image.PPMLayeredImage;
import java.awt.image.BufferedImage;
import manipulation.IManipulation;

public class LayeredImageModelImpl implements ILayeredImageModel {
  private ILayeredImage image;

  /**
   * Load the new image specified by path in the specified format to the model.
   *
   * @param format - the format of the image to be loaded.
   * @param path   - the path of the image to be loaded.
   * @throws IllegalArgumentException if the path or format are invalid.
   */
  @Override
  public void loadNewImage(String format, String path) throws IllegalArgumentException {
    format = format.toLowerCase();
    switch (format) {
      case "ppm":
        this.image = new PPMLayeredImage(path);
        break;
      case "jpg":
      case "jpeg":
        this.image = new JPEGLayeredImage(path);
        break;
      case "png":
        this.image = new PNGLayeredImage(path);
        break;
      default:
        throw new IllegalArgumentException("Not a valid format.");
    }
  }

  /**
   * Creates a new layer as the copy of the first layer in the image.
   */
  @Override
  public void addNewLayer() {
    this.image.addLayer();
  }

  /**
   * Remove the specified layer from the layeredImage.
   *
   * @param layerNum - the number of the layer to be removed.
   * @throws IllegalArgumentException if the layer number is not valid.
   */
  @Override
  public void removeLayer(int layerNum) throws IllegalArgumentException {
    this.image.removeLayer(layerNum);
  }

  /**
   * Apply the specified manipulation to the specified layer of the image.
   *
   * @param layerNum - the number of the layer to be manipulated.
   * @param manip    - the manipulation to apply to the layer.
   * @throws IllegalArgumentException if the layer number or manipulation are not valid.
   */
  @Override
  public void applyManipulation(int layerNum, IManipulation manip) throws IllegalArgumentException {
    if (manip == null) {
      throw new IllegalArgumentException("Manipulation cannot be null.");
    }
    manip.apply(this.image.getLayer(layerNum));
  }

  /**
   * Save the layered image from this model to a new ouput path.
   *
   * @return the new output path that the image is saved to.
   */
  @Override
  public String saveLayeredImage() {
    return this.image.saveImage();
  }

  /**
   * Export the topmost visible layer of the image.
   *
   * @return the string representing where the new image was stored.
   * @throws IllegalStateException if all layers are invisible.
   */
  @Override
  public String exportTopLayer() throws IllegalStateException {
    return this.image.exportImage();
  }

  /**
   * Saves the image at a new file path in the new format.
   *
   * @param format - the format to save the image in.
   * @return a string representing the path to the new saved image.
   * @throws IllegalArgumentException if the format is invalid.
   */
  @Override
  public String saveAsFormat(String format) throws IllegalArgumentException {
    format = format.toLowerCase();
    switch (format) {
      case "ppm":
        return this.image.saveImageAs(ImageType.PPM);
      case "jpg":
      case "jpeg":
        return this.image.saveImageAs(ImageType.JPEG);
      case "png":
        return this.image.saveImageAs(ImageType.PNG);
      default:
        throw new IllegalArgumentException("Not a valid format.");
    }
  }

  /**
   * Toggle the transparency of the specified layer.  That is, if the layer was visible, it will
   * become invisible and vise versa.
   *
   * @param layerNum - the number of the layer to be toggled.
   * @throws IllegalArgumentException if the layer number is invalid.
   */
  @Override
  public void toggleTransparent(int layerNum) throws IllegalArgumentException {
    this.image.toggleLayerTransparency(layerNum);
  }

  @Override
  public BufferedImage exportTopVisibleBufferedImage() {
    return this.image.exportTopVisibleBufferedImage();
  }
}
