package view;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Interface to outline what views for this application should be able to handle.
 */
public interface IView {

  /**
   * Register the given listener as a view event listener for this view.
   * @param listener - the listener to be registered.
   */
  void registerViewEventListener(IViewListener listener);

  /**
   * Updates the current image for the view so that the user can see the updates that they make.
   * @param filepath - the file path of the image to be displayed.
   * @throws IllegalArgumentException if the file path does not exist.
   */
  void updateImage(String filepath) throws IllegalArgumentException;

  void updateImage(BufferedImage img);
}
