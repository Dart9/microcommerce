package com.ecommerce.microcommerce.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {
    public List<Address> findAll();
    public Address findById(int id);
    public Address save (Address address);
    public void delete(Address address);
}
