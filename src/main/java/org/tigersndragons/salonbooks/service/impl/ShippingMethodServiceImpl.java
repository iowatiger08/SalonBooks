package org.tigersndragons.salonbooks.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tigersndragons.salonbooks.model.ShippingMethod;
import org.tigersndragons.salonbooks.model.type.ShipperType;
import org.tigersndragons.salonbooks.repository.ShipperRepository;
import org.tigersndragons.salonbooks.service.ShippingMethodService;

@Service
public class ShippingMethodServiceImpl extends BaseServiceImpl implements ShippingMethodService {

  private static final long serialVersionUID = 1L;

  @Autowired private ShipperRepository shipperRepository;

  public List<ShippingMethod> getListOfActiveShippers() {
    return shipperRepository.findAll();
  }

  public ShippingMethod getShipperById(Long id) {
    return shipperRepository.findById(id).orElse(null);
  }

  public ShippingMethod createShipper() {
    return new ShippingMethod();
  }

  public ShippingMethod getDefaultShipper() {
    ShippingMethod shipper = new ShippingMethod();
    shipper.setId(0L);
    shipper.setName(ShipperType.WALKIN);
    return shipper;
  }
}
