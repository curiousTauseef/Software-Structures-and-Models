package com.wontheone.hiber01;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name ="Person_Table")
public class Person {

    @Id
    @GeneratedValue
    @Column (name = "id_column")
    private Integer id;

    @Column (name = "name_column")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
