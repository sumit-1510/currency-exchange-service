package com.learning.microservice.currencyexchangeservice.controller;

import com.learning.microservice.currencyexchangeservice.bean.CurrencyExchange;
import com.learning.microservice.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    Logger logger= LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;
    @Autowired
    private Environment environment;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getCurrencyExchange(@PathVariable String from, @PathVariable String to){
        logger.info("Received Call for Currency Exchange from {} to {}",from,to);
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        // get port from environment
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnv(port);
        return currencyExchange;
    }

}
