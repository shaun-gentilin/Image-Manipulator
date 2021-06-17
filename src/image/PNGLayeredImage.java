package image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PNGLayeredImage extends AbstractLayeredImage {
  public PNGLayeredImage(String filename) {
    super(filename, "PNG");
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
        layers.add(new PNGImage(reader.nextLine()));
      }
      reader.close();
    } catch(FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }
  }
}
