package model;

/**
 * This interface defines the methods for image processing.
 */
public interface ImageModelInterface {

  /**
   * Retrieves the image data associated with the given name.
   *
   * @param name The name of the image to retrieve.
   * @return The image data as a 3D array of integers.
   */
  int[][][] getHashMap(String name);

  /**
   * Sets a 3D array of the image into the hashmap 'imageMap' with the key being its name and value
   * being the array.
   *
   * @param name       The name of the image as the key.
   * @param imageArray The 3D integer array representing the RGB values of the image.
   */
  void setHashMap(String name, int[][][] imageArray);

  /**
   * Applies a red filter to the image with the given name and saves the result with
   * the specified name.
   *
   * @param name The name of the source image.
   * @param save The name to assign to the resulting image.
   */
  void redImage(String name, String save);

  /**
   * Applies a green filter to the image with the given name and saves the result with
   * the specified name.
   *
   * @param name The name of the source image.
   * @param save The name to assign to the resulting image.
   */
  void greenImage(String name, String save);

  /**
   * Applies a blue filter to the image with the given name and saves the result with
   * the specified name.
   *
   * @param name The name of the source image.
   * @param save The name to assign to the resulting image.
   */
  void blueImage(String name, String save);

  /**
   * Converts the image with the given name to its value component and saves the result with
   * the specified name.
   *
   * @param name The name of the source image.
   * @param save The name to assign to the resulting image.
   * @param p    An optional parameter for which the value component is applied to the
   *             specified percentage of the image's width, and the rest remains unchanged.
   */
  void valueImage(String name, String save, double p);

  /**
   * Converts the image with the given name to its intensity component and saves the result
   * with the specified name.
   *
   * @param name The name of the source image.
   * @param save The name to assign to the resulting image.
   * @param p    An optional parameter for which the intensity component is applied to the
   *             specified percentage of the image's width, and the rest remains unchanged.
   */
  void intensityImage(String name, String save, double p);

  /**
   * Converts the image with the given name to its luma component and saves the result with
   * the specified name.
   *
   * @param name The name of the source image.
   * @param save The name to assign to the resulting image.
   * @param p    An optional parameter for which the value component is applied to the specified
   *             percentage of the image's width, and the rest remains unchanged.
   */
  void lumaImage(String name, String save, double p);

  /**
   * Flips the image horizontally and saves the result with the specified name.
   *
   * @param name The name of the source image.
   * @param save The name to assign to the resulting image.
   */
  void flipHorizontalImage(String name, String save);

  /**
   * Flips the image vertically and saves the result with the specified name.
   *
   * @param name The name of the source image.
   * @param save The name to assign to the resulting image.
   */
  void flipVerticalImage(String name, String save);

  /**
   * Brightens the image with the given name by a specified parameter and saves the result
   * with the specified name.
   *
   * @param name  The name of the source image.
   * @param save  The name to assign to the resulting image.
   * @param param The brightness adjustment parameter.
   */
  void brightenImage(String name, String save, int param);

  /**
   * Splits the image with the given name into its RGB components and saves each component
   * with the specified names.
   *
   * @param name  The name of the source image.
   * @param save1 The name to assign to the red component.
   * @param save2 The name to assign to the green component.
   * @param save3 The name to assign to the blue component.
   */
  void rgbSplit(String name, String save1, String save2, String save3);

  /**
   * Combines the red, green, and blue components with the specified names into a single image
   * and saves it with the specified name.
   *
   * @param save  The name to assign to the resulting image.
   * @param name1 The name of the red component.
   * @param name2 The name of the green component.
   * @param name3 The name of the blue component.
   */
  void rgbCombine(String save, String name1, String name2, String name3);

  /**
   * Applies a sepia filter to the image with the given name and saves the result with the
   * specified name.
   *
   * @param name The name of the source image.
   * @param save The name to assign to the resulting image.
   */
  void sepiaImage(String name, String save, double p);

  /**
   * Applies a blur filter to the image with the given name and saves the result with the
   * specified name.
   *
   * @param name The name of the source image.
   * @param save The name to assign to the resulting image.
   */
  void blurImage(String name, String save, double p);

  /**
   * Applies a sharpening filter to the image with the given name and saves the result with the
   * specified name.
   *
   * @param name The name of the source image.
   * @param save The name to assign to the resulting image.
   */
  void sharpenImage(String name, String save, double p);
}
