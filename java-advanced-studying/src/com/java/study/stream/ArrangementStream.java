package com.java.study.stream;

import java.util.Arrays;

public class ArrangementStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 예제 01. 
		System.out.println("--------for문 사용--------");
		int [] num = {5, 6, 2, 3, 7, 9, 8};
		
		// for문 사용
		int oddResult = 0;
		for(int i = 0; i < num.length; i++) {
			// 홀수 데이터만 출력
			if (num[i] % 2 == 1) {
				oddResult += num[i];
			}
		}
		
		System.out.println("oddResult : " + oddResult + "\n");
		System.out.println("--------스트림 사용--------");
		
		// 예제 02.
		int [] num2 = {5, 6, 2, 3, 7, 9, 8};
		
		int sum = Arrays.stream(num2).filter(e -> e % 2 == 1).sum();
		System.out.println("sum : " + sum + "\n");
	}

}
