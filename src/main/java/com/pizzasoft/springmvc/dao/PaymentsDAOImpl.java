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
package com.pizzasoft.springmvc.dao;

import com.pizzasoft.springmvc.model.Payment;
import com.pizzasoft.springmvc.model.Payment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ricardoeuan
 */
public class PaymentsDAOImpl implements PaymentsDAO {
    
    private final JdbcTemplate jdbcTemplate;
    
    public PaymentsDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Payment findById(int id) {
        String sql = "SELECT * FROM Payments WHERE idPayments="+id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Payment>() {
        @Override
            public Payment extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    Payment payment = new Payment();
                    payment.setIdPayments(rs.getInt("idPayments"));
                    payment.setDate(rs.getDate("date"));
                    payment.setDescription(rs.getString("description"));
                    payment.setAmount(rs.getFloat("amount"));                    
                    return payment;
                }
                return null;
            }            
        });
    }
    
    @Override
    public List<Payment> findByYear(int year) {
        String sql = "SELECT * FROM Payments WHERE date BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 11:59:59'";
        List<Payment> listPayments = jdbcTemplate.query(sql, new RowMapper <Payment>(){
            @Override
            public Payment mapRow(ResultSet rs, int i) throws SQLException {
                Payment payment = new Payment();
                
                payment.setIdPayments(rs.getInt("idPayments"));
                payment.setDate(rs.getDate("date"));
                payment.setDescription(rs.getString("description"));
                payment.setAmount(rs.getFloat("amount"));                    
                return payment;
            }
           
        });
        return listPayments;
    }

    @Override
    public List<Payment> findByYearMonth(int year, int month) {
        String sql = "SELECT * FROM Payments WHERE date BETWEEN '"+year+"-"+ month+"-01 00:00:00' AND '"+year+"-"+month+"-31 11:59:59'";
        List<Payment> listPayments = jdbcTemplate.query(sql, new RowMapper <Payment>(){
            @Override
            public Payment mapRow(ResultSet rs, int i) throws SQLException {
                Payment payment = new Payment();
                
                payment.setIdPayments(rs.getInt("idPayments"));
                payment.setDate(rs.getDate("date"));
                payment.setDescription(rs.getString("description"));
                payment.setAmount(rs.getFloat("amount"));                    
                return payment;
            }
           
        });
        return listPayments;
    }

    @Override
    public List<Payment> findByYearMonthDay(int year, int month, int day) {
        String sql = "SELECT * FROM Payments WHERE date BETWEEN '"+year+"-"+ month+"-"+day+" 00:00:00' AND '"+year+"-"+month+"-"+day+" 23:59:59'";
        List<Payment> listPayments = jdbcTemplate.query(sql, new RowMapper <Payment>(){
            @Override
            public Payment mapRow(ResultSet rs, int i) throws SQLException {
                Payment payment = new Payment();
                
                payment.setIdPayments(rs.getInt("idPayments"));
                payment.setDate(rs.getDate("date"));
                payment.setDescription(rs.getString("description"));
                payment.setAmount(rs.getFloat("amount"));                    
                return payment;
            }
           
        });
        return listPayments;
    }   

    @Override
    public List<Payment> findAll() {
        String sql = "SELECT * FROM Payments";
        List<Payment> listPayments = jdbcTemplate.query(sql, new RowMapper<Payment>() {

            @Override
            public Payment mapRow(ResultSet rs, int i) throws SQLException {
                Payment payment = new Payment();
                
                payment.setIdPayments(rs.getInt("idPayments"));
                payment.setDate(rs.getDate("date"));
                payment.setDescription(rs.getString("description"));
                payment.setAmount(rs.getFloat("amount"));                    
                return payment;
            }           
        });
        return listPayments;
    }

    @Override
    public boolean doesExist(Payment payment) {
        return findById(payment.getIdPayments())!=null;
    }   
    
}
