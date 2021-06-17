package image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PPMLayeredImage extends AbstractLayeredImage {
  public PPMLayeredImage(String filename) {
    super(filename, "PPM");
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
        layers.add(new PPMImage(reader.nextLine()));
      }
      reader.close();
    } catch(FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }
  }

  /**
   * Add a new layer to this image at the top which is a copy of the main layer (layer 0).
   */
  @Override
  public void addLayer() throws IllegalArgumentException {
    try {
      File input = new File(filename);
      Scanner reader = new Scanner(input);
      if (!(reader.hasNextLine())) {
        throw new IllegalArgumentException("No main file to copy.");
      }
      else {
        layers.add(new PPMImage(reader.next()));
      }
    } catch(FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }
  }
}
