# Image-Manipulator
This repository consists of interfaces, classes, and methods for manipulating images.
More specifically, this code has functionality to blur, sharpen, grayscale, and sepiotome images.
The code can also programically generate a checkerboard image that can then be used in the manipulation 
methods.  The code also provides a model interface, as well as an implementation of the interface, 
which allows the storage and manipulation of multiple images.  The user can add, remove, apply a 
manipulation, and get an image from the model.

The design consists of an interface IManipulator which defines methods that manipulator operations should 
implement in order to work properly with the rest of the program.  There are then four function objects
that implement the IManipulator interface which each are able to apply their own manipulation (blur, 
sharpen, sepiatone, and grayscale), to whatever image it is to be applied to.

There is also an IImage interface.  This interface defines methods that various types of images should 
implement.  Classes that are a part of this interface are able to be loaded (either read from a file or
generated programically), as well as exported with their own specific format (e.g. PPM).  
There are also some methods that allow for images to be updated so that the manipulations are able 
to be performed (e.g. setPixel).

Lastly, there is an ImageModel interface which defines methods that should be used by models for this
image manipulator.  The model is able to keep track of multiple images and there are methods that allow
for images to be added, removed, and most importanly, manipulated.  The applyManipulation method of the
model interface takes in a function object from IManipulation and applys that manipulation to the 
specified image.
