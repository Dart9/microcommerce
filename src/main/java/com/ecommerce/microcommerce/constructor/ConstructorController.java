package com.ecommerce.microcommerce.constructor;

import com.ecommerce.microcommerce.exception.NotFoundProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConstructorController {

    @Autowired
    private ConstructorDao constructorDao;

    @GetMapping(value = "/Constructors")
    public Iterable<Constructor> listConstructors(){
        Iterable<Constructor> constructors = constructorDao.findAll();
        return constructors;
    }

    @GetMapping(value = "/Constructors/{id}")
    public Constructor displayProduct(@PathVariable int id) throws NotFoundProductException {
        Constructor constructor = constructorDao.findById(id);
        if(constructor == null)
            throw new NotFoundProductException("Le constructeur avec l'id " + id + " est INTROUVABLE.");

        return constructor;
    }

}
