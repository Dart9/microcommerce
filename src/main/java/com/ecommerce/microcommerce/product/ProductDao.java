package com.ecommerce.microcommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    public List<Product> findAll();
    public Product findById(int id);
    public Product save (Product product);
    public void delete(Product product);
    public List<Product> findByPriceGreaterThan(double threshold);
    public List<Product> findByDescriptionLike(String search);
}
