package Image;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents. Feel free to change this method
 *  as required.
 */



public class ImageUtil {
  //matrices for various manipulations
  double [] [] blur = {{1/16, 1/8, 1/16}, {1/8, 1/4, 1/8}, {1/16, 1/8, 1/16}};
  double [] [] sharp = {{-1/8, -1/8, -1/8, -1/8, -1/8}, {-1/8, 1/4, 1/4, 1/4, -1/8},
      {-1/8, 1/4, 1, 1/4, -1/8}, {-1/8, 1/4, 1/4, 1/4, -1/8}, {-1/8, -1/8, -1/8, -1/8, -1/8}};
  double [] [] greyscale = {{.2126, .7152, .0722}, {.2126, .7152, .0722}, {.2126, .07152, .0722}};
  double [] [] sepia = {{.393, .769, .189}, {.349, .686, .168}, {.272, .534, .131}};
  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @return
   */
  public static int[][][] readPPM(String filename) {
    Scanner sc;
    try {
        sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
        System.out.println("File "+filename+ " not found!");
      return new int[0][][];
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0)!='#') {
            builder.append(s+System.lineSeparator());
        }
    }
    
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token; 

    token = sc.next();
    if (!token.equals("P3")) {
        System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: "+width);
    int height = sc.nextInt();
    System.out.println("Height of image: "+height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 256): "+maxValue);
    int [][][] pixels = new int[width][height][3];
    for (int i=0;i<height;i++) {
        for (int j=0;j<width;j++) {
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            int [] pixel = {r, g, b};
            pixels[j][i] = pixel;
            System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
        }
    }
    return pixels;
  }

  //demo main
  public static void main(String []args) {
      String filename;
      
      if (args.length>0) {
          filename = args[0];
      }
      else {
          filename = "sample.ppm";
      }
      
      ImageUtil.readPPM(filename);
  }
}

