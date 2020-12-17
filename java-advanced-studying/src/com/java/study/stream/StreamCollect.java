package com.java.study.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @since 20.12.15
 * @category 스트림의 생성, 중간 연산, 최종 연산
 * @version JDK 1.8.0
 * @author youngminkim
 */
public class StreamCollect {

	public static void main(String[] args) {
		/**
		 	Stream의 collect 메서드 활용
		 */
		
		// Example 01 (기본 스트림의 사용)
		String [] str = {"Box", "Toy", "Man", "Robot", "Apple"};
		Stream<String> st = Arrays.stream(str);
		
		List<String> lst =  st.filter(a -> a.length() < 5)
							  .collect(() -> new ArrayList<>(), 
							   (c, s) -> c.add(s),
							   (lst1, lst2) -> lst1.addAll(lst2));
		System.out.println(lst);	
		
	}
}
