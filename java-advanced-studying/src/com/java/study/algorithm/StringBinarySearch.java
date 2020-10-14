package com.java.study.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class StringBinarySearch {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("Box");
		list.add("Robot");
		list.add("Apple");
		
		Collections.sort(list);
		
		int idx = Collections.binarySearch(list, "Robot"); 	// 탐색
		
		System.out.println("idx : " + idx);					// 인덱스 출력
		System.out.println("result : " + list.get(idx)); 	// 탐색 결과 출력
		
	}
}
