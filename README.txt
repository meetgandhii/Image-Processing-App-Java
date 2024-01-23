Image Processing Application

*USE "help"/"h"/"--help"/"--h" as a command to see what all you can do here*

This is an image processing application that provides various image manipulation operations.
It consists of a controller class ImageController and a model class ImageModel.
The application can load images in different formats (like png, jpg and ppm), apply various image
processing operations, and save the resulting images (can be saved in ppm, png or jpg).

1. How to Use

   To use the program, follow these steps:
    - Run the Main Method: Start by running the main method of the program located in
      the "src" directory.
    - Choose Your Interaction Method:
        > Command Line Inputs: You can directly enter commands using the command line.
          This allows you to interact with the application by typing commands one by one
          in the console.
        > Run a Script File: You can run a script file by using the 'run' command followed by the
          relative location of the script file. For example, you can execute a series of commands
          stored in a script file by entering 'run res/commands.txt'.

2. Image Processing Operations:
   You can apply various image processing operations using the following commands:

    red-component - Keep the red component of the image.
    green-component - Keep the green component of the image.
    blue-component - Keep the blue component of the image.
    value-component - Extract the value (brightness) component of the image.
    luma-component - Convert the image to grayscale using luma formula.
    intensity-component - Convert the image to grayscale using intensity formula.
    horizontal-flip - Flip the image horizontally.
    vertical-flip - Flip the image vertically.
    brighten - Adjust the brightness of the image.
    rgb-split - Split the image into its RGB components.
    rgb-combine - Combine separate RGB components to create an image.
    blur - Apply a blur effect to the image.
    sharpen - Apply a sharpening effect to the image.
    sepia - Apply a sepia filter to the image.

3. Example:
    ---------------------------------------------------
    red-component image_to_use output_image
    ---------------------------------------------------

4. Save an Image:
   You can save the processed image using the save command:
    ---------------------------------------------------
    save <output_path> <image_to_save>
    Supported image formats for saving: .ppm, .png, .jpg, and others.
    ---------------------------------------------------

5. Quit the Application:
    To exit the application, use the 'quit' command.

