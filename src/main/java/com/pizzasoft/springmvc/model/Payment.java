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
import java.util.Date;

/**
 *
 * @author ricardoeuan
 */
public class Payment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    
    /**
     * Default Constructor
     */
    public Payment() {
        
    }
    
    /**
     * 
     * @param idPayments
     * @param date
     * @param description
     * @param amount
     */
    public Payment(int idPayments, Date date, String description, float amount) {       
        this.idPayments = idPayments;
        this.date = date;
        this.description = description;
        this.amount = amount;
    }
    
    /**
     * 
     */
    private int idPayments;
    
    /**
     * 
     */
    private Date date;
    
    /**
     * 
     */
    private String description;
    
    /**
     * 
     */
    private float amount;

    /**
     * @return the idPayments
     */
    public int getIdPayments() {
        return idPayments;
    }

    /**
     * @param idPayments the idPayments to set
     */
    public void setIdPayments(int idPayments) {
        this.idPayments = idPayments;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
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
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (idPayments ^ (idPayments >>> 32));
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
        Payment other = (Payment) obj;
        return idPayments == other.idPayments;
    }
    
    @Override
    public String toString() {
        return "Payment [idPayment=" + idPayments + ", date=" + date +", description=" + description + ", amount="+ amount +"]"; 
    }
}
