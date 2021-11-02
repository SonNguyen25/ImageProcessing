package model.storage;

import java.util.HashMap;
import java.util.Map;

import model.ImageModel;

/**
 * The class that implements the ImageLibrary model.
 * The class represents the storage for images.
 * You can perform operations related to methods that manipulate the storage.
 */
public class ImageStorage implements ImageLibrary {
  private final Map<String, ImageModel> storage;

  /**
   * A constructor to initiates a hashmap that acts like a storage for images.
   */
  public ImageStorage() {
    this.storage = new HashMap<>();
  }

  /**
   * Put an image into the hashmap to store it.
   * @param key represents the name of the image.
   * @param model represents the model of the image.
   */
  @Override
  public void put(String key, ImageModel model) {
    this.storage.put(key, model);
  }

  /**
   * Remove an image from the storage.
   * @param key represents the name of the image.
   * @param model represents the model of the image.
   */
  @Override
  public void remove(String key, ImageModel model) {
    this.storage.remove(key, model);
  }

  @Override
  public ImageModel contain(String name) {
    ImageModel copy;
    copy = this.storage.getOrDefault(name, null);
    return copy;
  }


}