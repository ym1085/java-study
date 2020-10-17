package com.java.study.enumexam;

class CustomerExam {
	
	enum Gender {
		MALE, FEMALE // 클래스 내에 정의된 열거형 Gender 메소드
	}
	
	private String name;
	private Gender gen;
	
	public CustomerExam(String n, String g) {
		name = n;
		
		if(g.equals("man"))
			gen = Gender.MALE;
	
		else
			gen = Gender.FEMALE;
	}
	
	public Gender getGen() {
		return gen;
	}
}

public class Customer {

	public static void main(String[] args) {
		CustomerExam cs = new CustomerExam("Mike", "man");
		
		// Gender : MALE 출력
		// 즉 인스턴스 자체를 출력 하였다
		System.out.println("Gender : " + cs.getGen()); 		
	}

}
