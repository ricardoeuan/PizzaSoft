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
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ricardoeuan
 */
@JsonInclude(Include.NON_NULL)
public class Order implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    /**
     * Default Constructor
     */
    public Order() {
        this.idOrders = 0;
    }
    
    /**
     * 
     * @param idOrders
     * @param name
     * @param status
     * @param payment
     * @param direction
     * @param pizzas
     * @param extras
     * @param beverages
     * @param androidId
     */
    public Order(int idOrders, String name, int status, Payment payment, Direction direction, List<Pizza> pizzas, List<Extra> extras, List<Beverage> beverages, String androidId) {
        this.idOrders = idOrders;
        this.name = name;
        this.status = status;
        this.payment = payment;
        this.direction = direction;
        this.pizzas = pizzas;     
        this.extras = extras;
        this.beverages = beverages;          
        this.androidId = androidId;
    }
    
    /**
     * 
     */
    private int idOrders;
    
    /**
     * 
     */
    private String name;
    
    /**
     * 
     */
    private int status;
    
    /**
     * 
     */
    private Payment payment;
    
    /**
     * 
     */
    private Direction direction;
    
    /**
     * 
     */
    private List<Pizza> pizzas;        
    
    /**
     * 
     */
    private List<Extra> extras;
    
    /**
     * 
     */
    private List<Beverage> beverages;              
    
    /**
     * 
     */
    private String androidId;      

    /**
     * @return the idOrders
     */
    public int getIdOrders() {
        return idOrders;
    }

    /**
     * @param idOrders the idOrders to set
     */
    public void setIdOrders(int idOrders) {
        this.idOrders = idOrders;
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
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    
    /**
     * @return the payment
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @return the pizzas
     */
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * @param pizzas the pizzas to set
     */
    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }        

    /**
     * @return the extras
     */
    public List<Extra> getExtras() {
        return extras;
    }

    /**
     * @param extras the extras to set
     */
    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }

    /**
     * @return the beverages
     */
    public List<Beverage> getBeverages() {
        return beverages;
    }

    /**
     * @param beverages the beverages to set
     */
    public void setBeverages(List<Beverage> beverages) {
        this.beverages = beverages;
    }                 

    /**
     * @return the androidId
     */
    public String getAndroidId() {
        return androidId;
    }

    /**
     * @param androidId the androidId to set
     */
    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (idOrders ^ (idOrders >>> 32));
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
        Order other = (Order) obj;
        return idOrders == other.idOrders;
    }
    
    @Override
    public String toString() {
        return "Order [idorder=" + idOrders + ", name=" + name +", pizzas=" + pizzas + ", extras="+ extras +", beverages="+ beverages +", payment="+ payment +", direction="+ direction +", androidId="+ androidId +", status="+ status +"]"; 
    }    
   
}
