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

import com.pizzasoft.springmvc.model.Category;
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
public class CategoryDAOImpl implements CategoryDAO {

    private final JdbcTemplate jdbcTemplate;
    
    public CategoryDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public Category findById(int id) {
        String sql = "SELECT * FROM Categories WHERE idCategories = " + id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Category>() {

            @Override
            public Category extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    Category category = new Category();
                    category.setIdCategories(rs.getInt("idCategories"));
                    category.setName(rs.getString("name"));
                    
                    return category;
                }
                return null;
            }            
        });
    }

    @Override
    public Category findByName(String name) {
        String sql = "SELECT * FROM Categories where name='" + name + "'";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Category>() {

            @Override
            public Category extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    Category category = new Category();
                    category.setIdCategories(rs.getInt("idCategories"));
                    category.setName(rs.getString("name"));
                    
                    return category;
                }
                return null;
            }            
        });
    }

    @Override
    public void save(Category category) {
        String sql = "INSERT INTO Categories (name) VALUES (?)";
        jdbcTemplate.update(sql, category.getName());
    }

    @Override
    public void update(Category category) {
        String sql = "UPDATE Categories SET name=? WHERE idCategories=?";
        jdbcTemplate.update(sql, category.getName(), category.getIdCategories());
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM Categories where idCategories=?";
       jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM Categories";
        List<Category> listCategories = jdbcTemplate.query(sql, new RowMapper<Category>(){
            
            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
                Category category = new Category();                
                category.setIdCategories(rs.getInt("idCategories"));
                category.setName(rs.getString("name"));                                
                return category;
            }           
        });
        return listCategories;
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE * FROM Categories";
       jdbcTemplate.update(sql);
    }

    @Override
    public boolean doesExist(Category category) {
        return findByName(category.getName())!=null;
    }
}
