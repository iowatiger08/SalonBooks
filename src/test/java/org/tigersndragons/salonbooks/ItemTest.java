package org.tigersndragons.salonbooks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tigersndragons.salonbooks.model.Item;
import org.tigersndragons.salonbooks.service.ItemService;

public class ItemTest extends BaseTestCase {

  @Autowired ItemService itemService;

  private Item e1;
  private Item e2;

  @BeforeEach
  public void setUp() {
    e1 = new Item();
    e2 = new Item();
  }

  @Test
  public void testMatchId() {
    e1.setId(0L);
    e2.setId(0L);
    assertTrue(e1.equals(e2));
    e2.setId(1L);
    assertFalse(e1.equals(e2));
  }

  @Test
  public void testMatchingNotes() {
    e1.setId(0L);
    e1.setDescription("auser");
    e2.setId(0L);
    e2.setDescription("auser");
    assertTrue(e1.equals(e2));
    assertTrue(e1.getDescription().equals(e2.getDescription()));
    e2.setId(1L);
    assertFalse(e1.equals(e2));
    assertTrue(e1.getDescription().equals(e2.getDescription()));
  }

  private Item getDefaultItem() {
    Item item = new Item();
    item.setId(0L);
    item.setIsService(1);
    item.setSku("TEST01");
    item.setLabel("IS FOR TEST");
    item.setDeletedFlag("N");
    item.setPrice(new BigDecimal("0.01"));
    return item;
  }

  @Test
  public void retrieveDefaultItem() {
    Item emp = itemService.createItem();
    assertTrue(StringUtils.equals(emp.getSku(), getDefaultItem().getSku()));
  }

  @Test
  public void retrieveListOfItem() {
    List<Item> apptList = itemService.getListOfItems();
    assertTrue(CollectionUtils.isNotEmpty(apptList) && apptList.size() > 0);
    assertTrue(apptList.get(0).equals(this.getDefaultItem()));
  }

  @Test
  public void retrieveItemById() {
    Item emp = itemService.getItemById(0L);
    Item e2 = getDefaultItem();
    assertTrue(emp.equals(e2));
    assertTrue(StringUtils.equals(emp.getSku(), e2.getSku()));
  }

  @Test
  public void testgetItemBySku() {
    Item anItem = itemService.getItemBySku("TEST01");
    assertNotNull(anItem);
    assertTrue(getDefaultItem().equals(anItem));
  }

  @Test
  public void testSaveDefaultItem() {
    Item io = getDefaultItem();
    io.setId(null);
    io.setSku("TEST09");
    itemService.saveItem(io);
    assertNotNull(io.getId());
  }
}
