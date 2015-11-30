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
public class Pizza implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    /**
     * Default Constructor
     */
    public Pizza() {
        this.idPizzas = 0;
    }   
    
    /**
     * 
     */
    public Pizza(int idPizzas, Price price, List<PizzaToppings> pizzaToppings) {
        this.idPizzas = idPizzas;
        this.price = price;
        this.pizzaToppings = pizzaToppings;
    }
    
    /**
     * 
     */
    private int idPizzas;
    
    /**
     * 
     */
    private Price price;
    
    /**
     * 
     */
    private List<PizzaToppings> pizzaToppings;            

    /**
     * @return the idPizzas
     */
    public int getIdPizzas() {
        return idPizzas;
    }

    /**
     * @param idPizzas the idPizzas to set
     */
    public void setIdPizzas(int idPizzas) {
        this.idPizzas = idPizzas;
    }

    /**
     * @return the price
     */
    public Price getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Price price) {
        this.price = price;
    }

    /**
     * @return the pizzaToppings
     */
    public List<PizzaToppings> getPizzaToppings() {
        return pizzaToppings;
    }

    /**
     * @param pizzaToppings the pizzaToppings to set
     */
    public void setPizzaToppings(List<PizzaToppings> pizzaToppings) {
        this.pizzaToppings = pizzaToppings;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (idPizzas ^ (idPizzas >>> 32));
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
        Pizza other = (Pizza) obj;
        return idPizzas == other.idPizzas;
    }
    
    @Override
    public String toString() {
        return "Extra [idExtras=" + idPizzas + ", price=" + price + ", pizzaToppings=" + pizzaToppings + "]"; 
    }
    
}
