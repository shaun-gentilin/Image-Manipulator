package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import image.hw5.IImage;
import image.hw5.PPMImage;
import manipulation.BlurManip;
import manipulation.GrayscaleManip;
import manipulation.SepiatoneManip;
import manipulation.SharpenManip;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for our Image Model.
 */
public class ImageModelImplTest {

  //applyManipulation TESTS

  IImage simple1;
  IImage koala1;
  List<IImage> applyManipulationImages1;

  ImageModel applyManipulationModel1;
  ImageModel applyManipulationModel2;
  ImageModel applyManipulationModel3;
  ImageModel applyManipulationModel4;

  IImage simple2;
  IImage koala2;
  List<IImage> applyManipulationImages2;
  ImageModel applyManipulationModel5;

  IImage simple3;
  IImage koala3;
  List<IImage> applyManipulationImages3;
  ImageModel applyManipulationModel6;

  IImage simple4;
  IImage koala4;
  List<IImage> applyManipulationImages4;
  ImageModel applyManipulationModel7;

  @Before
  public void applyManipulationInit() {
    simple1 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    koala1 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    applyManipulationImages1 = new ArrayList<>();
    applyManipulationImages1.add(simple1);
    applyManipulationImages1.add(koala1);

    applyManipulationModel1 = new ImageModelImpl(applyManipulationImages1);
    applyManipulationModel2 = new ImageModelImpl(applyManipulationImages1);
    applyManipulationModel3 = new ImageModelImpl(applyManipulationImages1);
    applyManipulationModel4 = new ImageModelImpl(applyManipulationImages1);

    simple2 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    koala2 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    applyManipulationImages2 = new ArrayList<>();
    applyManipulationImages2.add(simple2);
    applyManipulationImages2.add(koala2);

    applyManipulationModel5 = new ImageModelImpl(applyManipulationImages2);

    simple3 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    koala3 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    applyManipulationImages3 = new ArrayList<>();
    applyManipulationImages3.add(simple3);
    applyManipulationImages3.add(koala3);

    applyManipulationModel6 = new ImageModelImpl(applyManipulationImages3);

    simple4 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    koala4 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    applyManipulationImages4 = new ArrayList<>();
    applyManipulationImages4.add(simple4);
    applyManipulationImages4.add(koala4);

    applyManipulationModel7 = new ImageModelImpl(applyManipulationImages4);
  }

  /*
  Test a case where the grayscale manipulation is called.
   */
  @Test
  public void testApplyManipulationGrayscale() {
    IImage image = applyManipulationModel4.applyManipulation(new GrayscaleManip(), 0);
    assertEquals(90, image.getPixel(0, 0)[0]);
    assertEquals(90, image.getPixel(0, 0)[1]);
    assertEquals(90, image.getPixel(0, 0)[2]);
    assertEquals(80, image.getPixel(1, 0)[0]);
    assertEquals(80, image.getPixel(1, 0)[1]);
    assertEquals(80, image.getPixel(1, 0)[2]);
    assertEquals(100, image.getPixel(0, 1)[0]);
    assertEquals(100, image.getPixel(0, 1)[1]);
    assertEquals(100, image.getPixel(0, 1)[2]);
    assertEquals(72, image.getPixel(1, 1)[0]);
    assertEquals(72, image.getPixel(1, 1)[1]);
    assertEquals(72, image.getPixel(1, 1)[2]);
  }

  /*
  Test a case where the sepiatone manipulation is applied.
   */
  @Test
  public void testApplyManipulationSepiatone() {
    IImage image = applyManipulationModel5.applyManipulation(new SepiatoneManip(), 0);
    assertEquals(119, image.getPixel(0, 0)[0]);
    assertEquals(106, image.getPixel(0, 0)[1]);
    assertEquals(83, image.getPixel(0, 0)[2]);
    assertEquals(128, image.getPixel(1, 0)[0]);
    assertEquals(114, image.getPixel(1, 0)[1]);
    assertEquals(89, image.getPixel(1, 0)[2]);
    assertEquals(122, image.getPixel(0, 1)[0]);
    assertEquals(109, image.getPixel(0, 1)[1]);
    assertEquals(84, image.getPixel(0, 1)[2]);
    assertEquals(109, image.getPixel(1, 1)[0]);
    assertEquals(97, image.getPixel(1, 1)[1]);
    assertEquals(76, image.getPixel(1, 1)[2]);
  }

