package controller;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import model.ImageModel;
import view.ImageView;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for GUI ImageController.
 * We create a mock model and mock view to test if the controller is taking the correct inputs
 * and providing the model and view with the unchanged values as it should.
 */
public class GUIControllerTest {

  @Test
  public void ErrorTestRGBCombine() {
    StringBuilder mockLog = new StringBuilder();
    ImageModel mockImageModel = new GUIControllerTest.MockImageModel(mockLog);
    Readable in;
    PrintStream out;
    OutputStream outStream = new ByteArrayOutputStream();
    out = new PrintStream(outStream);
    String stringinput = "rgb-combine input-rgb-cone input-r-split input-g-split input-b-split\n";
    in = new StringReader(stringinput);
    ImageView mockview = new GUIControllerTest.MockImageView(mockLog, in, out);
    GUIController imageController = new GUIController(mockImageModel);
    imageController.setView(mockview);
    ImageController controller = new ImageController(mockImageModel, mockview);

    imageController.handleClick(stringinput);


    String actual = outStream.toString();
    assertEquals("display error is called\n", mockLog.toString());
  }

  @Test
  public void ErrorTestSplit() {
    StringBuilder mockLog = new StringBuilder();
    ImageModel mockImageModel = new GUIControllerTest.MockImageModel(mockLog);
    Readable in;
    PrintStream out;
    OutputStream outStream = new ByteArrayOutputStream();
    out = new PrintStream(outStream);
    String stringinput = "rgb-combine input-rcombine input-r-split input-g-split input-b-split\n";
    in = new StringReader(stringinput);
    ImageView mockview = new GUIControllerTest.MockImageView(mockLog, in, out);
    GUIController imageController = new GUIController(mockImageModel);
    imageController.setView(mockview);
    ImageController controller = new ImageController(mockImageModel, mockview);

    imageController.handleClick(stringinput);


    String actual = outStream.toString();
    assertEquals("display error is called\n", mockLog.toString());
  }

