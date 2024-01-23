package model;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStreamReader;

import javax.imageio.IIOException;

import controller.ImageControllerCLI;

import static org.junit.Assert.assertEquals;


/**
 * Test class for Image Model when it used ImageModelInterface.
 */
public class ImageModelTest {

  String path = "res/testFile.png";
  String path2 = "res/testFile.png";
  String path3 = "res/testFile.ppm";
  String name = "landscape";

  @Test(expected = IllegalArgumentException.class)
  public void testRedImageImageNotFound() {
    ImageModel imageModel = new ImageModel();
    imageModel.redImage("non_existent_image", "redImage");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreenImageImageNotFound() {
    ImageModel imageModel = new ImageModel();
    imageModel.greenImage("non_existent_image", "greenImage");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlueImageImageNotFound() {
    ImageModel imageModel = new ImageModel();
    imageModel.blueImage("non_existent_image", "blueImage");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueImageImageNotFound() {
    ImageModel imageModel = new ImageModel();
    imageModel.valueImage("non_existent_image", "valueImage", 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIntensityImageImageNotFound() {
    ImageModel imageModel = new ImageModel();
    imageModel.intensityImage("non_existent_image", "intensityImage", 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLumaImageImageNotFound() {
    ImageModel imageModel = new ImageModel();
    imageModel.lumaImage("non_existent_image", "lumaImage", 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipHorizontalImageImageNotFound() {
    ImageModel imageModel = new ImageModel();
    imageModel.flipHorizontalImage("non_existent_image", "flipHorizontalImage");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipVerticalImageImageNotFound() {
    ImageModel imageModel = new ImageModel();
    imageModel.flipVerticalImage("non_existent_image", "flipVerticalImage");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBrightenImageImageNotFound() {
    ImageModel imageModel = new ImageModel();
    imageModel.brightenImage("non_existent_image", "brightenImage", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRgbSplitImageNotFound() {
    ImageModel imageModel = new ImageModel();
    imageModel.rgbSplit("non_existent_image", "redSplit", "greenSplit",
            "blueSplit");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRgbCombineImageNotFound() {
    ImageModel imageModel = new ImageModel();
    imageModel.rgbCombine("combinedImage", "non_existent_image1",
            "non_existent_image2", "non_existent_image3");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSepiaImageImageNotFound() {
    ImageModel imageModel = new ImageModel();
    imageModel.sepiaImage("non_existent_image", "sepiaImage", 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlurImageImageNotFound() {
    ImageModel imageModel = new ImageModel();
    imageModel.blurImage("non_existent_image", "blurImage", 100);
  }

  @Test(expected = IIOException.class)
  public void testSharpenImageImageNotFound() throws Exception {
    ImageModel imageModel = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(imageModel,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("/res", "non");
    imageModel.sharpenImage("non_existent_image", "sharpenImage", 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWithoutLoadImage() {
    ImageModel imageModel = new ImageModel();
    imageModel.sharpenImage("input", "sharpenImage", 100);
  }

  @Test
  public void getWidthpng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    int actual = model.getWidth(name);

    assertEquals(actual, 3);
  }

  @Test
  public void getHeightpng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    int actual = model.getHeight(name);

    assertEquals(actual, 3);
  }

  @Test
  public void getHashMappng() throws Exception {
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
  public void redImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.redImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}}
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
  public void greenImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.greenImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}}
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
  public void blueImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.blueImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}}
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
  public void valueImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.valueImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}}
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
  public void intensityImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.intensityImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{85, 85, 85}, {85, 85, 85}, {85, 85, 85}},
            {{85, 85, 85}, {85, 85, 85}, {85, 85, 85}},
            {{85, 85, 85}, {85, 85, 85}, {85, 85, 85}}
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
  public void lumaImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.lumaImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{54, 54, 54}, {182, 182, 182}, {18, 18, 18}},
            {{18, 18, 18}, {182, 182, 182}, {54, 54, 54}},
            {{54, 54, 54}, {182, 182, 182}, {18, 18, 18}}
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
  public void flipHorizontalImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.flipHorizontalImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j][0], actual[i][j][0]);
        assertEquals(expected[i][j][1], actual[i][j][1]);
        assertEquals(expected[i][j][2], actual[i][j][2]);
      }
    }
  }

  @Test
  public void flipVerticalImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.flipVerticalImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j][0], actual[i][j][0]);
        assertEquals(expected[i][j][1], actual[i][j][1]);
        assertEquals(expected[i][j][2], actual[i][j][2]);
      }
    }
  }

  @Test
  public void brightenImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.brightenImage(name, "save", 10);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 10, 10}, {10, 255, 10}, {10, 10, 255}},
            {{10, 10, 255}, {10, 255, 10}, {255, 10, 10}},
            {{255, 10, 10}, {10, 255, 10}, {10, 10, 255}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j][0], actual[i][j][0]);
        assertEquals(expected[i][j][1], actual[i][j][1]);
        assertEquals(expected[i][j][2], actual[i][j][2]);
      }
    }
  }

  @Test
  public void darkenImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.brightenImage(name, "save", -10);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{245, 0, 0}, {0, 245, 0}, {0, 0, 245}},
            {{0, 0, 245}, {0, 245, 0}, {245, 0, 0}},
            {{245, 0, 0}, {0, 245, 0}, {0, 0, 245}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j][0], actual[i][j][0]);
        assertEquals(expected[i][j][1], actual[i][j][1]);
        assertEquals(expected[i][j][2], actual[i][j][2]);
      }
    }
  }

  @Test
  public void rgbSplitpng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.rgbSplit(name, "save1", "save2", "save3");
    int[][][] actual1 = model.getHashMap("save1");
    int[][][] actual2 = model.getHashMap("save2");
    int[][][] actual3 = model.getHashMap("save3");
    int[][][] expected1 = {
            {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}}
    };
    int[][][] expected2 = {
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}}
    };
    int[][][] expected3 = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}}
    };
    for (int i = 0; i < expected1.length; i++) {
      for (int j = 0; j < expected1[0].length; j++) {
        Assert.assertArrayEquals(expected1[j][i], actual1[j][i]);
        Assert.assertArrayEquals(expected2[j][i], actual2[j][i]);
        Assert.assertArrayEquals(expected3[j][i], actual3[j][i]);
      }
    }
  }

  @Test
  public void rgbCombinepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("res/red.png", "red");
    controller.loadImage("res/green.png", "green");
    controller.loadImage("res/blue.png", "blue");
    model.rgbCombine("save", "red", "green", "blue");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        Assert.assertArrayEquals(expected[j][i], actual[j][i]);

      }
    }

  }

  @Test
  public void sepiaImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.sepiaImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{100, 88, 69}, {196, 174, 136}, {48, 42, 33}},
            {{48, 42, 33}, {196, 174, 136}, {100, 88, 69}},
            {{100, 88, 69}, {196, 174, 136}, {48, 42, 33}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        Assert.assertArrayEquals(expected[j][i], actual[j][i]);
      }
    }
  }

  @Test
  public void blurImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.blurImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{63, 46, 31}, {46, 94, 46}, {31, 46, 63}},
            {{62, 61, 63}, {61, 125, 61}, {63, 61, 62}},
            {{63, 46, 31}, {46, 94, 46}, {31, 46, 63}}
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
  public void sharpenImagepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.sharpenImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{63, 255, 63}, {224, 126, 32}, {63, 0, 63}},
            {{32, 255, 63}, {1, 95, 193}, {63, 0, 32}},
            {{63, 32, 0}, {0, 32, 32}, {0, 0, 63}}
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
  public void getWidthjpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    int actual = model.getWidth(name);

    assertEquals(actual, 3);
  }

  @Test
  public void getHeightjpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    int actual = model.getHeight(name);

    assertEquals(actual, 3);
  }

  @Test
  public void getHashMapjpg() throws Exception {
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
  public void redImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.redImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}}
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
  public void greenImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.greenImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}}
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
  public void blueImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.blueImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}}
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
  public void valueImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.valueImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}}
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
  public void intensityImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.intensityImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{85, 85, 85}, {85, 85, 85}, {85, 85, 85}},
            {{85, 85, 85}, {85, 85, 85}, {85, 85, 85}},
            {{85, 85, 85}, {85, 85, 85}, {85, 85, 85}}
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
  public void lumaImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.lumaImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{54, 54, 54}, {182, 182, 182}, {18, 18, 18}},
            {{18, 18, 18}, {182, 182, 182}, {54, 54, 54}},
            {{54, 54, 54}, {182, 182, 182}, {18, 18, 18}}
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
  public void flipHorizontalImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.flipHorizontalImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j][0], actual[i][j][0]);
        assertEquals(expected[i][j][1], actual[i][j][1]);
        assertEquals(expected[i][j][2], actual[i][j][2]);
      }
    }
  }

  @Test
  public void flipVerticalImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.flipVerticalImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j][0], actual[i][j][0]);
        assertEquals(expected[i][j][1], actual[i][j][1]);
        assertEquals(expected[i][j][2], actual[i][j][2]);
      }
    }
  }

  @Test
  public void brightenImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.brightenImage(name, "save", 10);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 10, 10}, {10, 255, 10}, {10, 10, 255}},
            {{10, 10, 255}, {10, 255, 10}, {255, 10, 10}},
            {{255, 10, 10}, {10, 255, 10}, {10, 10, 255}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j][0], actual[i][j][0]);
        assertEquals(expected[i][j][1], actual[i][j][1]);
        assertEquals(expected[i][j][2], actual[i][j][2]);
      }
    }
  }

  @Test
  public void darkenImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.brightenImage(name, "save", -10);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{245, 0, 0}, {0, 245, 0}, {0, 0, 245}},
            {{0, 0, 245}, {0, 245, 0}, {245, 0, 0}},
            {{245, 0, 0}, {0, 245, 0}, {0, 0, 245}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j][0], actual[i][j][0]);
        assertEquals(expected[i][j][1], actual[i][j][1]);
        assertEquals(expected[i][j][2], actual[i][j][2]);
      }
    }
  }

  @Test
  public void rgbSplitjpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.rgbSplit(name, "save1", "save2", "save3");
    int[][][] actual1 = model.getHashMap("save1");
    int[][][] actual2 = model.getHashMap("save2");
    int[][][] actual3 = model.getHashMap("save3");
    int[][][] expected1 = {
            {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}}
    };
    int[][][] expected2 = {
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}}
    };
    int[][][] expected3 = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}}
    };
    for (int i = 0; i < expected1.length; i++) {
      for (int j = 0; j < expected1[0].length; j++) {
        Assert.assertArrayEquals(expected1[j][i], actual1[j][i]);
        Assert.assertArrayEquals(expected2[j][i], actual2[j][i]);
        Assert.assertArrayEquals(expected3[j][i], actual3[j][i]);
      }
    }
  }

  @Test
  public void rgbCombinejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("res/red.png", "red");
    controller.loadImage("res/green.png", "green");
    controller.loadImage("res/blue.png", "blue");
    model.rgbCombine("save", "red", "green", "blue");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        Assert.assertArrayEquals(expected[j][i], actual[j][i]);

      }
    }

  }

  @Test
  public void sepiaImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.sepiaImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{100, 88, 69}, {196, 174, 136}, {48, 42, 33}},
            {{48, 42, 33}, {196, 174, 136}, {100, 88, 69}},
            {{100, 88, 69}, {196, 174, 136}, {48, 42, 33}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        Assert.assertArrayEquals(expected[j][i], actual[j][i]);
      }
    }
  }

  @Test
  public void blurImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.blurImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{63, 46, 31}, {46, 94, 46}, {31, 46, 63}},
            {{62, 61, 63}, {61, 125, 61}, {63, 61, 62}},
            {{63, 46, 31}, {46, 94, 46}, {31, 46, 63}}
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
  public void sharpenImagejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path2, name);
    model.sharpenImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{63, 255, 63}, {224, 126, 32}, {63, 0, 63}},
            {{32, 255, 63}, {1, 95, 193}, {63, 0, 32}},
            {{63, 32, 0}, {0, 32, 32}, {0, 0, 63}}
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
  public void getWidthppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    int actual = model.getWidth(name);

    assertEquals(actual, 3);
  }

  @Test
  public void getHeightppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    int actual = model.getHeight(name);

    assertEquals(actual, 3);
  }

  @Test
  public void getHashMapppm() throws Exception {
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
  public void redImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.redImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}}
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
  public void greenImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.greenImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}}
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
  public void blueImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.blueImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}}
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
  public void valueImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.valueImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}}
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
  public void intensityImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.intensityImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{85, 85, 85}, {85, 85, 85}, {85, 85, 85}},
            {{85, 85, 85}, {85, 85, 85}, {85, 85, 85}},
            {{85, 85, 85}, {85, 85, 85}, {85, 85, 85}}
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
  public void lumaImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.lumaImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{54, 54, 54}, {182, 182, 182}, {18, 18, 18}},
            {{18, 18, 18}, {182, 182, 182}, {54, 54, 54}},
            {{54, 54, 54}, {182, 182, 182}, {18, 18, 18}}
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
  public void flipHorizontalImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.flipHorizontalImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j][0], actual[i][j][0]);
        assertEquals(expected[i][j][1], actual[i][j][1]);
        assertEquals(expected[i][j][2], actual[i][j][2]);
      }
    }
  }

  @Test
  public void flipVerticalImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.flipVerticalImage(name, "save");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j][0], actual[i][j][0]);
        assertEquals(expected[i][j][1], actual[i][j][1]);
        assertEquals(expected[i][j][2], actual[i][j][2]);
      }
    }
  }

  @Test
  public void brightenImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.brightenImage(name, "save", 10);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 10, 10}, {10, 255, 10}, {10, 10, 255}},
            {{10, 10, 255}, {10, 255, 10}, {255, 10, 10}},
            {{255, 10, 10}, {10, 255, 10}, {10, 10, 255}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j][0], actual[i][j][0]);
        assertEquals(expected[i][j][1], actual[i][j][1]);
        assertEquals(expected[i][j][2], actual[i][j][2]);
      }
    }
  }

  @Test
  public void darkenImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.brightenImage(name, "save", -10);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{245, 0, 0}, {0, 245, 0}, {0, 0, 245}},
            {{0, 0, 245}, {0, 245, 0}, {245, 0, 0}},
            {{245, 0, 0}, {0, 245, 0}, {0, 0, 245}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j][0], actual[i][j][0]);
        assertEquals(expected[i][j][1], actual[i][j][1]);
        assertEquals(expected[i][j][2], actual[i][j][2]);
      }
    }
  }

  @Test
  public void rgbSplitppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.rgbSplit(name, "save1", "save2", "save3");
    int[][][] actual1 = model.getHashMap("save1");
    int[][][] actual2 = model.getHashMap("save2");
    int[][][] actual3 = model.getHashMap("save3");
    int[][][] expected1 = {
            {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 0, 0}, {0, 0, 0}}
    };
    int[][][] expected2 = {
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 255, 0}, {0, 0, 0}}
    };
    int[][][] expected3 = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 255}}
    };
    for (int i = 0; i < expected1.length; i++) {
      for (int j = 0; j < expected1[0].length; j++) {
        Assert.assertArrayEquals(expected1[j][i], actual1[j][i]);
        Assert.assertArrayEquals(expected2[j][i], actual2[j][i]);
        Assert.assertArrayEquals(expected3[j][i], actual3[j][i]);
      }
    }
  }

  @Test
  public void rgbCombineppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("res/red.png", "red");
    controller.loadImage("res/green.png", "green");
    controller.loadImage("res/blue.png", "blue");
    model.rgbCombine("save", "red", "green", "blue");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        Assert.assertArrayEquals(expected[j][i], actual[j][i]);

      }
    }

  }

  @Test
  public void sepiaImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.sepiaImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{100, 88, 69}, {196, 174, 136}, {48, 42, 33}},
            {{48, 42, 33}, {196, 174, 136}, {100, 88, 69}},
            {{100, 88, 69}, {196, 174, 136}, {48, 42, 33}}
    };
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        Assert.assertArrayEquals(expected[j][i], actual[j][i]);
      }
    }
  }

  @Test
  public void blurImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.blurImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{63, 46, 31}, {46, 94, 46}, {31, 46, 63}},
            {{62, 61, 63}, {61, 125, 61}, {63, 61, 62}},
            {{63, 46, 31}, {46, 94, 46}, {31, 46, 63}}
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
  public void sharpenImageppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path3, name);
    model.sharpenImage(name, "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{63, 255, 63}, {224, 126, 32}, {63, 0, 63}},
            {{32, 255, 63}, {1, 95, 193}, {63, 0, 32}},
            {{63, 32, 0}, {0, 32, 32}, {0, 0, 63}}
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
  public void flipHorizontalTwicepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.flipHorizontalImage(name, "save");
    model.flipHorizontalImage("save", "save2");
    int[][][] actual = model.getHashMap("save2");
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
  public void flipVerticalTwicepng() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.flipVerticalImage(name, "save");
    model.flipVerticalImage("save", "save2");
    int[][][] actual = model.getHashMap("save2");
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
  public void flipHorizontalTwicejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.flipHorizontalImage(name, "save");
    model.flipHorizontalImage("save", "save2");
    int[][][] actual = model.getHashMap("save2");
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
  public void flipVerticalTwicejpg() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.flipVerticalImage(name, "save");
    model.flipVerticalImage("save", "save2");
    int[][][] actual = model.getHashMap("save2");
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
  public void flipHorizontalTwiceppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.flipHorizontalImage(name, "save");
    model.flipHorizontalImage("save", "save2");
    int[][][] actual = model.getHashMap("save2");
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
  public void flipVerticalTwiceppm() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.flipVerticalImage(name, "save");
    model.flipVerticalImage("save", "save2");
    int[][][] actual = model.getHashMap("save2");
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
  public void rgbcombineallforms() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("res/red.png", "red_png");
    controller.loadImage("res/blue.png", "blue_ppm");
    controller.loadImage("res/green.png", "green_jpg");
    model.rgbCombine("save", "red_png", "green_jpg", "blue_ppm");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    int width = model.getWidth("save");
    int height = model.getHeight("save");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Assert.assertArrayEquals(actual[j][i], expected[j][i]);
      }
    }
  }

  @Test
  public void rgbSplitAndCombine() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("res/testFile.png", "testFile");
    model.rgbSplit("testFile", "red", "green", "blue");
    model.rgbCombine("save", "red", "green", "blue");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    int width = model.getWidth("save");
    int height = model.getHeight("save");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Assert.assertArrayEquals(actual[j][i], expected[j][i]);
      }
    }
  }

  @Test
  public void brightenAndDarken() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("res/testFile.png", "testFile");
    model.rgbSplit("testFile", "red", "green", "blue");
    model.rgbCombine("save", "red", "green", "blue");
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    int width = model.getWidth("save");
    int height = model.getHeight("save");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Assert.assertArrayEquals(actual[j][i], expected[j][i]);
      }
    }
  }

  @Test
  public void multipleTestingLoadBrightenSepia() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.brightenImage(name, "save", 20);
    model.sepiaImage("save", "save2", 100);
    int[][][] actual = model.getHashMap("save2");
    int[][][] expected = {
            {{119, 106, 82}, {207, 185, 144}, {71, 63, 49}},
            {{71, 63, 49}, {207, 185, 144}, {119, 106, 82}},
            {{119, 106, 82}, {207, 185, 144}, {71, 63, 49}}
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
  public void multipleTestingLoadSharpenHorizontalFlip() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.sharpenImage(name, "save", 100);
    model.flipHorizontalImage("save", "save2");
    int[][][] actual = model.getHashMap("save2");
    int[][][] expected = {
            {{63, 32, 0}, {0, 32, 32}, {0, 0, 63}},
            {{32, 255, 63}, {1, 95, 193}, {63, 0, 32}},
            {{63, 255, 63}, {224, 126, 32}, {63, 0, 63}}
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
  public void multipleTestingLoadBlurVerticalFlip() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.blurImage(name, "save", 100);
    model.flipVerticalImage("save", "save2");
    int[][][] actual = model.getHashMap("save2");
    int[][][] expected = {
            {{31, 46, 63}, {46, 94, 46}, {63, 46, 31}},
            {{63, 61, 62}, {61, 125, 61}, {62, 61, 63}},
            {{31, 46, 63}, {46, 94, 46}, {63, 46, 31}}
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
  public void multipleImages() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    controller.loadImage(path2, "name1");
    controller.loadImage(path3, "name2");
    model.blurImage("name", "save", 100);
    model.sepiaImage("name1", "save2", 100);
    model.sharpenImage("name2", "save3", 100);
    model.flipVerticalImage("save", "save2");
    int[][][] actual = model.getHashMap("save");
    int[][][] actual2 = model.getHashMap("save2");
    int[][][] actual3 = model.getHashMap("save3");
    int[][][] expected = {
            {{63, 46, 31}, {62, 61, 63}, {63, 46, 31}},
            {{46, 94, 46}, {61, 125, 61}, {46, 94, 46}},
            {{31, 46, 63}, {63, 61, 62}, {31, 46, 63}}
    };
    int[][][] expected2 = {
            {{31, 46, 63}, {63, 61, 62}, {31, 46, 63}},
            {{46, 94, 46}, {61, 125, 61}, {46, 94, 46}},
            {{63, 46, 31}, {62, 61, 63}, {63, 46, 31}}
    };
    int[][][] expected3 = {
            {{63, 255, 63}, {32, 255, 63}, {63, 32, 0}},
            {{224, 126, 32}, {1, 95, 193}, {0, 32, 32}},
            {{63, 0, 63}, {63, 0, 32}, {0, 0, 63}}
    };
    int width = model.getWidth("name");
    int height = model.getHeight("name");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Assert.assertArrayEquals(actual[j][i], expected[i][j]);
        Assert.assertArrayEquals(actual2[j][i], expected2[i][j]);
        Assert.assertArrayEquals(actual3[j][i], expected3[i][j]);
      }
    }
  }

  @Test
  public void flipHorizontalVertical() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, name);
    model.flipHorizontalImage(name, "save");
    model.flipVerticalImage("save", "save2");
    int[][][] actual = model.getHashMap("save2");
    int[][][] expected = {
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}}
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
  public void overWriteImage() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("res/red.png", "name");
    controller.loadImage("res/blue.png", "name");
    int[][][] actual = model.getHashMap("name");
    int[][][] expected = {
            {{0, 0, 0}, {0, 0, 255}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 255}, {0, 0, 0}, {0, 0, 255}}
    };
    int width = model.getWidth("name");
    int height = model.getHeight("name");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Assert.assertArrayEquals(actual[j][i], expected[i][j]);
      }
    }
  }

  @Test
  public void brightenMax() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("res/red.png", "name");
    model.brightenImage("name", "save", 255);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}}
    };
    int width = model.getWidth("save");
    int height = model.getHeight("save");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Assert.assertArrayEquals(actual[j][i], expected[i][j]);
      }
    }
  }

  @Test
  public void darkenMax() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("res/red.png", "name");
    model.brightenImage("name", "save", -255);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
    };
    int width = model.getWidth("save");
    int height = model.getHeight("save");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Assert.assertArrayEquals(actual[j][i], expected[i][j]);
      }
    }
  }

  @Test
  public void saveOverwrite() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage("res/red.png", "name");
    model.brightenImage("name", "save", -255);
    controller.createImage("res/testing/gets_overwritten.png", "save", "jpg");
    model.brightenImage("name", "save", 255);
    controller.createImage("res/testing/gets_overwritten.png", "save", "jpg");
    controller.loadImage("res/testing/gets_overwritten.png", "new");
    int[][][] actual = model.getHashMap("new");
    int[][][] expected = {
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}}
    };
    int width = model.getWidth("save");
    int height = model.getHeight("save");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Assert.assertArrayEquals(actual[j][i], expected[i][j]);
      }
    }
  }


}