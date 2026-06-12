package org.tigersndragons.salonbooks.core.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tigersndragons.salonbooks.model.Item;
import org.tigersndragons.salonbooks.model.flows.AddItemActions;
import org.tigersndragons.salonbooks.model.flows.ItemFlowActions;
import org.tigersndragons.salonbooks.service.ItemService;

@Transactional
@Controller
public class ItemController {

  @Autowired private ItemService itemService;

  public void setItemService(ItemService itemService) {
    this.itemService = itemService;
  }

  @RequestMapping(value = "/item/list", method = RequestMethod.GET)
  public String showItem(
      // @PathVariable("itemId") Long itemId,
      Map<String, Object> model) {
    ItemFlowActions itemFlowActions = new ItemFlowActions();
    AddItemActions addItemActions = new AddItemActions();
    List<Item> itemList = itemService.getListOfItems();
    itemFlowActions.setItemList(itemList.toArray(new Item[itemList.size()]));
    Long nextItemId = calculateNextItemId(itemList);
    addItemActions.setNextItemId(nextItemId);
    model.put("itemFlowActions", itemFlowActions);
    model.put("addItemActions", addItemActions);
    model.put("itemList", itemList.toArray(new Item[itemList.size()]));
    return "item";
  }

  private Long calculateNextItemId(List<Item> itemList) {

    Item it = itemList.get(0);
    return it.getId() + 1L;
  }

  @RequestMapping(value = "/item/list", method = RequestMethod.POST, params = "addItem")
  public String addItemToOrder(
      @ModelAttribute("addItemActions") AddItemActions addItemActions, Model model) {

    Item it = new Item();
    it.setId(addItemActions.getNextItemId());
    it.setDescription(addItemActions.getDescription());
    it.setIsService(addItemActions.getIsService());
    it.setLabel(addItemActions.getLabel());
    it.setSku(addItemActions.getSku());
    it.setPrice(addItemActions.getPrice());
    it.setCreateDate(LocalDateTime.now());
    it.setUpdateDate(LocalDateTime.now());
    //    oi.setNotes(addOrderItemActions.)
    itemService.saveItem(it);
    return "redirect:/item/list";
  }
}
