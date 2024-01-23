package controller;

import java.io.IOException;

/**
 * The ImageController interface defines the contract for controlling image processing operations.
 */
public interface ImageControllerInterface {

  /**
   * Run the main loop to process user commands and script files.
   *
   * @throws IOException If an error occurs during input/output operations.
   */
  void runMain();

  /**
   * Loads an image from the specified path and assigns it a name.
   *
   * @param path The path to the image file.
   * @param name The name to assign to the loaded image.
   * @throws IOException If an error occurs while loading the image.
   */
  void loadImage(String path, String name) throws Exception;

  /**
   * Creates an image from the internal data and saves it to the specified path with the given name
   * and file type.
   *
   * @param path     The path to save the image.
   * @param name     The name of the image to save.
   * @param fileType The file type of the image (e.g., "jpg", "png").
   */
  void createImage(String path, String name, String fileType);
}
