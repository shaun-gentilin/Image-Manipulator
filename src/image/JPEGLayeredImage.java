package image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JPEGLayeredImage extends AbstractLayeredImage {
  public JPEGLayeredImage(String filename) {
    super(filename, "JPEG");
  }

  /**
   * Loads a layered image based on the filename.
   *
   * @param filename - filename of the text file specifying where the images are stored.
   */
  @Override
  public void loadImageLayers(String filename) {
    try {
      File input = new File(filename);
      Scanner reader = new Scanner(input);
      while (reader.hasNextLine()) {
        layers.add(new JPEGImage(reader.nextLine()));
      }
      reader.close();
    } catch(FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }
  }

  /**
   * Add a new layer to this image at the top which is a copy of the primary layer (layer 0).
   * Change the pathname to include information about the layer (e.g. the number of the layer) so
   * that the path name will be unique to this layer (i.e. no repeated path names if there are
   * multiple copies).
   */
  @Override
  public void addLayer(int layerNum) throws IllegalArgumentException {

  }
}
