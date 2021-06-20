package controller;

import image.IImage;
import image.ILayeredImage;
import image.ImageType;
import image.JPEGImage;
import image.JPEGLayeredImage;
import image.PNGLayeredImage;
import image.PPMImage;
import image.PPMLayeredImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import manipulation.BlurManip;
import manipulation.GrayscaleManip;
import manipulation.IManipulation;
import manipulation.SepiatoneManip;
import manipulation.SharpenManip;

public class Controller implements IController {

  private Readable in;
  private boolean loadedImage;
  private ArrayList<ILayeredImage> images;

  /**
   * Simple Constructor for Controller
   *
   * @param in Readable input
   */

  public Controller(Readable in) {
    if (in == null) {
      throw new IllegalArgumentException("Input is null.");
    }
    this.in = in;
    this.loadedImage = false;
    this.images = new ArrayList<>();
  }

  /**
   * Method to run the "new" command that creates a new layered image and loads it from the given
   * path name.
   * @param format - the format of the image o be loaded in.
   * @param path - the path that the image is to be loaded from.
   */
  private void newLayeredImage(String format, String path) throws IllegalArgumentException {
    switch (format) {
      case "jpeg":
        this.images.add(new JPEGLayeredImage(path));
        break;
      case "png":
        this.images.add(new PNGLayeredImage(path));
        break;
      case "ppm":
        this.images.add(new PPMLayeredImage(path));
        break;
      default:
        throw new IllegalArgumentException("Not a valid file type.");
    }
  }

  /**
   * Run a script that allows the user to upload, export, save, and manipulation images or layered
   * images. This method returns only when the user explicitly tells it to.
   */
  @Override
  public void runScript() {
    int listIndex = -1;
    int layers = 0;
    int layerIndex = -1;
    Scanner scan = new Scanner(in);
    while (scan.hasNext()) {
      String input = scan.next();
      String format;
      String path = "";
      switch (input) {
        case "new":
          try {
            format = scan.next();
            while (!path.contains(".")) {
              path += " " + scan.next();
            }
            path = path.substring(1);
            newLayeredImage(format, path);
            listIndex++;
            layers = getLayers(listIndex);
          } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("New command must have 2 arguments following it.");
          }

          break;

        case "create":
          layers = ifCreate(listIndex, layers);
          break;

        case "current":
          String currentLayerIndex;
          try {
            currentLayerIndex = scan.next();
            layerIndex = ifCurrent(currentLayerIndex);
          } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Current command must have 1 argument"
                + " following it.");
          }

          break;

        case "load":
          try {
            format = scan.next();
            String filePath = scan.next();
            ifLoad(format, listIndex, layerIndex, filePath);
          } catch(NoSuchElementException e) {
            throw new IllegalArgumentException("Current command must have 2 arguments following it.");
          }
          break;

        case "remove":
          ifRemove(listIndex, layerIndex);
          break;

        case "blur":
          ifBlur(listIndex, layerIndex);
          break;

        case "sepia":
          ifSepia(listIndex, layerIndex);
          break;

        case "sharpen":
          ifSharpen(listIndex, layerIndex);
          break;

        case "gray":
          ifGray(listIndex, layerIndex);
          break;

        case "save":
          ifSave(listIndex);
          break;

        case "export":
          ifExport(listIndex);
          break;

        case "saveas":
          try {
            format = scan.next();
            ifSaveAs(format, listIndex);
          } catch(NoSuchElementException e) {
            throw new IllegalArgumentException("Current command must have 2 arguments following it.");
          }
          break;

        case "invisible":
          ifInvisible(listIndex, layerIndex);
          break;

        case "quit":
          return;