  @Test
  public void testControllerMethods() throws Exception {
    StringBuilder mockLog = new StringBuilder();
    ImageModel mockImageModel = new GUIControllerTest.MockImageModel(mockLog);
    Readable in;
    PrintStream out;
    OutputStream outStream = new ByteArrayOutputStream();
    out = new PrintStream(outStream);
    String stringinput = "load res/input.png input\n" +
            "value-component input input\n" +
            "red-component input input\n"
            + "green-component input input-green-component\n"
            + "blue-component input input-blue-component\n"
            + "value-component input input-value-component\n"
            + "luma-component input input-luma-component\n"
            + "intensity-component input input-intensity-component\n"
            + "horizontal-flip input input-horizontal-flip\n"
            + "vertical-flip input input-vertical-flip\n"
            + "brighten 20 input input-brighten\n"
            + "rgb-split input input-r-split input-g-split input-b-split\n"
            + "rgb-combine input-rgb-combine input-r-split input-g-split input-b-split\n"
            + "blur input input-blur\n"
            + "sharpen input input-sharpen\n"
            + "sepia input input-sepia\n" +
            "compress 70 input compression70\n"
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
    String[] stringInput = {
        "load res/input.png input",
        "value-component input input split 50",
        "red-component input input-red-component",
        "green-component input input-green-component",
        "blue-component input input-blue-component",
        "value-component input input-value-component",
        "luma-component input input-luma-component",
        "intensity-component input input-intensity-component",
        "horizontal-flip input input-horizontal-flip",
        "vertical-flip input input-vertical-flip",
        "brighten 20 input input-brighten",
        "rgb-split input input-r-split input-g-split input-b-split",
        "rgb-combine input-rgb-combine input-r-split input-g-split input-b-split",
        "blur input input-blur",
        "sharpen input input-sharpen",
        "sepia input input-sepia",
        "compress 70 input compression70",
        "histogram input res/histogram.jpg",
        "color-correct input colorCorrect1",
        "levels-adjust 10 100 200 input levels-adjust1",
        "blur input blur-split-1 split 33",
        "sharpen input sharpen-split-1 split 33",
        "sepia input sepia-split-1 split 33",
        "value-component input value-component-split-1 split 33",
        "intensity-component input intensity-component-split-1 split 33",
        "luma-component input luma-component-split-1 split 33",
        "color-correct input color-correct-split-1 split 33",
        "levels-adjust 11 111 222 input levels-adjust-split-1 split 33"};
    in = new StringReader(stringinput);
    ImageView mockview = new GUIControllerTest.MockImageView(mockLog, in, out);
    GUIController imageController = new GUIController(mockImageModel);
    imageController.setView(mockview);
    ImageController controller = new ImageController(mockImageModel, mockview);
    for (int i = 0; i < stringInput.length; i++) {
      imageController.handleClick(stringInput[i]);
    }

    String actual = outStream.toString();
    assertEquals("load image is called\n"
                    + "display image is called\n"
                    + "Creating histogram of input and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Creating a value image with name input and applying to 50.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of input and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Creating a red image with name input-red-component\n"
                    + "display image is called\n"
                    + "Creating histogram of input-red-component and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Creating a green image with name input-green-component\n"
                    + "display image is called\n"
                    + "Creating histogram of input-green-component and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Creating a blue image with name input-blue-component\n"
                    + "display image is called\n"
                    + "Creating histogram of input-blue-component and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Creating a value image with name input-value-component and " +
                    "applying to 100.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of input-value-component and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Creating a luma image with name input-luma-componentand applying to100.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of input-luma-component and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Creating an intensity image with name input-intensity-componentand " +
                    "applying to100.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of input-intensity-component and saving as " +
                    "histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Creating a horizontally flipped image with name input-horizontal-flip\n"
                    + "display image is called\n"
                    + "Creating histogram of input-horizontal-flip and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Creating a vertically flipped image with name input-vertical-flip\n"
                    + "display image is called\n"
                    + "Creating histogram of input-vertical-flip and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "display error is called\n"
                    + "display error is called\n"
                    + "display error is called\n"
                    + "Applying blur filter to image with name input and saving as " +
                    "input-blurand applying to100.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of input-blur and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Applying sharpen filter to image with name input and saving as " +
                    "input-sharpenand applying to100.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of input-sharpen and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Applying sepia filter to image with name input and saving as " +
                    "input-sepiaand applying to100.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of input-sepia and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Compressing image input by 70.0 and saving as compression70\n"
                    + "display image is called\n"
                    + "Creating histogram of input and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Creating histogram of input and saving as res/histogram.jpg\n"
                    + "display image is called\n"
                    + "Creating histogram of res/histogram.jpg and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Applying color correct to image with name input and saving as " +
                    "colorCorrect1and applying to100.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of colorCorrect1 and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Applying levels adjust of black: 10, mid: 100 and white: 200 to image " +
                    "with name input and saving as levels-adjust1and applying to100.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of 200 and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Applying blur filter to image with name input and saving as " +
                    "blur-split-1and applying to33.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of blur-split-1 and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Applying sharpen filter to image with name input and saving as " +
                    "sharpen-split-1and applying to33.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of sharpen-split-1 and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Applying sepia filter to image with name input and saving as " +
                    "sepia-split-1and applying to33.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of sepia-split-1 and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Creating a value image with name value-component-split-1 and applying " +
                    "to 33.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of value-component-split-1 and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Creating an intensity image with name intensity-component-split-1and " +
                    "applying to33.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of intensity-component-split-1 and saving as " +
                    "histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Creating a luma image with name luma-component-split-1and applying to33.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of luma-component-split-1 and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Applying color correct to image with name input and saving as " +
                    "color-correct-split-1and applying to33.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of color-correct-split-1 and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n"
                    + "Applying levels adjust of black: 11, mid: 111 and white: 222 to image "
                    + "with name input and saving as levels-adjust-split-1and applying to33.0%\n"
                    + "display image is called\n"
                    + "Creating histogram of levels-adjust-split-1 and saving as histogramTemp\n"
                    + "histogram image is called\n"
                    + "previous image is called\n",
            mockLog.toString());


  }


