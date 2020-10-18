package com.java.study.nestedclass;

/**

	1. PrintableExam -> PrintableExam02 -> Lambda 까지 코드를 간략화 하여 사용이 되고 있다.
 */

interface Printable03 {
	void print(String s);
}

interface Calculator {
	int addNum(int num, int num2);
}

public class Lambda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 기존에 사용되는 Person p = new Person(); 형식과 다른 익명 클래스를 사용할때의 호출 방법이다.
		
		// 익명 클래스 호출 시 사용되는 호출 방식.
		Printable03 prn03 = new Printable03() {
			
			@Override
			public void print(String s) {
				System.out.println(s);
			}
		};
		
		prn03.print("What is Lambda? \n");
	
		// Lambda 사용 예제!! 
		// Lambda(람다)는 익명 클래스에서 파생되어 나온 형식이라 봐도 무방하다.
		// 일회용으로 호출 되어야 하는 메소드를 호출 해야 할 때 사용이 된다.
		// 람다를 사용할 때는 Interface 구현이 필요하다.
		// 또한 해당 인터페이스 형으로 값을 받아야 한다.
		
		Printable03 prn04 = (str) -> {
			System.out.println("str : " + str);
		};
	
		prn04.print("Lambda is functional Programming \n");
		
		Calculator cal = (int x, int y) -> {
			int result = x + y;
			return result; 
		};
	
		int result = cal.addNum(50, 60);
		System.out.println("result : " + result);
	}
}
