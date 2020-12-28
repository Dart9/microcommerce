package com.ecommerce.microcommerce.product;

import com.ecommerce.microcommerce.exception.NotFoundProductException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/Products", method = RequestMethod.GET)
    public MappingJacksonValue listProducts(){
        Iterable<Product> products = productDao.findAll();
        return getFilteredObject(products);
    }

    @GetMapping(value = "/Products/number")
    public long numberOfProduct(){
        return productDao.count();
    }

    @GetMapping(value = "/Products/{id}")
    public MappingJacksonValue displayProduct(@PathVariable int id) throws NotFoundProductException {
        Product product = productDao.findById(id);
        if(product == null)
            throw new NotFoundProductException("Le produit avec l'id " + id + " est INTROUVABLE.");

        return getFilteredObject(product);
    }

    @GetMapping(value = "/ProductsGreaterThan/{threshold}")
    public MappingJacksonValue listProductsGreaterThan(@PathVariable double threshold){
        Iterable<Product> products = productDao.findByPriceGreaterThan(threshold);
        return getFilteredObject(products);
    }

//    @GetMapping(value = "/Products/{description}")
//    public MappingJacksonValue listProductsByDescription(@PathVariable String description){
//        Iterable<Product> products = productDao.findByDescriptionLike("%" + description + "%");
//        return getFilteredObject(products);
//    }

    @DeleteMapping(value = "/Products/{id}")
    public void removeProduct(@PathVariable int id){
        Product product = productDao.getOne(id);
        productDao.delete(product);
    }

    @PutMapping(value = "/Products")
    public void updateProduct(@RequestBody Product product){
        productDao.save(product);
    }

    @PostMapping(value = "/Products")
    public ResponseEntity<Void> addProduct(@RequestBody Product product){

        Product productAdd = productDao.save(product);

        if(productAdd == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdd.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    private FilterProvider getFilterProvider(){
        SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("purchasePrice");
        FilterProvider listOfOurFilter = new SimpleFilterProvider().addFilter("myDynamicFilter", myFilter);
        return listOfOurFilter;
    }

    private MappingJacksonValue getFilteredObject(Object object){
        MappingJacksonValue filteredObject = new MappingJacksonValue(object);
        filteredObject.setFilters(getFilterProvider());
        return filteredObject;
    }
}
