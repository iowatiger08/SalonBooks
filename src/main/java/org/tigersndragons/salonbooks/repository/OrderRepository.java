package org.tigersndragons.salonbooks.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tigersndragons.salonbooks.model.Appointment;
import org.tigersndragons.salonbooks.model.Order;
import org.tigersndragons.salonbooks.model.Person;

public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByPersonOrderByIdDesc(Person person);

  Optional<Order> findFirstByAppointmentOrderByIdDesc(Appointment appointment);

  List<Order> findAllByOrderByIdDesc();
}
