package model;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Set;

/**
 * This class represents the model for image-related functionality.
 * It serves as a container for various image processing operations.
 */
public class ImageModel implements ImageModelExtendedInterface {

  private final HashMap<String, int[][][]> imageMap;

  /**
   * This is the constructor for the class ImageModel. It assigns a hashmap to each object which
   * stores the image name as key and the corresponding image 3d array as value. All the operations
   * retrieve and store into this hashmap.
   */
  public ImageModel() {
    imageMap = new HashMap<>();
  }


  /**
   * Retrieves the 3D RGB array of the image with the specified name.
   *
   * @param name The name of the image to retrieve.
   * @return The 3D integer array representing the RGB values of the image.
   * @throws IllegalArgumentException if the image with the given name is not found.
   */
  public int[][][] getHashMap(String name) {
    if (imageMap.containsKey(name)) {
      return imageMap.get(name);
    } else {
      throw new IllegalArgumentException("Image not found: " + name);
    }

  }

  /**
   * Sets a 3D array of the image into the hashmap 'imageMap' with the key being its name and value
   * being the array.
   *
   * @param name       The name of the image as the key.
   * @param imageArray The 3D integer array representing the RGB values of the image.
   */
  public void setHashMap(String name, int[][][] imageArray) {
    imageMap.put(name, imageArray);
  }

