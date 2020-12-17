package com.java.study.methodref;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class ArrangeList {
	
	public static void main(String[] args) {
		// static 메소드 기반 메소드 참조 
		
		// 람다식 기반 예제 01.
		List<Integer> lst = Arrays.asList(1, 3, 5, 7, 9);
		
		Consumer<List<Integer>> c = l -> Collections.reverse(l);
		c.accept(lst);
		System.out.println(lst);
		
		System.out.println("--------경계선--------");
		
		List<Integer> lst2 = Arrays.asList(3, 5, 6, 7, 8);
		
		Consumer<List<Integer>> c2 = Collections::reverse; // 메소드 참조 형식
		c2.accept(lst2);
		System.out.println(lst2);
	}
}
