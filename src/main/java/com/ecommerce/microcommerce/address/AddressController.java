package com.ecommerce.microcommerce.address;

import com.ecommerce.microcommerce.address.AddressDao;
import com.ecommerce.microcommerce.address.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    private AddressDao addressDao;

    @GetMapping(value = "/Addresses")
    public Iterable<Address> listAddresses(){
        Iterable<Address> addresses = addressDao.findAll();
        return addresses;
    }
}
