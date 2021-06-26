package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import manipulation.BlurManip;
import manipulation.GrayscaleManip;
import manipulation.SepiatoneManip;
import manipulation.SharpenManip;
import model.ILayeredImageModel;
import view.IView;
import view.IViewListener;

/**
 * Class to run script and handle user events sent by the view.
 */
public class Controller implements IController, IViewListener {
  ILayeredImageModel model;
  IView view;


  /**
   * Constructor to initialize a model and view for the purposes of using a GUI.
   * @param model - the model to be used and manipulated for this program.
   * @param view - the view to be used to display information for the program.
   * @param in - a readable to represent a script of commands passed by the user.  Should be null if
   *           the user does not want to pass in any commands and just wants to use the GUI.
   */
  public Controller(ILayeredImageModel model, IView view, Readable in) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(view);
    this.model = model;
    this.view = view;
    this.view.registerViewEventListener(this);

    if (in != null) {
      run(in);
    }
  }

  @Override
  public void run(Readable in) {
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
            this.model.loadNewImage(format, path);
          } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("New command must have 2 arguments following it.");
          }
          break;

        case "create":
          this.model.addNewLayer();
          break;

        case "remove":
          int layerNum = Integer.parseInt(scan.next());
          this.model.removeLayer(layerNum);
          break;

        case "sepia":
        case "sharpen":
        case "gray":
        case "blur":
          layerNum = Integer.parseInt(scan.next());
          manipulation(layerNum, input);
          break;

        case "save":
          this.model.saveLayeredImage();
          break;

        case "export":
          this.model.exportTopLayer();
          break;

        case "saveas":
          try {
            format = scan.next();
            this.model.saveAsFormat(format);
          } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Saveas command must have 1 argument following it "
                + "representing the format of the image.");
          }
          break;

        case "invisible":
          layerNum = Integer.parseInt(scan.next());
          this.model.toggleTransparent(layerNum);
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
          path = path.substring(1); //to get rid of the space at the beginning caused by the while
          ifRun(path);
          break;

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
    IController controller = new Controller(this.model, this.view, reader);
  }

  /**
   * Helper method if blur is parsed through readable.
   *
   * @param layerNum - the number of the layer to apply blur to.
   */
  private void manipulation(int layerNum, String manip) {
    switch (manip) {
      case "blur":
        this.model.applyManipulation(layerNum, new BlurManip());
        break;
      case "sepia":
        this.model.applyManipulation(layerNum, new SepiatoneManip());
        break;
      case "gray":
        this.model.applyManipulation(layerNum, new GrayscaleManip());
        break;
      case "sharpen":
        this.model.applyManipulation(layerNum, new SharpenManip());
        break;
      default:
        throw new IllegalArgumentException("Not a valid manipulation to apply.");
    }
  }

  /**
   * Handles the event where a new Image is displayed.
   * @param filePath      filePath of image
   * @param format        format of image
   */
  @Override
  public void handleNewImageEvent(String filePath, String format) {
    this.model.loadNewImage(format, filePath);
    this.view.updateImage(this.model.exportTopVisibleBufferedImage());
  }

  /**
   * Handles the event where a new layer is created.
   */
  @Override
  public void handleCreateNewLayerEvent() {
    this.model.addNewLayer();
    this.view.updateImage(this.model.exportTopVisibleBufferedImage());
  }

  /**
   * Hanldes the event where a specific layer index is removed.
   * @param layerNum      layer index
   */
  @Override
  public void handleRemoveLayerEvent(int layerNum) {
    this.model.removeLayer(layerNum);
    this.view.updateImage(this.model.exportTopVisibleBufferedImage());
  }

  /**
   * Handles the event where a blur manipulation is applied on a specific layer.
   * @param layerNum      layer index
   */
  @Override
  public void handleBlurEvent(int layerNum) {
    this.model.applyManipulation(layerNum, new BlurManip());
    this.view.updateImage(this.model.exportTopVisibleBufferedImage());
  }

  /**
   * Handles the event where a sharpen manipulation is applied on a specific layer.
   * @param layerNum      layer index
   */
  @Override
  public void handleSharpenEvent(int layerNum) {
    this.model.applyManipulation(layerNum, new SharpenManip());
    this.view.updateImage(this.model.exportTopVisibleBufferedImage());
  }

  /**
   * Handles the event where a grayscale manipulation is applied on a specific layer.
   * @param layerNum      layer index
   */
  @Override
  public void handleGrayscaleEvent(int layerNum) {
    this.model.applyManipulation(layerNum, new GrayscaleManip());
    this.view.updateImage(this.model.exportTopVisibleBufferedImage());
  }

  /**
   * Handles the event where a sepia manipulation is applied on a specific layer.
   * @param layerNum      layer index
   */
  @Override
  public void handleSepiaToneEvent(int layerNum) {
    this.model.applyManipulation(layerNum, new SepiatoneManip());
    this.view.updateImage(this.model.exportTopVisibleBufferedImage());
  }

  /**
   * Handles the event where the layered image is saved.
   */
  @Override
  public void handleSaveEvent() {
    this.model.saveLayeredImage();
    this.view.updateImage(this.model.exportTopVisibleBufferedImage());
  }

  /**
   * Handles the event where the topmost layer is exported.
   */
  @Override
  public void handleExportEvent() {
    this.model.exportTopLayer();
    this.view.updateImage(this.model.exportTopVisibleBufferedImage());
  }

  /**
   * Handles the event where the current layered image is saved as a new format.
   * @param format        format type
   */
  @Override
  public void handleSaveAsEvent(String format) {
    this.model.saveAsFormat(format);
    this.view.updateImage(this.model.exportTopVisibleBufferedImage());
  }

  /**
   * Handles the event where a specific layer's visibility is toggled.
   * @param layerNum      layer index
   */
  @Override
  public void handleToggleInvisibleEvent(int layerNum) {
    this.model.toggleTransparent(layerNum);
    this.view.updateImage(this.model.exportTopVisibleBufferedImage());
  }
}
