package image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Test;

/**
 * Test class for JPEGLayeredImage.
 */
public class JPEGLayeredImageTest {

  //loadImageLayers TESTS

  /*
  Test loading a valid layered image.
   */
  @Test
  public void testLoadImageLayersValidImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    assertEquals(5, image.getHeight());
    assertEquals(5, image.getWidth());
    assertEquals(255, image.getMaxColorValue());
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
    ILayeredImage image = new JPEGLayeredImage(path);
    assertEquals(5, image.getHeight());
  }

  /*
  Test loading a layered image with layers that have mismatched dimensions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageLayersMismatchedDimensions() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\TestImagesHW0"
        + "6\\JPG\\invalid image (not same dimensions)\\non-valid-image-mismatched-dimensions.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
  }

  /*
  Test loading a layered image with mismatched image types.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageLayersMismatchedImageTypes() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05\\"
        + "TestImagesHW06\\bad layered image mismatched image types\\mismatched-types.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
  }

  //getWidth TESTS


  @Test
  public void testGetWidthLayers() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    assertEquals(5, image.getWidth());
  }

  //getHeight TESTS


  @Test
  public void testGetHeightLayers() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    assertEquals(5, image.getHeight());
  }

  //getMaxColorValue TESTS


  @Test
  public void testGetMaxColor() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    assertEquals(255, image.getMaxColorValue());
  }

  //removeLayer TESTS


  @Test
  public void testRemoveLayer() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    IImage layer = image.getLayer(0);
    image.removeLayer(0);
    assertNotEquals(layer, image.getLayer(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLayerInvalidLayerLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    image.removeLayer(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLayerInvalidLayerUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    image.removeLayer(100);
  }

  //toggleLayerTransparency TESTS


  /*
  Test the case where all layers are toggled to be transparent.  If the last file in the list of
  layers is toggled to be transparent, it should not be exported (the next topmost visible layer
  should be exported).  So we should get an exception if all layers are toggled transparent.
   */
  @Test(expected = IllegalStateException.class)
  public void testToggleTransparencyOff() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    image.toggleLayerTransparency(0);
    image.toggleLayerTransparency(1);
    image.exportImage();
  }

  /*
  Test a case where a negative layer number is passed to the method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testToggleTransparencyInvalidLowerBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    image.toggleLayerTransparency(-1);
  }

  /*
  Test a case where a layer number that is too high is passed to the method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testToggleTransparencyInvalidUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    image.toggleLayerTransparency(100);
  }

  //exportImage TESTS


  /*
  Test exporting an image if there is at least one visible layer.  The topmost visible layer should
  be exported.
   */
  @Test
  public void testExportImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    //change one pixel so we know it worked
    image.getLayer(1).setPixel(0,0, new int[]{1,2,3});
    image.exportImage();
    String exportPath = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\black1.jpg";
    JPEGImage outputImage = new JPEGImage(exportPath);
    assertEquals(5, outputImage.getHeight());
    assertEquals(5, outputImage.getWidth());
    assertEquals(255, outputImage.getMaxColorValue());
    assertEquals(1, outputImage.getPixel(0, 0)[0]);
    assertEquals(2, outputImage.getPixel(0, 0)[1]);
    assertEquals(3, outputImage.getPixel(0, 0)[2]);
    assertEquals(0, outputImage.getPixel(0, 1)[0]);
    assertEquals(0, outputImage.getPixel(0, 1)[1]);
    assertEquals(0, outputImage.getPixel(0, 1)[2]);
    assertEquals(0, outputImage.getPixel(0, 2)[0]);
    assertEquals(0, outputImage.getPixel(0, 2)[1]);
    assertEquals(0, outputImage.getPixel(0, 2)[2]);
    assertEquals(0, outputImage.getPixel(0, 3)[0]);
    assertEquals(0, outputImage.getPixel(0, 3)[1]);
    assertEquals(0, outputImage.getPixel(0, 3)[2]);
    assertEquals(0, outputImage.getPixel(0, 4)[0]);
    assertEquals(0, outputImage.getPixel(0, 4)[1]);
    assertEquals(0, outputImage.getPixel(0, 4)[2]);

    //reset it for other tests
    image.getLayer(1).setPixel(0,0, new int[]{0,0,0});
    image.exportImage();
  }

  /*
  Test a case where all layers are transparent, so there is nothing to export.
   */
  @Test(expected = IllegalStateException.class)
  public void testExportImageAllTransparent() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
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
        + "\\JPG\\save image jpg test no changes\\save-image-text-test-no-changes.txt";
    ILayeredImage image = new JPEGLayeredImage(path);

    //change one pixel so we know it works
    image.getLayer(1).setPixel(0,0, new int[]{1,2,3});
    image.saveImage();

    try {
      File input = new File(path);
      Scanner reader = new Scanner(input);
      reader.nextLine();
      JPEGImage savedImage = new JPEGImage(reader.nextLine());
      assertEquals(1, savedImage.getPixel(0,0)[0]);
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
        + "\\JPG\\save image jpg test with changes\\save-image-text-test-with-changes.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    image.addLayer();
    image.saveImage();

    try {
      File input = new File(path);
      Scanner reader = new Scanner(input);
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\JPG\\save image jpg test with changes\\black0.jpg",
          reader.nextLine());
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\JPG\\save image jpg test with changes\\black1.jpg",
          reader.nextLine());
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\JPG\\save image jpg test with changes\\black0-layer2.jpg",
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
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    IImage layer = image.getLayer(0);

    IImage expected = new JPEGImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\black0.jpg");
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
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    IImage layer = image.getLayer(-1);
  }

  /*
  Test passing a layer number that is too high to the method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetLayerInvalidLayerUpperBound() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    IImage layer = image.getLayer(100);
  }

  //addLayer TESTS


  /*
  Test that the method works on a valid layered image.
   */
  @Test
  public void testAddLayerValidImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    image.addLayer();

    IImage layer = image.getLayer(2);
    IImage expected = new JPEGImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\black0.jpg");

    assertEquals(expected.getHeight(), layer.getHeight());
    assertEquals(expected.getWidth(), layer.getWidth());
    assertEquals(expected.getMaxColorValue(), layer.getMaxColorValue());
    assertEquals(expected.getPixel(0, 0)[0], layer.getPixel(0, 0)[0]);
    assertEquals(expected.getPixel(0, 0)[1], layer.getPixel(0, 0)[1]);
    assertEquals(expected.getPixel(0, 0)[2], layer.getPixel(0, 0)[2]);
  }

  /*
  Test that the method returns an exception if the filename is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddLayerInvalidFilename() {
    String path = "bad path";
    ILayeredImage image = new JPEGLayeredImage(path);
    image.addLayer();
  }

  /*
  Test that the method throws an exception if the original text file specifying the locations of
  the layers is empty (e.g. there is no file to copy the new layer from).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddLayerTextFileInvalid() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\invalid text file\\invalid-text-file.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    image.addLayer();
  }

  //replaceLayer TESTS


  /*
  Test replacing a valid layer with a valid image.
   */
  @Test
  public void testReplaceLayerValidLayerValidImage() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    IImage newImage = new JPEGImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\black0.jpg");
    image.replaceLayer(newImage, 1);

    IImage expected = image.getLayer(1);
    assertEquals(expected.getHeight(), newImage.getHeight());
    assertEquals(expected.getWidth(), newImage.getWidth());
    assertEquals(expected.getMaxColorValue(), newImage.getMaxColorValue());
    assertEquals(expected.getPixel(0, 0)[0], newImage.getPixel(0, 0)[0]);
    assertEquals(expected.getPixel(0, 0)[1], newImage.getPixel(0, 0)[1]);
    assertEquals(expected.getPixel(0, 0)[2], newImage.getPixel(0, 0)[2]);
  }

  /*
  Test that an exception is thrown if the layer number is too low.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerIndexTooLow() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    IImage newImage = new JPEGImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\black0.jpg");
    image.replaceLayer(newImage, -5);
  }

  /*
  Test that an exception is thrown if the layer number is too high.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerIndexTooHigh() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    IImage newImage = new JPEGImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\black0.jpg");
    image.replaceLayer(newImage, 100);
  }

  /*
  Test that an exception is thrown if the new image is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerNewImageNull() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    image.replaceLayer(null, 0);
  }

  /*
  Test that an exception is thrown if the new image has improper dimensions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerInvalidDimensions() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    IImage newImage = new JPEGImage("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500"
        + "\\hw05\\TestImagesHW06\\JPG\\invalid image (not same dimensions)\\woman.jpg");
    image.replaceLayer(newImage, 0);
  }

  /*
  Test that an exception is thrown if the new image is not the proper image type.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReplaceLayerInvalidImageType() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    IImage newImage = new PNGImage("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500"
        + "\\hw05\\TestImagesHW06\\PNG\\black0.png");
    image.replaceLayer(newImage, 0);
  }

  //getAmountLayers TESTS


  /*
  Test that the correct amount of layers is returned.
   */
  @Test
  public void testGetAmountLayers() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    assertEquals(2, image.getAmountLayers());
  }

  //saveImageAs TESTS


  /*
  Test saving an image as a JPEG.
   */
  @Test
  public void testSaveImageAsJPEG() {
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    image.saveImageAs(ImageType.JPEG);

    try {
      File input = new File(path);
      Scanner reader = new Scanner(input);
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\JPG\\valid image\\black0.jpg",
          reader.nextLine());
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\JPG\\valid image\\black1.jpg",
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
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    image.saveImageAs(ImageType.PNG);

    try {
      File input = new File(path);
      Scanner reader = new Scanner(input);
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\JPG\\valid image\\black0.png",
          reader.nextLine());
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\JPG\\valid image\\black1.png",
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
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    image.saveImageAs(ImageType.PPM);

    try {
      File input = new File(path);
      Scanner reader = new Scanner(input);
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\JPG\\valid image\\black0.ppm",
          reader.nextLine());
      assertEquals("C:\\Users\\Shaun\\College\\Summer 2021 (Year 3)\\CS3500\\hw05"
              + "\\TestImagesHW06\\JPG\\valid image\\black1.ppm",
          reader.nextLine());
      assertFalse(reader.hasNextLine());
      reader.close();
    } catch (FileNotFoundException error) {
      throw new IllegalArgumentException("Cannot read file.");
    }
  }
}