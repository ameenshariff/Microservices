package com.aj.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aj.microservices.currencyexchangeservice.bean.ExchangeValue;

public interface ExchangeValueRepo extends JpaRepository<ExchangeValue, Long> {
	ExchangeValue findByFromAndTo(String from,String to);

}
