package com.verizon.bs.forms;

import java.io.Serializable;
 
public class Customer implements Serializable{
 
    private static final long serialVersionUID = 1L;
    private String id;   
	private String name;
    private String sm_id;
        
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getSm_id() {
		return sm_id;
	}
	public void setSm_id(String sm_id) {
		this.sm_id = sm_id;
	}
	private String address;
    private Long mobile;
    private String emailid;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Long getMobile() {
        return mobile;
    }
    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }
    public String getEmailid() {
        return emailid;
    }
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
     
}
