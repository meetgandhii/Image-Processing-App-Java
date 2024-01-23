package model;

/**
 * Extends ImageModelInterface and adds additional image processing methods.
 */
public interface ImageModelExtendedInterface extends ImageModelInterface {

  /**
   * Compresses the given image using a specified compression parameter.
   *
   * @param name  The name of the image to be compressed.
   * @param save  The name under which the compressed image will be saved.
   * @param param The compression parameter.
   * @throws IllegalArgumentException if the compression percentage is not between 0 and 100.
   */
  void imageCompression(String name, String save, double param) throws IllegalArgumentException;

  /**
   * Generates and saves a histogram image for the given image.
   *
   * @param name      The name of the image for which the histogram is generated.
   * @param destImage The name under which the histogram image will be saved.
   */
  void generateAndSaveHistogramImage(String name, String destImage);

  /**
   * Performs color correction on the given image based on a specified parameter.
   *
   * @param name The name of the image to be color-corrected.
   * @param save The name under which the color-corrected image will be saved.
   * @param p    An optional parameter for which the color correction is applied to the
   *             specified percentage of the image's width, and the rest remains unchanged.
   */
  void colorCorrect(String name, String save, double p);

  /**
   * Adjusts the levels of the given image, setting black, mid, and white points.
   *
   * @param black The black level for the image.
   * @param mid   The mid level for the image.
   * @param white The white level for the image.
   * @param name  The name of the image to be adjusted.
   * @param save  The name under which the adjusted image will be saved.
   * @param p     An optional parameter for which the color correction is applied to the
   *              specified percentage of the image's width, and the rest remains unchanged.
   */
  void levelsAdjust(int black, int mid, int white, String name, String save, double p);
}