  /*
  Test a case where the sharpen manipulation is applied.
   */
  @Test
  public void testApplyManipulationSharpen() {
    IImage image = applyManipulationModel6.applyManipulation(new SharpenManip(), 0);
    assertEquals(171, image.getPixel(0, 0)[0]);
    assertEquals(148, image.getPixel(0, 0)[1]);
    assertEquals(152, image.getPixel(0, 0)[2]);
    assertEquals(245, image.getPixel(1, 0)[0]);
    assertEquals(113, image.getPixel(1, 0)[1]);
    assertEquals(174, image.getPixel(1, 0)[2]);
    assertEquals(137, image.getPixel(0, 1)[0]);
    assertEquals(170, image.getPixel(0, 1)[1]);
    assertEquals(141, image.getPixel(0, 1)[2]);
    assertEquals(113, image.getPixel(1, 1)[0]);
    assertEquals(131, image.getPixel(1, 1)[1]);
    assertEquals(255, image.getPixel(1, 1)[2]);
  }

  @Test
  public void testApplyManipulationBlur() {
    IImage image = applyManipulationModel7.applyManipulation(new BlurManip(), 0);
    assertEquals(58, image.getPixel(0, 0)[0]);
    assertEquals(46, image.getPixel(0, 0)[1]);
    assertEquals(44, image.getPixel(0, 0)[2]);
    assertEquals(68, image.getPixel(1, 0)[0]);
    assertEquals(37, image.getPixel(1, 0)[1]);
    assertEquals(61, image.getPixel(1, 0)[2]);
    assertEquals(41, image.getPixel(0, 1)[0]);
    assertEquals(51, image.getPixel(0, 1)[1]);
    assertEquals(53, image.getPixel(0, 1)[2]);
    assertEquals(44, image.getPixel(1, 1)[0]);
    assertEquals(42, image.getPixel(1, 1)[1]);
    assertEquals(81, image.getPixel(1, 1)[2]);
  }

  //exception tests

  /*
  Test a case where the manipulation passed to the method is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testApplyManipulationNullManip() {
    applyManipulationModel1.applyManipulation(null, 0);
  }

  /*
  Test a case where the image number is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testApplyManipulationNegativeImageNumber() {
    applyManipulationModel2.applyManipulation(new GrayscaleManip(), -5);
  }

  /*
Test a case where the image number is negative.
 */
  @Test(expected = IllegalArgumentException.class)
  public void testApplyManipulationImageNumberTooHigh() {
    applyManipulationModel3.applyManipulation(new GrayscaleManip(), 10);
  }


  //exportImage TESTS
  IImage exportImageSimple1;
  IImage exportImageKoala1;
  List<IImage> exportImageImages1;
  ImageModel exportImageModel1;

  IImage exportImageSimple2;
  IImage exportImageKoala2;
  List<IImage> exportImageImages2;
  ImageModel exportImageModel2;

  IImage exportImageSimple3;
  IImage exportImageKoala3;
  List<IImage> exportImageImages3;
  ImageModel exportImageModel3;

  IImage exportImageSimple4;
  IImage exportImageKoala4;
  List<IImage> exportImageImages4;
  ImageModel exportImageModel4;

  @Before
  public void testExportImageInit() {
    exportImageSimple1 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    exportImageKoala1 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    exportImageImages1 = new ArrayList<>();
    exportImageImages1.add(exportImageSimple1);
    exportImageImages1.add(exportImageKoala1);
    exportImageModel1 = new ImageModelImpl(exportImageImages1);

    exportImageSimple2 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    exportImageKoala2 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    exportImageImages2 = new ArrayList<>();
    exportImageImages2.add(exportImageSimple1);
    exportImageImages2.add(exportImageKoala1);
    exportImageModel2 = new ImageModelImpl(exportImageImages2);

    exportImageSimple3 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    exportImageKoala3 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    exportImageImages3 = new ArrayList<>();
    exportImageImages3.add(exportImageSimple1);
    exportImageImages3.add(exportImageKoala1);
    exportImageModel3 = new ImageModelImpl(exportImageImages3);

    exportImageSimple4 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    exportImageKoala4 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    exportImageImages4 = new ArrayList<>();
    exportImageImages4.add(exportImageSimple1);
    exportImageImages4.add(exportImageKoala1);
    exportImageModel4 = new ImageModelImpl(exportImageImages4);
  }

