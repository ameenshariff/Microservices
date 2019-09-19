package com.aj.microservices.limitsservice.bean;

public class LimitConfig {

	private int maximum;
	private int minimum;
	
	public LimitConfig() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public LimitConfig(int maximum, int minimum) {
		super();
		this.maximum = maximum;
		this.minimum = minimum;
	}



	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	
	
}
