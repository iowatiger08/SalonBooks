package org.tigersndragons.salonbooks.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tigersndragons.salonbooks.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findBySku(String sku);
    List<Item> findByDeletedFlagOrderByIdAsc(String deletedFlag);
}
