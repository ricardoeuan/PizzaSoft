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

import com.pizzasoft.springmvc.dao.OrdersDAO;
import com.pizzasoft.springmvc.model.Order;
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
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    OrdersDAO orderDAO;
    
    // GET Categories
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> listAllOrder() {
        List<Order> orders = orderDAO.findAll();
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    // GET Order
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrder(@PathVariable("id") int id) {
        System.out.println("Fetching order with id " + id);
        Order order = orderDAO.findById(id);
        if(order == null){
            System.out.println("Order with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    
    // POST Order
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Void> createOrder(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Order" + order.getName());
        
//        if(orderDAO.doesExist(order)) {
//            System.out.println("Order with name " + order.getName() + " already exists.");
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
        
        orderDAO.save(order);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(order.getIdOrders()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    // PUT Order
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> updateOrder(@PathVariable("id") int id, @RequestBody Order order) {
        System.out.println("Updating order " + id);
        
        Order currentOrder = orderDAO.findById(id);
        
        if(currentOrder == null) {
            System.out.println("Order with id " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        currentOrder.setIdOrders(id);        
        currentOrder.setStatus(order.getStatus());                
        
        orderDAO.update(currentOrder);
        return new ResponseEntity<>(currentOrder, HttpStatus.OK);
    }
    
    // DELETE Order
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") int id) {
        System.out.println("Fetching and deleting order with id " + id);
        
        Order order = orderDAO.findById(id);
        if(order == null) {
            System.out.println("Order with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        orderDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
    
    // DELETE Categories
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<Order> deleteAllCategories(@PathVariable("id") int id) {
        System.out.println("Deleting all orders");
        
        orderDAO.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
