package view;

/**
 * Interface to outline what events should be able to be handled by listeners (e.g. the controller).
 */
public interface IViewListener {
  void handleNewImageEvent();
  void handleCreateNewLayerEvent();
  void handleSetCurrentEvent();
  void handleRemoveLayerEvent();
  void handleBlurEvent();
  void handleSharpenEvent();
  void handleGrayscaleEvent();
  void handleSepiaToneEvent();
  void handleSaveEvent();
  void handleExportEvent();
  void handleSaveAsEvent();
  void handleMakeInvisibleEvent();
}
