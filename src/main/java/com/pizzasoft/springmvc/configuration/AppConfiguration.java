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
package com.pizzasoft.springmvc.configuration;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.jndi.JndiTemplate;

import com.pizzasoft.springmvc.dao.BeverageDAO;
import com.pizzasoft.springmvc.dao.BeverageDAOImpl;
import com.pizzasoft.springmvc.dao.CategoryDAO;
import com.pizzasoft.springmvc.dao.CategoryDAOImpl;
import com.pizzasoft.springmvc.dao.ExtraDAO;
import com.pizzasoft.springmvc.dao.ExtraDAOImpl;
import com.pizzasoft.springmvc.dao.IngredientsDAO;
import com.pizzasoft.springmvc.dao.IngredientsDAOImpl;
import com.pizzasoft.springmvc.dao.OrdersDAO;
import com.pizzasoft.springmvc.dao.OrdersDAOImpl;
import com.pizzasoft.springmvc.dao.PaymentsDAO;
import com.pizzasoft.springmvc.dao.PaymentsDAOImpl;
import com.pizzasoft.springmvc.dao.PizzaToppingsDAO;
import com.pizzasoft.springmvc.dao.PizzaToppingsDAOImpl;


/**
 *
 * @author ricardoeuan
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.pizzasoft.springmvc")
public class AppConfiguration extends WebMvcConfigurerAdapter {        
  
    @Bean
    public DataSource getDataSource() {
        JndiTemplate jndiTemplate = new JndiTemplate();
        DataSource dataSource;
        try {
            dataSource = (DataSource) jndiTemplate.lookup("java:/SottovoceDS");
            dataSource.getConnection("root", "");            
            return dataSource;
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(AppConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;        
    }
    
    @Bean
    public BeverageDAO getBeverageDao() {
        return new BeverageDAOImpl(getDataSource());
    }    
    
    @Bean
    public ExtraDAO getExtraDAO() {
        return new ExtraDAOImpl(getDataSource());
    } 
    
    @Bean
    public CategoryDAO getCategoryDAO() {
        return new CategoryDAOImpl(getDataSource());
    }
    
    @Bean
    public PizzaToppingsDAO getPizzaToppingsDAO() {
        return new PizzaToppingsDAOImpl(getDataSource());
    }
    
    @Bean
    public IngredientsDAO getIngredientsDAO() {
        return new IngredientsDAOImpl(getDataSource());
    }
    
    @Bean
    public OrdersDAO getOrdersDAO() {
        return new OrdersDAOImpl(getDataSource());
    }
    
    @Bean
    public PaymentsDAO getPaymentsDAO() {
        return new PaymentsDAOImpl(getDataSource());
    }
}
