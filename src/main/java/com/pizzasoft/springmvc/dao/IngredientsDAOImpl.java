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

import com.pizzasoft.springmvc.model.Ingredient;
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
public class IngredientsDAOImpl implements IngredientsDAO {
    
    private final JdbcTemplate jdbcTemplate;
    
    public IngredientsDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Ingredient findById(int id) {
        String sql = "SELECT * FROM Ingredients where idIngredients=" + id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Ingredient>() {

            @Override
            public Ingredient extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setIdIngredients(rs.getInt("idIngredients"));
                    ingredient.setName(rs.getString("name"));
                    return ingredient;
                }
                return null;
            }            
        });
    }

    @Override
    public Ingredient findByName(String name) {
        String sql = "SELECT * FROM Ingredients where name=" + name;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Ingredient>() {

            @Override
            public Ingredient extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setIdIngredients(rs.getInt("idIngredients"));
                    ingredient.setName(rs.getString("name"));
                    return ingredient;
                }
                return null;
            }            
        });
    }

    @Override
    public void save(Ingredient ingredient) {
        String sql = "INSERT INTO Ingredients (name, description, alcohol, cost, measure)"
                + "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, ingredient.getName());
    }

    @Override
    public void update(Ingredient ingredient) {
        String sql = "UPDATE Ingredients SET name=?, description=?, alcohol=?, cost=?, measure=? "
                + "WHERE idIngredients=?";
        jdbcTemplate.update(sql, ingredient.getName());
    }

    @Override
    public void deleteById(int id) {
       String sql = "DELETE FROM Ingredients where idIngredients=?";
       jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Ingredient> findAll() {
        String sql = "SELECT * FROM Ingredients";
        List<Ingredient> listIngredient = jdbcTemplate.query(sql, new RowMapper<Ingredient>(){
            
            @Override
            public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
                Ingredient ingredientElement = new Ingredient();
                                
                ingredientElement.setIdIngredients(rs.getInt("idIngredients"));
                ingredientElement.setName(rs.getString("name"));
                
                return ingredientElement;
            }           
        });
        return listIngredient;
    }

    @Override
    public void deleteAll() {
       String sql = "DELETE * FROM Ingredients";
       jdbcTemplate.update(sql);
    }

    @Override
    public boolean doesExist(Ingredient ingredient) {
        return findByName(ingredient.getName())!=null;
    }
   
}