  /**
   * This is a mock image view.
   * A mock image view is used to test if the controller is receiving inputs properly and,
   * is providing proper outputs.
   * The class imitates zero functionality of the actual class.
   */
  public class MockImageView extends ImageView {
    private final StringBuilder logView;

    public MockImageView(StringBuilder logView, Readable in, Appendable out) {
      super(in, out);
      this.logView = logView;
    }

    public void displayImage(int[][][] imageData) {
      logView.append("display image is called\n");
    }

    public void displayErrorMessage(String errorMessage) {
      logView.append("display error is called\n");
    }

    public void previousImage(int[][][] imageData) {
      logView.append("previous image is called\n");
    }

    public void displayHistogram(int[][][] imageData) {
      logView.append("histogram image is called\n");
    }

    public void loadedImage(int[][][] imageData) {
      logView.append("load image is called\n");
    }

  }


  /**
   * This is a mock image model.
   * A mock image model is used to test if the controller is receiving inputs properly and,
   * is providing proper outputs.
   * The class imitates zero functionality of the actual class.
   */
  public class MockImageModel extends ImageModel {
    private final StringBuilder logModel;

    /**
     * Constructor for Mock ImageModel class.
     *
     * @param logModel The logModel gets appended to check if all inputs are properly provided.
     */
    public MockImageModel(StringBuilder logModel) {
      this.logModel = logModel;
    }

    @Override
    public int[][][] getHashMap(String name) {
      return null;
    }

    @Override
    public void redImage(String name, String save) {
      logModel.append("Creating a red image with name " + save + "\n");
    }

    @Override
    public void greenImage(String name, String save) {
      logModel.append("Creating a green image with name " + save + "\n");
    }

    @Override
    public void blueImage(String name, String save) {
      logModel.append("Creating a blue image with name " + save + "\n");
    }

    @Override
    public void flipHorizontalImage(String name, String save) {
      logModel.append("Creating a horizontally flipped image with name " + save + "\n");
    }

    @Override
    public void flipVerticalImage(String name, String save) {
      logModel.append("Creating a vertically flipped image with name " + save + "\n");
    }

    @Override
    public void valueImage(String name, String save, double p) {
      logModel.append("Creating a value image with name " + save + " and applying to " + p + "%\n");
    }

    @Override
    public void intensityImage(String name, String save, double p) {
      logModel.append("Creating an intensity image with name " + save + "and applying to"
              + p + "%\n");
    }

    @Override
    public void lumaImage(String name, String save, double p) {
      logModel.append("Creating a luma image with name " + save + "and applying to" + p + "%\n");
    }

    @Override
    public void sepiaImage(String name, String save, double p) {
      logModel.append("Applying sepia filter to image with name " + name + " and saving as " + save
              + "and applying to" + p + "%\n");
    }

    @Override
    public void blurImage(String name, String save, double p) {
      logModel.append("Applying blur filter to image with name " + name + " and saving as " + save
              + "and applying to" + p + "%\n");
    }

    @Override
    public void sharpenImage(String name, String save, double p) {
      logModel.append("Applying sharpen filter to image with name " + name + " and saving as "
              + save
              + "and applying to" + p + "%\n");
    }

    @Override
    public void imageCompression(String name, String save, double param) {
      logModel.append("Compressing image " + name + " by " + param + " and saving as "
              + save + "\n");
    }

    @Override
    public void generateAndSaveHistogramImage(String name, String save) {
      logModel.append("Creating histogram of " + name + " and saving as " + save + "\n");
    }

    @Override
    public void colorCorrect(String name, String save, double p) {
      logModel.append("Applying color correct to image with name " + name + " and saving as "
              + save + "and applying to" + p + "%\n");
    }

    @Override
    public void levelsAdjust(int black, int mid, int white, String name, String save, double p) {
      logModel.append("Applying levels adjust of black: " + black + ", mid: " + mid + " and white: "
              + white + " to image with name " + name + " and saving as " + save
              + "and applying to" + p + "%\n");
    }

    public String getLog() {
      return logModel.toString();
    }

    @Override
    public String toString() {
      return getLog();
    }
  }

}
