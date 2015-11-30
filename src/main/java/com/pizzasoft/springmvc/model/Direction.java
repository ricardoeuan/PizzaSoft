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
public class Direction implements Serializable {
    
    public static final long serialVersionUID = 1L;
    
    /**
     * Default Constructor
     */
    public Direction() {
        
    }
    
    /**
     * 
     * @param idDirections
     * @param zipCode
     * @param country
     * @param neighborhood
     * @param street
     * @param noExt
     * @param noInt
     * @param betweenStreetA
     * @param betweenStreetB
     */
    public Direction(int idDirections, String zipCode, String country, String neighborhood, String street, String noExt, String noInt, String betweenStreetA, String betweenStreetB) {
        this.idDirections = idDirections;
        this.zipCode = zipCode;
        this.county = country;
        this.neighborhood = neighborhood;
        this.street = street;
        this.noExt = noExt;
        this.noInt = noInt;
        this.betweenStreetA = betweenStreetA;
        this.betweenStreetB = betweenStreetB;
    }
    
    /**
     * 
     */
    private int idDirections;
    
    /**
     * 
     */
    private String zipCode;
    
    /**
     * 
     */
    private String county;
    
    /**
     * 
     */
    private String neighborhood;
    
    /**
     * 
     */
    private String street;
    
    /**
     * 
     */
    private String noExt;
    
    /**
     * 
     */
    private String noInt;
    
    /**
     * 
     */
    private String betweenStreetA;
    
    /**
     * 
     */
    private String betweenStreetB;

    /**
     * @return the idDirections
     */
    public int getIdDirections() {
        return idDirections;
    }

    /**
     * @param idDirections the idDirections to set
     */
    public void setIdDirections(int idDirections) {
        this.idDirections = idDirections;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the county
     */
    public String getCountry() {
        return county;
    }

    /**
     * @param country the county to set
     */
    public void setCountry(String country) {
        this.county = country;
    }

    /**
     * @return the neighborhood
     */
    public String getNeighborhood() {
        return neighborhood;
    }

    /**
     * @param neighborhood the neighborhood to set
     */
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the noExt
     */
    public String getNoExt() {
        return noExt;
    }

    /**
     * @param noExt the noExt to set
     */
    public void setNoExt(String noExt) {
        this.noExt = noExt;
    }

    /**
     * @return the noInt
     */
    public String getNoInt() {
        return noInt;
    }

    /**
     * @param noInt the noInt to set
     */
    public void setNoInt(String noInt) {
        this.noInt = noInt;
    }

    /**
     * @return the betweenStreetA
     */
    public String getBetweenStreetA() {
        return betweenStreetA;
    }

    /**
     * @param betweenStreetA the betweenStreetA to set
     */
    public void setBetweenStreetA(String betweenStreetA) {
        this.betweenStreetA = betweenStreetA;
    }

    /**
     * @return the betweenStreetB
     */
    public String getBetweenStreetB() {
        return betweenStreetB;
    }

    /**
     * @param betweenStreetB the betweenStreetB to set
     */
    public void setBetweenStreetB(String betweenStreetB) {
        this.betweenStreetB = betweenStreetB;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (idDirections ^ (idDirections >>> 32));
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
        Direction other = (Direction) obj;
        return idDirections == other.idDirections;
    }
    
    @Override
    public String toString() {
        return "Direction [idDirections=" + idDirections + ", zipCode=" + zipCode +", country=" + county + ", neighborhood="+ neighborhood +", street="+ street +", noExt="+ noExt +", noInt="+ noInt +", betweenStreetA="+ betweenStreetA +", betweenStreetB="+ betweenStreetB +"]"; 
    }
}
