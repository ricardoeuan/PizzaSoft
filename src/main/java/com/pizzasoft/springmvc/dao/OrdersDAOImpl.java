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

import com.pizzasoft.springmvc.model.Beverage;
import com.pizzasoft.springmvc.model.Direction;
import com.pizzasoft.springmvc.model.Extra;
import com.pizzasoft.springmvc.model.Order;
import com.pizzasoft.springmvc.model.Payment;
import com.pizzasoft.springmvc.model.Pizza;
import com.pizzasoft.springmvc.model.PizzaToppings;
import com.pizzasoft.springmvc.model.Price;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
public class OrdersDAOImpl implements OrdersDAO {
    
    private final JdbcTemplate jdbcTemplate;
    
    public OrdersDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    /**
     * TODO: Implement toppings list algorithm
     * @param id
     * @return 
     */
    @Override
    public Order findById(int id) { 
        String sql = "SELECT o.*, py.*, dir.*, p.*, pt.*, pr.*, e.*, ca.name, b.*, c.status, d.androidIdentifier FROM Orders o"
                + " LEFT JOIN Orders_Extras oe ON o.idOrders = oe.idOrders LEFT JOIN Extras e ON oe.idExtras = e.idExtras"
                + " LEFT JOIN Categories ca ON e.idCategories = ca.idCategories"
                + " LEFT JOIN Orders_Beverages ob ON o.idOrders = ob.idOrders LEFT JOIN Beverages b ON ob.idBeverages = b.idBeverages"
                + " LEFT JOIN Orders_Pizzas p ON o.idOrders = p.idOrders"
                + " LEFT JOIN Pizzas_PizzaToppings pp ON p.idPizzas = pp.idPizzas LEFT JOIN PizzaToppings pt ON pp.idPizzasToppings = pt.idPizzaToppings"
                + " LEFT JOIN Prices pr ON p.idPrices = pr.idPrices"
                + " LEFT JOIN Devices d ON o.idOrders = d.idOrders "                                
                + " LEFT JOIN Checks c ON o.idOrders = c.idOrders LEFT JOIN Payments py ON c.idPayments = py.idPayments"
                + " LEFT JOIN Directions dir ON py.idDirections = dir.idDirections"
                + " WHERE o.idOrders="+id;
                
        final List<Pizza> pizzasList = new ArrayList<>();
        //final List<PizzaToppings> toppings = new ArrayList<>();
        final List<Extra> extrasList = new ArrayList<>();
        final List<Beverage> beveragesList = new ArrayList<>();        
        
        return jdbcTemplate.query(sql, new ResultSetExtractor<Order>() {

            @Override
            public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
                if(rs.next()) {                                                                                       
                    Order order = new Order();                                        
                    
                    int pizzaId = rs.getInt("p.idPizzas");
                    //int toppingId = rs.getInt("pt.idPizzaToppings");
                    int extraId = rs.getInt("e.idExtras");
                    int beverageId = rs.getInt("b.idBeverages");                      
                    
                    Payment payment = new Payment(rs.getInt("py.idPayments"), rs.getDate("py.date"), rs.getString("py.description"), rs.getFloat("py.amount"));
                    Direction direction = new Direction(rs.getInt("dir.idDirections"), rs.getString("dir.zipCode"), rs.getString("dir.county"), rs.getString("dir.neighborhood"), rs.getString("dir.street"), rs.getString("dir.noExt"), rs.getString("noInt"), rs.getString("betweenStreetA"), rs.getString("betweenStreetB"));
                    Price pizzaPrice = new Price(rs.getInt("pr.idPrices"), rs.getFloat("pr.cost"), rs.getString("pr.size"));                                                                                
                    
                    order.setIdOrders(rs.getInt("o.idOrders"));
                    order.setName(rs.getString("o.name"));                                       
                    order.setPayment(payment);
                    order.setDirection(direction);                    
                    order.setStatus(rs.getInt("c.status"));
                    order.setAndroidId(rs.getString("d.androidIdentifier"));                                           
                    
                    //PizzaToppings topping = new PizzaToppings(toppingId, rs.getString("pt.name"), rs.getString("pt.description"), null);
                    //toppings.add(topping);
                    
                    Pizza pizza = new Pizza(pizzaId, pizzaPrice, null);
                    Extra extra = new Extra(extraId, rs.getString("e.name"), rs.getString("e.description"), rs.getFloat("e.cost"), rs.getString("ca.name"));
                    Beverage beverage = new Beverage(beverageId, rs.getString("b.name"), rs.getString("b.description"), rs.getBoolean("b.alcohol"), rs.getFloat("b.cost"), rs.getString("b.measure"));
                    
                    pizzasList.add(pizza);
                    extrasList.add(extra);
                    beveragesList.add(beverage);                     
                    
                    while(rs.next()) {                                                     
                        if(rs.getInt("p.idPizzas") != pizzaId) {    
                            //toppings.subList(0, 1);
                            //if(rs.getInt("pt.idPizzaToppings") != toppingId) {
                            //    topping = new PizzaToppings(rs.getInt("pt.idPizzaToppings"), rs.getString("pt.name"), rs.getString("pt.description"), null);
                            //    toppings.add(topping);
                            //}
                            pizza = new Pizza(rs.getInt("p.idPizzas"), pizzaPrice, null);
                            pizzasList.add(pizza);
                        }  
                        
                        if (rs.getInt("e.idExtras") != extraId) {
                            extra = new Extra(rs.getInt("e.idExtras"), rs.getString("e.name"), rs.getString("e.description"), rs.getFloat("e.cost"), rs.getString("ca.name"));
                            extrasList.add(extra);
                        }  
                        
                        if (rs.getInt("b.idBeverages") != beverageId) {
                            beverage = new Beverage(rs.getInt("b.idBeverages"), rs.getString("b.name"), rs.getString("b.description"), rs.getBoolean("b.alcohol"), rs.getFloat("b.cost"), rs.getString("b.measure"));
                            beveragesList.add(beverage);
                        }                        
                    }          
                    
                    order.setPizzas(pizzasList);
                    order.setExtras(extrasList);
                    order.setBeverages(beveragesList); 
                    
                    
                    return order;
                }
                return null;
            }            
        });                                
    }

    @Override
    public Order findByName(String name) {
        return null; 
    }

    @Override
    public void save(Order order) {                
        String sql = "INSERT INTO Orders (name) VALUES (?)";
        jdbcTemplate.update(sql, order.getName().trim().concat("_"+new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()).toString()));
        
        String selectOrderId = "SELECT MAX(idOrders) FROM Orders";
        int orderId = jdbcTemplate.queryForObject(selectOrderId, Integer.class);
        
        String addDirection = "INSERT INTO Directions (zipCode, county, neighborhood, street, noExt, noInt, betweenStreetA, betweenStreetB) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(addDirection, order.getDirection().getZipCode(), order.getDirection().getCountry(), order.getDirection().getNeighborhood(), order.getDirection().getStreet(), order.getDirection().getNoExt(), order.getDirection().getNoInt(), order.getDirection().getBetweenStreetA(), order.getDirection().getBetweenStreetB());
        
        String addPayment = "INSERT INTO Payments (date, description, amount, idDirections) VALUES (?, ?, ?, LAST_INSERT_ID())";
        jdbcTemplate.update(addPayment, order.getPayment().getDate(), order.getPayment().getDescription(), order.getPayment().getAmount());
        
        String addCheck = "INSERT INTO Checks (idOrders, idPayments, status) VALUES (?, LAST_INSERT_ID() ,?)";
        jdbcTemplate.update(addCheck, orderId, order.getStatus());
        
        String addDevice = "INSERT INTO Devices (androidIdentifier, idOrders) VALUES (?, ?)";
        jdbcTemplate.update(addDevice, order.getAndroidId(), orderId);
        
        if(!order.getPizzas().isEmpty()) {
            for(int i = 0; i < order.getPizzas().size(); i++) {          
                String addPizzas = "INSERT INTO Orders_Pizzas (idOrders, idPrices) VALUES (?, ?)";
                jdbcTemplate.update(addPizzas, orderId, order.getPizzas().get(i).getPrice().getIdPrices());
                for(int j = 0; j < order.getPizzas().get(i).getPizzaToppings().size();j++) {
                    String fkPizzasPt = "INSERT INTO Pizzas_PizzaToppings (idPizzas, idPizzasToppings) VALUES (LAST_INSERT_ID(), ?)";
                    jdbcTemplate.update(fkPizzasPt, order.getPizzas().get(i).getPizzaToppings().get(j).getIdPizzaToppings());                
                }                                                                
            }
        }
        
        if(!order.getExtras().isEmpty()) {
            for(int i = 0; i < order.getExtras().size(); i++) {
                String addExtras = "INSERT INTO Orders_Extras (idOrders, idExtras) VALUES (?, ?)";
                jdbcTemplate.update(addExtras, orderId, order.getExtras().get(i).getIdExtras());
            }
        }
        
        if(!order.getBeverages().isEmpty()) {
            for(int i = 0; i < order.getBeverages().size(); i++) {
                String addBeverages = "INSERT INTO Orders_Beverages (idOrders, idBeverages) VALUES (?, ?)";
                jdbcTemplate.update(addBeverages, orderId, order.getBeverages().get(i).getIdBeverages());
            }
        }
    }

    @Override
    public void update(Order order) {   
        String sql = "UPDATE Checks SET status=? WHERE idOrders="+order.getIdOrders();
        jdbcTemplate.update(sql, order.getStatus());
    }

    @Override
    public void deleteById(int id) {         
        String disableFk = "SET foreign_key_checks = 0";
        jdbcTemplate.update(disableFk);
                
        String sql = " DELETE o, oe, ob, op, ch, d"
                + " FROM Orders o"
                + " JOIN Orders_Extras oe ON oe.idOrders = o.idOrders"
                + " JOIN Orders_Beverages ob ON ob.idOrders = o.idOrders"
                + " JOIN Orders_Pizzas op ON op.idOrders = o.idOrders"
                + " JOIN Checks ch ON ch.idOrders = o.idOrders"
                + " JOIN Devices d ON d.idOrders = o.idOrders"
                + " WHERE o.idOrders="+id;
        jdbcTemplate.update(sql);
        
        String enableFk = "SET foreign_key_checks = 1";               
        jdbcTemplate.update(enableFk);      
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT o.*, ch.*, d.* FROM Orders o LEFT JOIN Checks ch ON o.idOrders = ch.idOrders LEFT JOIN Devices d ON o.idOrders = d.idOrders ORDER BY o.idOrders";
        List<Order> listOrders = jdbcTemplate.query(sql, new RowMapper<Order>(){

            @Override
            public Order mapRow(ResultSet rs, int i) throws SQLException {
                Order order = new Order();
                
                order.setIdOrders(rs.getInt("o.idOrders"));
                order.setName(rs.getString("o.name"));
                order.setStatus(rs.getInt("ch.status"));
                order.setAndroidId(rs.getString("d.androididentifier"));
                
                return order;
            }            
        });
        return listOrders;
    }

    @Override
    public void deleteAll() {
        String disableFk = "SET foreign_key_checks = 0;";
        jdbcTemplate.update(disableFk);

        String sql = "DROP TABLE IF EXISTS Orders_Extras, Orders_Beverages, Orders_Pizzas, Checks, Devices, Orders";
        jdbcTemplate.update(sql);
        
        String enableFk = "SET foreign_key_checks = 1";               
        jdbcTemplate.update(enableFk);    
    }

    @Override
    public boolean doesExist(Order order) {
        return findByName(order.getName())!=null;
    }
    
}
