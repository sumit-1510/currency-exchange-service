package com.learning.microservice.currencyexchangeservice.repository;

import com.learning.microservice.currencyexchangeservice.bean.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {

    CurrencyExchange findByFromAndTo(String from,String to);

}
