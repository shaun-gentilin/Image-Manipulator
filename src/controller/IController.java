package controller;

/**
 * Interface for an image manipulator controller. An implementation will work with the
 * ILayeredImageModel interface to provide proper manipulations to images.
 */
public interface IController {

  /**
   * Run a script that allows the user to upload, export, save, and manipulation images or layered
   * images. This method returns only when the user explicitly tells it to.
   */
  void runScript();
}
