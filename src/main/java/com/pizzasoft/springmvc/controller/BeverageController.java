/* Copyright (c) 2015, Ricardo Euán
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */
package com.pizzasoft.springmvc.controller;

import java.util.List;
 
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pizzasoft.springmvc.model.Beverage;
import com.pizzasoft.springmvc.dao.BeverageDAO;

/**
 *
 * @author ricardoeuan
 */
@RestController
@RequestMapping("/menu")
public class BeverageController {
    
    @Autowired
    BeverageDAO beverageDAO;
    
    
    // GET Beverages
    @RequestMapping(value = "/beverages", method = RequestMethod.GET)
    public ResponseEntity<List<Beverage>> listAllBeverages() {
        List<Beverage> beverages = beverageDAO.findAll();
        if(beverages.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(beverages, HttpStatus.OK);
    }
    
    // GET Beverage
    @RequestMapping(value = "/beverages/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Beverage> getBeverage(@PathVariable("id") int id) {
        System.out.println("Fetching beverage with id " + id);
        Beverage beverage = beverageDAO.findById(id);
        if(beverage == null){
            System.out.println("Beverage with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(beverage, HttpStatus.OK);
    }
    
    // POST Beverage
    @RequestMapping(value = "/beverages", method = RequestMethod.POST)
    public ResponseEntity<Void> createBeverage(@RequestBody Beverage beverage, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Beverager" + beverage.getName());
        
//        if(beverageDAO.doesExist(beverage)) {
//            System.out.println("Beverage with name " + beverage.getName() + " already exists.");
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
        
        beverageDAO.save(beverage);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/management/menu/beverages/{id}").buildAndExpand(beverage.getIdBeverages()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    // PUT Beverage
    @RequestMapping(value = "/beverages/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Beverage> updateBeverage(@PathVariable("id") int id, @RequestBody Beverage beverage) {
        System.out.println("Updating beverage " + id);
        
        Beverage currentBeverage = beverageDAO.findById(id);
        
        if(currentBeverage == null) {
            System.out.println("Beverage with id " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        currentBeverage.setName(beverage.getName());
        currentBeverage.setDescription(beverage.getDescription());
        currentBeverage.setCost(beverage.getCost());
        currentBeverage.setAlcohol(beverage.getAlcohol());
        currentBeverage.setMeasure(beverage.getMeasure());
        
        beverageDAO.update(currentBeverage);
        return new ResponseEntity<>(currentBeverage, HttpStatus.OK);
    }
    
    // DELETE Beverage
    @RequestMapping(value = "/beverages/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Beverage> deleteBeverage(@PathVariable("id") int id) {
        System.out.println("Fetching and deleting beverage with id " + id);
        
        Beverage beverage = beverageDAO.findById(id);
        if(beverage == null) {
            System.out.println("Beverage with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        beverageDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
    
    // DELETE Beverages
    @RequestMapping(value = "/beverages", method = RequestMethod.DELETE)
    public ResponseEntity<Beverage> deleteAllBeverages(@PathVariable("id") int id) {
        System.out.println("Deleting all beverages");
        
        beverageDAO.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
