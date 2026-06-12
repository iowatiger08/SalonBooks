package org.tigersndragons.salonbooks.service;

import java.util.List;
import org.tigersndragons.salonbooks.model.Item;

public interface ItemService {
  List<Item> getListOfItems();

  Item getItemById(Long id);

  Item createItem();

  void saveItem(Item item);

  Item getItemBySku(String sku);
}
