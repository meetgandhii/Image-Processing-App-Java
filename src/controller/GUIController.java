package controller;


import model.ImageModel;
import view.ImageView;

/**
 * The `GUIController` class is responsible for controlling interactions between the GUI view
 * and the image controller of GUI.
 */
public class GUIController implements GUIImageControllerInterface {

  private static ImageView view;
  private static ImageModel model;
  private final ImageController controller = new ImageController(model, view);

  /**
   * Constructs a `GUIController` with the specified image model. The view is set later using
   * a setter called setView.
   *
   * @param model The image model to be associated with the controller.
   */
  public GUIController(ImageModel model) {
    GUIController.model = model;
  }

  // Setter method to set the view after its creation
  @Override
  public void setView(ImageView view) {
    GUIController.view = view;
  }

  @Override
  public void handleClick(String input) {
    try {
      String[] partInFile = input.split(" ");
      String command1 = partInFile[0];
      controller.switchCaseHelper(command1, partInFile);
      if (partInFile.length < 5) {
        controller.displayImageInFrame(partInFile[2]);
      } else {
        controller.displayImageInFrame(partInFile[partInFile.length - 3]);
      }
    } catch (Exception e) {
      view.displayErrorMessage("No File Selected");
    }

  }

  @Override
  public int[][][] displayPreview(String inp, int param) throws Exception {
    String[] parts = inp.split(" ");
    String[] updatedParts = new String[parts.length];
    System.arraycopy(parts, 0, updatedParts, 0, parts.length);
    updatedParts[parts.length - 1] = String.valueOf(param);
    updatedParts[parts.length - 3] = "tempImage";
    controller.switchCaseHelper(updatedParts[0], updatedParts);
    return controller.getHashMapFromModel("tempImage");
  }

}
