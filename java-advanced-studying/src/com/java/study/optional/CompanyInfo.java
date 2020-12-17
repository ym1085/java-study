package com.java.study.optional;

import java.util.Optional;

class Company {
	public String phone;
	public String address;
	
	public Company(String p, String addr) {
		this.phone = p;
		this.address = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}

class CompanyInfo {

	public static void main(String[] args) {
		// Optional 클래스 
		Company ci = new Company(null, "Republic of Korea");
		
		String phone;
		String address;
		
		if(ci.phone != null)
			phone = ci.phone;
		else 
			phone = "There is no phone number";
		
		if(ci.address != null) 
			address = ci.address;
		else
			address = "There is no address";
	
		System.out.println(phone);
		System.out.println(address + "\n");
		System.out.println("----다음 예제----");
		
		String phone2;
		String address2;
		
		Optional<Company> ci2
			= Optional.of(new Company(null,  "Republic of Korea"));
		
		phone2 = ci2.map(e -> e.getPhone()).orElse("There is no phone");
		address2 = ci2.map(e -> e.getAddress()).orElse("There is no address"); 
		
		System.out.println(phone2);
		System.out.println(address);
	}
}
