package org.tigersndragons.salonbooks.service;

import java.util.List;

import org.tigersndragons.salonbooks.model.Address;
import org.tigersndragons.salonbooks.model.Person;

public interface AddressService {

	public Address getAddressbyId(Long id);
	
	public List<Address> getAddressByPerson(Person p);
	
	public void saveAddress(Address addy);
	
	public Address getBillingAddressForPerson(Person p);

	public Address createDefaultAddress();
	public Address createDefaultAddressForPerson(Person p);
}
