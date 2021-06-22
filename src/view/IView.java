package view;

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
}
