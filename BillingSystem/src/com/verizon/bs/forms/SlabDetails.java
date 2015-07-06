package com.verizon.bs.forms;

import java.io.Serializable;
 
public class SlabDetails implements Serializable{
 
    public String getStartReading() {
		return startReading;
	}
	public void setStartReading(String startReading) {
		this.startReading = startReading;
	}
	public String getEndReading() {
		return endReading;
	}
	public void setEndReading(String endReading) {
		this.endReading = endReading;
	}
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	private static final long serialVersionUID = 1L;
    private String startReading;
    private String endReading;
	private String provider_name;
	private String rate;
	

  
}
