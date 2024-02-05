package com.learning.microservice.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerSampleAPIController {

    Logger logger= LoggerFactory.getLogger(CircuitBreakerSampleAPIController.class);
    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
   // @RateLimiter(name = "default")
    //10secs only 10000 calls
    //@CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
    //@Bulkhead(name = "sample-api") //To limit the number of concurrent calls
    public String sampleAPI(){
        logger.info("sample API call recieved");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http:localhost:8080/dummy", String.class);
        return forEntity.getBody();
    }

    public String hardCodedResponse(Exception e){
        return "fallbackResponse";
    }
}
