package com.java.study.Lambda;

interface Printable {
	void print(String s);
}

public class OneParamNoReturn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Printable p;
		
		// 01. 줄임 없는 표현
		p = (String s) -> { System.out.println(s); };
		p.print("Hello guys");
		
		// 02. 중괄호 생략
		p = (String s) -> System.out.println(s);
		p.print("Hello guys2"); 
		
		// 03. 매개변수 형(String) 생략
		p = (s) -> System.out.println(s);
		p.print("Hello guys3");
		
		// 04. 매개변수 소괄호 생략
		p = s -> System.out.println(s);
		p.print("Hello guys4");
	}
}
