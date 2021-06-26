package view;

/**
 * Interface to outline what events should be able to be handled by listeners (e.g. the
 * controller).
 */
public interface IViewListener {

  /**
   * Handles the event where a new Image is displayed.
   * @param filePath      filePath of image
   * @param format        format of image
   */
  void handleNewImageEvent(String filePath, String format);

  /**
   * Handles the event where a new layer is created.
   */
  void handleCreateNewLayerEvent();

  /**
   * Hanldes the event where a specific layer index is removed.
   * @param layerNum      layer index
   */
  void handleRemoveLayerEvent(int layerNum);

  /**
   * Handles the event where a blur manipulation is applied on a specific layer.
   * @param layerNum      layer index
   */
  void handleBlurEvent(int layerNum);

  /**
   * Handles the event where a sharpen manipulation is applied on a specific layer.
   * @param layerNum      layer index
   */
  void handleSharpenEvent(int layerNum);

  /**
   * Handles the event where a grayscale manipulation is applied on a specific layer.
   * @param layerNum      layer index
   */
  void handleGrayscaleEvent(int layerNum);

  /**
   * Handles the event where a sepia manipulation is applied on a specific layer.
   * @param layerNum      layer index
   */
  void handleSepiaToneEvent(int layerNum);

  /**
   * Handles the event where the layered image is saved.
   */
  void handleSaveEvent();

  /**
   * Handles the event where the topmost layer is exported.
   */
  void handleExportEvent();

  /**
   * Handles the event where the current layered image is saved as a new format.
   * @param format        format type
   */
  void handleSaveAsEvent(String format);

  /**
   * Handles the event where a specific layer's visibility is toggled.
   * @param layerNum      layer index
   */
  void handleToggleInvisibleEvent(int layerNum);
}
