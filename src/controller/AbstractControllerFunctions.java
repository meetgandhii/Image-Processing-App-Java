package controller;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.ImageModel;

/**
 * This is an abstract class used by both the GUI and the CLI controllers. It contains all the
 * common methods and code between them, to prevent code duplication.
 */
public abstract class AbstractControllerFunctions implements ImageControllerInterface {

  protected static ImageModel model;
  protected int[][][] img;

  @Override
  public void loadImage(String path, String name) throws Exception {
    String directory = path;
    if (path.toLowerCase().endsWith(".ppm")) {
      // Load PPM image
      readPPM(directory, name);
    } else {
      readOther(directory, name);
    }

  }

  protected void help() {
    System.out.println(
            "load image-path image-name: Load an image from the specified path"
                    + " and refer it to henceforth in the program by the given image name.\n"
                    + "save image-path image-name: Save the image with the given name to the"
                    + " specified path which should include the name of the file.\n"
                    + "red-component image-name dest-image-name: Create an image with the"
                    + "red-component of the image with the given name, and refer to it henceforth "
                    + "in the program by the given destination name.\n"
                    + "green-component image-name dest-image-name: Create an image with the "
                    + "green-component of the image with the given name, and refer to it henceforth"
                    + " in the program by the given destination name.\n"
                    + "blue-component image-name dest-image-name: Create an image with the "
                    + "blue-component of the image with the given name, and refer to it henceforth"
                    + " in the program by the given destination name.\n"
                    + "value-component image-name dest-image-name: Create an image with the "
                    + "value-component of the image with the given name, and refer to it henceforth"
                    + " in the program by the given destination name.\n"
                    + "luma-component image-name dest-image-name: Create an image with the "
                    + "luma-component of the image with the given name, and refer to it henceforth"
                    + " in the program by the given destination name.\n"
                    + "intensity-component image-name dest-image-name: Create an image with the "
                    + "intensity-component of the image with the given name, and refer to it"
                    + " henceforth in the program by the given destination name.\n"
                    + "horizontal-flip image-name dest-image-name: Flip an image horizontally to"
                    + " create a new image, referred to henceforth by the given destination name.\n"
                    + "vertical-flip image-name dest-image-name: Flip an image vertically to"
                    + " create a new image, referred to henceforth by the given destination name.\n"
                    + "brighten increment image-name dest-image-name: brighten the image by the"
                    + " given increment to create a new image, referred to henceforth by the given "
                    + "destination name. The increment may be positive (brightening) or negative "
                    + "(darkening).\n"
                    + "rgb-split image-name dest-image-name-red dest-image-name-green "
                    + "dest-image-name-blue: split the given image into three images containing "
                    + "its red, green and blue components respectively.\n"
                    + "rgb-split image-name dest-image-name-red dest-image-name-green "
                    + "dest-image-name-blue: split the given image into three images containing "
                    + "its red, green and blue components respectively.\n"
                    + "rgb-combine image-name red-image green-image blue-image: Combine the three "
                    + "images that are individually red, green and blue into a single img that "
                    + "gets its red, green and blue components from the three imgs respectively.\n"
                    + "blur image-name dest-image-name: blur the given image and store the"
                    + " result in another image with the given name.\n"
                    + "sharpen image-name dest-image-name: sharpen the given image and store "
                    + "the result in another image with the given name.\n"
                    + "sepia image-name dest-image-name: produce a sepia-toned version of the"
                    + " given image and store the result in another image with the given name.\n"
                    + "run script-file: Load and run the script commands in the specified file.\n"
                    + "histogram image-name dest-image-name: Support the ability to produce an"
                    + " image that represents the histogram of a given image.\n"
                    + "compress percentage image-name dest-image-name: Supports the ability to "
                    + "compress an image by the given percentage.\n"
                    + "color-correct image-name dest-image-name: Supports the ability to "
                    + "color-correct an image by aligning the meaningful peaks of its histogram.\n"
                    + "levels-adjust b m w image-name dest-image-name: Support the ability to "
                    + "adjust the levels of an image by the given parameters.\n"
                    + "Optional parameter split: blur image-name dest-image split p: Support the"
                    + " ability to specify a vertical line to generate a split view of operations"
                    + " on percentage p, on functions blur, sharpen, sepia, greyscale, "
                    + "color correction and levels adjustment\n"
                    + "-file name-of-script.txt: Support the ability to accept a script file "
                    + "as a command-line option"
    );

  }

