package com.poseidon.pta.services;

import com.poseidon.pta.repositories.BidListRepository;
import com.poseidon.pta.security.SpringSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;

public class TestBeans {

    @Lazy
    @Autowired
    BidListRepository bidListRepository;

    @Bean
    BidListRepository bidListRepository() {
        return bidListRepository;
    }

//    @Bean
//    BidListService bidListService() {
//        return new BidListService(bidListRepository);
//    }

//    @Bean
//    DataSource dataSource() {
//        return new DataSource();
//    }

}
