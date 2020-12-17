package com.java.study.Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Supply {

	public static List<Integer> makeIntList(Supplier<Integer> s, int n) {
		System.out.println("TEST 02.");
		List<Integer> lst = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			lst.add(s.get());
		}
		
		return lst;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 예제 01.
		Supplier<Integer> s = () -> {
			// 람다를 선언하는 것은 구현부를 작성하는 것 과 같다 생각하자.
			// 이 말은 나만 이 해 할 수 있 는 말 이 다.
			Random rnd = new Random();
			int result = rnd.nextInt(50);
			System.out.println("result : " + result);
			System.out.println("TEST01.");
			return result;
		};
		
		List<Integer> list = makeIntList(s, 5);
		System.out.println(list);
	
	}
}
