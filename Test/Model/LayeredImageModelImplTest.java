package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import image.JPEGLayeredImage;
import manipulation.BlurManip;
import manipulation.IManipulation;
import org.junit.Test;
import image.ILayeredImage;
import java.util.List;
import java.util.ArrayList;

/**
 * Test class for LayeredImageModelImpl.
 */
public class LayeredImageModelImplTest {

  @Test
  public void testApplyManipulation() {
    List<ILayeredImage> images = new ArrayList<>();
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    images.add(image);
    LayeredImageModelImpl model = new LayeredImageModelImpl(images);
    IManipulation blur = new BlurManip();
    model.applyManipulation(blur, 0, 0);
    assertEquals(model.getImage(0, 0).getWidth(), 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyManipulationinvalidManip() {
    List<ILayeredImage> images = new ArrayList<>();
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    images.add(image);
    LayeredImageModelImpl model = new LayeredImageModelImpl(images);
    model.applyManipulation(null, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyManipulationInvalidLayerImageLowerBound() {
    List<ILayeredImage> images = new ArrayList<>();
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    images.add(image);
    LayeredImageModelImpl model = new LayeredImageModelImpl(images);
    IManipulation blur = new BlurManip();
    model.applyManipulation(blur, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyManipulationInvalidLayerImageUpperBound() {
    List<ILayeredImage> images = new ArrayList<>();
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    images.add(image);
    LayeredImageModelImpl model = new LayeredImageModelImpl(images);
    IManipulation blur = new BlurManip();
    model.applyManipulation(blur, 100, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyManipulationInvalidLayerLowerBound() {
    List<ILayeredImage> images = new ArrayList<>();
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    images.add(image);
    LayeredImageModelImpl model = new LayeredImageModelImpl(images);
    IManipulation blur = new BlurManip();
    model.applyManipulation(blur, 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyManipulationInvalidLayerUpperBound() {
    List<ILayeredImage> images = new ArrayList<>();
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    images.add(image);
    LayeredImageModelImpl model = new LayeredImageModelImpl(images);
    IManipulation blur = new BlurManip();
    model.applyManipulation(blur, 0, 100);
  }

  @Test
  public void testExportImage() {
    List<ILayeredImage> images = new ArrayList<>();
    String path = "C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt";
    ILayeredImage image = new JPEGLayeredImage(path);
    images.add(image);
    LayeredImageModelImpl model = new LayeredImageModelImpl(images);
    assertTrue(model.exportImage(0).equals("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImagesHW06\\JPG\\valid image\\valid-image.txt"));
  }
}

