package org.tigersndragons.salonbooks.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tigersndragons.salonbooks.model.ShippingMethod;
@Service
public interface ShippingMethodService {
	public List<ShippingMethod> getListOfActiveShippers();
	
	public ShippingMethod getShipperById(Long id);
	
	public ShippingMethod createShipper();
	
	public ShippingMethod getDefaultShipper();
}
