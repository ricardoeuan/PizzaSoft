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

import com.pizzasoft.springmvc.dao.ExtraDAO;
import com.pizzasoft.springmvc.model.Extra;
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
public class ExtraController {
    @Autowired
    ExtraDAO extraDAO;
    
    
    // GET Extras
    @RequestMapping(value = "/extras", method = RequestMethod.GET)
    public ResponseEntity<List<Extra>> listAllExtras() {
        List<Extra> extras = extraDAO.findAll();
        if(extras.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(extras, HttpStatus.OK);
    }
    
    // GET 
    @RequestMapping(value = "/extras/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Extra> getExtra(@PathVariable("id") int id) {
        System.out.println("Fetching extra with id " + id);
        Extra beverage = extraDAO.findById(id);
        if(beverage == null){
            System.out.println("Extra with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(beverage, HttpStatus.OK);
    }
    
    // POST Extra
    @RequestMapping(value = "/extras", method = RequestMethod.POST)
    public ResponseEntity<Void> createExtra(@RequestBody Extra extra, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Extra" + extra.getName());
        
//        if(extraDAO.doesExist(currentExtra)) {
//            System.out.println("Extra with name " + currentExtra.getName() + " already exists.");
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
        
        extraDAO.save(extra);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/management/menu/extras/{id}").buildAndExpand(extra.getIdExtras()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    // PUT Extra
    @RequestMapping(value = "/extras/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Extra> updateExtra(@PathVariable("id") int id, @RequestBody Extra extra) {
        System.out.println("Updating extra " + id);
        
        Extra currentExtra = extraDAO.findById(id);
        
        if(currentExtra == null) {
            System.out.println("Extra with id " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        currentExtra.setName(extra.getName());
        currentExtra.setDescription(extra.getDescription());
        currentExtra.setCost(extra.getCost());
        currentExtra.setCategory(extra.getCategory());
        
        extraDAO.update(currentExtra);
        return new ResponseEntity<>(currentExtra, HttpStatus.OK);
    }
    
    // DELETE Extra
    @RequestMapping(value = "/extras/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Extra> deleteExtra(@PathVariable("id") int id) {
        System.out.println("Fetching and deleting beverage with id " + id);
        
        Extra extra = extraDAO.findById(id);
        if(extra == null) {
            System.out.println("Extra with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        extraDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
    
    // DELETE Extras
    @RequestMapping(value = "/extras", method = RequestMethod.DELETE)
    public ResponseEntity<Extra> deleteAllExtras(@PathVariable("id") int id) {
        System.out.println("Deleting all extras");
        
        extraDAO.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
