package org.tigersndragons.salonbooks.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class MonthlyTotals implements Serializable{
//extends SalonObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String month;
	private String year;
	
	private int numOfOrders;
	private BigDecimal totalForMonth;
	private BigDecimal inventoryCosts;
	private BigDecimal inventoryItemsSold;
	private int servicesOnlyOrders;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getNumOfOrders() {
		return numOfOrders;
	}
	public void setNumOfOrders(int numOfOrders) {
		this.numOfOrders = numOfOrders;
	}
	public BigDecimal getTotalForMonth() {
		return totalForMonth;
	}
	public void setTotalForMonth(BigDecimal totalForMonth) {
		this.totalForMonth = totalForMonth;
	}
	public BigDecimal getInventoryCosts() {
		return inventoryCosts;
	}
	public void setInventoryCosts(BigDecimal inventoryCosts) {
		this.inventoryCosts = inventoryCosts;
	}
	public BigDecimal getInventoryItemsSold() {
		return inventoryItemsSold;
	}
	public void setInventoryItemsSold(BigDecimal inventoryItemsSold) {
		this.inventoryItemsSold = inventoryItemsSold;
	}
	public int getServicesOnlyOrders() {
		return servicesOnlyOrders;
	}
	public void setServicesOnlyOrders(int servicesOnlyOrders) {
		this.servicesOnlyOrders = servicesOnlyOrders;
	}
	
	

}
