package image;

import java.util.List;

/**
 * A class to represent a layered image in the JPEG format.  All layers of this image must be in
 * this format and have the same dimensions.
 */
public class JPEGLayeredImage extends AbstractLayeredImage {

  /**
   * A constructor for the PPMLayeredImage class.
   *
   * @param filename - the file path representing the text file that specifies where the layer
   *                 images are being stored.
   */
  public JPEGLayeredImage(String filename) {
    super(filename);
  }

  /**
   * Constructor that allows for fields to be initialize from parameters.  Allows for conversion
   * from one layered image type to another.
   *
   * @param filename          - the file path for this image.
   * @param layers            - the layers for this image.
   * @param transparentLayers - the layers that are transparent in this image.
   * @param width             - the width for this image.
   * @param height            - the height for this image.
   * @param maxColorValue     - the maximum color value for this image.
   */
  public JPEGLayeredImage(String filename, List<IImage> layers,
      List<IImage> transparentLayers, int width, int height, int maxColorValue) {
    super(filename, layers, transparentLayers,width, height, maxColorValue);
  }

  /**
   * Helper to the loadImageLayers function that allows for the correct type of image to be added to
   * the list of layers.
   *
   * @param path - the file path of the image to be added to the list of layers.
   */
  @Override
  protected IImage loadImageLayersHelp(String path) {
    return new JPEGImage(path);
  }

  /**
   * Helper for the addLayer method.  This method will allow concrete implementations of this
   * generic class to create images from the given path that have their own unique paths.
   *
   * @param primaryLayerPath - the path of the original image in this layered image.  To be used to
   *                         copy to the new image (will be loaded in).
   * @return an image that is a copy of the primary layer with a new unique file path.
   */
  @Override
  protected IImage addLayerHelp(String primaryLayerPath) {
    //take the primary path without the .ppm at the end and add stuff to it so that we know
    //this will be a valid file path.  Add in information about the layer so that the path will
    //be unique to the new layer.
    String newPath = primaryLayerPath.substring(0, primaryLayerPath.length() - 4)
        + "-layer" + (this.layers.size() - 1) + ".jpg";

    //create the new image object and load the contents of the original image into the new object.
    //This will create a new image object with a new file path, but the same properties as the
    //original layer.
    return new JPEGImage(newPath, true);
  }

  /**
   * Give the add-on for the file output path for the specific image type that we are working with.
   * For example, for a ppm image, the addition should be "-output-ppm.txt".
   *
   * @return the String add-on.
   */
  @Override
  protected String saveImagePathAddition() {
    return "-output-jpg.txt";
  }
}
