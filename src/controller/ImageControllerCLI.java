package controller;

import java.util.Scanner;

import model.ImageModel;

/**
 * The ImageController class is responsible for relaying the inputs and outputs to model and view
 * and calling the individual classes of model and view as and when necessary. It also is in charge
 * of loading and saving the image since that is the input and output of the function respectively.
 * This ImageController handles the CLI way of running the program, i.e. only uses the model class.
 */
public class ImageControllerCLI extends AbstractControllerFunctions {

  private final Readable in;
  private final Appendable out;


  /**
   * Constructor for ImageControllerCLI class.
   *
   * @param model The ImageModel instance to be controlled by this controller.
   * @param in    A readable in to accept inputs directly to the controller.
   * @param out   An appendable out to write output into it which can be used to display them later.
   */
  public ImageControllerCLI(ImageModel model, Readable in, Appendable out) {
    ImageControllerCLI.model = model;
    this.in = in;
    this.out = out;
  }

  @Override
  protected void switchCaseHelper(String command, String[] parts) throws Exception {
    switch (command) {
      case "load":
        try {
          loadCaseHelper(parts);
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
        out.append("unknown command: ").append(command).append("\n");
        break;
    }
  }

  @Override
  public void runMain() {
    try (Scanner scanner = new Scanner(in)) {
      runMainHelper(scanner);
    }
  }
}