        case "run":
          try {
            while (!path.contains(".")) {
              path += " " + scan.next();
            }
          } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("There should be 1 argument following the run "
                + "command.  This argument should be the path to run the script from.");
          }
          path = path.substring(1);
          ifRun(path);

        default:
          throw new IllegalArgumentException("Not a valid command.");
      }
    }
  }

  /**
   * Run the provided script file and all of the commands it contains.  This should work just as if
   * the user entered commands on the command line.
   * @param path - the path name for the script file to be run.
   */
  private void ifRun(String path) {
    File input = new File(path);
    Readable reader;
    try {
      reader = new FileReader(input);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Not a valid script file to read from.");
    }
    IController controller = new Controller(reader);
    controller.runScript();
  }

  /**
   * Helper method if create is parsed through readable.
   * @param listIndex     index of list of layered images.
   * @param layers        amount of layers in specific layered image.
   * @return              amount of layers after adding layer.
   */
  private int ifCreate(int listIndex, int layers) {
    this.images.get(listIndex).addLayer();
    return layers++;
  }

  /**
   * Helper method if current is parsed through readable.
   * @param currentLayerIndex     currentLayerIndex as a string.
   * @return                      currentLayerIndex as an integer.
   * @throws IllegalArgumentException if currentLayerIndex is invalid.
   */
  private int ifCurrent(String currentLayerIndex) throws IllegalArgumentException {
    try {
      return Integer.parseInt(currentLayerIndex);
    } catch (NumberFormatException error) {
      throw new IllegalArgumentException("Invalid Layer Index.");
    }
  }

  /**
   * Helper method if load is parsed through readable.
   * @param format            format of image.
   * @param listIndex         index of list of Layered Images.
   * @param layerIndex        Layer of specific layered image.
   * @param filePath          FilePath to be loeaded into specific layer.
   * @throws IllegalArgumentException if format is invalid.
   */
  private void ifLoad(String format, int listIndex, int layerIndex, String filePath) throws IllegalArgumentException {
    if (format.equals("jpeg")) {
      this.images.get(listIndex)
          .replaceLayer(new JPEGImage(filePath), layerIndex); //load image and replace layer
    } else if (format.equals("ppm")) {
      this.images.get(listIndex)
          .replaceLayer(new PPMImage(filePath), layerIndex); //load image and replace laye
    } else if (format.equals("png")) {
      this.images.get(listIndex)
          .replaceLayer(new PPMImage(filePath), layerIndex); //load image and replace laye
    } else {
      throw new IllegalArgumentException("Please enter a valid format.");
    }
  }

  /**
   * Helper method if remove is parsed through readable.
   * @param listIndex       index of list of layered images.
   * @param layerIndex      layer index of layered image.
   */
  private void ifRemove(int listIndex, int layerIndex) {
    images.get(listIndex).removeLayer(layerIndex);
  }

  private int getLayers(int listIndex) {
    return images.get(listIndex).getAmountLayers();
  }

  /**
   * Helper method if blur is parsed through readable.
   * @param listIndex       index of list of layered images.
   * @param layerIndex      layer index of layered image.
   */
  private void ifBlur(int listIndex, int layerIndex) {
    IManipulation blur = new BlurManip();
    IImage image = blur.apply(images.get(listIndex).getLayer(layerIndex));
    images.get(listIndex).replaceLayer(image, layerIndex);
  }

  /**
   * Helper method if sepia is parsed through readable.
   * @param listIndex       index of list of layered images.
   * @param layerIndex      layer index of layered image.
   */
  private void ifSepia(int listIndex, int layerIndex) {
    IManipulation sep = new SepiatoneManip();
    IImage image = sep.apply(images.get(listIndex).getLayer(layerIndex));
    images.get(listIndex).replaceLayer(image, layerIndex);
  }

  /**
   * Helper method if sharpen is parsed through readable.
   * @param listIndex       index of list of layered images.
   * @param layerIndex      layer index of layered image.
   */
  private void ifSharpen(int listIndex, int layerIndex) {
    IManipulation sharp = new SharpenManip();
    IImage image = sharp.apply(images.get(listIndex).getLayer(layerIndex));
    images.get(listIndex).replaceLayer(image, layerIndex);
  }

  /**
   * Helper method if gray is parsed through readable.
   * @param listIndex       index of list of layered images.
   * @param layerIndex      layer index of layered image.
   */
  private void ifGray(int listIndex, int layerIndex) {
    IManipulation gray = new GrayscaleManip();
    IImage image = gray.apply(images.get(listIndex).getLayer(layerIndex));
    images.get(listIndex).replaceLayer(image, layerIndex);
  }

  /**
   * Helper method if save is parsed through readable.
   * @param listIndex       index of list of layered images.
   */
  private void ifSave(int listIndex) {
    String output = this.images.get(listIndex).saveImage();
    System.out.println("Layered Image saved to path: " + output);
  }

  /**
   * Helper method if export is parsed through readable.
   * @param listIndex     index of list of layered images.
   */
  private void ifExport(int listIndex) {
    String exportPath = this.images.get(listIndex).exportImage();
    System.out.println("Topmost Layer saved to path: " + exportPath);
  }

  /**
   * Helper method if invisible is parsed through readable.
   * @param listIndex     index of list of layered images.
   * @param layerIndex    layered of specific layered image.
   */
  private void ifInvisible(int listIndex, int layerIndex) {
    this.images.get(listIndex).toggleLayerTransparency(layerIndex - 1);
  }

  /**
   * Helper method if saveas is parsed through readable.
   * @param format         specific format type.
   * @param listIndex      index of list of layered images.
   */
  private void ifSaveAs(String format, int listIndex) {
    if (!format.equals("jpeg") || !format.equals("ppm") || !format.equals("png")) {
      throw new IllegalArgumentException("Invalid format");
    }
    if (format.equals("jpeg")) {
      String output = images.get(listIndex).saveImageAs(ImageType.JPEG);
      System.out.println("Layered Image exported to path: " + output);
    } else if (format.equals("ppm")) {
      String output = images.get(listIndex).saveImageAs(ImageType.PPM);
      System.out.println("Layered Image exported to path: " + output);
    } else if (format.equals("png")) {
      String output = images.get(listIndex).saveImageAs(ImageType.PNG);
      System.out.println("Layered Image exported to path: " + output);
    }
  }

}
