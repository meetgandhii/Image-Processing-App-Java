package controller;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import model.ImageModel;

import static org.junit.Assert.assertEquals;


/**
 * This is a test class for ImageControl.
 * We create a mock model to test if the controller is taking the correct inputs and providing the
 * model with the unchanged inputs as it should.
 */
public class ImageControllerNewTest {

  @Test
  public void testControllerMethods() throws IOException {
    StringBuilder mockLog = new StringBuilder();
    ImageModel mockImageModel = new MockImageModel(mockLog);
    Readable in;
    PrintStream out;
    OutputStream outStream = new ByteArrayOutputStream();
    out = new PrintStream(outStream);
    String stringInput = "compress 70 input compression70\n"
            + "histogram input res/histogram.jpg\n"
            + "color-correct input colorCorrect1\n"
            + "levels-adjust 10 100 200 input levels-adjust1\n"
            + "blur input blur-split-1 split 33\n"
            + "sharpen input sharpen-split-1 split 33\n"
            + "sepia input sepia-split-1 split 33\n"
            + "value-component input value-component-split-1 split 33\n"
            + "intensity-component input intensity-component-split-1 split 33\n"
            + "luma-component input luma-component-split-1 split 33\n"
            + "color-correct input color-correct-split-1 split 33\n"
            + "levels-adjust 11 111 222 input levels-adjust-split-1 split 33\n";
    in = new StringReader(stringInput);
    ImageControllerCLI imageController = new ImageControllerCLI(mockImageModel, in, out);
    imageController.runMain();
    String actual = outStream.toString();
    assertEquals("Compressing image input by 70.0 and saving as compression70\n"
            + "Creating histogram of input and saving as res/histogram.jpg\n"
            + "Applying color correct to image with name input and saving as "
            + "colorCorrect1and applying to100.0%\n"
            + "Applying levels adjust of black: 10, mid: 100 and white: 200 to "
            + "image with name input and saving as levels-adjust1and applying to100.0%\n"
            + "Applying blur filter to image with name input and saving as blur-split-1and "
            + "applying to33.0%\n"
            + "Applying sharpen filter to image with name input and saving as sharpen-split-1and "
            + "applying to33.0%\n"
            + "Applying sepia filter to image with name input and saving as sepia-split-1and "
            + "applying to33.0%\n"
            + "Creating a value image with name value-component-split-1and applying to33.0%\n"
            + "Creating an intensity image with name intensity-component-split-1and "
            + "applying to33.0%\n"
            + "Creating a luma image with name luma-component-split-1and applying to33.0%\n"
            + "Applying color correct to image with name input and saving as "
            + "color-correct-split-1and applying to33.0%\n"
            + "Applying levels adjust of black: 11, mid: 111 and white: 222 to image "
            + "with name input and saving as levels-adjust-split-1and applying to33.0%\n",
            mockLog.toString());
  }

  /**
   * This is a mock image model.
   * A mock image model is used to test if the controller is receiving inputs properly and,
   * is providing proper outputs.
   * The class imitates zero functionality of the actual class.
   */
  public class MockImageModel extends ImageModel {
    private final StringBuilder log;

    /**
     * Constructor for Mock ImageModel class.
     *
     * @param log The log gets appended to check if all inputs are properly provided.
     */
    public MockImageModel(StringBuilder log) {
      this.log = log;
    }

    @Override
    public void valueImage(String name, String save, double p) {
      log.append("Creating a value image with name " + save + "and applying to" + p + "%\n");
    }

    @Override
    public void intensityImage(String name, String save, double p) {
      log.append("Creating an intensity image with name " + save + "and applying to" + p + "%\n");
    }

    @Override
    public void lumaImage(String name, String save, double p) {
      log.append("Creating a luma image with name " + save + "and applying to" + p + "%\n");
    }

    @Override
    public void sepiaImage(String name, String save, double p) {
      log.append("Applying sepia filter to image with name " + name + " and saving as " + save
              + "and applying to" + p + "%\n");
    }

    @Override
    public void blurImage(String name, String save, double p) {
      log.append("Applying blur filter to image with name " + name + " and saving as " + save
              + "and applying to" + p + "%\n");
    }

    @Override
    public void sharpenImage(String name, String save, double p) {
      log.append("Applying sharpen filter to image with name " + name + " and saving as " + save
              + "and applying to" + p + "%\n");
    }

    @Override
    public void imageCompression(String name, String save, double param) {
      log.append("Compressing image " + name + " by " + param + " and saving as " + save + "\n");
    }

    @Override
    public void generateAndSaveHistogramImage(String name, String save) {
      log.append("Creating histogram of " + name + " and saving as " + save + "\n");
    }

    @Override
    public void colorCorrect(String name, String save, double p) {
      log.append("Applying color correct to image with name " + name + " and saving as "
              + save + "and applying to" + p + "%\n");
    }

    @Override
    public void levelsAdjust(int black, int mid, int white, String name, String save, double p) {
      log.append("Applying levels adjust of black: " + black + ", mid: " + mid + " and white: "
              + white + " to image with name " + name + " and saving as " + save
              + "and applying to" + p + "%\n");
    }

    public String getLog() {
      return log.toString();
    }

    @Override
    public String toString() {
      return getLog();
    }
  }
}
