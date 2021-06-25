package view;

/**
 * Interface to outline what events should be able to be handled by listeners (e.g. the controller).
 */
public interface IViewListener {
  void handleNewImageEvent(String filePath, String format);
  void handleCreateNewLayerEvent();
  void handleRemoveLayerEvent(int layerNum);
  void handleBlurEvent(int layerNum);
  void handleSharpenEvent(int layerNum);
  void handleGrayscaleEvent(int layerNum);
  void handleSepiaToneEvent(int layerNum);
  void handleSaveEvent();
  void handleExportEvent();
  void handleSaveAsEvent(String format);
  void handleToggleInvisibleEvent(int layerNum);
}
