package controller;

import java.util.Scanner;

import model.ImageModel;
import view.ImageView;

/**
 * The ImageController class is responsible for relaying the inputs and outputs to model and view
 * and calling the individual classes of model and view as and when necessary. It also is in charge
 * of loading and saving the image since that is the input and output of the function respectively.
 * This ImageController handles the GUI way of running the program, i.e. uses the View class.
 */
public class ImageController extends AbstractControllerFunctions {

  private static ImageView view;
  private int[][][] lastImage;

  /**
   * Constructor for ImageController class.
   *
   * @param model The ImageModel instance to be controlled by this controller.
   * @param view The ImageView instance to be controlled by this controller.
   */
  public ImageController(ImageModel model, ImageView view) {
    ImageController.model = model;
    ImageController.view = view;
  }

  protected void displayImageInFrame(String imageNameInHashmap) {
    view.displayImage(model.getHashMap(imageNameInHashmap));
    model.generateAndSaveHistogramImage(imageNameInHashmap, "histogramTemp");
    view.displayHistogram(model.getHashMap("histogramTemp"));
    view.previousImage(lastImage);
    lastImage = model.getHashMap(imageNameInHashmap);
  }

  @Override
  protected void switchCaseHelper(String command, String[] parts) throws Exception {

    switch (command) {
      case "load":
        try {
          loadCaseHelper(parts);
          lastImage = model.getHashMap(parts[2]);

          view.loadedImage(model.getHashMap(parts[2]));
          break;
        } catch (Exception e) {
          throw new Exception("Please select a file");
        }
      case "save":
        saveCaseHelper(parts);
        break;
      case "red-component":
        redComponentHelper(parts);
        break;
      case "green-component":
        greenComponentHelper(parts);
        break;
      case "blue-component":
        blueComponentHelper(parts);
        break;
      case "value-component":
        valueComponentHelper(parts);
        break;
      case "luma-component":
        lumaComponentHelper(parts);
        break;
      case "intensity-component":
        intensityComponentHelper(parts);
        break;
      case "horizontal-flip":
        horizontalFlipComponentHelper(parts);
        break;
      case "vertical-flip":
        verticalFlipComponentHelper(parts);
        break;
      case "brighten":
        brightenComponentHelper(parts);
        break;
      case "rgb-split":
        rgbSplitComponentHelper(parts);
        break;
      case "rgb-combine":
        rgbCombineComponentHelper(parts);
        break;
      case "blur":
        blurComponentHelper(parts);
        break;
      case "sharpen":
        sharpenComponentHelper(parts);
        break;
      case "sepia":
        sepiaComponentHelper(parts);
        break;
      case "compress":
        compressComponentHelper(parts);
        break;
      case "histogram":
        histogramComponentHelper(parts);
        break;
      case "color-correct":
        colorCorrectComponentHelper(parts);
        break;
      case "levels-adjust":
        levelsAdjustComponentHelper(parts);
        break;
      default:
        view.out.append("unknown command: ").append(command).append("\n");
        break;
    }
  }

  @Override
  public void runMain() {
    try (Scanner scanner = new Scanner(view.in)) {
      runMainHelper(scanner);
    }
  }
}