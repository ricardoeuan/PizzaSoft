/*
 * Copyright (c) 2015, ricardoeuan
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.pizzasoft.springmvc.controller;

import com.pizzasoft.springmvc.dao.PizzaToppingsDAO;
import com.pizzasoft.springmvc.model.PizzaToppings;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author ricardoeuan
 */

@RestController
@RequestMapping("/menu")
public class PizzaToppingsController {
    
    @Autowired 
    PizzaToppingsDAO pizzaToppingsDAO;
    
    // GET Categories
    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public ResponseEntity<List<PizzaToppings>> listAllPizzaToppings() {
        List<PizzaToppings> pizzasToppings = pizzaToppingsDAO.findAll();
        if(pizzasToppings.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pizzasToppings, HttpStatus.OK);
    }
    
    // GET PizzaToppings
    @RequestMapping(value = "/pizzas/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PizzaToppings> getPizzaToppings(@PathVariable("id") int id) {
        System.out.println("Fetching pizza with id " + id);
        PizzaToppings pizza = pizzaToppingsDAO.findById(id);
        if(pizza == null){
            System.out.println("Pizza with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }
    
    // POST PizzaToppings
    @RequestMapping(value = "/pizzas", method = RequestMethod.POST)
    public ResponseEntity<Void> createPizzaToppings(@RequestBody PizzaToppings pizzaToppings, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Pizza" + pizzaToppings.getName());
        
//        if(pizzaToppingsDAO.doesExist(pizzaToppings)) {
//            System.out.println("PizzaToppings with name " + pizzaToppings.getName() + " already exists.");
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
        
        pizzaToppingsDAO.save(pizzaToppings);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/management/menu/pizzas/{id}").buildAndExpand(pizzaToppings.getIdPizzaToppings()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    // PUT PizzaToppings
    @RequestMapping(value = "/pizzas/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PizzaToppings> updatePizzaToppings(@PathVariable("id") int id, @RequestBody PizzaToppings pizza) {
        System.out.println("Updating pizza " + id);
        
        PizzaToppings currentPizzaToppings = pizzaToppingsDAO.findById(id);
        
        if(currentPizzaToppings == null) {
            System.out.println("Pizza with id " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        currentPizzaToppings.setName(pizza.getName());
        currentPizzaToppings.setDescription(pizza.getDescription());
        currentPizzaToppings.setIngredients(pizza.getIngredients());
        
        pizzaToppingsDAO.update(currentPizzaToppings);
        return new ResponseEntity<>(currentPizzaToppings, HttpStatus.OK);
    }
    
    // DELETE PizzaToppings
    @RequestMapping(value = "/pizzas/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PizzaToppings> deletePizzaToppings(@PathVariable("id") int id) {
        System.out.println("Fetching and deleting pizza with id " + id);
        
        PizzaToppings pizza = pizzaToppingsDAO.findById(id);
        if(pizza == null) {
            System.out.println("Pizza with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        pizzaToppingsDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
    
    // DELETE Categories
    @RequestMapping(value = "/pizzas", method = RequestMethod.DELETE)
    public ResponseEntity<PizzaToppings> deleteAllCategories(@PathVariable("id") int id) {
        System.out.println("Deleting all pizzas");
        
        pizzaToppingsDAO.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }   
}
