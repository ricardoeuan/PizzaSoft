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

import com.pizzasoft.springmvc.model.Extra;
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
public class ExtraDAOImpl implements ExtraDAO {
    
    private final JdbcTemplate jdbcTemplate;
    
    public ExtraDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Extra findById(int id) {
        String sql = "SELECT e.idExtras, e.name, e.description, e.cost, c.name FROM Extras e CROSS JOIN Categories c WHERE e.idCategories = c.idCategories AND e.idExtras=" + id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Extra>() {

            @Override
            public Extra extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    Extra extra = new Extra();                                             
                    extra.setIdExtras(rs.getInt("e.idExtras"));
                    extra.setName(rs.getString("e.name"));
                    extra.setDescription(rs.getString("e.description"));
                    extra.setCost(rs.getFloat("e.cost"));                    
                    extra.setCategory(rs.getString("c.name"));
                    
                    return extra;
                }
                return null;
            }            
        });
    }

    @Override
    public Extra findByName(String name) {
        String sql = "SELECT e.idExtras, e.name, e.description, e.cost, c.name FROM Extras e CROSS JOIN Categories c WHERE e.idCategories = c.idCategories AND e.name=" + name;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Extra>() {

            @Override
            public Extra extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    Extra extra = new Extra();                                             
                    extra.setIdExtras(rs.getInt("e.idExtras"));
                    extra.setName(rs.getString("e.name"));
                    extra.setDescription(rs.getString("e.description"));
                    extra.setCost(rs.getFloat("e.cost"));                    
                    extra.setCategory(rs.getString("c.name"));
                    
                    return extra;
                }
                return null;
            }            
        });
    }

    @Override
    public void save(Extra extra) {
        String idQuery = "SELECT idCategories FROM Categories WHERE name='"+extra.getCategory()+"'";
        int id = jdbcTemplate.queryForObject(idQuery, Integer.class);
        String sql = "INSERT INTO Extras (name, description, cost, idCategories)"
                + "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, extra.getName(), extra.getDescription(), extra.getCost(), id);
    }

    @Override
    public void update(Extra extra) {
        String idQuery = "SELECT idCategories FROM Categories WHERE name='"+extra.getCategory()+"'";
        int id = jdbcTemplate.queryForObject(idQuery, Integer.class);
        String sql = "UPDATE Extras SET name=?, description=?, cost=?, idCategories=? "
                + "WHERE idExtras=?";
        jdbcTemplate.update(sql, extra.getName(), extra.getDescription(), extra.getCost(), id, extra.getIdExtras());
    }

    @Override
    public void deleteById(int id) {
       String sql = "DELETE FROM Extras where idExtras=?";
       jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Extra> findAll() {
        String sql = "SELECT *, c.name FROM Extras e CROSS JOIN Categories c WHERE e.idCategories = c.idCategories";
        List<Extra> listExtras = jdbcTemplate.query(sql, new RowMapper<Extra>(){
            
            @Override
            public Extra mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
                Extra extra = new Extra();
                
                extra.setIdExtras(rs.getInt("idExtras"));
                extra.setName(rs.getString("name"));
                extra.setDescription(rs.getString("description"));                
                extra.setCost(rs.getFloat("cost"));
                extra.setCategory(rs.getString("c.name"));
                
                return extra;
            }           
        });
        return listExtras;
    }

    @Override
    public void deleteAll() {
       String sql = "DELETE * FROM Extras";
       jdbcTemplate.update(sql);
    }

    @Override
    public boolean doesExist(Extra extra) {
        return findByName(extra.getName())!=null;
    }
    
}
