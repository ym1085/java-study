package com.java.study.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class StrComp implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		return s1.compareToIgnoreCase(s2);		// 대소문자 비교
	}
}

class StringComparaTorbnSearch {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		
		list.add("Robot");
		list.add("Apple");
		list.add("BOX");
			
		StrComp cmp = new StrComp();
		Collections.sort(list);
		
		// 정렬된 리스트에서 오브젝트의 위치를 반환하는 java.util.Collections 클래스 메소드입니다.
		// 반드시 정렬 된 상태여야 합니다. 이진 탐색으로 값을 찾기 때문에 정렬이 되어 있지 않으면 이진 탐색을 할 수가 없습니다.
		int idx = Collections.binarySearch(list, "Robot", cmp); // 탐색
		
		/*
			public static <T> int binarySearch(List<?  Extends Comparable<? super T>> list, T key)
     			-> list에서 key를 찾아 그 인덱스 값 반환, 못 찾으면 음의 정수 반환		
		 */
		
		System.out.println("idx : " + idx);
		System.out.println("list.get : " + list.get(idx));
	}
}
