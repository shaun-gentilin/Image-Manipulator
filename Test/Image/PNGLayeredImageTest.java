package image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Test;

/**
 * Test class for PNGLayeredImage
 */
public class PNGLayeredImageTest {

  //loadImageLayers TESTS

  /*
  Test loading a valid layered image.
   */
  @Test
  public void testLoadImageLayersValidImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    assertEquals(5, image.getHeight());
    assertEquals(5, image.getWidth());
    assertEquals(0, image.getMaxColorValue());
    assertEquals(0, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 1)[0]);

    assertEquals(0, image.getLayer(1).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 1)[0]);
  }

  /*
  Test loading a layered image from an invalid file path.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageLayersInvalidFile() {
    String path = "badPath";
    ILayeredImage image = new PNGLayeredImage(path);
    assertEquals(5, image.getHeight());
  }

  /*
  Test loading a layered image with layers that have mismatched dimensions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageLayersMismatchedDimensions() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\invalid image mismatched dimensions\\invalid-image-mismatched-dimensions.txt";
    ILayeredImage image = new PNGLayeredImage(path);
  }

  /*
  Test loading a layered image with mismatched image types.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageLayersMismatchedImageTypes() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\bad layered image mismatched image types\\mismatched-types.txt";
    ILayeredImage image = new PNGLayeredImage(path);
  }

  //getWidth TESTS


  @Test
  public void testGetWidthLayers() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    assertEquals(5, image.getWidth());
  }

  //getHeight TESTS


  @Test
  public void testGetHeightLayers() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    assertEquals(5, image.getHeight());
  }

  //getMaxColorValue TESTS


  @Test
  public void testGetMaxColor() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    assertEquals(0, image.getMaxColorValue());
  }

  //removeLayer TESTS


  @Test
  public void testRemoveLayer() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    IImage layer = image.getLayer(0);
    image.removeLayer(0);
    assertNotEquals(layer, image.getLayer(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLayerInvalidLayerLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.removeLayer(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLayerInvalidLayerUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.removeLayer(100);
  }

  //toggleLayerTransparency TESTS


  /*
  Test the case where a layer is toggled to be transparent.  If the last file in the list of layers
  is toggled to be transparent, it should not be exported (the next topmost visible layer should be
  exported).
   */
  @Test
  public void testToggleTransparencyOn() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.toggleLayerTransparency(1);
    String outputPath = image.exportImage();
    assertNotEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\PNG\\valid image\\black1.png", outputPath);
  }

  /*
  Test the case where a layer is toggled to be visible.  This image should be exported if it is the
  topmost.
   */
  @Test
  public void testToggleTransparencyOff() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.toggleLayerTransparency(1);
    image.toggleLayerTransparency(1);
    String outputPath = image.exportImage();
    assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\valid image\\"
        + "black1-output.png", outputPath);
  }

  /*
  Test a case where a negative layer number is passed to the method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testToggleTransparencyInvalidLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.toggleLayerTransparency(-1);
  }

  /*
  Test a case where a layer number that is too high is passed to the method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testToggleTransparencyInvalidUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.toggleLayerTransparency(100);
  }

  //exportImage TESTS


  /*
  Test exporting an image if there is at least one visible layer.  The topmost visible layer should
  be exported.
   */
  @Test
  public void testExportImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    String outputPath = image.exportImage();
    assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\valid image\\"
        + "black1-output.png", outputPath);
    assertEquals(5, image.getHeight());
    assertEquals(5, image.getWidth());
    assertEquals(0, image.getMaxColorValue());
    assertEquals(0, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(0).getPixel(1, 1)[0]);

    assertEquals(0, image.getLayer(1).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(0, 1)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 0)[0]);
    assertEquals(0, image.getLayer(1).getPixel(1, 1)[0]);
  }

  /*
  Test a case where all layers are transparent, so there is nothing to export.
   */
  @Test(expected = IllegalStateException.class)
  public void testExportImageAllTransparent() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.toggleLayerTransparency(0);
    image.toggleLayerTransparency(1);
    image.exportImage();
  }

  //saveImage TESTS


  /*
  Test saving an image if no changes have been made to the image.
   */
  @Test
  public void testSaveImageNoChanges() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\save image png test no changes\\no-changes.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.saveImage();

    String outPath = path.substring(0, path.length() - 4) + "-output-png.txt";
    try {
      File input = new File(outPath);
      Scanner reader = new Scanner(input);
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
              + "TestImagesHW06\\PNG\\save image png test no changes\\black0-output.png",
          reader.nextLine());
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
              + "TestImagesHW06\\PNG\\save image png test no changes\\black1-output.png",
          reader.nextLine());
      assertFalse(reader.hasNextLine());
      reader.close();
    } catch (FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }
  }

  /*
  Test saving an image if a layer has been added to the layered image.
   */
  @Test
  public void testSaveImageLayerAdded() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\save image png test with changes\\with-changes.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.addLayer();
    image.saveImage();

    String outPath = path.substring(0, path.length() - 4) + "-output-png.txt";
    try {
      File input = new File(outPath);
      Scanner reader = new Scanner(input);
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\PNG\\save image png test with changes\\black0-output.png",
          reader.nextLine());
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\PNG\\save image png test with changes\\black1-output.png",
          reader.nextLine());
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\PNG\\save image png test with changes\\black0-layer1-output.png",
          reader.nextLine());
      assertFalse(reader.hasNextLine());
      reader.close();
    } catch (FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }
  }

  //getLayer TESTS


  /*
  Test getting a layer from the image.
   */
  @Test
  public void testGetLayer() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    IImage layer = image.getLayer(0);

    IImage expected = new PNGImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\valid image\\black0.png");
    assertEquals(expected.getHeight(), layer.getHeight());
    assertEquals(expected.getWidth(), layer.getWidth());
    assertEquals(expected.getMaxColorValue(), layer.getMaxColorValue());
    assertEquals(expected.getPixel(0, 0)[0], layer.getPixel(0, 0)[0]);
    assertEquals(expected.getPixel(0, 0)[1], layer.getPixel(0, 0)[1]);
    assertEquals(expected.getPixel(0, 0)[2], layer.getPixel(0, 0)[2]);
  }

  /*
  Test passing a negative layer number to the method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetLayerInvalidLayerLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    IImage layer = image.getLayer(-1);
  }

  /*
  Test passing a layer number that is too high to the method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetLayerInvalidLayerUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    IImage layer = image.getLayer(100);
  }

  //addLayer TESTS


  /*
  Test that the method works on a valid layered image.
   */
  @Test
  public void testAddLayerValidImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.addLayer();

    String outputPath = image.exportImage();

    IImage layer = image.getLayer(2);
    IImage expected = new PNGImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\valid image\\black0.png");

    assertEquals(expected.getHeight(), layer.getHeight());
    assertEquals(expected.getWidth(), layer.getWidth());
    assertEquals(expected.getMaxColorValue(), layer.getMaxColorValue());
    assertEquals(expected.getPixel(0, 0)[0], layer.getPixel(0, 0)[0]);
    assertEquals(expected.getPixel(0, 0)[1], layer.getPixel(0, 0)[1]);
    assertEquals(expected.getPixel(0, 0)[2], layer.getPixel(0, 0)[2]);
    assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\valid "
        + "image\\black0-layer1-output.png", outputPath);
  }

  /*
  Test that the method returns an exception if the filename is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddLayerInvalidFilename() {
    String path = "bad path";
    ILayeredImage image = new PNGLayeredImage(path);
    image.addLayer();
  }

  /*
  Test that the method throws an exception if the original text file specifying the locations of
  the layers is empty (e.g. there is no file to copy the new layer from).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddLayerTextFileInvalid() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\invalid text file\\invalid-text-file.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.addLayer();
  }

  //replaceLayer TESTS


  /*
  Test replacing a valid layer with a valid image.
   */
  @Test
  public void testReplaceLayerValidLayerValidImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    IImage newImage = new PNGImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\valid image\\black0.png");
    image.replaceLayer(newImage, 1);
    String outputPath = image.exportImage();
    assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 "
            + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\valid image\\black0-output.png",
        outputPath);
  }

  /*
  Test that an exception is thrown if the layer number is too low.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerIndexTooLow() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    IImage newImage = new PNGImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\valid image\\black0.png");
    image.replaceLayer(newImage, -5);
  }

  /*
  Test that an exception is thrown if the layer number is too high.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerIndexTooHigh() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    IImage newImage = new PNGImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\valid image\\black0.png");
    image.replaceLayer(newImage, 100);
  }

  /*
  Test that an exception is thrown if the new image is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerNewImageNull() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.replaceLayer(null, 0);
  }

  /*
  Test that an exception is thrown if the new image has improper dimensions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerInvalidDimensions() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    IImage newImage = new PNGImage("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500"
        + "\\hw05\\TestImagesHW06\\PNG\\invalid image mismatched dimensions\\woman.png");
    image.replaceLayer(newImage, 0);
  }

  /*
  Test that an exception is thrown if the new image is not the proper image type.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerInvalidImageType() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    IImage newImage = new JPEGImage("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500"
        + "\\hw05\\TestImagesHW06\\JPG\\black0.jpg");
    image.replaceLayer(newImage, 0);
  }

  //getAmountLayers TESTS


  /*
  Test that the correct amount of layers is returned.
   */
  @Test
  public void testGetAmountLayers() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    assertEquals(2, image.getAmountLayers());
  }

  //saveImageAs TESTS


  /*
  Test saving an image as a JPEG.
   */
  @Test
  public void testSaveImageAsJPEG() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.saveImageAs(ImageType.JPEG);

    String outPath = path.substring(0, path.length() - 4) + "-output-jpeg.txt";
    try {
      File input = new File(outPath);
      Scanner reader = new Scanner(input);
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\PNG\\valid image\\black0-output.jpg",
          reader.nextLine());
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\PNG\\valid image\\black1-output.jpg",
          reader.nextLine());
      assertFalse(reader.hasNextLine());
      reader.close();
    } catch (FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }
  }

  /*
  Test saving an image as a PNG.
   */
  @Test
  public void testSaveImageAsPNG() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.saveImageAs(ImageType.PNG);

    String outPath = path.substring(0, path.length() - 4) + "-output-png.txt";
    try {
      File input = new File(outPath);
      Scanner reader = new Scanner(input);
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\PNG\\valid image\\black0-output.png",
          reader.nextLine());
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\PNG\\valid image\\black1-output.png",
          reader.nextLine());
      assertFalse(reader.hasNextLine());
      reader.close();
    } catch (FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }
  }

  /*
  Test saving an image as a PPM.
   */
  @Test
  public void testSaveImageAsPPM() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW06"
        + "\\PNG\\valid image\\valid-png-image.txt";
    ILayeredImage image = new PNGLayeredImage(path);
    image.saveImageAs(ImageType.PPM);

    String outPath = path.substring(0, path.length() - 4) + "-output-ppm.txt";
    try {
      File input = new File(outPath);
      Scanner reader = new Scanner(input);
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\PNG\\valid image\\black0-output.ppm",
          reader.nextLine());
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\PNG\\valid image\\black1-output.ppm",
          reader.nextLine());
      assertFalse(reader.hasNextLine());
      reader.close();
    } catch (FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }
  }
}