package org.tigersndragons.salonbooks.service;

import java.util.List;
import org.tigersndragons.salonbooks.model.Address;
import org.tigersndragons.salonbooks.model.Person;

public interface AddressService {

  Address getAddressbyId(Long id);

  List<Address> getAddressByPerson(Person p);

  void saveAddress(Address addy);

  Address getBillingAddressForPerson(Person p);

  Address createDefaultAddress();

  Address createDefaultAddressForPerson(Person p);
}
