/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontheone.hiber03;

import javax.persistence.Entity;

/**
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
@Entity
public class FlyingCar extends Car {
    
    double altitude;

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    
}
