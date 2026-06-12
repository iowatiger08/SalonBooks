package org.tigersndragons.salonbooks.service;

import java.util.List;
import org.tigersndragons.salonbooks.model.ShippingMethod;

public interface ShippingMethodService {
  List<ShippingMethod> getListOfActiveShippers();

  ShippingMethod getShipperById(Long id);

  ShippingMethod createShipper();

  ShippingMethod getDefaultShipper();
}
