package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class to handle the view for the image manipulation application.  This will function as a gui
 * for the users of the application to interact with the program.
 */
public class View extends JFrame implements IView, ActionListener {

  private final List<IViewListener> viewListeners;
  private JPanel imagePanel; //the panel to display the image that involves scrolling
  private JLabel imageLabel; //the image to be displayed on the imagePanel
  private final JTextArea formatTextArea; //image format to be used when events are called
  private final JTextArea layerNumTextArea; //layer number to be used when events are called

  /**
   * Initializes the view and all of its objects including the menu of features.
   */
  public View() {
    super();
    this.viewListeners = new ArrayList<>();

    setSize(new Dimension(1000, 10000));
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    setLayout(new FlowLayout());

    JMenuBar menuBar = new JMenuBar();
    JMenu featuresMenu = new JMenu("Features");
    menuBar.add(featuresMenu);

    JMenuItem newImage = new JMenuItem("New Image");
    JMenuItem createNewLayer = new JMenuItem("Create New Layer");
    JMenuItem removeLayer = new JMenuItem("Remove a Layer");
    JMenuItem blur = new JMenuItem("Blur");
    JMenuItem sharpen = new JMenuItem("Sharpen");
    JMenuItem grayscale = new JMenuItem("Grayscale");
    JMenuItem sepiaTone = new JMenuItem("Sepia Tone");
    JMenuItem save = new JMenuItem("Save the Image");
    JMenuItem export = new JMenuItem("Export the Top Layer");
    JMenuItem saveAs = new JMenuItem("Save the Image as a ...");
    JMenuItem makeInvisible = new JMenuItem("Toggle Invisible");

    List<JMenuItem> features = new ArrayList<>();
    features.add(newImage);
    features.add(createNewLayer);
    features.add(removeLayer);
    features.add(blur);
    features.add(sharpen);
    features.add(grayscale);
    features.add(sepiaTone);
    features.add(save);
    features.add(export);
    features.add(saveAs);
    features.add(makeInvisible);

    for (JMenuItem i : features) {
      i.addActionListener(this);
      featuresMenu.add(i);
    }

    add(menuBar);

    this.imagePanel = new JPanel();
    this.imagePanel.setLayout(new GridLayout(0, 1, 10, 10));
    add(this.imagePanel);

    this.imageLabel = new JLabel();
    JScrollPane scrollPane = new JScrollPane(this.imageLabel);
    this.imagePanel.add(scrollPane);

    this.formatTextArea = new JTextArea(1, 10);
    this.formatTextArea.setBorder(BorderFactory.createTitledBorder("Image Format"));
    add(this.formatTextArea);

    this.layerNumTextArea = new JTextArea(1, 10);
    this.layerNumTextArea.setBorder(BorderFactory.createTitledBorder("Layer Number"));
    add(this.layerNumTextArea);

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
      case "Export the Top Layer":
        emitExportEvent();
        break;
      case "Save the Image as a ...":
        emitSaveAsEvent();
        break;
      case "Toggle Invisible":
        emitToggleInvisibleEvent();
        break;
      default:
        throw new IllegalArgumentException("Not a valid event."); //should never get here
    }
  }

  /**
   * Tell the listener to handle the new image event.
   */
  private void emitNewImageEvent() {

    JFileChooser openBrowser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file for layered "
        + "image", "txt");
    openBrowser.setFileFilter(filter);
    String filePath = "";
    int retValue = openBrowser.showOpenDialog(View.this);
    if(retValue == JFileChooser.APPROVE_OPTION) {
      File file = openBrowser.getSelectedFile();
      filePath = file.getAbsolutePath();
    }


    for (IViewListener listener : this.viewListeners) {
      listener.handleNewImageEvent(filePath, this.formatTextArea.getText());
    }
  }

  /**
   * Tell the listener to handle the create new layer event.
   */
  private void emitCreateNewLayerEvent() {
    for (IViewListener listener : this.viewListeners) {
      listener.handleCreateNewLayerEvent();
    }
  }

  /**
   * Tell the listener to handle the remove layer event.
   */
  private void emitRemoveLayerEvent() {
    int layerNum = this.getLayerNum();
    for (IViewListener listener : this.viewListeners) {
      listener.handleRemoveLayerEvent(layerNum);
    }
  }

  /**
   * Tell the listener to handle the blur event.
   */
  private void emitBlurEvent() {
    int layerNum = this.getLayerNum();
    for (IViewListener listener : this.viewListeners) {
      listener.handleBlurEvent(layerNum);
    }
  }

  /**
   * Tell the listener to handle the sharpen event.
   */
  private void emitSharpenEvent() {
    int layerNum = this.getLayerNum();
    for (IViewListener listener : this.viewListeners) {
      listener.handleSharpenEvent(layerNum);
    }
  }

  /**
   * Tell the listener to handle the grayscale event.
   */
  private void emitGrayscaleEvent() {
    int layerNum = this.getLayerNum();
    for (IViewListener listener : this.viewListeners) {
      listener.handleGrayscaleEvent(layerNum);
    }
  }

  /**
   * Tell the listener to handle the sepia tone event.
   */
  private void emitSepiaToneEvent() {
    int layerNum = this.getLayerNum();
    for (IViewListener listener : this.viewListeners) {
      listener.handleSepiaToneEvent(layerNum);
    }
  }

  /**
   * Tell the listener to handle the save event.
   */
  private void emitSaveEvent() {
    for (IViewListener listener : this.viewListeners) {
      listener.handleSaveEvent();
    }
  }

  /**
   * Tell the listener to handle the export event.
   */
  private void emitExportEvent() {
    for (IViewListener listener : this.viewListeners) {
      listener.handleExportEvent();
    }
  }

  /**
   * Tell the listener to handle the save as event.
   */
  private void emitSaveAsEvent() {

    /*
    Code for a button chooser for the type of file to save it as

    JFrame buttonFrame = new JFrame("Format Selector");
    JButton jpegButton = new JButton("jpeg");
    JButton ppmButton = new JButton("ppm");
    JButton pngButton = new JButton("png");
     */
    for (IViewListener listener : this.viewListeners) {
      listener.handleSaveAsEvent(this.formatTextArea.getText());
    }
  }

  /**
   * Tell the listener to handle the make invisible event.
   */
  private void emitToggleInvisibleEvent() {
    int layerNum = this.getLayerNum();
    for (IViewListener listener : this.viewListeners) {
      listener.handleToggleInvisibleEvent(layerNum);
    }
  }

  /**
   * Register the given listener as a view event listener for this view.
   * @param listener - the listener to be registered.
   */
  @Override
  public void registerViewEventListener(IViewListener listener){
    this.viewListeners.add(Objects.requireNonNull(listener));
  }

  @Override
  public void updateImage(String filepath) {
    this.imagePanel.remove(0); //remove whatever was in there
    this.imageLabel = new JLabel(new ImageIcon(filepath));
    JScrollPane scrollPane = new JScrollPane(this.imageLabel);
    this.imagePanel.add(scrollPane);
    repaint();
    revalidate();
  }

  @Override
  public void updateImage(BufferedImage img) {
    this.imagePanel.remove(0); //remove whatever was in there
    this.imageLabel = new JLabel(new ImageIcon(img));
    JScrollPane scrollPane = new JScrollPane(this.imageLabel);
    this.imagePanel.add(scrollPane);
    repaint();
    revalidate();
  }

  private int getLayerNum() {
    try {
      return Integer.parseInt(this.layerNumTextArea.getText());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Layer number must be a number.");
    }
  }
}