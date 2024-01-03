package org.example.addressserivce.service;

import org.example.addressserivce.entity.Address;
import org.example.addressserivce.repo.AddressRepo;
import org.example.addressserivce.response.AddressResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class AddressService {

    @Autowired
    AddressRepo addressRepo;
    @Autowired
    private ModelMapper modelMapper;
    public AddressResponse findAddressByEmployeeId(int employeeId){
        Address address = addressRepo.findAddressByEmployeeId(String.valueOf(employeeId));

        AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);
        return addressResponse;
    }
}
