package org.tigersndragons.salonbooks.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tigersndragons.salonbooks.ServiceUtils;
import org.tigersndragons.salonbooks.model.Item;
import org.tigersndragons.salonbooks.repository.ItemRepository;
import org.tigersndragons.salonbooks.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl extends BaseServiceImpl implements ItemService {

    private static final long serialVersionUID = 1L;

    @Autowired private ItemRepository itemRepository;

    public List<Item> getListOfItems() {
        return itemRepository.findByDeletedFlagOrderByIdAsc("N");
    }

    public Item getItemById(Long id) {
        ServiceUtils.assertNotNull("id cannot be null", id);
        return itemRepository.findById(id).orElse(null);
    }

    public Item createItem() {
        return getDefaultItem();
    }

    public void saveItem(Item item) {
        ServiceUtils.assertNotNull("item cannot be null", item);
        itemRepository.save(item);
    }

    public Item getItemBySku(String sku) {
        ServiceUtils.assertNotNull("sku cannot be null", sku);
        return itemRepository.findBySku(sku).orElse(null);
    }

    private Item getDefaultItem() {
        Item item = new Item();
        item.setIsService(1);
        item.setSku("TEST01");
        item.setLabel("IS FOR TEST");
        item.setDeletedFlag("N");
        item.setPrice(new BigDecimal("0.01"));
        return item;
    }
}