6. Sample Script File

    commands.txt is an example script file that demonstrates how to perform multiple
    image processing operations. It can also be found in the res folder (res/commands.txt).
    This script file loads the same image in all 3 formats supported, performs every available
    operation and saves it in all 3 formats, demonstrating the entire program's functionality.

    ---------------------------------------------------------------------------------------
    load input/input.png landscape-png
    load input/input.jpg landscape-jpg
    load input/input.ppm landscape-ppm
    save res/input.png landscape-png
    save res/input.jpg landscape-jpg
    save res/input.ppm landscape-ppm
    red-component landscape-png landscape-png-red-component
    green-component landscape-png landscape-png-green-component
    blue-component landscape-png landscape-png-blue-component
    value-component landscape-png landscape-png-value-component
    luma-component landscape-png landscape-png-luma-component
    intensity-component landscape-png landscape-png-intensity-component
    horizontal-flip landscape-png landscape-png-horizontal-flip
    vertical-flip landscape-png landscape-png-vertical-flip
    brighten 20 landscape-png landscape-png-brighten
    rgb-split landscape-png landscape-png-r-split landscape-png-g-split landscape-png-b-split
    rgb-combine landscape-png-rgb-combine landscape-png-r-split landscape-png-g-split landscape-png-b-split
    blur landscape-png landscape-png-blur
    sharpen landscape-png landscape-png-sharpen
    sepia landscape-png landscape-png-sepia
    red-component landscape-jpg landscape-jpg-red-component
    green-component landscape-jpg landscape-jpg-green-component
    blue-component landscape-jpg landscape-jpg-blue-component
    value-component landscape-jpg landscape-jpg-value-component
    luma-component landscape-jpg landscape-jpg-luma-component
    intensity-component landscape-jpg landscape-jpg-intensity-component
    horizontal-flip landscape-jpg landscape-jpg-horizontal-flip
    vertical-flip landscape-jpg landscape-jpg-vertical-flip
    brighten 20 landscape-jpg landscape-jpg-brighten
    rgb-split landscape-jpg landscape-jpg-r-split landscape-jpg-g-split landscape-jpg-b-split
    rgb-combine landscape-jpg-rgb-combine landscape-jpg-r-split landscape-jpg-g-split landscape-jpg-b-split
    blur landscape-jpg landscape-jpg-blur
    sharpen landscape-jpg landscape-jpg-sharpen
    sepia landscape-jpg landscape-jpg-sepia
    red-component landscape-ppm landscape-ppm-red-component
    green-component landscape-ppm landscape-ppm-green-component
    blue-component landscape-ppm landscape-ppm-blue-component
    value-component landscape-ppm landscape-ppm-value-component
    luma-component landscape-ppm landscape-ppm-luma-component
    intensity-component landscape-ppm landscape-ppm-intensity-component
    horizontal-flip landscape-ppm landscape-ppm-horizontal-flip
    vertical-flip landscape-ppm landscape-ppm-vertical-flip
    brighten 20 landscape-ppm landscape-ppm-brighten
    rgb-split landscape-ppm landscape-ppm-r-split landscape-ppm-g-split landscape-ppm-b-split
    rgb-combine landscape-ppm-rgb-combine landscape-ppm-r-split landscape-ppm-g-split landscape-ppm-b-split
    blur landscape-ppm landscape-ppm-blur
    sharpen landscape-ppm landscape-ppm-sharpen
    sepia landscape-ppm landscape-ppm-sepia
    save res/landscape-png-red-component.png landscape-png-red-component
    save res/landscape-png-green-component.png landscape-png-green-component
    save res/landscape-png-blue-component.png landscape-png-blue-component
    save res/landscape-png-value-component.png landscape-png-value-component
    save res/landscape-png-luma-component.png landscape-png-luma-component
    save res/landscape-png-intensity-component.png landscape-png-intensity-component
    save res/landscape-png-horizontal-flip.png landscape-png-horizontal-flip
    save res/landscape-png-vertical-flip.png landscape-png-vertical-flip
    save res/landscape-png-r-split.png landscape-png-r-split
    save res/landscape-png-g-split.png landscape-png-g-split
    save res/landscape-png-b-split.png landscape-png-b-split
    save res/landscape-png-rgb-combine.png landscape-png-rgb-combine
    save res/landscape-png-brighten.png landscape-png-brighten
    save res/landscape-png-blur.png landscape-png-blur
    save res/landscape-png-sharpen.png landscape-png-sharpen
    save res/landscape-png-sepia.png landscape-png-sepia
    save res/landscape-jpg-red-component.jpg landscape-jpg-red-component
    save res/landscape-jpg-green-component.jpg landscape-jpg-green-component
    save res/landscape-jpg-blue-component.jpg landscape-jpg-blue-component
    save res/landscape-jpg-value-component.jpg landscape-jpg-value-component
    save res/landscape-jpg-luma-component.jpg landscape-jpg-luma-component
    save res/landscape-jpg-intensity-component.jpg landscape-jpg-intensity-component
    save res/landscape-jpg-horizontal-flip.jpg landscape-jpg-horizontal-flip
    save res/landscape-jpg-vertical-flip.jpg landscape-jpg-vertical-flip
    save res/landscape-jpg-r-split.jpg landscape-jpg-r-split
    save res/landscape-jpg-g-split.jpg landscape-jpg-g-split
    save res/landscape-jpg-b-split.jpg landscape-jpg-b-split
    save res/landscape-jpg-rgb-combine.jpg landscape-jpg-rgb-combine
    save res/landscape-jpg-brighten.jpg landscape-jpg-brighten
    save res/landscape-jpg-blur.jpg landscape-jpg-blur
    save res/landscape-jpg-sharpen.jpg landscape-jpg-sharpen
    save res/landscape-jpg-sepia.jpg landscape-jpg-sepia
    save res/landscape-ppm-red-component.ppm landscape-ppm-red-component
    save res/landscape-ppm-green-component.ppm landscape-ppm-green-component
    save res/landscape-ppm-blue-component.ppm landscape-ppm-blue-component
    save res/landscape-ppm-value-component.ppm landscape-ppm-value-component
    save res/landscape-ppm-luma-component.ppm landscape-ppm-luma-component
    save res/landscape-ppm-intensity-component.ppm landscape-ppm-intensity-component
    save res/landscape-ppm-horizontal-flip.ppm landscape-ppm-horizontal-flip
    save res/landscape-ppm-vertical-flip.ppm landscape-ppm-vertical-flip
    save res/landscape-ppm-r-split.ppm landscape-ppm-r-split
    save res/landscape-ppm-g-split.ppm landscape-ppm-g-split
    save res/landscape-ppm-b-split.ppm landscape-ppm-b-split
    save res/landscape-ppm-rgb-combine.ppm landscape-ppm-rgb-combine
    save res/landscape-ppm-brighten.ppm landscape-ppm-brighten
    save res/landscape-ppm-blur.ppm landscape-ppm-blur
    save res/landscape-ppm-sharpen.ppm landscape-ppm-sharpen
    save res/landscape-ppm-sepia.ppm landscape-ppm-sepia
    ---------------------------------------------------------------------------------------

