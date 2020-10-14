package com.java.study.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @since 20201013
 * @content WildCard
 * @author ymkim
 *
 */
public class SortCollectionFrameWork {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("Toy", "Box", "Robot", "Weapon");
		list = new ArrayList<String>(list); // ArrayList collection framework 형식으로 변경
		
		Iterator<String> itr = list.iterator();
		while(itr.hasNext()) {
			System.out.print(itr.next() + "\t");
		}
		
		System.out.println();

		// 1. public static <T extends Comparable<T>> void sort(List<T> list)
		// 2. public static <T extends Comparable<? suepr T>> void sort(List<T> list)
		// 3. String Class는 실제로 Comparbale<T> 안터페이스를 구현하고 있다.
		Collections.sort(list);  
		
		Iterator<String> itr2 = list.iterator();
		while(itr2.hasNext()) {
			System.out.print(itr2.next() + "\t");
		}
		
		System.out.println();
		
	}

}
