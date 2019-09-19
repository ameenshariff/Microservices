package com.aj.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.aj.microservices.currencyconversionservice.bean.CurrencyConversionBean;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
		
		Map<String, String> uriVariables=new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		System.out.println(" hyu    "+uriVariables);
		
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConversionBean.class, uriVariables);
		
		System.out.println(responseEntity);
		
		CurrencyConversionBean response = responseEntity.getBody();
		
		System.out.println(response);
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),
				quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());
	}
	
	
	//Using feign
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
		
//		Map<String, String> uriVariables=new HashMap<String, String>();
//		uriVariables.put("from", from);
//		uriVariables.put("to", to);
//		
//		System.out.println(" hyu    "+uriVariables);
//		
//		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
//				CurrencyConversionBean.class, uriVariables);
//		
//		System.out.println(responseEntity);
		
		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
		
		System.out.println(response);
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),
				quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());
	}

}
