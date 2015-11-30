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

import com.pizzasoft.springmvc.dao.IngredientsDAO;
import com.pizzasoft.springmvc.model.Ingredient;
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
public class IngredientController {
    
    @Autowired
    IngredientsDAO ingredientsDAO;
    
    // GET Ingredients
    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    public ResponseEntity<List<Ingredient>> listAllIngredients() {
        List<Ingredient> ingredients = ingredientsDAO.findAll();
        if(ingredients.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }
    
    // GET Ingredient
    @RequestMapping(value = "/ingredients/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ingredient> getIngredient(@PathVariable("id") int id) {
        System.out.println("Fetching ingredient with id " + id);
        Ingredient ingredient = ingredientsDAO.findById(id);
        if(ingredient == null){
            System.out.println("Ingredient with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }
    
    // POST Ingredient
    @RequestMapping(value = "/ingredients", method = RequestMethod.POST)
    public ResponseEntity<Void> createIngredient(@RequestBody Ingredient ingredient, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Ingredient" + ingredient.getName());
        
//        if(ingredientsDAO.doesExist(ingredient)) {
//            System.out.println("Ingredient with name " + ingredient.getName() + " already exists.");
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
        
        ingredientsDAO.save(ingredient);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/management/menu/ingredients/{id}").buildAndExpand(ingredient.getIdIngredients()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    // PUT Ingredient
    @RequestMapping(value = "/ingredients/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable("id") int id, @RequestBody Ingredient ingredient) {
        System.out.println("Updating ingredient " + id);
        
        Ingredient currentIngredient = ingredientsDAO.findById(id);
        
        if(currentIngredient == null) {
            System.out.println("Ingredient with id " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        currentIngredient.setName(ingredient.getName());
        
        ingredientsDAO.update(currentIngredient);
        return new ResponseEntity<>(currentIngredient, HttpStatus.OK);
    }
    
    // DELETE Ingredient
    @RequestMapping(value = "/ingredients/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Ingredient> deleteIngredient(@PathVariable("id") int id) {
        System.out.println("Fetching and deleting ingredient with id " + id);
        
        Ingredient ingredient = ingredientsDAO.findById(id);
        if(ingredient == null) {
            System.out.println("Ingredient with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        ingredientsDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
    
    // DELETE Ingredients
    @RequestMapping(value = "/ingredients", method = RequestMethod.DELETE)
    public ResponseEntity<Ingredient> deleteAllIngredients(@PathVariable("id") int id) {
        System.out.println("Deleting all ingredients");
        
        ingredientsDAO.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
