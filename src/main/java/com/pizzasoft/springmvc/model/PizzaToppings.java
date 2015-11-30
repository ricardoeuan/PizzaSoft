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
package com.pizzasoft.springmvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ricardoeuan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PizzaToppings implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    /**
     * Default Constructor
     */
    public PizzaToppings() {
        idPizzaToppings = 0;
    }
    
    /**
     * @param idPizzaToppings
     * @param name
     * @param description
     */
    public PizzaToppings(int idPizzaToppings, String name, String description, List<String> ingredients) {
       this.idPizzaToppings = idPizzaToppings;
       this.name = name;
       this.description = description;
       this.ingredients = ingredients;
    }
    
    /**
     * 
     */
    private int idPizzaToppings;
    
    /**
     * 
     */
    private String name;
    
    /**
     * 
     */
    private String description;
    
    /**
     * 
     */
    private List<String> ingredients;        

    /**
     * @return the idPizzaToppings
     */
    public int getIdPizzaToppings() {
        return idPizzaToppings;
    }

    /**
     * @param idPizzaToppings the idPizzaToppings to set
     */
    public void setIdPizzaToppings(int idPizzaToppings) {
        this.idPizzaToppings = idPizzaToppings;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
     /**
     * @return the ingredients
     */
    public List<String> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients the ingredients to set
     */
    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (idPizzaToppings ^ (idPizzaToppings >>> 32));
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PizzaToppings other = (PizzaToppings) obj;
        return idPizzaToppings == other.idPizzaToppings;
    }
    
    @Override
    public String toString() {
        return "PizzaToppings [idPizzaToppings=" + idPizzaToppings + ", name=" + name +", description=" + description + "]"; 
    }
   
}
