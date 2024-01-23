package model;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStreamReader;

import controller.ImageControllerCLI;

import static org.junit.Assert.assertEquals;


/**
 * Test class for Image Model.
 */
public class ImageModelNewTest {

  private static final String INPUT_IMAGE = "inputImage";
  private static final String COMPRESSED_IMAGE = "compressedImage";
  String path = "res/testFile.png";
  private ImageModelExtendedInterface imageModel;

  @Test(expected = IllegalArgumentException.class)
  public void testImageCompressionWithNegativeParam() {
    imageModel = new ImageModel();
    imageModel.imageCompression(INPUT_IMAGE, COMPRESSED_IMAGE, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageCompressionWithInvalidParam() {
    imageModel = new ImageModel();
    imageModel.imageCompression(INPUT_IMAGE, COMPRESSED_IMAGE, 101);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageCompressionWithInvalidInput() {
    imageModel = new ImageModel();
    imageModel.imageCompression(INPUT_IMAGE, COMPRESSED_IMAGE, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHistogramInvalidInput() {
    imageModel = new ImageModel();
    imageModel.generateAndSaveHistogramImage(INPUT_IMAGE, COMPRESSED_IMAGE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAscendingOrderInputLevelsAdjust() {
    ImageModel imageModel = new ImageModel();
    imageModel.levelsAdjust(150, 100, 200, "inp", "out", 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAscendingOrderInputLevelsAdjust2() {
    ImageModel imageModel = new ImageModel();
    imageModel.levelsAdjust(15, 200, 100, "inp", "out", 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAscendingOrderInputLevelsAdjust3() {
    ImageModel imageModel = new ImageModel();
    imageModel.levelsAdjust(150, 200, 100, "inp", "out", 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalBelowZeroInputLevelsAdjust() {
    ImageModel imageModel = new ImageModel();
    imageModel.levelsAdjust(-1, 100, 200, "inp", "out", 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAbove255oInputLevelsAdjust() {
    ImageModel imageModel = new ImageModel();
    imageModel.levelsAdjust(1, 100, 300, "inp", "out", 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageColorCorrectionWithInvalidInput() {
    imageModel = new ImageModel();
    imageModel.colorCorrect(INPUT_IMAGE, COMPRESSED_IMAGE, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalBlur1() {
    imageModel = new ImageModel();
    imageModel.blurImage(INPUT_IMAGE, COMPRESSED_IMAGE, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalBlur2() {
    imageModel = new ImageModel();
    imageModel.blurImage(INPUT_IMAGE, COMPRESSED_IMAGE, 110);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalSharpen1() {
    imageModel = new ImageModel();
    imageModel.sharpenImage(INPUT_IMAGE, COMPRESSED_IMAGE, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalSharpen2() {
    imageModel = new ImageModel();
    imageModel.sharpenImage(INPUT_IMAGE, COMPRESSED_IMAGE, 110);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalSepia1() {
    imageModel = new ImageModel();
    imageModel.sepiaImage(INPUT_IMAGE, COMPRESSED_IMAGE, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalSepia2() {
    imageModel = new ImageModel();
    imageModel.sepiaImage(INPUT_IMAGE, COMPRESSED_IMAGE, 110);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalValue1() {
    imageModel = new ImageModel();
    imageModel.valueImage(INPUT_IMAGE, COMPRESSED_IMAGE, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalValue2() {
    imageModel = new ImageModel();
    imageModel.valueImage(INPUT_IMAGE, COMPRESSED_IMAGE, 110);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalIntensity1() {
    imageModel = new ImageModel();
    imageModel.intensityImage(INPUT_IMAGE, COMPRESSED_IMAGE, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalIntensity2() {
    imageModel = new ImageModel();
    imageModel.intensityImage(INPUT_IMAGE, COMPRESSED_IMAGE, 110);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalLuma1() {
    imageModel = new ImageModel();
    imageModel.lumaImage(INPUT_IMAGE, COMPRESSED_IMAGE, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalLuma2() {
    imageModel = new ImageModel();
    imageModel.lumaImage(INPUT_IMAGE, COMPRESSED_IMAGE, 110);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalColorCorrection1() {
    imageModel = new ImageModel();
    imageModel.colorCorrect(INPUT_IMAGE, COMPRESSED_IMAGE, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalColorCorrection2() {
    imageModel = new ImageModel();
    imageModel.colorCorrect(INPUT_IMAGE, COMPRESSED_IMAGE, 110);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalLevels1() {
    imageModel = new ImageModel();
    imageModel.levelsAdjust(10, 100, 200, INPUT_IMAGE, COMPRESSED_IMAGE, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidParameterVerticalLevels2() {
    imageModel = new ImageModel();
    imageModel.levelsAdjust(10, 100, 200, INPUT_IMAGE, COMPRESSED_IMAGE, 110);
  }

  @Test
  public void testHistogram() throws Exception {
    ImageModel imageModel = new ImageModel();
    int[][][] img = new int[256][256][3];
    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 256; j++) {
        img[i][j][0] = 0;
        img[i][j][1] = 0;
        img[i][j][2] = 0;
      }
    }
    imageModel.setHashMap("input", img);
    imageModel.generateAndSaveHistogramImage("input", "inputHistogram");
    int[][][] actual = imageModel.getHashMap("inputHistogram");
    int[][][] expected = new int[256][256][3];


    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 256; j++) {
        if (i == 0 && j < 128) {
          expected[i][j] = new int[]{0, 0, 255};
        } else {
          expected[i][j] = new int[]{255, 255, 255};
        }
        if (i == 1 && j > 127) {
          expected[i][j] = new int[]{0, 0, 255};
        }
      }
    }
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void testCompression0() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.imageCompression("name", "save", 0);
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
  public void testCompression100() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.imageCompression("name", "save", 100);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
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
  public void testCompression90() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.imageCompression("name", "save", 90);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{0, 0, 0}, {0, 96, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 96, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 96, 0}, {0, 0, 0}}
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
  public void testCompression80() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.imageCompression("name", "save", 80);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{48, 0, 48}, {48, 191, 48}, {48, 0, 48}},
            {{48, 0, 48}, {48, 191, 48}, {48, 0, 48}},
            {{48, 0, 48}, {48, 191, 48}, {48, 0, 48}}
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
  public void testBlurPartial() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.blurImage("name", "save", 60);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{63, 46, 31}, {46, 94, 46}, {31, 46, 63}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void testSharpenPartial() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.sharpenImage("name", "save", 60);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{63, 255, 63}, {224, 126, 32}, {63, 0, 63}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void testSepiaPartial() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.sepiaImage("name", "save", 60);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{100, 88, 69}, {196, 174, 136}, {48, 42, 33}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void testValuePartial() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.valueImage("name", "save", 60);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void testIntensityPartial() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.intensityImage("name", "save", 60);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{85, 85, 85}, {85, 85, 85}, {85, 85, 85}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void testLumaPartial() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.lumaImage("name", "save", 60);
    int[][][] actual = model.getHashMap("save");
    int[][][] expected = {
            {{54, 54, 54}, {182, 182, 182}, {18, 18, 18}},
            {{0, 0, 255}, {0, 255, 0}, {255, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}}
    };
    Assert.assertArrayEquals(expected, actual);
  }


  @Test
  public void testLevelsAdjust() throws Exception {
    ImageModel imageModel = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(imageModel,
            new InputStreamReader(System.in), System.out);
    int[][][] image = {
            {{80, 100, 220}, {110, 255, 120}, {110, 123, 155}},
            {{120, 230, 155}, {150, 255, 230}, {155, 230, 10}},
            {{225, 130, 120}, {60, 255, 40}, {240, 20, 255}}
    };
    imageModel.setHashMap("levelsAdjustImage", image);
    imageModel.levelsAdjust(10, 100, 200, "levelsAdjustImage",
            "levelsAdjust", 100);
    int[][][] actual = imageModel.getHashMap("levelsAdjust");
    int[][][] expected = {
            {{100, 128, 255}, {141, 255, 154}, {141, 158, 199}},
            {{154, 255, 199}, {193, 255, 255}, {199, 255, 0}},
            {{255, 167, 154}, {72, 255, 44}, {255, 14, 255}}
    };
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void testLevelsAdjustSplit() throws Exception {
    ImageModel imageModel = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(imageModel,
            new InputStreamReader(System.in), System.out);
    int[][][] image = {
            {{80, 100, 220}, {110, 255, 120}, {110, 123, 155}},
            {{120, 230, 155}, {150, 255, 230}, {155, 230, 10}},
            {{225, 130, 120}, {60, 255, 40}, {240, 20, 255}}
    };
    imageModel.setHashMap("levelsAdjustImage", image);
    imageModel.levelsAdjust(10, 100, 200,
            "levelsAdjustImage", "levelsAdjust", 50);
    int[][][] actual = imageModel.getHashMap("levelsAdjust");
    int[][][] expected = {
            {{100, 128, 255}, {141, 255, 154}, {141, 158, 199}},
            {{120, 230, 155}, {150, 255, 230}, {155, 230, 10}},
            {{225, 130, 120}, {60, 255, 40}, {240, 20, 255}}
    };
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void testLevelsAdjustMultiple() throws Exception {
    ImageModel imageModel = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(imageModel,
            new InputStreamReader(System.in), System.out);
    int[][][] image = {
            {{80, 100, 220}, {110, 255, 120}, {110, 123, 155}},
            {{120, 230, 155}, {150, 255, 230}, {155, 230, 10}},
            {{225, 130, 120}, {60, 255, 40}, {240, 20, 255}}
    };
    imageModel.setHashMap("levelsAdjustImage", image);
    imageModel.levelsAdjust(10, 100, 200,
            "levelsAdjustImage", "levelsAdjust1", 50);
    imageModel.levelsAdjust(10, 100, 200,
            "levelsAdjust1", "levelsAdjust", 0);

    int[][][] actual = imageModel.getHashMap("levelsAdjust");
    int[][][] expected = {
            {{100, 128, 255}, {141, 255, 154}, {141, 158, 199}},
            {{120, 230, 155}, {150, 255, 230}, {155, 230, 10}},
            {{225, 130, 120}, {60, 255, 40}, {240, 20, 255}}
    };
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void testColorCorrection() {
    ImageModel imageModel = new ImageModel();
    int[][][] image = {
            {{80, 100, 220}, {110, 255, 120}, {110, 123, 155}},
            {{120, 230, 155}, {150, 255, 230}, {155, 230, 10}},
            {{225, 130, 120}, {60, 255, 40}, {220, 20, 255}}
    };
    imageModel.setHashMap("input", image);
    imageModel.colorCorrect("input", "colorCorrect", 100);
    int[][][] actual = {
            {{143, 18, 238}, {173, 173, 138}, {173, 41, 173}},
            {{183, 148, 173}, {213, 173, 230}, {218, 148, 28}},
            {{225, 48, 138}, {123, 173, 58}, {220, 20, 255}}
    };
    Assert.assertArrayEquals(imageModel.getHashMap("colorCorrect"), actual);
  }

  @Test
  public void testColorCorrectionSplit() {
    ImageModel imageModel = new ImageModel();
    int[][][] image = {
            {{80, 100, 220}, {110, 255, 120}, {110, 123, 155}},
            {{120, 230, 155}, {150, 255, 230}, {155, 230, 10}},
            {{225, 130, 120}, {60, 255, 40}, {220, 20, 255}}
    };
    imageModel.setHashMap("input", image);
    imageModel.colorCorrect("input", "colorCorrect", 50);
    int[][][] actual = {
            {{143, 18, 238}, {173, 173, 138}, {173, 41, 173}},
            {{120, 230, 155}, {150, 255, 230}, {155, 230, 10}},
            {{225, 130, 120}, {60, 255, 40}, {220, 20, 255}}
    };
    Assert.assertArrayEquals(imageModel.getHashMap("colorCorrect"), actual);
  }

  @Test
  public void noChangesToImage0Split() {
    ImageModel imageModel = new ImageModel();
    int[][][] image = {
            {{80, 100, 220}, {110, 255, 120}, {110, 123, 155}},
            {{120, 230, 155}, {150, 255, 230}, {155, 230, 10}},
            {{225, 130, 120}, {60, 255, 40}, {220, 20, 255}}
    };
    imageModel.setHashMap("input", image);
    imageModel.colorCorrect("input", "colorCorrect", 0);
    imageModel.levelsAdjust(20, 100, 200,
            "colorCorrect", "levelsAdjust", 0);
    int[][][] actual = {
            {{80, 100, 220}, {110, 255, 120}, {110, 123, 155}},
            {{120, 230, 155}, {150, 255, 230}, {155, 230, 10}},
            {{225, 130, 120}, {60, 255, 40}, {220, 20, 255}}
    };
    Assert.assertArrayEquals(imageModel.getHashMap("colorCorrect"), actual);
  }

  @Test
  public void testMultiple() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.imageCompression("name", "save", 90);
    model.colorCorrect("save", "save2", 0);
    int[][][] actual = model.getHashMap("save2");
    int[][][] expected = {
            {{0, 0, 0}, {0, 96, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 96, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 96, 0}, {0, 0, 0}}
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
  public void testMultiple2() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.imageCompression("name", "save", 90);
    model.colorCorrect("save", "save2", 100);
    int[][][] actual = model.getHashMap("save2");
    int[][][] expected = {
            {{0, 0, 0}, {0, 96, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 96, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 96, 0}, {0, 0, 0}}
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
  public void testMultiple3() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.imageCompression("name", "save", 90);
    model.colorCorrect("save", "save2", 50);
    int[][][] actual = model.getHashMap("save2");
    int[][][] expected = {
            {{0, 0, 0}, {0, 96, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 96, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 96, 0}, {0, 0, 0}}
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
  public void testMultipleCompress() throws Exception {
    ImageModel model = new ImageModel();

    ImageControllerCLI controller = new ImageControllerCLI(model,
            new InputStreamReader(System.in), System.out);
    controller.loadImage(path, "name");
    model.imageCompression("name", "save", 90);
    model.imageCompression("save", "save2", 90);
    int[][][] actual = model.getHashMap("save2");
    int[][][] expected = {
            {{0, 0, 0}, {0, 36, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 36, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 36, 0}, {0, 0, 0}}
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
  public void testOverwrite() {
    ImageModel imageModel = new ImageModel();
    int[][][] image = {
            {{80, 100, 220}, {110, 255, 120}, {110, 123, 155}},
            {{120, 230, 155}, {150, 255, 230}, {155, 230, 10}},
            {{225, 130, 120}, {60, 255, 40}, {220, 20, 255}}
    };
    imageModel.setHashMap("input", image);
    imageModel.imageCompression("input", "colorCorrect", 90);
    imageModel.colorCorrect("input", "colorCorrect", 50);
    int[][][] actual = {
            {{143, 18, 238}, {173, 173, 138}, {173, 41, 173}},
            {{120, 230, 155}, {150, 255, 230}, {155, 230, 10}},
            {{225, 130, 120}, {60, 255, 40}, {220, 20, 255}}
    };
    Assert.assertArrayEquals(imageModel.getHashMap("colorCorrect"), actual);
  }

  @Test
  public void testColorMultiple() {
    ImageModel imageModel = new ImageModel();
    int[][][] image = {
            {{80, 100, 220}, {110, 255, 120}, {110, 123, 155}},
            {{120, 230, 155}, {150, 255, 230}, {155, 230, 10}},
            {{225, 130, 120}, {60, 255, 40}, {220, 20, 255}}
    };
    imageModel.setHashMap("input", image);
    imageModel.colorCorrect("input", "colorCorrect1", 90);
    imageModel.colorCorrect("colorCorrect1", "colorCorrect", 50);
    int[][][] actual = {
            {{143, 18, 238}, {173, 173, 138}, {173, 41, 173}},
            {{183, 148, 173}, {213, 173, 230}, {218, 148, 28}},
            {{225, 130, 120}, {60, 255, 40}, {220, 20, 255}}
    };
    Assert.assertArrayEquals(imageModel.getHashMap("colorCorrect"), actual);
  }
}