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
package com.pizzasoft.springmvc.model;

import java.io.Serializable;

/**
 *
 * @author ricardoeuan
 */
public class Beverage implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    /**
     * 
     */
    private int idBeverages;

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
    private boolean alcohol;

    /**
     * 
     */
    private float cost;

    /**
     * 
     */
    private String measure;
    
    /**
     * Default constructor
     */
    public Beverage() {
        idBeverages = 0;
    }

    /**
     * @param idBeverages     
     * @param name     
     * @param description     
     * @param alcohol     
     * @param cost     
     * @param measure     
     */
    public Beverage(int idBeverages, String name, String description, boolean alcohol, float cost, String measure) {
        this.idBeverages = idBeverages;
        this.name = name;
        this.description = description;
        this.alcohol = alcohol;
        this.cost = cost;
        this.measure = measure;
    }

    /**
     * @return idBeverages
     */
    public int getIdBeverages() {
        return idBeverages;
    }
    
    public void setBeveragesId(int idBeverages){
        this.idBeverages = idBeverages;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;        
    }
    
    public void setDescription(String description) {
        this.description = description;        
    }
    
    public boolean getAlcohol() {
        return alcohol;
    }
    
    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }
    
    public float getCost() {
        return cost;
    }
    
    public void setCost(float cost) {
        this.cost = cost;
    }
    
    public String getMeasure() {
        return measure;
    }
    
    public void setMeasure(String measure) {
        this.measure = measure;
    }
    
     @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (idBeverages ^ (idBeverages >>> 32));
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
        Beverage other = (Beverage) obj;
        return idBeverages == other.idBeverages;
    }
    
    @Override
    public String toString() {
        return "Beverage [idBeverages=" + idBeverages + ", name=" + name + ", descrtiption=" + description + ", alcohol=" + alcohol +", cost=" + cost + "measure=" + measure + "]"; 
    }
}

