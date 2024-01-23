package view;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;


import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.GUIController;
import model.ImageModel;

/**
 * The ImageView class displays images, buttons to perform functions on the said image and handles
 * user interactions. It implements the ImageViewInterface. It creates a JFrame to display the
 * image and buttons.
 */
public class ImageView implements ImageViewInterface {
  public final Appendable out;
  private final JFrame frame;
  private final JPanel imagePanel;
  private final JLabel[] imageLabels;
  private final GUIController GUIcontroller;
  private final int[] param_levels = {0, 128, 255};
  public Readable in;
  private int param_compress;
  private int splitParam = 100;
  private boolean isNotSaved = false;
  private String stringInput = "";
  private boolean correctSplit = true;

  private boolean imageLoaded = false;
  private String splitCommand = "";

  /**
   * Constructs an ImageView object with the given Readable and Appendable objects.
   *
   * @param in  The Readable object to be used for input.
   * @param out The Appendable object to be used for output.
   */
  public ImageView(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
    this.frame = new JFrame("Image View");
    this.GUIcontroller = new GUIController(new ImageModel());

    GUIcontroller.setView(this);
    JPanel buttonsPanel = new JPanel(new GridLayout(2, 9));
    JButton[] buttons = new JButton[17];
    JTextField textField = new JTextField(15);
    for (int i = 0; i < 17; i++) {
      buttons[i] = new JButton();
      buttonsPanel.add(buttons[i]);
      final int buttonIndex = i;

      buttons[i].addActionListener(e -> {
        try {
          String input = handleButtonClick(buttonIndex);
          GUIcontroller.handleClick(input);
        } catch (Exception ex) {
          throw new RuntimeException(ex);
        }
      });


      if (i == 12) {
        buttons[i].addActionListener(e -> showPopupTextFieldForCompress());
      }

    }
    for (int j = 0; j < 17; j++) {
      if (j == 4 || j == 5 || j == 6 || j == 7 || j == 8 || j == 9 || j == 13 || j == 14) {
        int finalI = j;
        buttons[j].addActionListener(e -> {
          if (finalI == 14) {
            showPopupTextFieldForLevels();
          }
          try {
            if (finalI != 14) {
              splitViewHelper(finalI);
            }
          } catch (Exception ex) {
            throw new RuntimeException(ex);
          }
        });

      }
    }

    buttons[0].setText("Load an Image");
    buttons[1].setText("Red-Component");
    buttons[2].setText("Green-Component");
    buttons[3].setText("Blue-Component");
    buttons[4].setText("value-component");
    buttons[5].setText("intensity-component");
    buttons[6].setText("luma-component");
    buttons[7].setText("sepia");
    buttons[8].setText("blur");
    buttons[9].setText("sharpen");
    buttons[10].setText("Flip Horizontally");
    buttons[11].setText("Flip Vertically");
    buttons[12].setText("Compress");
    buttons[13].setText("color-correct");
    buttons[14].setText("levels-adjust");
    buttons[15].setText("Save Histogram");
    buttons[16].setText("Save the Image");

    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(buttonsPanel, BorderLayout.NORTH);
    frame.getContentPane().add(textField);
    this.imagePanel = new JPanel();
    imagePanel.setLayout(new GridLayout(2, 2));
    this.imageLabels = new JLabel[4];
    JScrollPane[] scrollPanes = new JScrollPane[4];
    for (int i = 0; i < 4; i++) {
      imageLabels[i] = new JLabel();
      scrollPanes[i] = new JScrollPane(imageLabels[i]);
      imagePanel.add(scrollPanes[i]);
    }

    frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setResizable(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private void splitViewHelper(int finalI) {
    int dialogResult = JOptionPane.showConfirmDialog(null,
            "Do you want to view the split preview of the image",
            "Confirmation", JOptionPane.YES_NO_OPTION);
    if (dialogResult == JOptionPane.YES_OPTION) {
      JDialog splitDialog = new JDialog(frame, "Split Preview", true);
      splitDialog.setLayout(new BorderLayout());

      JPanel panel = new JPanel();
      JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
      slider.setMajorTickSpacing(10);
      slider.setMinorTickSpacing(1);
      slider.setPaintTicks(true);
      slider.setPaintLabels(true);

      JLabel valueLabel = new JLabel("Value: " + slider.getValue());
      JLabel imageLabelSplit = new JLabel();
      panel.setLayout(new FlowLayout());

      slider.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
          valueLabel.setText("Value: " + slider.getValue());
          try {
            String input = handleButtonClick(finalI);
            int[][][] previewImage = GUIcontroller.displayPreview(input, slider.getValue());
            ImageIcon imageIcon = createImageIcon(previewImage);
            imageLabelSplit.setIcon(imageIcon);
          } catch (Exception ex) {
            throw new RuntimeException(ex);
          }
        }
      });

      panel.add(slider);
      panel.add(valueLabel);
      panel.add(imageLabelSplit);
      splitDialog.add(panel, BorderLayout.CENTER);
      JButton okButton = new JButton("OK");
      JButton cancelButton = new JButton("Cancel");
      okButton.addActionListener(e1 -> {
        int userInput = slider.getValue();
        if (userInput >= 0 && userInput <= 100) {
          splitParam = 100;
          correctSplit = true;
        } else {
          correctSplit = false;
          JOptionPane.showMessageDialog(null,
                  "Parameter for split must be an integer between 0 and 100.",
                  "Error", JOptionPane.ERROR_MESSAGE);
        }
        splitDialog.dispose();
      });

      cancelButton.addActionListener(e12 -> {
        correctSplit = true;
        splitParam = 0;
        splitDialog.dispose();
      });

      JPanel buttonPanel = new JPanel();
      buttonPanel.add(okButton);
      buttonPanel.add(cancelButton);
      splitDialog.add(buttonPanel, BorderLayout.SOUTH);

      splitDialog.setSize(800, 500);
      splitDialog.setLocationRelativeTo(frame);
      splitDialog.setVisible(true);
    } else {
      correctSplit = true;
      splitParam = 100;
    }
  }

  private void showPopupTextFieldForCompress() {
    JTextField textField = new JTextField(15);

    JPanel panel = new JPanel();
    panel.add(new JLabel("Enter the parameter for compression:"));
    panel.add(textField);

    int result = JOptionPane.showConfirmDialog(null, panel,
            "Popup TextField", JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
      try {
        int userInput = Integer.parseInt(textField.getText());

        if (userInput >= 0 && userInput <= 100) {
          param_compress = userInput;
        } else {
          JOptionPane.showMessageDialog(null,
                  "Parameter for compression must be an integer between 0 and 100.",
                  "Error", JOptionPane.ERROR_MESSAGE);
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null,
                "Invalid input. Please enter a valid integer.", "Error",
                JOptionPane.ERROR_MESSAGE);
      }
    }
  }


  private void showPopupTextFieldForLevels() {
    JTextField textField_B = new JTextField(15);
    JTextField textField_M = new JTextField(15);
    JTextField textField_W = new JTextField(15);

    JPanel panel = new JPanel();
    panel.add(new JLabel("Enter the parameters B, M, W one after another in that order:"));
    panel.add(textField_B);
    panel.add(textField_M);
    panel.add(textField_W);

    int result = JOptionPane.showConfirmDialog(null, panel, "Popup TextField",
            JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
      try {
        int b = Integer.parseInt(textField_B.getText());
        int m = Integer.parseInt(textField_M.getText());
        int w = Integer.parseInt(textField_W.getText());

        if (b >= 0 && b <= 255 && m >= 0 && m <= 255 && w >= 0 && w <= 255 && b <= m && m <= w) {
          param_levels[0] = b;
          param_levels[1] = m;
          param_levels[2] = w;
          splitViewHelper(14);
        } else {
          JOptionPane.showMessageDialog(null,
                  "Parameters B, M, and W must be integers between 0 and 100 and in "
                          + "ascending order.", "Error", JOptionPane.ERROR_MESSAGE);
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid input. Please enter "
                + "valid integers for B, M, and W.", "Error", JOptionPane.ERROR_MESSAGE);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }


  private void loadHelper() throws Exception {
    imageLoaded = true;
    isNotSaved = true;
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File("./res/"));
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images",
            "ppm", "jpg", "jpeg", "png");
    fileChooser.setFileFilter(filter);
    int returnValue = fileChooser.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getCanonicalPath();
      stringInput = "load /" + filePath + " input";
    } else {
      return;
    }
  }

  private String handleButtonClick(int buttonIndex) {
    if (correctSplit) {
      stringInput = "";
      splitCommand = "";
      try {
        if (!imageLoaded && buttonIndex != 0) {
          throw new Exception("Please load an image first.");
        }
        {
          switch (buttonIndex) {
            case 0:
              handleCase0();
              break;
            case 1:
              handleCaseWithCommand("red-component");
              break;
            case 2:
              handleCaseWithCommand("green-component");
              break;
            case 3:
              handleCaseWithCommand("blue-component");
              break;
            case 4:
              handleCaseWithSplitCommand("value-component", splitParam);
              break;
            case 5:
              handleCaseWithSplitCommand("intensity-component", splitParam);
              break;
            case 6:
              handleCaseWithSplitCommand("luma-component", splitParam);
              break;
            case 7:
              handleCaseWithSplitCommand("sepia", splitParam);
              break;
            case 8:
              handleCaseWithSplitCommand("blur", splitParam);
              break;
            case 9:
              handleCaseWithSplitCommand("sharpen", splitParam);
              break;
            case 10:
              handleCaseWithCommand("horizontal-flip");
              break;
            case 11:
              handleCaseWithCommand("vertical-flip");
              break;
            case 12:
              handleCaseWithParam(param_compress);
              param_compress = 0;
              break;
            case 13:
              handleCaseWithSplitCommand("color-correct", splitParam);
              break;
            case 14:
              handleCaseWithLevelsAdjust();
              break;
            case 15:
              handleFileSave("histogramTemp");
              isNotSaved = true;
              break;
            case 16:
              handleFileSave("input");
              break;
            default:
              break;
          }
        }
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
    return stringInput;
  }

  private void handleFileSave(String suffix) {
    JFileChooser saveFileChooser = createSaveFileChooser();
    JComboBox<String> fileTypeComboBox = createFileTypeComboBox();
    JPanel fileTypePanel = createFileTypePanel(fileTypeComboBox);

    int fileTypeResult = showFileTypeDialog(fileTypePanel);

    if (fileTypeResult == JOptionPane.OK_OPTION) {
      int saveResult = showSaveDialog(saveFileChooser);

      if (saveResult == JFileChooser.APPROVE_OPTION) {
        File selectedFile = saveFileChooser.getSelectedFile();
        String selectedFileType = (String) fileTypeComboBox.getSelectedItem();
        String folderPath = selectedFile.getAbsolutePath() + "." + selectedFileType;
        stringInput = "save /" + folderPath + " " + suffix;
        isNotSaved = false;
      }
    }
  }

  private JFileChooser createSaveFileChooser() {
    JFileChooser saveFileChooser = new JFileChooser();
    saveFileChooser.setCurrentDirectory(new File("./res/"));
    saveFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    return saveFileChooser;
  }

  private JComboBox<String> createFileTypeComboBox() {
    JComboBox<String> fileTypeComboBox = new JComboBox<>(new String[]{"ppm", "png", "jpg"});
    fileTypeComboBox.setSelectedIndex(0);
    return fileTypeComboBox;
  }

  private JPanel createFileTypePanel(JComboBox<String> fileTypeComboBox) {
    JPanel fileTypePanel = new JPanel();
    fileTypePanel.setLayout(new BorderLayout());
    fileTypePanel.add(new JLabel("Choose file type:"), BorderLayout.NORTH);
    fileTypePanel.add(fileTypeComboBox, BorderLayout.CENTER);
    return fileTypePanel;
  }

  private int showFileTypeDialog(JPanel fileTypePanel) {
    return JOptionPane.showConfirmDialog(null, fileTypePanel,
            "Choose File Type", JOptionPane.OK_CANCEL_OPTION);
  }

  private int showSaveDialog(JFileChooser saveFileChooser) {
    return saveFileChooser.showSaveDialog(null);
  }

  private void handleCase0() throws Exception {
    if (isNotSaved) {
      Object[] options = {"I understand", "Cancel"};

      // Show the option dialog
      int result = JOptionPane.showOptionDialog(null,
              "The image is not saved, do you still wish to proceed", "Load alert",
              JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
              options[1]);

      if (result == JOptionPane.YES_OPTION) {

        loadHelper();
      }
    } else {
      loadHelper();
    }
  }

  private void handleCaseWithCommand(String command) {
    isNotSaved = true;
    stringInput = command + " input input";
  }

  private void handleCaseWithSplitCommand(String command, int param) {
    isNotSaved = true;
    splitCommand = command;
    stringInput = command + " input input split " + param;
  }

  private void handleCaseWithParam(int param) {
    isNotSaved = true;
    stringInput = "compress" + " " + param + " input input";
  }

  private void handleCaseWithLevelsAdjust() {
    isNotSaved = true;
    splitCommand = "levels-adjust";
    stringInput = splitCommand + " " + param_levels[0] + " " + param_levels[1] + " "
            + param_levels[2] + " input input split " + splitParam;
  }

  @Override
  public void displayImage(int[][][] imageData) {
    displayHelper(imageData, 3,
            "Current Image");
  }

  private void displayHelper(int[][][] imageData, int index, String tooltip) {
    try {
      ImageIcon imageIcon = createImageIcon(imageData);
      imageLabels[index].setIcon(imageIcon);
      imageLabels[index].setToolTipText(tooltip);
      imagePanel.revalidate();
      imagePanel.repaint();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void displayErrorMessage(String errorMessage) {
    JOptionPane.showMessageDialog(null, errorMessage, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void previousImage(int[][][] imageData) {
    displayHelper(imageData, 2,
            "Previous Image");
  }

  @Override
  public void displayHistogram(int[][][] imageData) {
    displayHelper(imageData, 1,
            "Histogram");
  }

  @Override
  public void loadedImage(int[][][] imageData) {
    displayHelper(imageData, 0,
            "Loaded Image");
  }

  private ImageIcon createImageIcon(int[][][] imageData) {
    int width = imageData.length;
    int height = imageData[0].length;
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = (imageData[x][y][0] << 16) | (imageData[x][y][1] << 8) | imageData[x][y][2];
        bufferedImage.setRGB(x, y, rgb);
      }
    }
    return new ImageIcon(bufferedImage);
  }
}
