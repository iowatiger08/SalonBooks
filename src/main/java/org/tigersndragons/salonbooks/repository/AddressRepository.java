package org.tigersndragons.salonbooks.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tigersndragons.salonbooks.model.Address;
import org.tigersndragons.salonbooks.model.Person;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByPersonOrderByUpdateDateDesc(Person person);
    Optional<Address> findFirstByPersonAndBillingAddress(Person person, int billingAddress);
}
