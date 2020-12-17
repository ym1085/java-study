package com.java.study.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStream {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>(Arrays.asList("Toy", "Robot", "Car"));
		 
		// 스트림 생성
		list.stream().forEach(e -> System.out.println(e));
	}
}
