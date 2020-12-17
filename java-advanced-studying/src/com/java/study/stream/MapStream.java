package com.java.study.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapStream {

	public static void main(String[] args) {
		// 중간 연산자 filter, map 복습
		
		// 예제 01
		int [] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		Arrays.stream(arr)
				.filter(n -> n % 2 == 1)
				.forEach(t -> System.out.println("t : " + t));
		
		System.out.println();
		
		// 예제 02
		List<String> ls = Arrays.asList("Box", "Robot", "Toy");
		
		ls.stream()
			.filter(n -> n.length() == 3)
			.forEach(n -> System.out.println("n : " + n));
	
		System.out.println();
		
		// 예제 03
		List<String> ls2 = Arrays.asList("A", "B", "C", "D", "E");
	
		ls2.stream()
			.map(a -> a.toLowerCase())
			.map(a -> a.toUpperCase())
			.filter(a -> a.equals("E"))
			.forEach(a -> System.out.println("a : " + a));
	
	}
}
