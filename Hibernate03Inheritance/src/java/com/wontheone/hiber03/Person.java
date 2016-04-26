package com.wontheone.hiber03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
//@Entity(name ="Person_Table")
@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
public class Person {

    @Id
    @GeneratedValue
    @Column (name = "id_column")
    private Integer id;

    @Column (name = "name_column")
    private String name;
    
    @OneToMany(mappedBy="owner",
            targetEntity=Car.class,
 fetch=FetchType.EAGER,
 cascade=CascadeType.ALL)
//    @JoinTable(name="Owner_Car", joinColumns=@JoinColumn(name="CarOwner"),
//            inverseJoinColumns=@JoinColumn(name="CarNumber")
//    )
    private Collection<Car> cars = new ArrayList<Car>();

    @XmlTransient
    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
