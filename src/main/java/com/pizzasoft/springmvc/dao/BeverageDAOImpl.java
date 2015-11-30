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
package com.pizzasoft.springmvc.dao;
 
import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.pizzasoft.springmvc.model.Beverage;

/**
 *
 * @author ricardoeuan
 */
public class BeverageDAOImpl implements BeverageDAO {
    
    private final JdbcTemplate jdbcTemplate;
    
    public BeverageDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
        
    @Override
    public Beverage findById(int id) {
        String sql = "SELECT * FROM Beverages where idBeverages=" + id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Beverage>() {

            @Override
            public Beverage extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    Beverage beverage = new Beverage();
                    beverage.setBeveragesId(rs.getInt("idBeverages"));
                    beverage.setName(rs.getString("name"));
                    beverage.setDescription(rs.getString("description"));
                    beverage.setAlcohol(rs.getBoolean("alcohol"));
                    beverage.setCost(rs.getFloat("cost"));
                    beverage.setMeasure(rs.getString("measure"));
                    return beverage;
                }
                return null;
            }            
        });
    }

    @Override
    public Beverage findByName(String name) {
         String sql = "SELECT * FROM Beverages where name=" + name;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Beverage>() {

            @Override
            public Beverage extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {
                    Beverage beverage = new Beverage();
                    beverage.setBeveragesId(rs.getInt("idBeverages"));
                    beverage.setName(rs.getString("name"));
                    beverage.setDescription(rs.getString("description"));
                    beverage.setAlcohol(rs.getBoolean("alcohol"));
                    beverage.setCost(rs.getFloat("cost"));
                    beverage.setMeasure(rs.getString("measure"));
                    return beverage;
                }
                return null;
            }            
        });
    }

    @Override
    public void save(Beverage beverage) {
        String sql = "INSERT INTO Beverages (name, description, alcohol, cost, measure)"
                + "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, beverage.getName(), beverage.getDescription(), beverage.getAlcohol(), beverage.getCost(), beverage.getMeasure());
    }

    @Override
    public void update(Beverage beverage) {
        String sql = "UPDATE Beverages SET name=?, description=?, alcohol=?, cost=?, measure=? "
                + "WHERE idBeverages=?";
        jdbcTemplate.update(sql, beverage.getName(), beverage.getDescription(), beverage.getAlcohol(), beverage.getCost(), beverage.getMeasure(), beverage.getIdBeverages());
    }

    @Override
    public void deleteById(int id) {
       String sql = "DELETE FROM Beverages where idBeverages=?";
       jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Beverage> findAll() {
        String sql = "SELECT * FROM Beverages";
        List<Beverage> listBeverage = jdbcTemplate.query(sql, new RowMapper<Beverage>(){
            
            @Override
            public Beverage mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
                Beverage beverageElement = new Beverage();
                
                beverageElement.setBeveragesId(rs.getInt("idBeverages"));
                beverageElement.setName(rs.getString("name"));
                beverageElement.setDescription(rs.getString("description"));
                beverageElement.setAlcohol(rs.getBoolean("alcohol"));
                beverageElement.setCost(rs.getFloat("cost"));
                beverageElement.setMeasure(rs.getString("measure"));
                
                return beverageElement;
            }           
        });
        return listBeverage;
    }
    
    @Override
    public void deleteAll() {
       String sql = "DELETE * FROM Beverages";
       jdbcTemplate.update(sql);
    }

    @Override
    public boolean doesExist(Beverage beverage) {
        return findByName(beverage.getName())!=null;
    }            
}
