import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import controller.ImageController;
import controller.ImageControllerCLI;
import model.ImageModel;
import view.ImageView;


/**
 * The `Main` class serves as the entry point for the application.
 * It sets up an `ImageController` to manage images based on user input.
 */
public class Main {

  /**
   * The main method of the application, responsible for starting the program's execution.
   *
   * @throws IOException If an I/O error occurs during input or output operations.
   */
  public static void main(String[] args) throws Exception {
    ImageModel model = new ImageModel();


    if (args.length > 0) {
      handleCommandLineOptions(args, model);
    } else {
      ImageView view = new ImageView(new InputStreamReader(System.in), System.out);
      ImageController controller;
      controller = new ImageController(model, view);

      controller.runMain();
    }
  }

  private static void handleCommandLineOptions(String[] args, ImageModel model) throws Exception {
    String option = args[0].toLowerCase();
    ImageController controller;
    ImageControllerCLI controllerCLI;

    if ("-file".equals(option)) {
      if (args.length > 1) {
        String scriptFileName = args[1];

        try {
          controller = createControllerWithScript(model, scriptFileName);
          controller.runMain();
        } catch (FileNotFoundException e) {
          throw new FileNotFoundException("Error: Script file not found.");
        } catch (UnsupportedOperationException e) {
          throw new UnsupportedOperationException("Error: Unsupported file extension.");
        } catch (IOException e) {
          throw new IOException("Error: IOException while reading the script file.");
        }
      } else {
        throw new Exception("Error: Missing script file name.");
      }
    }
    else if ("-text".equals(option)) {
      controllerCLI = new ImageControllerCLI(model, new InputStreamReader(System.in), System.out);
      controllerCLI.runMain();
    }
    else {
      throw new Exception("Error: Unknown command-line option.");
    }
  }

  private static ImageController createControllerWithScript(ImageModel model, String scriptFileName)
          throws IOException {
    File scriptFile = new File(scriptFileName);

    if (!scriptFile.exists()) {
      throw new FileNotFoundException();
    }

    String extension = getFileExtension(scriptFileName);
    if (!"txt".equalsIgnoreCase(extension)) {
      throw new UnsupportedOperationException("Unsupported file extension.");
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(scriptFile))) {
      StringBuilder scriptContent = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        scriptContent.append(line).append("\n");
      }
      ImageView view = new ImageView(new StringReader(scriptContent.toString()), System.out);
      ImageController controller = new ImageController(model, view);

      return controller;
    }
  }

  private static String getFileExtension(String fileName) {
    int dotIndex = fileName.lastIndexOf('.');
    if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
      return ""; // No extension found
    }
    return fileName.substring(dotIndex + 1).toLowerCase();
  }
}
