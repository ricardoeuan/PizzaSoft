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

import com.pizzasoft.springmvc.model.PizzaToppings;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class PizzaToppingsDAOImpl implements PizzaToppingsDAO {
    
    private final JdbcTemplate jdbcTemplate;
    
    public PizzaToppingsDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public PizzaToppings findById(int id) {
  
        String sql = "SELECT p.idPizzaToppings, p.name, p.description, i.name FROM PizzaToppings p"
                + " LEFT JOIN PizzaToppings_Ingredients pi ON p.idPizzaToppings = pi.idPizzaToppings"
                + " LEFT JOIN Ingredients i ON pi.idIngredients = i.idIngredients"
                + " WHERE p.idPizzaToppings="+ id;
        final List<String> ingredientsList = new ArrayList<String>();        
        return jdbcTemplate.query(sql, new ResultSetExtractor<PizzaToppings>() {
            
            @Override
            public PizzaToppings extractData(ResultSet rs) throws SQLException, DataAccessException {                
                if(rs.next()) {
                    PizzaToppings pizzaToppings = new PizzaToppings();
                    pizzaToppings.setIdPizzaToppings(rs.getInt("p.idPizzaToppings"));
                    pizzaToppings.setName(rs.getString("p.name"));
                    pizzaToppings.setDescription(rs.getString("p.description"));                                             
                    ingredientsList.add(rs.getString("i.name"));   
                    
                    while(rs.next()) {
                        ingredientsList.add(rs.getString("i.name"));                           
                    }                    
                    pizzaToppings.setIngredients(ingredientsList);
                    
                    return pizzaToppings;
                }
                return null;
            }
        });
    }

    @Override
    public PizzaToppings findByName(String name) {
         String sql = "SELECT p.idPizzaToppings, p.name, p.description, i.name FROM PizzaToppings p"
                + " INNER JOIN PizzaToppings_Ingredients pi ON p.idPizzaToppings = pi.idPizzaToppings"
                + " INNER JOIN Ingredients i ON pi.idIngredients = i.idIngredients"
                + " WHERE p.name='"+ name +"'";
        final List<String> ingredientsList = new ArrayList<String>();        
        return jdbcTemplate.query(sql, new ResultSetExtractor<PizzaToppings>() {
            
            @Override
            public PizzaToppings extractData(ResultSet rs) throws SQLException, DataAccessException {                
                if(rs.next()) {
                    PizzaToppings pizzaToppings = new PizzaToppings();
                    pizzaToppings.setIdPizzaToppings(rs.getInt("p.idPizzaToppings"));
                    pizzaToppings.setName(rs.getString("p.name"));
                    pizzaToppings.setDescription(rs.getString("p.description"));                                             
                    ingredientsList.add(rs.getString("i.name"));   
                    
                    while(rs.next()) {
                        ingredientsList.add(rs.getString("i.name"));                         
                    }                    
                    pizzaToppings.setIngredients(ingredientsList);
                    
                    return pizzaToppings;
                }
                return null;
            }
        });
    }
    
    @Override
    public void save(PizzaToppings pizzaToppings) {                
        String sql = " INSERT INTO PizzaToppings (name, description) VALUES (?, ?)";
        jdbcTemplate.update(sql, pizzaToppings.getName(), pizzaToppings.getDescription());               
        
        if(!pizzaToppings.getIngredients().isEmpty()) {
            for(int i = 0; i< pizzaToppings.getIngredients().size(); i++) {
                String idQuery = "SELECT idIngredients FROM Ingredients WHERE name='"+pizzaToppings.getIngredients().get(i)+"'";
                int id = jdbcTemplate.queryForObject(idQuery, Integer.class);
                String addIngredients = "INSERT INTO PizzaToppings_Ingredients (idPizzaToppings, idIngredients) VALUES (LAST_INSERT_ID(), ?)";
                jdbcTemplate.update(addIngredients, id);
            }                
        }
    }
    
    @Override
    public void update(PizzaToppings pizzaToppings) {
        String sql = " UPDATE PizzaToppings SET name=?, description=? WHERE idPizzaToppings="+pizzaToppings.getIdPizzaToppings();
        jdbcTemplate.update(sql, pizzaToppings.getName(), pizzaToppings.getDescription());     
        
        String ingredientsCount = "SELECT COUNT(*) FROM PizzaToppings_Ingredients WHERE idPizzaToppings="+pizzaToppings.getIdPizzaToppings();		 
	int ingredientsList = jdbcTemplate.queryForObject(ingredientsCount, Integer.class);                
        
        if(!pizzaToppings.getIngredients().isEmpty()) {                         
            if(pizzaToppings.getIngredients().size() == ingredientsList) {
                for(int i = 0; i< pizzaToppings.getIngredients().size(); i++) {
                    String delSql = "DELETE FROM PizzaToppings_Ingredients WHERE idPizzaToppings=?";
                    jdbcTemplate.update(delSql, pizzaToppings.getIdPizzaToppings());
                    String idQuery = "SELECT idIngredients FROM Ingredients WHERE name='"+pizzaToppings.getIngredients().get(i)+"'";
                    int id = jdbcTemplate.queryForObject(idQuery, Integer.class);
                    String updSql = "INSERT INTO PizzaToppings_Ingredients (idPizzaToppings, idIngredients) VALUES (?, ?)";
                    jdbcTemplate.update(updSql, pizzaToppings.getIdPizzaToppings(), id);
                }
            } else if(pizzaToppings.getIngredients().size() > ingredientsList) {                  
                for(int i = 0; i< pizzaToppings.getIngredients().size(); i++) {
                    String idQuery = "SELECT idIngredients FROM Ingredients WHERE name='"+pizzaToppings.getIngredients().get(i)+"'";
                    int id = jdbcTemplate.queryForObject(idQuery, Integer.class);                    
                    String insSql = "INSERT INTO PizzaToppings_Ingredients (idPizzaToppings, idIngredients) SELECT "+pizzaToppings.getIdPizzaToppings()+", "+id
                                  + " FROM PizzaToppings_Ingredients WHERE NOT EXISTS (SELECT idPizzaToppings, idIngredients FROM PizzaToppings_Ingredients"
                                  + " WHERE idPizzaToppings="+pizzaToppings.getIdPizzaToppings()+" AND idIngredients="+id+")LIMIT 1";                    
                    jdbcTemplate.update(insSql);
                }
            } else {
                for(int i=0; i < ingredientsList - pizzaToppings.getIngredients().size(); i++){
                    String idQuery = "SELECT idIngredients FROM Ingredients WHERE name='"+pizzaToppings.getIngredients().get(i)+"'";
                    int id = jdbcTemplate.queryForObject(idQuery, Integer.class);
                    String delSql = "DELETE FROM PizzaToppings_Ingredients WHERE idPizzaToppings="+pizzaToppings.getIdPizzaToppings()+" AND idIngredients NOT IN ("+id+")";
                    jdbcTemplate.update(delSql);                    
                }       
                for(int i = 0; i< pizzaToppings.getIngredients().size(); i++) {
                    String idQuery = "SELECT idIngredients FROM Ingredients WHERE name='"+pizzaToppings.getIngredients().get(i)+"'";
                    int id = jdbcTemplate.queryForObject(idQuery, Integer.class);
                    String updSql = "UPDATE PizzaToppings_Ingredients SET idIngredients=? WHERE idPizzaToppings="+pizzaToppings.getIdPizzaToppings();
                    jdbcTemplate.update(updSql, id);
                }                
            }
                                                                            
        }  else {
            String sqlFk = "DELETE FROM PizzaToppings_Ingredients WHERE idPizzaToppings=?";
            jdbcTemplate.update(sqlFk, pizzaToppings.getIdPizzaToppings());
        }  
    }

    @Override
    public void deleteById(int id) {
       String sqlFk = "DELETE FROM PizzaToppings_Ingredients WHERE idPizzaToppings=?";
       jdbcTemplate.update(sqlFk, id);
       String sql = "DELETE FROM PizzaToppings WHERE idPizzaToppings=?";
       jdbcTemplate.update(sql, id);
       
    }
    
    @Override
    public List<PizzaToppings> findAll() {
        String sql = "SELECT p.* FROM PizzaToppings p";               
        List<PizzaToppings> listExtras = jdbcTemplate.query(sql, new RowMapper<PizzaToppings>(){
            
            @Override
            public PizzaToppings mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
                    PizzaToppings pizzaToppings = new PizzaToppings();
                    
                    pizzaToppings.setIdPizzaToppings(rs.getInt("p.idPizzaToppings"));
                    pizzaToppings.setName(rs.getString("p.name"));
                    pizzaToppings.setDescription(rs.getString("p.description"));                                            
                    
                    return pizzaToppings;                                             
            }           
        });
        return listExtras;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM PizzaToppings";
        jdbcTemplate.update(sql);
    }

    @Override
    public boolean doesExist(PizzaToppings pizzaToppings) {
        return findByName(pizzaToppings.getName())!=null;
    }
    
}
