package controller;

import java.util.Objects;
import model.ILayeredImageModel;
import view.IView;
import view.IViewListener;

public class Controller implements IController, IViewListener {
  ILayeredImageModel model;
  IView view;

  public Controller(ILayeredImageModel model, IView view) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(view);
    this.view.registerViewEventListener(this);
  }

  @Override
  public void run() {

  }

  @Override
  public void handleNewImageEvent() {

  }

  @Override
  public void handleCreateNewLayerEvent() {

  }

  @Override
  public void handleSetCurrentEvent() {

  }

  @Override
  public void handleRemoveLayerEvent() {

  }

  @Override
  public void handleBlurEvent() {

  }

  @Override
  public void handleSharpenEvent() {

  }

  @Override
  public void handleGrayscaleEvent() {

  }

  @Override
  public void handleSepiaToneEvent() {

  }

  @Override
  public void handleSaveEvent() {

  }

  @Override
  public void handleExportEvent() {

  }

  @Override
  public void handleSaveAsEvent() {

  }

  @Override
  public void handleMakeInvisibleEvent() {

  }
}