  /*
  Test a case where we are exporting a valid image that has had no manipulations on it.
   */
  @Test
  public void testExportImageValidImageNoManipulations() {
    exportImageModel1.exportImage(0);
    IImage outputImage = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels-output.ppm");
    assertEquals(2, outputImage.getWidth());
    assertEquals(2, outputImage.getHeight());
    assertEquals(255, outputImage.getMaxColorValue());
    assertEquals(101, outputImage.getPixel(0, 0)[0]);
    assertEquals(90, outputImage.getPixel(0, 0)[1]);
    assertEquals(58, outputImage.getPixel(0, 0)[2]);
    assertEquals(200, outputImage.getPixel(1, 0)[0]);
    assertEquals(44, outputImage.getPixel(1, 0)[1]);
    assertEquals(87, outputImage.getPixel(1, 0)[2]);
    assertEquals(56, outputImage.getPixel(0, 1)[0]);
    assertEquals(120, outputImage.getPixel(0, 1)[1]);
    assertEquals(43, outputImage.getPixel(0, 1)[2]);
    assertEquals(24, outputImage.getPixel(1, 1)[0]);
    assertEquals(68, outputImage.getPixel(1, 1)[1]);
    assertEquals(255, outputImage.getPixel(1, 1)[2]);
  }

  /*
  Test a case where we are exporting a valid image that has been manipulated.
   */
  @Test
  public void testExportImageValidImageWithManipulation() {
    exportImageModel2.applyManipulation(new GrayscaleManip(), 0);
    exportImageModel2.exportImage(0);
    IImage outputImage = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels-output.ppm");
    assertEquals(2, outputImage.getWidth());
    assertEquals(2, outputImage.getHeight());
    assertEquals(255, outputImage.getMaxColorValue());
    assertEquals(90, outputImage.getPixel(0, 0)[0]);
    assertEquals(90, outputImage.getPixel(0, 0)[1]);
    assertEquals(90, outputImage.getPixel(0, 0)[2]);
    assertEquals(80, outputImage.getPixel(1, 0)[0]);
    assertEquals(80, outputImage.getPixel(1, 0)[1]);
    assertEquals(80, outputImage.getPixel(1, 0)[2]);
    assertEquals(100, outputImage.getPixel(0, 1)[0]);
    assertEquals(100, outputImage.getPixel(0, 1)[1]);
    assertEquals(100, outputImage.getPixel(0, 1)[2]);
    assertEquals(72, outputImage.getPixel(1, 1)[0]);
    assertEquals(72, outputImage.getPixel(1, 1)[1]);
    assertEquals(72, outputImage.getPixel(1, 1)[2]);
  }

  //exception tests

  /*
  Test a case where the given image num is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExportImageNegativeImageNum() {
    exportImageModel3.exportImage(-1);
  }

  /*
  Test a case where the given image num is negative.
 */
  @Test(expected = IllegalArgumentException.class)
  public void testExportImageImageNumTooHigh() {
    exportImageModel4.exportImage(10);
  }

  //addImage TESTS


  IImage addImageSimple1;
  IImage addImageKoala1;
  List<IImage> addImageImages1;
  ImageModel addImageModel1;

  List<IImage> addImageImages2;
  ImageModel addImageModel2;

  @Before
  public void testAddImageInit() {
    addImageSimple1 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    addImageKoala1 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    addImageImages1 = new ArrayList<>();
    addImageImages1.add(addImageSimple1);
    addImageImages1.add(addImageKoala1);
    addImageModel1 = new ImageModelImpl(addImageImages1);

    addImageImages2 = new ArrayList<>();
    addImageModel2 = new ImageModelImpl(addImageImages2);
  }

