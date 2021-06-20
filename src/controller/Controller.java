package controller;

import image.IImage;
import image.ILayeredImage;
import image.ImageType;
import image.JPEGImage;
import image.JPEGLayeredImage;
import image.PNGImage;
import image.PNGLayeredImage;
import image.PPMImage;
import image.PPMLayeredImage;
import java.util.ArrayList;
import java.util.Scanner;
import manipulation.BlurManip;
import manipulation.GrayscaleManip;
import manipulation.IManipulation;
import manipulation.SepiatoneManip;
import manipulation.SharpenManip;

public class Controller implements IController {

  private Readable in;
  private boolean loadedImage;
  private ArrayList<ILayeredImage> images;

  /**
   * Simple Constructor for Controller
   * @param in      Readable input
   */

  public Controller(Readable in) {
    if(in == null) {
      throw new IllegalArgumentException("Input is null.");
    }
    this.in = in;
    this.loadedImage = false;
    this.images = new ArrayList<>();
  }
  /**
   * Run a script that allows the user to upload, export, save, and manipulation images or layered
   * images. This method returns only when the user explicitly tells it to.
   */
  @Override
  public void runScript() {
    int listIndex = -1;
    int layers = 0;
    int layerIndex = 0;
    Scanner scan = new Scanner(in);
    while(scan.hasNext()) {
      String input = scan.next();
      if(input.equals("new")) {
        String format = scan.next();
        while(!format.equals("jpeg") || !format.equals("ppm") || !format.equals("png")) {
          System.out.println("Please enter a valid format type.");
          format = scan.next();
        }
        if(format.equals("jpeg")) {
          boolean valid = false;
          while(valid == false) {
            try {
              String filePath = scan.next();
              if(!loadedImage) {

              }
              this.images.add(new JPEGLayeredImage(filePath));
              valid = true;
            } catch(IllegalArgumentException error) {
              System.out.println("Please enter a valid filetype");
            }
          }
        }
        if(format.equals("ppm")) {
          boolean valid = false;
          while(valid == false) {
            try {
              String filePath = scan.next();
              this.images.add(new PPMLayeredImage(filePath));
              valid = true;
            } catch(IllegalArgumentException error) {
              System.out.println("Please enter a valid filetype");
            }
          }
        }
        if(format.equals("png")) {
          boolean valid = false;
          while(valid == false) {
            try {
              String filePath = scan.next();
              this.images.add(new PNGLayeredImage(filePath));
              valid = true;
            } catch(IllegalArgumentException error) {
              System.out.println("Please enter a valid filetype");
            }
          }
        }
        listIndex++;
      }
      if(input.equals("create")) { //create layers
        String layer = scan.next();
        while(!(layer.equals("layer"))) {
          System.out.println("Please enter layer to create a new layer.");
          layer = scan.next();
        }
        this.images.get(listIndex).addLayer();
        layers++;
      }
      if(input.equals("current")) { //set current layer
        boolean valid = false;
        while(valid == false) {
          try{
            String layerNum = scan.next();
            layerIndex = Integer.parseInt(layerNum);
            if(layerIndex > 1 && layerIndex < layers) {
              valid = true;
            }
            else {
              System.out.println("Please enter a valid integer for a layer to be manipulated.");
            }
          } catch(NumberFormatException error) {
            System.out.println("Please enter a valid integer for a layer to be manipulated.");
          }
        }
      }
      if(input.equals("load")) {
        boolean valid = false;
        String format = scan.next();
        while(!format.equals("jpeg") || !format.equals("ppm") || !format.equals("png")) {
          System.out.println("Please enter a valid format type.");
          format = scan.next();
        }
        if(format.equals("jpeg")) {
          while(valid == false) {
            String filePath = scan.next();
            try {
              this.images.get(listIndex).replaceLayer(new JPEGImage(filePath), layerIndex - 1); //load image and replace layer
              valid = true;
            } catch (IllegalArgumentException error) {
              System.out.println("Please enter a valid image file name.");
            }
          }
        }
        if(format.equals("ppm")) {
          while(valid == false) {
            String filePath = scan.next();
            try {
              this.images.get(listIndex).replaceLayer(new PPMImage(filePath), layerIndex - 1); //load image and replace layer
              valid = true;
            } catch (IllegalArgumentException error) {
              System.out.println("Please enter a valid image file name.");
            }
          }
        }
        if(format.equals("png")) {
          while(valid == false) {
            String filePath = scan.next();
            try {
              this.images.get(listIndex).replaceLayer(new PNGImage(filePath), layerIndex - 1); //load image and replace layer
              valid = true;
            } catch (IllegalArgumentException error) {
              System.out.println("Please enter a valid image file name.");
            }
          }
        }
      }
      if(input.equals("blur")) {
        IManipulation blur = new BlurManip();
        IImage image = blur.apply(images.get(listIndex).getLayer(layerIndex-1));
        images.get(listIndex).replaceLayer(image, layerIndex-1);
      }
      if(input.equals("sepia")) {
        IManipulation sep = new SepiatoneManip();
        IImage image = sep.apply(images.get(listIndex).getLayer(layerIndex-1));
        images.get(listIndex).replaceLayer(image, layerIndex-1);
      }
      if(input.equals("sharpen")) {
        IManipulation sharp = new SharpenManip();
        IImage image = sharp.apply(images.get(listIndex).getLayer(layerIndex-1));
        images.get(listIndex).replaceLayer(image, layerIndex-1);
      }
      if(input.equals("gray")) {
        IManipulation gray = new GrayscaleManip();
        IImage image = gray.apply(images.get(listIndex).getLayer(layerIndex-1));
        images.get(listIndex).replaceLayer(image, layerIndex-1);
      }
      if(input.equals("save")) {
        this.images.get(listIndex).saveImage();
      }
      if(input.equals("export")) {
        String exportPath = this.images.get(listIndex).exportImage();
        System.out.println("Layered Image exported to path: " + exportPath);
      }
      if(input.equals("saveas")) {
        String format = scan.next();
        while(!format.equals("jpeg") || !format.equals("ppm") || !format.equals("png")) {
          System.out.println("Enter a valid filetype: jpeg, ppm, png.");
          format = scan.next();
        }
        if(format.equals("jpeg")) {
          images.get(listIndex).saveImageAs(ImageType.JPEG);
        }
        if(format.equals("ppm")) {
          images.get(listIndex).saveImageAs(ImageType.PPM);
        }
        if(format.equals("png")) {
          images.get(listIndex).saveImageAs(ImageType.PNG);
        }
      }
      if(input.equals("invisible")) { //toggles invisibile of current layer.
        this.images.get(listIndex).toggleLayerTransparency(layerIndex-1);
      }
      if(input.equals("quit")) {
        return;
      }
    }
  }
}
