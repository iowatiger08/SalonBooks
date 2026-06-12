package org.tigersndragons.salonbooks.model.flows;

import org.springframework.stereotype.Component;
import org.tigersndragons.salonbooks.model.Item;

@Component
public class ItemFlowActions extends SalonFlows {
	private static final long serialVersionUID = 1L;

	private Item[] itemList;
	
	public Item[] getItemList() {
		return itemList;
	}

	public void setItemList(Item[] itemList) {
		this.itemList = itemList;
	}
}
