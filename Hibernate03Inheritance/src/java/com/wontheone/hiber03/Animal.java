/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontheone.hiber03;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
public class Animal {
    
    String name;
    @Id
    @GeneratedValue
    private Integer id;

    public Animal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
