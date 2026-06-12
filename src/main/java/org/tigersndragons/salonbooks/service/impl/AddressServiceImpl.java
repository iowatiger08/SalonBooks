package org.tigersndragons.salonbooks.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tigersndragons.salonbooks.model.Address;
import org.tigersndragons.salonbooks.model.Person;
import org.tigersndragons.salonbooks.repository.AddressRepository;
import org.tigersndragons.salonbooks.service.AddressService;

@Service
@Transactional
public class AddressServiceImpl extends BaseServiceImpl implements AddressService {

  private static final long serialVersionUID = 1L;

  @Autowired private AddressRepository addressRepository;

  public Address getAddressbyId(Long id) {
    return addressRepository.findById(id).orElse(null);
  }

  public List<Address> getAddressByPerson(Person p) {
    return addressRepository.findByPersonOrderByUpdateDateDesc(p);
  }

  public void saveAddress(Address addy) {
    addressRepository.save(addy);
  }

  public Address getBillingAddressForPerson(Person p) {
    return addressRepository.findFirstByPersonAndBillingAddress(p, 1).orElse(null);
  }

  public Address createDefaultAddress() {
    Address emp = new Address();
    emp.setLine1("default");
    emp.setCity("Des Moines");
    emp.setState("IA");
    emp.setZip("50315");
    return emp;
  }

  public Address createDefaultAddressForPerson(Person p) {
    Address addy = createDefaultAddress();
    addy.setPerson(p);
    return addy;
  }
}
