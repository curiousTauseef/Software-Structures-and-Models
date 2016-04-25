/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontheone.hiber01;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
@Entity(name ="Car_Table")
public class Car {
    
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn (name = "owner_column")
    private Person owner;
    
    @Column (name = "price_column")
    private double price;

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
