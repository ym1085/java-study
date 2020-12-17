package com.java.study.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class BinaryOperatorSample {

	public static void main(String[] args) {
		List<String> ls = Arrays.asList("Box", "Simple", "Complex", "Robot");
		
		// 이 부분이 중요
		BinaryOperator<String> lc = (s1, s2) -> {
			if(s1.length() > s2.length()) {
				return s1;
			} else {
				return s2;
			}
		};
		
		String str = ls.parallelStream() // 병렬 처리
						.reduce("", lc); // 스트림 빈 경우 "" 반환
		
		System.out.println("str : " + str);
		System.out.println();
	}
}
