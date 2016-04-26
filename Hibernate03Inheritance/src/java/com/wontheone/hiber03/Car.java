/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontheone.hiber03;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
@XmlRootElement
@Entity
// @Entity(name ="Car_Table")
@NamedQuery(name = "FindAll", query = "from Car")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) 
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "owner_column",
            nullable=false)
    private Person owner;
    
    @Column (name = "price_column")
    private double price;

    public Person getOwner() {
        return owner;
    }

    @XmlElement
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(double price) {
        this.price = price;
    }
    
}
