package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Class to handle the view for the image manipulation application.  This will function as a gui
 * for the users of the application to interact with the program.
 */
public class View extends JFrame implements IView, ActionListener {

  private final List<IViewListener> viewListners;
  private final JMenuBar menuBar;
  private final JMenu featuresMenu;
  private final List<JMenuItem> features;
  private JLabel image;

  /**
   * Initializes the view and all of its objects including the menu of features.
   */
  public View() {
    super();
    this.viewListners = new ArrayList<>();

    setSize(new Dimension(600, 600));
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    setLayout(new FlowLayout());

    this.menuBar = new JMenuBar();
    this.featuresMenu = new JMenu("Features");
    this.menuBar.add(this.featuresMenu);

    JMenuItem newImage = new JMenuItem("New Image");
    JMenuItem createNewLayer = new JMenuItem("Create New Layer");
    JMenuItem setCurrent = new JMenuItem("Set the Current Layer");
    JMenuItem removeLayer = new JMenuItem("Remove a Layer");
    JMenuItem blur = new JMenuItem("Blur");
    JMenuItem sharpen = new JMenuItem("Sharpen");
    JMenuItem grayscale = new JMenuItem("Grayscale");
    JMenuItem sepiaTone = new JMenuItem("Sepia Tone");
    JMenuItem save = new JMenuItem("Save the Image");
    JMenuItem export = new JMenuItem("Export the Current Layer");
    JMenuItem saveAs = new JMenuItem("Save the Image as a ...");
    JMenuItem makeInvisible = new JMenuItem("Make the Current Layer Invisible");

    this.features = new ArrayList<>();
    this.features.add(newImage);
    this.features.add(createNewLayer);
    this.features.add(setCurrent);
    this.features.add(removeLayer);
    this.features.add(blur);
    this.features.add(sharpen);
    this.features.add(grayscale);
    this.features.add(sepiaTone);
    this.features.add(save);
    this.features.add(export);
    this.features.add(saveAs);
    this.features.add(makeInvisible);

    for (JMenuItem i : this.features) {
      i.addActionListener(this);
      this.featuresMenu.add(i);
    }

    add(this.menuBar);
    pack();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "New Image":
        emitNewImageEvent();
        break;
      case "Create New Layer":
        emitCreateNewLayerEvent();
        break;
      case "Set the Current Layer":
        emitSetCurrentEvent();
        break;
      case "Remove a Layer":
        emitRemoveLayerEvent();
        break;
      case "Blur":
        emitBlurEvent();
        break;
      case "Sharpen":
        emitSharpenEvent();
        break;
      case "Grayscale":
        emitGrayscaleEvent();
        break;
      case "Sepia Tone":
        emitSepiaToneEvent();
        break;
      case "Save the Image":
        emitSaveEvent();
        break;
      case "Export the Current Layer":
        emitExportEvent();
        break;
      case "Save the Image as a ...":
        emitSaveAsEvent();
        break;
      case "Make the Current Layer Invisible":
        emitMakeInvisibleEvent();
        break;
      default:
        throw new IllegalArgumentException("Not a valid event."); //should never get here
    }
  }

  /**
   * Tell the listener to handle the new image event.
   */
  private void emitNewImageEvent() {
    for (IViewListener listener : this.viewListners) {
      listener.handleNewImageEvent();
    }
  }

  /**
   * Tell the listener to handle the create new layer event.
   */
  private void emitCreateNewLayerEvent() {
    for (IViewListener listener : this.viewListners) {
      listener.handleCreateNewLayerEvent();
    }
  }

  /**
   * Tell the listener to handle the set current event.
   */
  private void emitSetCurrentEvent() {
    for (IViewListener listener : this.viewListners) {
      listener.handleSetCurrentEvent();
    }
  }

  /**
   * Tell the listener to handle the remove layer event.
   */
  private void emitRemoveLayerEvent() {
    for (IViewListener listener : this.viewListners) {
      listener.handleRemoveLayerEvent();
    }
  }

  /**
   * Tell the listener to handle the blur event.
   */
  private void emitBlurEvent() {
    for (IViewListener listener : this.viewListners) {
      listener.handleBlurEvent();
    }
  }

  /**
   * Tell the listener to handle the sharpen event.
   */
  private void emitSharpenEvent() {
    for (IViewListener listener : this.viewListners) {
      listener.handleSharpenEvent();
    }
  }

  /**
   * Tell the listener to handle the grayscale event.
   */
  private void emitGrayscaleEvent() {
    for (IViewListener listener : this.viewListners) {
      listener.handleGrayscaleEvent();
    }
  }

  /**
   * Tell the listener to handle the sepia tone event.
   */
  private void emitSepiaToneEvent() {
    for (IViewListener listener : this.viewListners) {
      listener.handleSepiaToneEvent();
    }
  }

  /**
   * Tell the listener to handle the save event.
   */
  private void emitSaveEvent() {
    for (IViewListener listener : this.viewListners) {
      listener.handleSaveEvent();
    }
  }

  /**
   * Tell the listener to handle the export event.
   */
  private void emitExportEvent() {
    for (IViewListener listener : this.viewListners) {
      listener.handleExportEvent();
    }
  }

  /**
   * Tell the listener to handle the save as event.
   */
  private void emitSaveAsEvent() {
    for (IViewListener listener : this.viewListners) {
      listener.handleSaveAsEvent();
    }
  }

  /**
   * Tell the listener to handle the make invisible event.
   */
  private void emitMakeInvisibleEvent() {
    for (IViewListener listener : this.viewListners) {
      listener.handleMakeInvisibleEvent();
    }
  }
}