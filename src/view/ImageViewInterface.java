package view;

/**
 * The ImageViewInterface provides an interface for the ImageView Class which displays
 * images, buttons to perform functions on the said image and handles user interactions.
 */
public interface ImageViewInterface {

  /**
   * Displays the given image data on the view.
   *
   * @param imageData The image data to be displayed.
   */
  void displayImage(int[][][] imageData);

  /**
   * Displays an error message on the view.
   *
   * @param errorMessage The error message to be displayed.
   */
  void displayErrorMessage(String errorMessage);

  /**
   * Displays the previous image in the view.
   *
   * @param imageData The image data of the previous image.
   */
  void previousImage(int[][][] imageData);

  /**
   * Displays the histogram of the current image on the view.
   *
   * @param imageData The image data of the generated histogram.
   */
  void displayHistogram(int[][][] imageData);

  /**
   * Displays the loaded image on the view.
   *
   * @param imageData The image data of the loaded image.
   */
  void loadedImage(int[][][] imageData);
}
