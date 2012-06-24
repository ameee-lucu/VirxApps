package com.virvx.main;

public class Accounts {
	
	private Long id;
	private String account_name;
	private String reg_id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return account_name;
	}
	
	

}