  /*
  Test a case where a valid image is successfully added to the list if the list was empty.
   */
  @Test
  public void testAddImageValidImageToEmpty() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    int imgNum = addImageModel2.addImage(image);
    assertEquals(image, addImageModel2.getImage(0));
    assertEquals(0, imgNum);
  }

  /*
 Test a case where a valid image is successfully added to the list if the list was not empty.
  */
  @Test
  public void testAddImageValidImageToNonEmpty() {
    IImage image = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    addImageModel1.addImage(image);
    assertEquals(image, addImageModel1.getImage(2));
  }

  //exception tests

  /*
  Test a case where the given image is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddImageNullImage() {
    addImageModel2.addImage(null);
  }

  //removeImage TESTS

  IImage removeImageSimple1;
  IImage removeImageKoala1;
  List<IImage> removeImageImages1;
  ImageModel removeImageModel1;

  List<IImage> removeImageImages2;
  ImageModel removeImageModel2;

  IImage removeImageSimple3;
  IImage removeImageKoala3;
  List<IImage> removeImageImages3;
  ImageModel removeImageModel3;

  @Before
  public void testRemoveImageInit() {
    removeImageSimple1 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    removeImageKoala1 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    removeImageImages1 = new ArrayList<>();
    removeImageImages1.add(removeImageSimple1);
    removeImageImages1.add(removeImageKoala1);
    removeImageModel1 = new ImageModelImpl(removeImageImages1);

    removeImageImages2 = new ArrayList<>();
    removeImageModel2 = new ImageModelImpl(removeImageImages2);

    removeImageSimple3 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    removeImageKoala3 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    removeImageImages3 = new ArrayList<>();
    removeImageImages3.add(removeImageSimple3);
    removeImageImages3.add(removeImageKoala3);
    removeImageModel3 = new ImageModelImpl(removeImageImages3);
  }

  /*
  Test a case where the image is successfully removed.
   */
  @Test
  public void testRemoveImageValidImage() {
    IImage image = removeImageModel1.getImage(0);
    removeImageModel1.removeImage(0);
    assertNotEquals(image, removeImageModel1.getImage(0));
  }

  //exception tests

  /*
  Test a case where the requested image had a negative index.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveImageNegativeIndex() {
    removeImageModel3.removeImage(-4);
  }

  /*
  Test a case where the requested image had too large of an index.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveImageHighIndex() {
    removeImageModel3.removeImage(10);
  }

  /*
  Test a case where there are no images (the list is empty).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveImageEmptyImageList() {
    removeImageModel2.removeImage(0);
  }

  //getImage TESTS


  IImage getImageSimple1;
  IImage getImageKoala1;
  List<IImage> getImageImages1;
  ImageModel getImageModel1;

  List<IImage> getImageImages2;
  ImageModel getImageModel2;

  IImage getImageSimple3;
  IImage getImageKoala3;
  List<IImage> getImageImages3;
  ImageModel getImageModel3;

  @Before
  public void testGetImageInit() {
    getImageSimple1 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    getImageKoala1 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    getImageImages1 = new ArrayList<>();
    getImageImages1.add(getImageSimple1);
    getImageImages1.add(getImageKoala1);
    getImageModel1 = new ImageModelImpl(getImageImages1);

    getImageImages2 = new ArrayList<>();
    getImageModel2 = new ImageModelImpl(getImageImages2);

    getImageSimple3 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\valid-image-four-pixels.ppm");
    getImageKoala3 = new PPMImage("C:\\Users\\Shaun\\College\\Summer 2021 "
        + "(Year 3)\\CS3500\\hw05\\TestImages\\Koala.ppm");
    getImageImages3 = new ArrayList<>();
    getImageImages3.add(getImageSimple3);
    getImageImages3.add(getImageKoala3);
    getImageModel3 = new ImageModelImpl(getImageImages3);
  }

  /*
  Test getting a valid image from a non empty list.
   */
  @Test
  public void testGetImageValidImageNonEmptyList() {
    assertEquals(getImageSimple1, getImageModel1.getImage(0));
    assertEquals(getImageKoala1, getImageModel1.getImage(1));
  }

  //exception tests
  /*
  Test a case where the requested image had a negative index.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetImageNegativeIndex() {
    getImageModel3.removeImage(-4);
  }

  /*
  Test a case where the requested image had too large of an index.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetImageHighIndex() {
    getImageModel3.removeImage(10);
  }

  /*
  Test a case where there are no images (the list is empty).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetImageEmptyImageList() {
    getImageModel2.removeImage(0);
  }

}