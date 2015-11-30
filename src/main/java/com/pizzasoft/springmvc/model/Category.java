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
public class Category implements Serializable {        
    
    public static final long serialVersionUID = 1L;
    
    /**
     * Default Constructor
     */
    public Category() {
        idCategories = 0;
    }
    
    /**     
     * @param idCategories     
     * @param name          
     */
    public Category(int idCategories, String name) {
        this.idCategories = idCategories;
        this.name = name;        
    }
    
    /**
     * 
     */
    private int idCategories;    
    
    /**
     * 
     */
    private String name;
          
    
    /**
     * @return the idCategories
     */
    public int getIdCategories() {
        return idCategories;
    }

    /**
     * @param idCategories the idCategories to set
     */
    public void setIdCategories(int idCategories) {
        this.idCategories = idCategories;
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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (idCategories ^ (idCategories >>> 32));
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
        Category other = (Category) obj;
        return idCategories == other.idCategories;
    }
    
    @Override
    public String toString() {
        return "Category [idCategories=" + idCategories + ", name=" + name +"]"; 
    }
}
