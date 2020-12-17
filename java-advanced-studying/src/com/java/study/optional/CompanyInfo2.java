package com.java.study.optional;
import java.util.Optional;

class CompanyInfoTest {
	Optional<String> phone; // null 일 수 있음
	Optional<String> adrs; // null 일 수 있음

	CompanyInfoTest(Optional<String> ph, Optional<String> ad) {
		phone = ph;
		adrs = ad;
	}

	public Optional<String> getPhone() {
		return phone;
	}

	public Optional<String> getAdrs() {
		return adrs;
	}

}

class CompanyInfo2 {
	
	public static void main(String[] args) {
		Optional<CompanyInfoTest> ci = Optional.of(
			new CompanyInfoTest(Optional.ofNullable(null), Optional.of("Koread"))
		);
		
		String phone = ci.flatMap(c -> c.getPhone()).orElse("There is no phone number.");
		String addr = ci.flatMap(c -> c.getAdrs()).orElse("There is no address.");
	
		System.out.println(phone);
		System.out.println(addr);
	}
}