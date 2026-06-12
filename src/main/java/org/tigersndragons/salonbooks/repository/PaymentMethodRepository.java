package org.tigersndragons.salonbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tigersndragons.salonbooks.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {}
