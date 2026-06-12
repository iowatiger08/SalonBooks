package org.tigersndragons.salonbooks.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tigersndragons.salonbooks.model.Item;
@Service
public interface ItemService {
	public List<Item> getListOfItems();
	
	public Item getItemById(Long id);
	
	public Item createItem();
	
	public void saveItem(Item item);
	
	public Item getItemBySku(String sku);
}
