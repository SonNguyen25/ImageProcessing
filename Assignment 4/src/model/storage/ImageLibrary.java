package model.storage;

import model.ImageModel;

/**
 * This interface represents the operations offered by an image storage.
 * One object of the model represents one storage of image stored in the program.
 */
public interface ImageLibrary {

  /**
   * Put an image into the hashmap to store it.
   * @param key represents the name of the image.
   * @param model represents the model of the image.
   */
  void put(String key, ImageModel model);

  /**
   * Remove an image from the storage.
   * @param key represents the name of the image.
   * @param model represents the model of the image.
   */
  void remove(String key, ImageModel model);

  public ImageModel contain(String name);
}