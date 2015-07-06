package com.verizon.bs.forms;

import java.io.Serializable;
 
public class ServiceProvider implements Serializable{
 
    private static final long serialVersionUID = 1L;
    private String provider_id;   
	private String provider_name;
	
	public String getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(String provider_id) {
		this.provider_id = provider_id;
	}
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
  
}
