package controller;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import javax.imageio.IIOException;

import model.ImageModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * This is a test class for ImageControl.
 * We create a mock model to test if the controller is taking the correct inputs and providing the
 * model with the unchanged inputs as it should.
 */
public class ImageControllerCLITest {

  String path = "res/testFile.png";
  String path2 = "res/testFile.jpg";
  String path3 = "res/testFile.ppm";
  String name = "landscape";

  @Test
  public void loadImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    int[][][] actual = model.getHashMap(name);
    int[][][] expected = {
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    int width = model.getWidth(name);
    int height = model.getHeight(name);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Assert.assertArrayEquals(actual[j][i], expected[j][i]);
      }
    }
  }

  @Test
  public void loadImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    int[][][] actual = model.getHashMap(name);
    int[][][] expected = {
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    int width = model.getWidth(name);
    int height = model.getHeight(name);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Assert.assertArrayEquals(actual[j][i], expected[j][i]);
      }
    }
  }

  @Test
  public void createImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    controller.createImage("res/testing/testfile.ppm", name, "ppm");
    File imageFile = new File("./res/testing/", "testfile.ppm");
    assertTrue("Image file should exist in the directory", imageFile.exists());
  }

  @Test
  public void createImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    controller.createImage("res/testing/testfile.jpg", name, "jpg");
    File imageFile = new File("./res/testing/", "testfile.jpg");
    assertTrue("Image file should exist in the directory", imageFile.exists());
  }

  @Test(expected = IIOException.class)
  public void saveError() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("res/red.jpg", "name");
    controller.createImage("./res/texting/save.jpg", "save", "jpg");
    int width = model.getWidth("save");
    int height = model.getHeight("save");
  }

  @Test(expected = IIOException.class)
  public void testLoadImageUnsupportedFileType() throws Exception {
    ImageModel imageModel = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(imageModel,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("unsupported_format.xyz", "image");
  }

  @Test(expected = NullPointerException.class)
  public void testLoadImageWithNullParameters() throws Exception {
    ImageModel imageModel = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(imageModel,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(null, null);
  }

  @Test(expected = NullPointerException.class)
  public void testLoadImageWithNullPath() throws Exception {
    ImageModel imageModel = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(imageModel,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(null, "something");
  }

  @Test(expected = IIOException.class)
  public void testLoadImageWithNullName() throws Exception {
    ImageModel imageModel = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(imageModel,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("something", null);
  }

  @Test
  public void createImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    controller.createImage("res/testing/testfile.png", name, "png");
    File imageFile = new File("./res/testing/", "testfile.png");
    assertTrue("Image file should exist in the directory", imageFile.exists());
  }

  @Test
  public void loadImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    int[][][] actual = model.getHashMap(name);
    int[][][] expected = {
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    int width = model.getWidth(name);
    int height = model.getHeight(name);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Assert.assertArrayEquals(actual[j][i], expected[j][i]);
      }
    }
  }

  @Test
  public void testControllerMethods() {
    StringBuilder mockLog = new StringBuilder();
    ImageModel mockImageModel = new MockImageModel(mockLog);
    Readable in;
    PrintStream out;
    OutputStream outStream = new ByteArrayOutputStream();
    out = new PrintStream(outStream);
    String stringInput = "red-component landscape-jpg landscape-jpg-red-component\n"
            + "green-component landscape-jpg landscape-jpg-green-component\n"
            + "blue-component landscape-jpg landscape-jpg-blue-component\n"
            + "value-component landscape-jpg landscape-jpg-value-component\n"
            + "luma-component landscape-jpg landscape-jpg-luma-component\n"
            + "intensity-component landscape-jpg landscape-jpg-intensity-component\n"
            + "horizontal-flip landscape-jpg landscape-jpg-horizontal-flip\n"
            + "vertical-flip landscape-jpg landscape-jpg-vertical-flip\n"
            + "brighten 20 landscape-jpg landscape-jpg-brighten\n"
            + "rgb-split landscape-jpg landscape-jpg-r-split landscape-jpg-g-split "
            + "landscape-jpg-b-split\n"
            + "rgb-combine landscape-jpg-rgb-combine landscape-jpg-r-split landscape-jpg-g-split "
            + "landscape-jpg-b-split\n"
            + "blur landscape-jpg landscape-jpg-blur\n"
            + "sharpen landscape-jpg landscape-jpg-sharpen\n"
            + "sepia landscape-jpg landscape-jpg-sepia\n";
    in = new StringReader(stringInput);
    ImageControllerCLI imageController = new ImageControllerCLI(mockImageModel, in, out);
    imageController.runMain();
    String actual = outStream.toString();
    assertEquals("Creating a red image with name landscape-jpg-red-component\n" +
            "Creating a green image with name landscape-jpg-green-component\n" +
            "Creating a blue image with name landscape-jpg-blue-component\n" +
            "Creating a value image with name landscape-jpg-value-componentand applying " +
            "to100.0%\n" +
            "Creating a luma image with name landscape-jpg-luma-componentand applying to100.0%\n" +
            "Creating an intensity image with name landscape-jpg-intensity-componentand " +
            "applying to100.0%\n" +
            "Creating a horizontally flipped image with name landscape-jpg-horizontal-flip\n" +
            "Creating a vertically flipped image with name landscape-jpg-vertical-flip\n" +
            "Brightening image with name landscape-jpg-brighten by 20\n" +
            "Splitting RGB channels for image with name landscape-jpg\n" +
            "Combining RGB channels for images landscape-jpg-r-split, landscape-jpg-g-split, " +
            "and landscape-jpg-b-split into landscape-jpg-rgb-combine\n" +
            "Applying blur filter to image with name landscape-jpg and saving as " +
            "landscape-jpg-blurand applying to100.0%\n" +
            "Applying sharpen filter to image with name landscape-jpg and saving as " +
            "landscape-jpg-sharpenand applying to100.0%\n" +
            "Applying sepia filter to image with name landscape-jpg and saving as " +
            "landscape-jpg-sepiaand applying to100.0%\n", mockLog.toString());
  }

  @Test
  public void testControllerMethods2() {
    StringBuilder mockLog = new StringBuilder();
    ImageModel mockImageModel = new ImageControllerCLITest.MockImageModel(mockLog);
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
                    + "Applying sharpen filter to image with name input and saving as " +
                    "sharpen-split-1and "
                    + "applying to33.0%\n"
                    + "Applying sepia filter to image with name input and saving as " +
                    "sepia-split-1and "
                    + "applying to33.0%\n"
                    + "Creating a value image with name value-component-split-1and " +
                    "applying to33.0%\n"
                    + "Creating an intensity image with name intensity-component-split-1and "
                    + "applying to33.0%\n"
                    + "Creating a luma image with name luma-component-split-1and " +
                    "applying to33.0%\n"
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

    @Override
    public void redImage(String name, String save) {
      log.append("Creating a red image with name " + save + "\n");
    }

    @Override
    public void greenImage(String name, String save) {
      log.append("Creating a green image with name " + save + "\n");
    }

    @Override
    public void blueImage(String name, String save) {
      log.append("Creating a blue image with name " + save + "\n");
    }

    @Override
    public void flipHorizontalImage(String name, String save) {
      log.append("Creating a horizontally flipped image with name " + save + "\n");
    }

    @Override
    public void flipVerticalImage(String name, String save) {
      log.append("Creating a vertically flipped image with name " + save + "\n");
    }

    @Override
    public void brightenImage(String name, String save, int param) {
      log.append("Brightening image with name " + save + " by " + param + "\n");
    }

    @Override
    public void rgbSplit(String name, String save1, String save2, String save3) {
      log.append("Splitting RGB channels for image with name " + name + "\n");
    }

    @Override
    public void rgbCombine(String save, String name1, String name2, String name3) {
      log.append("Combining RGB channels for images " + name1
              + ", " + name2 + ", and " + name3 + " into " + save + "\n");
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