  @Override
  public void flipHorizontalImage(String name, String save) {
    int[][][] imageToWorkOn = getHashMap(name);
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;
    int[][][] flipHorizontalImg = new int[width][height][3];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int flipColumn = width - 1 - j;

        flipHorizontalImg[j][i] = imageToWorkOn[flipColumn][i];
      }
    }
    setHashMap(save, flipHorizontalImg);

  }

  @Override
  public void flipVerticalImage(String name, String save) {
    int[][][] imageToWorkOn = getHashMap(name);
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;
    int[][][] flipVerticalImg = new int[width][height][3];

    for (int i = 0; i < height; i++) {
      int flipRow = height - 1 - i;

      for (int j = 0; j < width; j++) {
        flipVerticalImg[j][i] = imageToWorkOn[j][flipRow];
      }
    }
    setHashMap(save, flipVerticalImg);

  }

  @Override
  public void brightenImage(String name, String save, int param) {
    int[][][] imageToWorkOn = getHashMap(name);
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;
    int[][][] brightenImg = new int[width][height][3];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        brightenImg[j][i][0] = (imageToWorkOn[j][i][0] + param > 0 && imageToWorkOn[j][i][0]
                + param < 256) ? imageToWorkOn[j][i][0] + param : (param > 0) ? 255 : 0;

        brightenImg[j][i][1] = (imageToWorkOn[j][i][1] + param > 0 && imageToWorkOn[j][i][1]
                + param < 256) ? imageToWorkOn[j][i][1] + param : (param > 0) ? 255 : 0;

        brightenImg[j][i][2] = (imageToWorkOn[j][i][2] + param > 0 && imageToWorkOn[j][i][2]
                + param < 256) ? imageToWorkOn[j][i][2] + param : (param > 0) ? 255 : 0;
      }
    }
    setHashMap(save, brightenImg);
  }

  @Override
  public void rgbSplit(String name, String save1, String save2, String save3) {
    int[][][] imageToWorkOn = getHashMap(name);
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;
    int[][][] redImg = new int[width][height][3];
    int[][][] greenImg = new int[width][height][3];
    int[][][] blueImg = new int[width][height][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        redImg[j][i] = new int[]{imageToWorkOn[j][i][0], 0, 0};
        greenImg[j][i] = new int[]{0, imageToWorkOn[j][i][1], 0};
        blueImg[j][i] = new int[]{0, 0, imageToWorkOn[j][i][2]};
      }
    }
    setHashMap(save1, redImg);
    setHashMap(save2, greenImg);
    setHashMap(save3, blueImg);
  }

  @Override
  public void rgbCombine(String save, String name1, String name2, String name3) {
    int[][][] imageToWorkOn1 = getHashMap(name1);
    int[][][] imageToWorkOn2 = getHashMap(name2);
    int[][][] imageToWorkOn3 = getHashMap(name3);
    int width = imageToWorkOn1.length;
    int height = imageToWorkOn1[0].length;
    int[][][] combinedImg = new int[width][height][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        combinedImg[j][i] = new int[]{imageToWorkOn1[j][i][0], imageToWorkOn2[j][i][1],
                imageToWorkOn3[j][i][2]};
      }
    }
    setHashMap(save, combinedImg);
  }

  @Override
  public void blurImage(String name, String save, double p) {
    double[][] kernel = {
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
            {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}
    };
    helperFilter(name, save, kernel, p);

  }

  @Override
  public void sharpenImage(String name, String save, double p) {
    double[][] kernel = {
            {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
            {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
            {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
            {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
            {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0}
    };
    helperFilter(name, save, kernel, p);
  }

  private void helperFilter(String name, String save, double[][] kernel, double p) {
    int[][][] imageToWorkOn = getHashMap(name);
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;

    int len = kernel.length;
    int[][][] filterImg = new int[width][height][3];

    for (int i = 0; i < height; i++) {

      for (int j = 0; j < width * p * 0.01; j++) {
        int r = 0;
        int g = 0;
        int b = 0;

        for (int k = -len / 2; k <= len / 2; k++) {
          for (int l = -len / 2; l <= len / 2; l++) {
            if (j + l < 0 || j + l >= width || i + k < 0 || i + k >= height || k + 1 < 0
                    || k + 1 >= width || l + 1 < 0 || l + 1 >= height) {
              continue;
            }
            r += (int) (imageToWorkOn[j + l][i + k][0] * kernel[k + 1][l + 1]);
            g += (int) (imageToWorkOn[j + l][i + k][1] * kernel[k + 1][l + 1]);
            b += (int) (imageToWorkOn[j + l][i + k][2] * kernel[k + 1][l + 1]);
          }

        }


        filterImg[j][i][0] = Math.min(255, Math.max(0, r));
        filterImg[j][i][1] = Math.min(255, Math.max(0, g));
        filterImg[j][i][2] = Math.min(255, Math.max(0, b));
      }
      for (int k = (int) (width * p * 0.01); k < width; k++) {
        filterImg[k][i] = imageToWorkOn[k][i];
      }
    }
    setHashMap(save, filterImg);
  }

  @Override
  public void redImage(String name, String save) {
    int[][][] imageToWorkOn = getHashMap(name);
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;
    int[][][] redImage = new int[width][height][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        redImage[j][i] = new int[]{imageToWorkOn[j][i][0], 0, 0};
      }
    }
    setHashMap(save, redImage);
  }

  @Override
  public void greenImage(String name, String save) {
    int[][][] imageToWorkOn = getHashMap(name);
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;
    int[][][] greenImage = new int[width][height][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        greenImage[j][i] = new int[]{0, imageToWorkOn[j][i][1], 0};
      }
    }
    setHashMap(save, greenImage);
  }

  @Override
  public void blueImage(String name, String save) {
    int[][][] imageToWorkOn = getHashMap(name);
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;
    int[][][] blueImage = new int[width][height][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        blueImage[j][i] = new int[]{0, 0, imageToWorkOn[j][i][2]};
      }
    }
    setHashMap(save, blueImage);
  }

  @Override
  public void valueImage(String name, String save, double p) {
    int[][][] imageToWorkOn = getHashMap(name);
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;
    int[][][] valueImg = new int[width][height][3];
    int maxOfThree = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width * p * 0.01; j++) {
        if (imageToWorkOn[j][i][0] > imageToWorkOn[j][i][1]
                && imageToWorkOn[j][i][0] > imageToWorkOn[j][i][2]) {
          maxOfThree = imageToWorkOn[j][i][0];
        } else if (imageToWorkOn[j][i][1] > imageToWorkOn[j][i][0]
                && imageToWorkOn[j][i][1] > imageToWorkOn[j][i][2]) {
          maxOfThree = imageToWorkOn[j][i][1];
        } else if (imageToWorkOn[j][i][2] > imageToWorkOn[j][i][1]
                && imageToWorkOn[j][i][2] > imageToWorkOn[j][i][0]) {
          maxOfThree = imageToWorkOn[j][i][2];
        }
        valueImg[j][i] = new int[]{maxOfThree, maxOfThree, maxOfThree};
      }
      for (int k = (int) (width * p * 0.01); k < width; k++) {
        valueImg[k][i] = imageToWorkOn[k][i];
      }
    }
    setHashMap(save, valueImg);

  }

  private void greyscaleHelper(String name, String save, double p,
                               double p1, double p2, double p3) {
    int[][][] imageToWorkOn = getHashMap(name);
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;
    int greyscaleValue;
    int[][][] greyscaleImg = new int[width][height][3];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width * p * 0.01; j++) {
        greyscaleValue = (int) ((p1 * imageToWorkOn[j][i][0])
                + (p2 * imageToWorkOn[j][i][1]) + (p3 * imageToWorkOn[j][i][2]));
        greyscaleImg[j][i] = new int[]{greyscaleValue, greyscaleValue, greyscaleValue};
      }
      for (int k = (int) (width * p * 0.01); k < width; k++) {
        greyscaleImg[k][i] = imageToWorkOn[k][i];
      }
    }
    setHashMap(save, greyscaleImg);
  }

  @Override
  public void intensityImage(String name, String save, double p) {
    greyscaleHelper(name, save, p, (double) 1 / 3, (double) 1 / 3, (double) 1 / 3);
  }

  @Override
  public void lumaImage(String name, String save, double p) {
    greyscaleHelper(name, save, p, 0.2126, 0.7152, 0.0722);
  }


  @Override
  public void sepiaImage(String name, String save, double p) {
    int[][][] imageToWorkOn = getHashMap(name);
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;
    int sepia1;
    int sepia2;
    int sepia3;
    int[][][] sepiaImg = new int[width][height][3];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width * p * 0.01; j++) {
        sepia1 = (int) ((0.393 * imageToWorkOn[j][i][0] + 0.769 * imageToWorkOn[j][i][1]
                + 0.189 * imageToWorkOn[j][i][2]) > 255 ? 255 : (0.393 * imageToWorkOn[j][i][0]
                + 0.769 * imageToWorkOn[j][i][1] + 0.189 * imageToWorkOn[j][i][2]));
        sepia2 = (int) ((0.349 * imageToWorkOn[j][i][0] + 0.686 * imageToWorkOn[j][i][1]
                + 0.168 * imageToWorkOn[j][i][2]) > 255 ? 255 : (0.349 * imageToWorkOn[j][i][0]
                + 0.686 * imageToWorkOn[j][i][1] + 0.168 * imageToWorkOn[j][i][2]));
        sepia3 = (int) ((0.272 * imageToWorkOn[j][i][0] + 0.534 * imageToWorkOn[j][i][1]
                + 0.131 * imageToWorkOn[j][i][2]) > 255 ? 255 : (0.272 * imageToWorkOn[j][i][0]
                + 0.534 * imageToWorkOn[j][i][1] + 0.131 * imageToWorkOn[j][i][2]));

        sepiaImg[j][i] = new int[]{sepia1, sepia2, sepia3};
      }
      for (int k = (int) (width * p * 0.01); k < width; k++) {
        sepiaImg[k][i] = imageToWorkOn[k][i];
      }
    }

    setHashMap(save, sepiaImg);
  }

  /**
   * Gets the width of an image.
   *
   * @param name The name of the image.
   * @return The width of the image.
   */
  public int getWidth(String name) {
    int[][][] imageToWorkOn = getHashMap(name);
    int width = imageToWorkOn.length;
    return width;
  }

  /**
   * Gets the height of an image.
   *
   * @param name The name of the image.
   * @return The height of the image.
   */
  public int getHeight(String name) {
    int[][][] imageToWorkOn = getHashMap(name);
    int height = imageToWorkOn[0].length;
    return height;
  }

  private double[][] pad2DArray(double[][] inputArray, int n) {
    int targetLength = (int) Math.pow(2, n);
    double[][] paddedArray = new double[targetLength][targetLength];
    for (int i = 0; i < targetLength; i++) {
      for (int j = 0; j < targetLength; j++) {
        if (i < inputArray[0].length && j < inputArray.length) {
          paddedArray[i][j] = inputArray[j][i];
        } else {
          paddedArray[i][j] = 0;
        }
      }
    }
    return paddedArray;
  }

  private void haarTransform(int lengthOfPaddedArray, double[][] redpaddedArray,
                             double[][] greenpaddedArray, double[][] bluepaddedArray,
                             double[][] transformRedArray, double[][] transformGreenArray,
                             double[][] transformBlueArray) {
    int tempTransform = lengthOfPaddedArray;
    while (tempTransform > 1) {
      for (int i = 0; i < lengthOfPaddedArray; i++) {
        for (int j = 0; j < tempTransform / 2; j++) {
          transformRedArray[i][j] = (redpaddedArray[i][2 * j]
                  + redpaddedArray[i][(2 * j) + 1]) / Math.sqrt(2);
          transformGreenArray[i][j] = (greenpaddedArray[i][2 * j]
                  + greenpaddedArray[i][(2 * j) + 1]) / Math.sqrt(2);
          transformBlueArray[i][j] = (bluepaddedArray[i][2 * j]
                  + bluepaddedArray[i][(2 * j) + 1]) / Math.sqrt(2);
          int k = (tempTransform / 2) + j;
          transformRedArray[i][k] = (redpaddedArray[i][2 * j]
                  - redpaddedArray[i][(2 * j) + 1]) / Math.sqrt(2);
          transformGreenArray[i][k] = (greenpaddedArray[i][2 * j]
                  - greenpaddedArray[i][(2 * j) + 1]) / Math.sqrt(2);
          transformBlueArray[i][k] = (bluepaddedArray[i][2 * j]
                  - bluepaddedArray[i][(2 * j) + 1]) / Math.sqrt(2);

        }
      }
      for (int i1 = 0; i1 < lengthOfPaddedArray; i1++) {
        for (int j1 = 0; j1 < tempTransform; j1++) {
          redpaddedArray[i1][j1] = transformRedArray[i1][j1];
          greenpaddedArray[i1][j1] = transformGreenArray[i1][j1];
          bluepaddedArray[i1][j1] = transformBlueArray[i1][j1];
        }
      }

      for (int i = 0; i < lengthOfPaddedArray; i++) {
        for (int j = 0; j < tempTransform / 2; j++) {
          transformRedArray[j][i] = (redpaddedArray[2 * j][i]
                  + redpaddedArray[(2 * j) + 1][i]) / Math.sqrt(2);
          transformGreenArray[j][i] = (greenpaddedArray[2 * j][i]
                  + greenpaddedArray[(2 * j) + 1][i]) / Math.sqrt(2);
          transformBlueArray[j][i] = (bluepaddedArray[2 * j][i]
                  + bluepaddedArray[(2 * j) + 1][i]) / Math.sqrt(2);
          int k = (tempTransform / 2) + j;
          transformRedArray[k][i] = (redpaddedArray[2 * j][i]
                  - redpaddedArray[(2 * j) + 1][i]) / Math.sqrt(2);
          transformGreenArray[k][i] = (greenpaddedArray[2 * j][i]
                  - greenpaddedArray[(2 * j) + 1][i]) / Math.sqrt(2);
          transformBlueArray[k][i] = (bluepaddedArray[2 * j][i]
                  - bluepaddedArray[(2 * j) + 1][i]) / Math.sqrt(2);
        }
      }
      for (int i1 = 0; i1 < lengthOfPaddedArray; i1++) {
        for (int j1 = 0; j1 < tempTransform; j1++) {
          redpaddedArray[j1][i1] = transformRedArray[j1][i1];
          greenpaddedArray[j1][i1] = transformGreenArray[j1][i1];
          bluepaddedArray[j1][i1] = transformBlueArray[j1][i1];
        }
      }
      tempTransform = tempTransform / 2;
    }
  }

  private void threshold(int lengthOfPaddedArray, double[][] transformRedArray,
                         double[][] transformGreenArray,
                         double[][] transformBlueArray, double param) {
    double[][][] arrays = {transformRedArray, transformGreenArray, transformBlueArray};
    Set<Double> absoluteValuesSet = new HashSet<>();
    for (double[][] array : arrays) {
      for (double[] row : array) {
        for (double element : row) {
          absoluteValuesSet.add(Math.abs(element));
        }
      }
    }
    Double[] sortedArray = absoluteValuesSet.toArray(new Double[0]);
    Arrays.sort(sortedArray);
    double threshold = sortedArray[(int) (param * 0.01 * (sortedArray.length - 1))];

    for (int i = 0; i < lengthOfPaddedArray; i++) {
      for (int j = 0; j < lengthOfPaddedArray; j++) {
        transformRedArray[i][j] = (Math.abs(transformRedArray[i][j]) <= threshold) ? 0
                : transformRedArray[i][j];
        transformGreenArray[i][j] = (Math.abs(transformGreenArray[i][j]) <= threshold) ? 0
                : transformGreenArray[i][j];
        transformBlueArray[i][j] = (Math.abs(transformBlueArray[i][j]) <= threshold) ? 0
                : transformBlueArray[i][j];
      }
    }
  }

  private void inverseHaarTransform(int lengthOfPaddedArray, double[][] transformRedArray,
                                    double[][] transformGreenArray, double[][] transformBlueArray,
                                    double[][] inverseTransformRedArray,
                                    double[][] inverseTransformGreenArray,
                                    double[][] inverseTransformBlueArray) {
    int tempInverseTransform = 2;

    while (tempInverseTransform <= lengthOfPaddedArray) {
      for (int i = 0; i < lengthOfPaddedArray; i++) {
        for (int j = 0; j < tempInverseTransform; j += 2) {
          inverseTransformRedArray[j][i] = (transformRedArray[(j / 2)][i]
                  + transformRedArray[(j / 2) + (tempInverseTransform / 2)][i]) / Math.sqrt(2);
          inverseTransformGreenArray[j][i] = (transformGreenArray[(j / 2)][i]
                  + transformGreenArray[(j / 2) + (tempInverseTransform / 2)][i]) / Math.sqrt(2);
          inverseTransformBlueArray[j][i] = (transformBlueArray[(j / 2)][i]
                  + transformBlueArray[(j / 2) + (tempInverseTransform / 2)][i]) / Math.sqrt(2);
          inverseTransformRedArray[j + 1][i] = (transformRedArray[(j / 2)][i]
                  - transformRedArray[(j / 2) + (tempInverseTransform / 2)][i]) / Math.sqrt(2);
          inverseTransformGreenArray[j + 1][i] = (transformGreenArray[(j / 2)][i]
                  - transformGreenArray[(j / 2) + (tempInverseTransform / 2)][i]) / Math.sqrt(2);
          inverseTransformBlueArray[j + 1][i] = (transformBlueArray[(j / 2)][i]
                  - transformBlueArray[(j / 2) + (tempInverseTransform / 2)][i]) / Math.sqrt(2);
        }
      }
      for (int i1 = 0; i1 < lengthOfPaddedArray; i1++) {
        for (int j1 = 0; j1 < tempInverseTransform; j1++) {
          transformRedArray[j1][i1] = inverseTransformRedArray[j1][i1];
          transformGreenArray[j1][i1] = inverseTransformGreenArray[j1][i1];
          transformBlueArray[j1][i1] = inverseTransformBlueArray[j1][i1];
        }
      }
      for (int i = 0; i < lengthOfPaddedArray; i++) {
        for (int j = 0; j < tempInverseTransform; j += 2) {
          inverseTransformRedArray[i][j] = (transformRedArray[i][(j / 2)]
                  + transformRedArray[i][(j / 2) + (tempInverseTransform / 2)]) / Math.sqrt(2);
          inverseTransformGreenArray[i][j] = (transformGreenArray[i][(j / 2)]
                  + transformGreenArray[i][(j / 2) + (tempInverseTransform / 2)]) / Math.sqrt(2);
          inverseTransformBlueArray[i][j] = (transformBlueArray[i][(j / 2)]
                  + transformBlueArray[i][(j / 2) + (tempInverseTransform / 2)]) / Math.sqrt(2);
          inverseTransformRedArray[i][j + 1] = (transformRedArray[i][(j / 2)]
                  - transformRedArray[i][(j / 2) + (tempInverseTransform / 2)]) / Math.sqrt(2);
          inverseTransformGreenArray[i][j + 1] = (transformGreenArray[i][(j / 2)]
                  - transformGreenArray[i][(j / 2) + (tempInverseTransform / 2)]) / Math.sqrt(2);
          inverseTransformBlueArray[i][j + 1] = (transformBlueArray[i][(j / 2)]
                  - transformBlueArray[i][(j / 2) + (tempInverseTransform / 2)]) / Math.sqrt(2);
        }
      }
      for (int i1 = 0; i1 < lengthOfPaddedArray; i1++) {
        for (int j1 = 0; j1 < tempInverseTransform; j1++) {
          transformRedArray[i1][j1] = inverseTransformRedArray[i1][j1];
          transformGreenArray[i1][j1] = inverseTransformGreenArray[i1][j1];
          transformBlueArray[i1][j1] = inverseTransformBlueArray[i1][j1];
        }
      }
      tempInverseTransform = tempInverseTransform * 2;
    }
  }

  @Override
  public void imageCompression(String name, String save, double param)
          throws IllegalArgumentException {
    if (param < 0 || param > 100) {
      throw new IllegalArgumentException("Compression percentage should be between 0 and 100");
    }
    int[][][] imageToWorkOn = getHashMap(name);
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;

    double[][] redChannelArray = new double[width][height];
    double[][] greenChannelArray = new double[width][height];
    double[][] blueChannelArray = new double[width][height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        redChannelArray[j][i] = imageToWorkOn[j][i][0];
        greenChannelArray[j][i] = imageToWorkOn[j][i][1];
        blueChannelArray[j][i] = imageToWorkOn[j][i][2];
      }
    }

    int n1 = 0;
    int n2 = 0;
    while (Math.pow(2, n1) < redChannelArray.length) {
      n1++;
    }
    while (Math.pow(2, n2) < redChannelArray[0].length) {
      n2++;
    }
    int n = Math.max(n1, n2);
    int lengthOfPaddedArray = (int) Math.pow(2, n);
    double[][] redpaddedArray = pad2DArray(redChannelArray, n);
    double[][] greenpaddedArray = pad2DArray(greenChannelArray, n);
    double[][] bluepaddedArray = pad2DArray(blueChannelArray, n);


    double[][] transformRedArray = new double[lengthOfPaddedArray][lengthOfPaddedArray];
    double[][] transformGreenArray = new double[lengthOfPaddedArray][lengthOfPaddedArray];
    double[][] transformBlueArray = new double[lengthOfPaddedArray][lengthOfPaddedArray];

    haarTransform(lengthOfPaddedArray, redpaddedArray, greenpaddedArray, bluepaddedArray,
            transformRedArray, transformGreenArray, transformBlueArray);

    threshold(lengthOfPaddedArray, transformRedArray, transformGreenArray,
            transformBlueArray, param);

    double[][] inverseTransformRedArray = new double[lengthOfPaddedArray][lengthOfPaddedArray];
    double[][] inverseTransformGreenArray = new double[lengthOfPaddedArray][lengthOfPaddedArray];
    double[][] inverseTransformBlueArray = new double[lengthOfPaddedArray][lengthOfPaddedArray];

    inverseHaarTransform(lengthOfPaddedArray, transformRedArray, transformGreenArray,
            transformBlueArray, inverseTransformRedArray, inverseTransformGreenArray,
            inverseTransformBlueArray);

    for (int i = 0; i < lengthOfPaddedArray; i++) {
      for (int j = 0; j < lengthOfPaddedArray; j++) {
        transformRedArray[i][j] = (int) Math.min(255,
                Math.max(0, Math.round(transformRedArray[i][j])));
        transformGreenArray[i][j] = (int) Math.min(255,
                Math.max(0, Math.round(transformGreenArray[i][j])));
        transformBlueArray[i][j] = (int) Math.min(255,
                Math.max(0, Math.round(transformBlueArray[i][j])));
      }
    }

    int[][][] compressedImage = new int[width][height][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        compressedImage[j][i] = new int[]{(int) transformRedArray[i][j],
          (int) transformGreenArray[i][j], (int) transformBlueArray[i][j]};
      }
    }

    setHashMap(save, compressedImage);
  }

  @Override
  public void generateAndSaveHistogramImage(String imageName, String destImageName) {
    int[][][] image = getHashMap(imageName);

    int[] redHistogram = generateHistogram(image, 0);
    int[] greenHistogram = generateHistogram(image, 1);
    int[] blueHistogram = generateHistogram(image, 2);

    BufferedImage histogramImage = createHistogramImage(redHistogram,
            greenHistogram, blueHistogram);

    saveHistogramImage(destImageName, histogramImage);
  }

  private int[] generateHistogram(int[][][] image, int channel) {
    int[] histogram = new int[256];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int intensity = image[i][j][channel];
        histogram[intensity]++;
      }
    }
    return histogram;
  }

  private BufferedImage createHistogramImage(int[] redHistogram,
                                             int[] greenHistogram, int[] blueHistogram) {
    int imageSize = 256;
    BufferedImage histogramImage = new BufferedImage(imageSize,
            imageSize, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = histogramImage.createGraphics();

    int max = 0;
    for (int i = 0; i < redHistogram.length; i++) {
      max = Math.max(max, redHistogram[i]);
      max = Math.max(max, greenHistogram[i]);
      max = Math.max(max, blueHistogram[i]);
    }
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, imageSize, imageSize);

    g2d.setColor(Color.RED);
    drawHistogramLine(g2d, redHistogram, max);

    g2d.setColor(Color.GREEN);
    drawHistogramLine(g2d, greenHistogram, max);

    g2d.setColor(Color.BLUE);
    drawHistogramLine(g2d, blueHistogram, max);

    g2d.dispose();

    return histogramImage;
  }

  private void drawHistogramLine(Graphics2D g2d, int[] histogram, int maxHeight) {
    int imageSize = 256;

    for (int i = 0; i < imageSize - 1; i++) {
      int x1 = i;
      int y1 = imageSize - (int) ((double) histogram[i] / maxHeight * imageSize);

      int x2 = i + 1;
      int y2 = imageSize - (int) ((double) histogram[i + 1] / maxHeight * imageSize);

      g2d.drawLine(x1, y1, x2, y2);
    }
  }


  private void saveHistogramImage(String destImageName, BufferedImage histogramImage) {
    int[][][] temp = new int[histogramImage.getWidth()][histogramImage.getHeight()][3];
    for (int i = 0; i < histogramImage.getWidth(); i++) {
      for (int j = 0; j < histogramImage.getHeight(); j++) {
        int rgb = histogramImage.getRGB(i, j);
        temp[i][j][0] = (rgb >> 16) & 0xFF;
        temp[i][j][1] = (rgb >> 8) & 0xFF;
        temp[i][j][2] = rgb & 0xFF;
      }
    }
    setHashMap(destImageName, temp);
  }


  private int[][][] createColorCorrectedImage(int[] redHistogram, int[] greenHistogram,
                                              int[] blueHistogram, int[][][] imageToWorkOn,
                                              double p) {
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;
    int[][][] tempImage = new int[width][height][3];
    int redmax = 0;
    int greenmax = 0;
    int bluemax = 0;
    int redmaxvalue = 0;
    int greenmaxvalue = 0;
    int bluemaxvalue = 0;
    for (int i = 0; i < redHistogram.length; i++) {
      redmax = Math.max(redmax, redHistogram[i]);
      greenmax = Math.max(greenmax, greenHistogram[i]);
      bluemax = Math.max(bluemax, blueHistogram[i]);
      if (redmax == redHistogram[i]) {
        redmaxvalue = i;
      }
      if (greenmax == greenHistogram[i]) {
        greenmaxvalue = i;
      }
      if (bluemax == blueHistogram[i]) {
        bluemaxvalue = i;
      }
    }

    int avg = (greenmaxvalue + redmaxvalue + bluemaxvalue) / 3;

    int redOffset = avg - redmaxvalue;
    int greenOffset = avg - greenmaxvalue;
    int blueOffset = avg - bluemaxvalue;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width * p * 0.01; j++) {

        if (imageToWorkOn[j][i][0] + redOffset < 11) {
          tempImage[j][i][0] = imageToWorkOn[j][i][0];
        } else if (imageToWorkOn[j][i][0] + redOffset > 244) {
          tempImage[j][i][0] = imageToWorkOn[j][i][0];
        } else {
          tempImage[j][i][0] = imageToWorkOn[j][i][0] + redOffset;
        }
        if (imageToWorkOn[j][i][1] + greenOffset < 11) {
          tempImage[j][i][1] = imageToWorkOn[j][i][1];
        } else if (imageToWorkOn[j][i][1] + greenOffset > 244) {
          tempImage[j][i][1] = imageToWorkOn[j][i][1];
        } else {
          tempImage[j][i][1] = imageToWorkOn[j][i][1] + greenOffset;
        }
        if (imageToWorkOn[j][i][2] + blueOffset < 11) {
          tempImage[j][i][2] = imageToWorkOn[j][i][2];
        } else if (imageToWorkOn[j][i][2] + blueOffset > 244) {
          tempImage[j][i][2] = imageToWorkOn[j][i][2];
        } else {
          tempImage[j][i][2] = imageToWorkOn[j][i][2] + blueOffset;
        }
      }

      for (int k = (int) (width * p * 0.01); k < width; k++) {
        tempImage[k][i] = imageToWorkOn[k][i];
      }


    }
    return tempImage;
  }

  @Override
  public void colorCorrect(String imageName, String destImageName, double p) {
    int[][][] image = getHashMap(imageName);

    int[] redHistogram = generateHistogram(image, 0);
    int[] greenHistogram = generateHistogram(image, 1);
    int[] blueHistogram = generateHistogram(image, 2);

    int[][][] temp = createColorCorrectedImage(redHistogram, greenHistogram,
            blueHistogram, image, p);
    setHashMap(destImageName, temp);
  }

  @Override
  public void levelsAdjust(int black, int mid, int white, String name,
                           String save, double p) {
    int[][][] imageToWorkOn = getHashMap(name);
    int[][][] temp = createLevelsAdjustedImage(imageToWorkOn, black, mid, white, p);
    setHashMap(save, temp);
  }

  private int[][][] createLevelsAdjustedImage(int[][][] imageToWorkOn,
                                              int b, int m, int w, double p) {
    int width = imageToWorkOn.length;
    int height = imageToWorkOn[0].length;
    double temp = (b * b) * (m - w) - (b * (m * m - w * w)) + (m * m * w) - (m * w * w);
    double tempA = (-b * (128 - 255)) + (128 * w) - (255 * m);
    double tempB = ((b * b) * (128 - 255)) + (255 * m * m) - (128 * w * w);
    double tempC = ((b * b) * ((255 * m) - (128 * w))) - (b * ((255 * m * m) - (128 * w * w)));
    double tempA1 = tempA / temp;
    double tempB1 = tempB / temp;
    double tempC1 = tempC / temp;

    int[][][] adjustedImage = new int[width][height][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width * p * 0.01; j++) {
        for (int k = 0; k < 3; k++) {
          double term = (tempA1 * (imageToWorkOn[j][i][k] * imageToWorkOn[j][i][k]))
                  + (tempB1 * imageToWorkOn[j][i][k]) + tempC1;
          adjustedImage[j][i][k] = (term < b) ? 0 : (term > w) ? 255 : (int) term;
        }
      }
      for (int k = (int) (width * p * 0.01); k < width; k++) {
        adjustedImage[k][i] = imageToWorkOn[k][i];
      }
    }
    return adjustedImage;
  }

}
