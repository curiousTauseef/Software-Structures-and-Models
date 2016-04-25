/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontheone.hiber01.service;

import com.wontheone.hiber01.Car;
import com.wontheone.hiber01.DataProvider;
import com.wontheone.hiber01.HibernateUtil;
import com.wontheone.hiber01.Person;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * REST Web Service
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
@Path("car")
public class CarResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CarResource
     */
    public CarResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.wontheone.hiber01.service.CarResource
     *
     * @return an instance of com.wontheone.hiber01.Car
     */
    @GET
    @Produces("application/xml")
    public List<Car> getXml() {
        return DataProvider.getCars();
    }

    /**
     * Sub-resource GET method for {id}
     */
    @GET
    @Produces("application/xml")
    @Path("{personName}")
    public List<Car> getCarsByPersonName(@PathParam("personName") String personName) {
        return (List<Car>) DataProvider.getCarsByPersonName(personName);
    }
    
    @GET
    @Produces("application/xml")
    @Path("priceLessThan/{price}")
    public List<Car> getCarsLessThanPrice(@PathParam("price") String price) {
        return DataProvider.getCarsByLessThanPrice(Double.parseDouble(price));
    }

    /**
     * PUT method for updating or creating an instance of CarResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(Car content) {
    }
}
