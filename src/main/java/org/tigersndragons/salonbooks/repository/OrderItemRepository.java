package org.tigersndragons.salonbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tigersndragons.salonbooks.model.OrderItem;
import org.tigersndragons.salonbooks.model.OrderItemId;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
