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

import com.pizzasoft.springmvc.dao.PaymentsDAO;
import com.pizzasoft.springmvc.model.Payment;
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
@RequestMapping("/payments")
public class PaymentController {
    
    @Autowired
    PaymentsDAO paymentDAO;
    
    // GET Categories
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> listAllPayment() {
        List<Payment> payments = paymentDAO.findAll();
        if(payments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    
    // GET Payment
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> getPayment(@PathVariable("id") int id) {
        System.out.println("Fetching payment with id " + id);
        Payment payment = paymentDAO.findById(id);
        if(payment == null){
            System.out.println("Payment with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
    
    // GET Payment by Year
    @RequestMapping(value = "/{year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Payment>> getPaymentsByYear(@PathVariable("year") int year) {
        List<Payment> payments = paymentDAO.findByYear(year);
        if(payments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    
    // GET Payment by YearMonth
    @RequestMapping(value = "/{year}/{month}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Payment>> getPaymentsByYearMonth(@PathVariable("year") int year, @PathVariable("month") int month) {
        List<Payment> payments = paymentDAO.findByYearMonth(year, month);
        if(payments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    
    // GET Payment by YearMonthDay
    @RequestMapping(value = "/{year}/{month}/{day}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Payment>> getPaymentsByYearMonthDay(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day) {
        List<Payment> payments = paymentDAO.findByYearMonthDay(year, month, day);
        if(payments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    
    /*// POST Payment
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Void> createPayment(@RequestBody Payment payment, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Payment" + payment.getIdPayments());
        
//        if(paymentDAO.doesExist(payment)) {
//            System.out.println("Payment with name " + payment.getName() + " already exists.");
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
        
        paymentDAO.save(payment);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(payment.getIdPayments()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    // PUT Payment
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Payment> updatePayment(@PathVariable("id") int id, @RequestBody Payment payment) {
        System.out.println("Updating payment " + id);
        
        Payment currentPayment = paymentDAO.findById(id);
        
        if(currentPayment == null) {
            System.out.println("Payment with id " + id + " not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        currentPayment.setIdPayments(id);        
        currentPayment.setDate(payment.getDate());
        currentPayment.setDescription(payment.getDescription());
        currentPayment.setAmount(payment.getAmount());
        
        paymentDAO.update(currentPayment);
        return new ResponseEntity<>(currentPayment, HttpStatus.OK);
    }
    
    // DELETE Payment
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Payment> deletePayment(@PathVariable("id") int id) {
        System.out.println("Fetching and deleting payment with id " + id);
        
        Payment payment = paymentDAO.findById(id);
        if(payment == null) {
            System.out.println("Payment with id " + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        paymentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
    
    // DELETE Categories
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<Payment> deleteAllCategories(@PathVariable("id") int id) {
        System.out.println("Deleting all payments");
        
        paymentDAO.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
}