  private void readPPM(String path, String name) throws Exception {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File " + path + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new Exception("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    img = new int[width][height][3];
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        img[j][i] = new int[]{r, g, b};
      }
    }
    model.setHashMap(name, img);
  }

  private void readOther(String path, String name) throws IOException {
    BufferedImage otherFormatImage = ImageIO.read(new File(path));
    int width = otherFormatImage.getWidth();
    System.out.println("Width of image: " + width);
    int height = otherFormatImage.getHeight();
    img = new int[width][height][3];
    System.out.println("Height of image: " + height);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int color = otherFormatImage.getRGB(j, i);
        int b = color & 0xff;
        int g = (color & 0xff00) >> 8;
        int r = (color & 0xff0000) >> 16;
        img[j][i] = new int[]{r, g, b};

      }
    }
    model.setHashMap(name, img);

  }

  private void savePPM(String path, String name, String fileType) {
    int[][][] saveImage = model.getHashMap(name);
    int width = saveImage.length;
    int height = saveImage[0].length;
    String outputDirectory = path;
    try (PrintWriter writer = new PrintWriter(new FileWriter(outputDirectory))) {
      writer.println("P3");
      writer.println(width + " " + height);
      writer.println("255");
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          writer.println(saveImage[x][y][0] + " " + saveImage[x][y][1] + " " + saveImage[x][y][2]);
        }
      }
      System.out.println("Image saved to: " + outputDirectory);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void saveOther(String path, String name, String fileType) {
    int[][][] saveImage = model.getHashMap(name);
    int width = saveImage.length;
    int height = saveImage[0].length;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    Graphics2D g = image.createGraphics();

    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (x < saveImage.length && y < saveImage[0].length) {
          Color pixelColor = new Color(saveImage[x][y][0], saveImage[x][y][1], saveImage[x][y][2]);
          image.setRGB(x, y, pixelColor.getRGB());
        }
      }
    }
    g.dispose();
    String outputDirectory = path;
    File outputDir = new File(outputDirectory);
    if (!outputDir.exists()) {
      outputDir.mkdirs();
    }

    String filename = outputDirectory;
    try {
      File outputImageFile = new File(filename);
      ImageIO.write(image, fileType, outputImageFile);
      System.out.println("Image saved to: " + outputImageFile.getAbsolutePath());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void createImage(String path, String name, String fileType) throws
          IllegalArgumentException {
    if (Objects.equals(fileType, "ppm")) {
      savePPM(path, name, fileType);
    } else if (Objects.equals(fileType, "png") || (Objects.equals(fileType, "jpg"))) {
      saveOther(path, name, fileType);
    } else {
      throw new IllegalArgumentException("Incorrect file type: " + fileType);
    }
  }

  protected int[][][] getHashMapFromModel(String name) {
    return model.getHashMap(name);
  }

  void runMainHelper(Scanner scanner) {
    while (scanner.hasNextLine()) {
      try {

        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        if (input.equals("quit")) {
          System.exit(0);
          break;
        } else if (parts[0].equals("help") || (parts[0].equals("--help"))
                || (parts[0].equals("--h")) || (parts[0].equals("h"))) {
          help();
        } else if (parts[0].equals("run")) {
          // run the script file
          String pathName = parts[1];
          File scriptFile = new File(pathName);
          try (Scanner sc = new Scanner(scriptFile)) {
            while (sc.hasNextLine()) {
              String line = sc.nextLine();
              String[] partInFile = line.split(" ");
              String command1 = partInFile[0];
              switchCaseHelper(command1, partInFile);
            }
          } catch (IOException e) {
            throw new RuntimeException(e);
          }

        } else {
          String command = parts[0];
          switchCaseHelper(command, parts);
        }
      } catch (Exception e) {
        System.err.println("Error: Please select a file");
      }
    }
  }

  protected void switchCaseHelper(String command1, String[] partInFile) throws Exception {
    // will get override in the respective files of controller.
  }

  protected void redComponentHelper(String[] parts) {
    model.redImage(parts[1], parts[2]);
  }

  protected void greenComponentHelper(String[] parts) {
    model.greenImage(parts[1], parts[2]);
  }

  protected void blueComponentHelper(String[] parts) {
    model.blueImage(parts[1], parts[2]);
  }

  protected void valueComponentHelper(String[] parts) {
    if (parts.length == 5 && parts[3].equals("split")) {
      if (Double.parseDouble(parts[4]) > 255 || Double.parseDouble(parts[4]) < 0) {
        throw new IllegalArgumentException("Percentage should be between 0 and 100");
      }
      model.valueImage(parts[1], parts[2], Double.parseDouble(parts[4]));
    } else {
      model.valueImage(parts[1], parts[2], 100);
    }
  }

  protected void intensityComponentHelper(String[] parts) {
    if (parts.length == 5 && parts[3].equals("split")) {
      if (Double.parseDouble(parts[4]) > 255 || Double.parseDouble(parts[4]) < 0) {
        throw new IllegalArgumentException("Percentage should be between 0 and 100");
      }
      model.intensityImage(parts[1], parts[2], Double.parseDouble(parts[4]));
    } else {
      model.intensityImage(parts[1], parts[2], 100);
    }
  }

  protected void lumaComponentHelper(String[] parts) {
    if (parts.length == 5 && parts[3].equals("split")) {
      if (Double.parseDouble(parts[4]) > 255 || Double.parseDouble(parts[4]) < 0) {
        throw new IllegalArgumentException("Percentage should be between 0 and 100");
      }
      model.lumaImage(parts[1], parts[2], Double.parseDouble(parts[4]));
    } else {
      model.lumaImage(parts[1], parts[2], 100);
    }
  }

  private void validatePercentage(String percentage) {
    if (Double.parseDouble(percentage) > 255 || Double.parseDouble(percentage) < 0) {
      throw new IllegalArgumentException("Percentage should be between 0 and 100");
    }
  }

  protected void blurComponentHelper(String[] parts) {
    if (parts.length == 5 && parts[3].equals("split")) {
      validatePercentage(parts[4]);
      model.blurImage(parts[1], parts[2], Double.parseDouble(parts[4]));
    } else {
      model.blurImage(parts[1], parts[2], 100);
    }
  }

  protected void sharpenComponentHelper(String[] parts) {
    if (parts.length == 5 && parts[3].equals("split")) {
      validatePercentage(parts[4]);
      model.sharpenImage(parts[1], parts[2], Double.parseDouble(parts[4]));
    } else {
      model.sharpenImage(parts[1], parts[2], 100);
    }
  }

  protected void sepiaComponentHelper(String[] parts) {
    if (parts.length == 5 && parts[3].equals("split")) {
      validatePercentage(parts[4]);
      model.sepiaImage(parts[1], parts[2], Double.parseDouble(parts[4]));
    } else {
      model.sepiaImage(parts[1], parts[2], 100);
    }
  }

  protected void compressComponentHelper(String[] parts) {
    model.imageCompression(parts[2], parts[3], Integer.parseInt(parts[1]));
  }

  protected void histogramComponentHelper(String[] parts) {
    model.generateAndSaveHistogramImage(parts[1], parts[2]);
  }

  protected void colorCorrectComponentHelper(String[] parts) {
    if (parts.length == 5 && parts[3].equals("split")) {
      validatePercentage(parts[4]);
      model.colorCorrect(parts[1], parts[2], Double.parseDouble(parts[4]));
    } else {
      model.colorCorrect(parts[1], parts[2], 100);
    }
  }

  protected void levelsAdjustComponentHelper(String[] parts) throws Exception {
    if (Integer.parseInt(parts[1]) > Integer.parseInt(parts[2])
            || Integer.parseInt(parts[2]) > Integer.parseInt(parts[3])
            || Integer.parseInt(parts[1]) > Integer.parseInt(parts[3])
            || Integer.parseInt(parts[1]) > 255 || Integer.parseInt(parts[1]) < 0
            || Integer.parseInt(parts[3]) > 255 || Integer.parseInt(parts[3]) < 0
            || Integer.parseInt(parts[2]) > 255 || Integer.parseInt(parts[2]) < 0) {
      throw new Exception("Check if B M and W values are in ascending order and the "
              + "values of B M and W are between 0 and 255");
    }
    if (parts.length == 8 && parts[6].equals("split")) {
      validatePercentage(parts[7]);
      model.levelsAdjust(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
              Integer.parseInt(parts[3]), parts[4], parts[5], Double.parseDouble(parts[7]));
    } else {
      model.levelsAdjust(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
              Integer.parseInt(parts[3]), parts[4], parts[5], 100);
    }
  }

  protected void horizontalFlipComponentHelper(String[] parts) {
    model.flipHorizontalImage(parts[1], parts[2]);
  }

  protected void verticalFlipComponentHelper(String[] parts) {
    model.flipVerticalImage(parts[1], parts[2]);
  }

  protected void brightenComponentHelper(String[] parts) {
    model.brightenImage(parts[2], parts[3], Integer.parseInt(parts[1]));
  }

  protected void rgbSplitComponentHelper(String[] parts) {
    model.rgbSplit(parts[1], parts[2], parts[3], parts[4]);
  }

  protected void rgbCombineComponentHelper(String[] parts) {
    model.rgbCombine(parts[1], parts[2], parts[3], parts[4]);
  }

  protected void saveCaseHelper(String[] parts) {
    String[] fileTypeArr = parts[1].split("\\.");
    String fileType = fileTypeArr[fileTypeArr.length - 1];
    createImage(parts[1], parts[2], fileType);
  }

  protected void loadCaseHelper(String[] parts) throws Exception {
    loadImage(parts[1], parts[2]);
  }

}
