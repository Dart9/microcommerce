package com.ecommerce.microcommerce.constructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstructorDao extends JpaRepository<Constructor, Integer> {

    public List<Constructor> findAll();
    public Constructor findById(int id);
    public Constructor save (Constructor constructor);
    public void delete(Constructor constructor);

}
