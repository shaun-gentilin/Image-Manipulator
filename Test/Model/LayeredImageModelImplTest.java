package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import manipulation.BlurManip;
import manipulation.GrayscaleManip;
import manipulation.SepiatoneManip;
import manipulation.SharpenManip;
import org.junit.Test;

public class LayeredImageModelImplTest {


  //testing to see if doesnt break
  @Test
  public void testLoadImageJPEG() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    assertEquals(0, 0);

  }

  //testing to see if doesnt break
  @Test
  public void testLoadImagePNG() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("png", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PNG\\valid image\\valid-png-image.txt");
    assertEquals(0, 0);
  }

  //testing to see if doesnt break
  @Test
  public void testLoadImagePPM() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("ppm", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\PPM\\valid image\\valid-image.txt");
    assertEquals(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidFormat() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("bad", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageInvalidPath() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "bad");

  }

  //testing to see if doesnt break
  @Test
  public void testAddNewLayer() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    image.addNewLayer();
    assertEquals(0, 0);
  }

  //testing to see if doesnt break
  @Test
  public void testRemoveLayer() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    image.removeLayer(0);
    assertEquals(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLayerInvalidLayerNum() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    image.removeLayer(-1);
  }

  /*
  TODO: applyManipulation
   */
  //testing to see if doesnt break
  @Test
  public void testApplyManipBlur() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    image.applyManipulation(0, new BlurManip());
    assertEquals(0, 0);
  }

  //testing to see if doesnt break
  @Test
  public void testApplyManipSepia() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    image.applyManipulation(0, new SepiatoneManip());
    assertEquals(0, 0);
  }

  //testing to see if doesnt break
  @Test
  public void testApplyManipGray() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    image.applyManipulation(0, new GrayscaleManip());
    assertEquals(0, 0);
  }

  //testing to see if doesnt break
  @Test
  public void testApplyManipSharpen() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    image.applyManipulation(0, new SharpenManip());
    assertEquals(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyManipInvalidLayerNum() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    image.applyManipulation(-1, new BlurManip());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyManipNULL() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    image.applyManipulation(0, null);
  }

  //testing to see if doesnt break
  @Test
  public void testSaveLayeredImage() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    assertEquals(image.saveLayeredImage(), "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\"
        + "valid-image-woman-output-jpg.txt");
  }

  //testing to see if doesnt break
  @Test
  public void testExportTopLayer() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    assertEquals(0, 0);
  }

  @Test(expected = IllegalStateException.class)
  public void testExportTopLayerAllInvis() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    image.toggleTransparent(0);
    image.toggleTransparent(1);
    image.exportTopLayer();
  }

  //TODO: saveAsFormat
  //save as jpeg
  @Test
  public void testSaveAsJPEG() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    assertEquals(image.saveAsFormat("jpeg"), "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\"
        + "valid-image-woman-output-jpg.txt");
  }

  //save as ppm
  @Test
  public void testSaveAsPPM() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    assertEquals(image.saveAsFormat("ppm"),"C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\"
        + "valid-image-woman-output-ppm.txt");
  }

  //save as png
  @Test
  public void testSaveAsPNG() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    assertEquals(image.saveAsFormat("png"),"C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\"
        + "valid-image-woman-output-png.txt");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveAsNullFormat() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    assertEquals(image.saveAsFormat("bad"),"C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\"
        + "valid-image-woman-output-jpeg.txt");
  }

  //testing to see if doesnt break
  @Test
  public void testToggleTrans() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    image.toggleTransparent(0);
    assertEquals(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testToggleTransInvalidLayerNum() {
    ILayeredImageModel image = new LayeredImageModelImpl();
    image.loadNewImage("jpeg", "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image woman\\valid-image-woman.txt");
    image.toggleTransparent(-1);
  }

}