7. What we did

    Design

    We use the MVC design pattern in this project.
        - The ImageControllerCLI is the controller that deals with the user input and
          generating the output. The GUIImageController class deals with input from GUI and sends
          it to ImageController for further actions.
        - The ImageModel class is the model that stores all the logic of the program and consists
          of all the operations that can be performed on an image.
        - The ImageView class is the view that contains the GUI of the app. It uses swing to -
          create visuals of the images, buttons for functions and dialogue boxes for errors.

    The controller, model and view packages consist of interfaces implemented by classes. The interface
    consists of all the public methods the respective class should have inside them for the
    proper functionality of the code. The interfaces are 'ImageControllerInterface.java',
    'ImageViewInterface.java', 'GUIImageControllerInterface.java', 'ImageModelInterface.java' and
    'ImageModelExtendedInterface.java'. If any new functions have to be added, we can create a 3rd
    interface in model and write the methods in it and extend Interface 2 to it,
    example 'public interface ImageModel3Interface extends ImageModelExtendedInterface' and so on.

    The ImageController and ImageControllerCLI class implements the ImageControllerInterface, which
    contains all the public methods that are used by the controller instance such as loadImage,
    createImage and runMain.

        Load Image - It loads the image from PPM Jpg or Png formats. The load image calls in a
        private helper method depending on the image type (the extension/type of image) namely
        ReadPPM and ReadOther.

        Save Image - It saves the image from PPM Jpg or Png formats. The save image calls in a
        private helper method depending on the image type (the extension/type of image) namely
        SavePPM and SaveOther.

    Since the buffered image saves the image in a vertical-first manner, our program saves
    it similarly to avoid any misinterpretations or mistakes while dealing with methods or testing.
    So an image that has 1st row red 2nd green 3rd blue would be saved as 1st column red 2nd green
    3rd blue/{{r,g,b}{r,g,b}{r,g,b}}.

        Run Main - It consists of the scanner that scans what input has been entered, the if else
        and switch statements to choose what to do according to the input and the calling of
        model methods corresponding to the input.

    The GUIImageController consists of methods used for parsing and using inputs of user via the
    graphical user interface. It takes in model as the input in the constructor and uses a setter
    to set the view to not form an infinite loop when it is called in the constructor of view.
    The other methods are 'handleClick' and 'displayPreview'.

        handleClick - It is called once the view receives an input, and it calls the
        switchcasehelper of the controller to pass on the input.

        displayPreview - It is called whenever the slider is used to split preview the result of
        an operation in the gui.

    The ImageModel class implements the ImageModelInterface (and now the ImageModel2Interface)
    which contains all the methods of the model which are called by the model instance namely,
        - getHashMap returns a 3d array of the image stored in the hashmap with the key as its name
        - setHashMap sets a 3d array of image as a value to its name as key in the hashmap
        - redImage converts an image to its red-component (only r values rest 0)
        - greenImage converts an image to its green-component (only g values rest 0)
        - blueImage converts an image to its blue-component (only b values rest 0)
        - valueImage converts an image to its value-component
          (all elements replaced by max of r g or b)
        - intensityImage converts an image to its intensity-component
          (all elements replaced by average of r g and b)
        - lumaImage converts an image to its luma-component
          (all elements replaced by 0.2126*r+0.7152*g+0.0722*b)
        - flipHorizontalImage keeping the rows constant, toggles the column numbers
        - flipVerticalImage keeping the columns constant, toggles the row numbers
        - brightenImage adds a parameter to each element in each channel,
          clamps if the result exceeds 255 or is lower than 0.
        - rgbSplit splits an image into its red-component green-component and blue-component and
          returns all 3 images formed
        - rgbCombine combines 3 images into 1 using 1st images r component, 2nd's g and 3rd's b.
        - sepiaImage converts an image to its sepia-form (formula provided in assignment)
        - blurImage uses a kernel to blur the image by centering the kernel
          at index and finding the result.
        - sharpenImage uses a kernel to sharpen the image by centering the kernel
          at index and finding the result.

    The ImageView class implements the ImageViewInterface which contains all the methods of the
    view which are called by the view instance namely,

        - displayImage Displays the latest version of the image with the operations performed.
        - displayErrorMessage Display the error in a dialogue box.
        - previousImage Displays the previous image, i.e. the image before the operation button was
          clicked
        - displayHistogram Displays the histogram of the current image.
        - loadedImage Always displays the original image which was loaded.

        To avoid confusion of which quadrant displays what image, tooltip has been added to each
        quadrant.

    A general flow would be -

    *CLI or FILE mode*

    runMain method is called on the controller in some main class

    1. User Input:
        User enters a command, which is read by the controller's runMain method.
        The input is split into an array using space as a delimiter.

    2. Load Image:
        If the first element of the array is "load," it goes to the load case in the switch statement.
        The loadImage method is called, passing the parameters (path-name, image-name-to-call-everytime).
        The file type (PPM or other) is checked at the end of the file.
        Depending on the file type:
        If it's PPM, ReadPPM is used to populate a 3D array with RGB values using a scanner.
        If it's another type, ReadOther is used to process and populate the 3D array using BufferedImage.
        Store Image in HashMap:
        The populated 3D array is stored in the imageMap hashmap with the image-name as the key.

    3. Perform Image Processing:
        User enters a command like 'some-function' 'image-name' 'result-name'.
        The some-function is used to determine the specific action to be taken.
        The image-name is used to retrieve the corresponding image from the imageMap.
        The result-name is used as a key to store the result of the image processing into the imageMap.

    4. Saving the Image:
        User enters a command 'save' 'destination-name' 'image-to-save-name'
        The controller's runMain method processes the command and identifies it as a 'save' operation.
        The saveImage method is called, passing the parameters (destination-name, image-to-save-name, fileType).
        The saveImage method retrieves the specified image from the imageMap using 'image-to-save-name'.
        Depending on the file type, the method calls the helper SavePPM or SaveOther.
        The saved image is stored in a specific directory location based on the 'destination-name'.


    *GUI MODE*

    runMain method is called on the controller in some main class. The GUI opens up

    1. User Input:
        User clicks on load image first. Clicking on anything else displays error as "Load the image
        first." Once user clicks load image, a dialogue box appears which user can use to select
        the image to perform functions on.

    2. Load Image:
        The image gets selected once user presses open. The handleclick makes it go into view's
        switch case which translates this action into 'load Image-Directory Image-Name' and sends
        it to GUIController which inturn sends it to Controller. Now the controller functions the
        same way it would in CLI mode. Then the GUIController calls an additional method 'displayImageInFrame'
        which creates an image icon of the image and displays it in the GUI. This method displays
        the current image, the previous image, the histogram of current and the first loaded image.

    3. Perform Image Processing:
        Similarly, whenever user clicks on some button to perform a function, The handleclick makes it go into view's
        switch case which translates this action into the corresponding CLI version of the function and sends
        it to GUIController which inturn sends it to Controller and the controller functions the
        same way it would in CLI mode. The GUIController again calls an additional method 'displayImageInFrame'
        which creates an image icon of the image and displays it in the GUI.

    4. Saving the Image:
        User clicks on 'save' which opens a dialogue box where the user can select the type of
        file to save as, and the file name of the saved file along with choosing the directory
        to save into.


    In Assignment 5 the changes that we made to the design are as follows:

    We changed the load and save which was initially in our model to the controller. We did this
    as the input and output of the image is done by the controller and not the model.

    We also added multiple helper methods to the model class to make the code more readable and
    easier to understand. which also reduced the lines of code per function drastically.

    We added a new interface called the ImageModel2Interface which contains all the public methods of
    extra new functionality that we added to the model class. This interface extends the older
    interface hence we do not change any existing code of the older interface, and is implemented by
    the ImageModel class. hence the class implements both the interfaces and no code has
    to change. We then go forward to adding extra functionalities in the model without touching the
    existing code.

    Extra Functionalities added -

     - Compression Performs compression on an image by applying the Haar Wavelet Transform.
     - Histogram Generates an image representing the histogram of a given image.
     - Color Correct Aligns the meaningful peaks of an image's histogram to correct its color.
     - Levels Adjust Adjusts the levels of an image based on specified black, mid, and white values.

