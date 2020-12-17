package com.java.study.Lambda;

// 일반 연산 인터페이스
interface Calculate {
	int add(int a, int b);
	
	// 추상 메서드가 아닌 minus, amp는 반드시 구현하지 않아도 된다.
	default int minus(int a, int b) { return a - b; }
	static int amp(int a, int b) { return a * b; }; 
}

// 제네릭을 통해 람다를 구현.
interface Generator <T> {
	T calculate(T a, T b);
}

public class TwoParamAndReturn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculate cal;
		cal = (a, b) -> {
			if(a < 10) {
				System.out.println("You have to enter number more than 10");
			}
			return a + b;
		};
		
		int result = cal.add(9, 12);
		System.out.println("result : " + result + "\n");
	
		Generator<Integer> gen;
		gen = (a, b) -> {
			int addResult = 0;
	
			addResult = a + b;
			return addResult;
		};
		
		System.out.println("result2 : " + gen.calculate(3, 5));
	}
}
