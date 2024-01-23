package controller;

import view.ImageView;

/**
 * The GUIImageControllerInterface defines the controller handling GUI interactions related to
 * image processing. It takes in inputs from the view and sends it to controller for further
 * processing and functions.
 */
public interface GUIImageControllerInterface {

  /**
   * This method is triggered whenever there is a click in the GUI. The view calls it and passes
   * the input to the GUIController, so it can further send it to the ImageController to carry out
   * the rest of the functions.
   *
   * @param input The input representing the user action as a string.
   * @throws Exception If an error occurs while processing the user action.
   */
  void handleClick(String input) throws Exception;

  /**
   * Sets the associated view for the controller. The view is responsible for displaying
   * information to the user.
   *
   * @param view The ImageView instance to be associated with the controller.
   */
  void setView(ImageView view);

  /**
   * Displays a preview of the image based on the input command and split percent in a new window.
   *
   * @param inp   The input command for the preview.
   * @param param The parameter for the preview.
   * @return The image data for the preview.
   * @throws Exception If an error occurs during the preview.
   */
  int[][][] displayPreview(String inp, int param) throws Exception;
}