To run the jar file use the command

    java -jar Assignment4.jar

Note - the image to be loaded and the path to be saved should be relative to the jar file's location.
In order to run the -file command use the sample file jarCommand.txt

the code is

java -jar Assignment4.jar -file jarCommand.txt


For Assignment 6

Completed parts - EVERYTHING
Design Changes -

Added a new interface and class for GUI Controller, created a new ImageController and created a view
for displaying the GUI. The view only interacts with the GUI controller and only the GUI Controller
interacts with the Controller. No one other than the controller interacts with the model.
Thus our design strictly adheres to the MVC Design Pattern.
Also a new test file has been added in tests directory for testing the new GUI Controller.
The major change is having 2 seperate controllers, one for CLI and one for GUI. This was done to
run the program jar file on GUI when double clicked and on CLI with the parameter -text.
Also created an abstract class which contains common code between the CLI and GUI controller.

--------------------------------------------------------------------------------------------

    CITATION - Image Source

    The nature image used in this project is sourced from
    [FreePNGClipart](https://freepngclipart.com)
    and is available for free download at the following URL:
    [Nature Image](https://freepngclipart.com/free-png/5254-nature-to-use-free-download).

    Nature To Use Free Download is very suitable for designing purposes. Nature To Use
    Free Download is related with various tags like sort, kind, scale, existence, nature,
    creation, use, environment, manner, scope, world, Nature.