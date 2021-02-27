package com.breaded.breadedapi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breaded.breadedapi.entity.Address;
import com.breaded.breadedapi.repository.AddressRepository;
import com.breaded.breadedapi.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}

}
