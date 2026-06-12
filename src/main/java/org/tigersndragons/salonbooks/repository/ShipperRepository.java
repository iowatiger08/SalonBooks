package org.tigersndragons.salonbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tigersndragons.salonbooks.model.ShippingMethod;

public interface ShipperRepository extends JpaRepository<ShippingMethod, Long> {}
