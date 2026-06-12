package org.tigersndragons.salonbooks.model.flows;

import java.io.Serializable;
import java.math.BigDecimal;

public class AddItemActions implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long nextItemId;
	private String sku;
	private int isService;
	private String label;
	private String description;
	private BigDecimal price = new BigDecimal("0.00");
	private BigDecimal unitCost = new BigDecimal("0.00");
	private String addItem;
	public BigDecimal getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}
	public void setNextItemId(Long nextItemId) {
		this.nextItemId= nextItemId;
	}
	public Long getNextItemId() {
		return nextItemId;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getIsService() {
		return isService;
	}
	public void setIsService(int isService) {
		this.isService = isService;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getAddItem() {
		return addItem;
	}
	public void setAddItem(String addItem) {
		this.addItem = addItem;
	}

}
