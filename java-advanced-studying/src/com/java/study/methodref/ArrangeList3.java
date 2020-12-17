package com.java.study.methodref;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

class JustSort {
	public void sort(List<?> lst) {
		Collections.reverse(lst);
	}
}

class ArrangeList3 {

	public static void main(String[] args) {
		
		// 람다식 기반 예제 01.
		List<Integer> ls = Arrays.asList(1, 3, 5, 7, 9);
		
		JustSort js = new JustSort();

		Consumer<List<Integer>> c = l -> js.sort(l);
		c.accept(ls);
		System.out.println(ls);
		
		System.out.println("----경계선----");
		
		// 메소드 참조 기반 예제 01.
		List<Integer> ls2 = Arrays.asList(4, 3, 1, 7, 8);
		
		JustSort js2 = new JustSort();
		
		Consumer<List<Integer>> c2 = js2::sort;
		c2.accept(ls2);
		System.out.println(ls2);
	
		js2 = null;
	}

}
