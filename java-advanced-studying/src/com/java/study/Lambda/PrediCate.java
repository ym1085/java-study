package com.java.study.Lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class PrediCate {
	
	// Example 01
	public static int add(IntPredicate p, List<Integer> lst) {
		int num = 0;
		for(int n : lst) {
			if(p.test(n)) {
				num+=n;
			}
		}
		return num;
	}
	
	// Example 02
	public static int sum(Predicate<Integer> p, List<Integer> lst) {
		int s = 0;
		for(int n : lst) {
			if(p.test(n)) {
				s += n;
			}
		}
		return s;
	}
	
	public static void main(String[] args) {
		// Predicate는 자바 진영에서 정의한 함수형 인터페이스를 의미 한다.
		// boolean test(T t) 추상 메서드가 구현되어 있다.
		
		// 1. 값 저장
		List<Integer> list = Arrays.asList(1, 3, 4, 5, 6, 7);
		int result;
		
		// 2. 메소드 호출과 동시에 람다의 결과 전달
		result = sum(n -> n % 2 == 0, list);
		System.out.println("짝수 합 : " + result);
		
		result = sum(n -> n % 2 != 0, list);
		System.out.println("홀수 합 : " + result);
		System.out.println();
		
		int result2 = add((n) -> {
			if(n % 2 == 0) {
				return true;
			}else {
				return false;
			}
		}, list);
		
		System.out.println("result2 : " + result2);
	}

}
