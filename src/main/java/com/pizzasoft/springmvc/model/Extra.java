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

import java.io.Serializable;

/**
 *
 * @author ricardoeuan
 */
public class Extra  implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    /**
     * Default constructor
     */
    public Extra() {
        idExtras = 0;
    }
    
    /**     
     * @param idExtras
     * @param name
     * @param description
     * @param cost
     * @param category 
     */
    public Extra(int idExtras, String name, String description, float cost, String category) {
        this.idExtras = idExtras;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.category = category;
    }    
    
    /**
     * 
     */
    private int idExtras;
    
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
    private float cost;
    
    /**
     * 
     */
    private String category;

    /**
     * @return the idExtras
     */
    public int getIdExtras() {
        return idExtras;
    }

    /**
     * @param idExtras the idExtras to set
     */
    public void setIdExtras(int idExtras) {
        this.idExtras = idExtras;
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
     * @return the cost
     */
    public float getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (idExtras ^ (idExtras >>> 32));
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
        Extra other = (Extra) obj;
        return idExtras == other.idExtras;
    }
    
    @Override
    public String toString() {
        return "Extra [idExtras=" + idExtras + ", name=" + name + ", descrtiption=" + description + ", category=" + category + "]"; 
    }
}
