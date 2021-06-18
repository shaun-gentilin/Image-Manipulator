package image;

import image.hw5.PPMImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
   * Add a new layer to this image at the top which is a copy of the primary layer (layer 0).
   * Change the pathname to include information about the layer (e.g. the number of the layer) so
   * that the path name will be unique to this layer (i.e. no repeated path names if there are
   * multiple copies).
   */
  @Override
  public void addLayer(int layerNum) throws IllegalArgumentException {
    //initialize the reader
    Scanner reader;
    try {
      File input = new File(filename);
      reader = new Scanner(input);
    } catch(FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }

    //make the new layer as a copy of the original layer (layer 0).  This will be the first entry
    //in the given text file (filename)
    if (!(reader.hasNextLine())) {
      throw new IllegalArgumentException("No main file to copy.");
    }
    else {
      String primaryLayerPath = reader.nextLine();
      //take the primary path without the .ppm at the end and add stuff to it so that we know
      //this will be a valid file path.  Add in information about the layer so that the path will
      //be unique to the new layer.
      String newPath = primaryLayerPath.substring(0, primaryLayerPath.length() - 5)
          + "-layer" + layerNum;

      //create the new file so that we can create a new PPMImage object with the new name.
      File newLayer = new File(newPath);
      try {
        newLayer.createNewFile();
      } catch (IOException e) {
        throw new IllegalStateException("Cannot create new file.");
      }

      //create the new image object and load the contents of the original image into the new object.
      //This will create a new image object with a new file path, but the same properties as the
      //original layer.
      PPMImage newLayerImage = new PPMImage(newPath);
      newLayerImage.loadImage(primaryLayerPath);

      //add the new layer to the list of layers
      layers.add(newLayerImage);
    }
    reader.close();
  }
}
