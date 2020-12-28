package com.ecommerce.microcommerce.product;

import com.ecommerce.microcommerce.constructor.Constructor;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
//@JsonFilter("myDynamicFilter")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Length(min=3, max=20)
    private String description;

    @Min(value=1)
    private double price;

    @Min(value=1)
    private double purchasePrice;

    @ManyToOne @JoinColumn(name = "constructor_id", nullable = false)
    @JsonManagedReference
    private Constructor constructor;

    public Product(){

    }

//    public Product(int id, String description, double price, double purchasePrice) {
//        this.id = id;
//        this.description = description;
//        this.price = price;
//        this.purchasePrice = purchasePrice;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    @Override
    public String toString() {
        return "id = " + id
                + " description = " + description
                + " price = " + price
                + " purchasePrice = " + purchasePrice;
    }
}
