package com.java.study.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 		@since 2020-09-29
 * 		@author youngminkim
 * 		@Topic ArrayList<E> - LinkedList<E>
 * 
 *  	@Contents
 * 			ArrayList<E> 
 * 				1. ArrayList는 내부적으로 배열 기반으로 데이터를 처리 한다.
 *				2. 배열은 데이터를 저장할때 빈 공간을 만들지 않고 저장 한다.
 * 				3. 데이터가 삭제되면 빈 공간에 따라 한 칸씩 데이터가 당겨진다.				
 * 
 * 			LinkedList<E>
 * 				1. 리스트 기반 자료구조는 열차 칸을 더하고 빼는 형태의 자료구조이다.
 *				2. 인스턴스의 저장 : 열차 칸을 하나 더한다.
 *				3. 인스턴스의 삭제 : 해당 열차 칸을 삭제한다.
 *				4. 리스트 기반은 포인터를 통해 데이터를 저장 관리 하기 때문에 가능하다.
 *			
 *			정리
 *				 최 상단에 Collection<E>이 존재하고 하단에 Set<E>, List<E>, Queue<E> 인터페이스가 
 *		   		상위 Collection<E> 인터페이스를 상속 한다.
 *		  
 *		    	 List<E> 인터페이스를 구현하는 ArrayList<E>와 LinkedList<E>는 각각 배열 기반, 리스트 기반
 *		   		으로 데이터를 저장한다. 
 *		   
 *		    	 Itetable<E> 인터페이스를 Collection<E> 인터페이스가 구현하기 때문에 하위에 존재하는 모든 인터페이스들은
 *	   		 	Iterator 메소드를 사용하여 자료 타입에 상관없이 반복자를 호출 할수 있다.
 *
 *
 */

public class ArrayListAndLinkedList {
	public static void main(String[] args) {
		
		// ArrayList<E>
		List<String> list = new LinkedList<String>();	

		list.add("Toy");
		list.add("Box");
		list.add("Robot");

		for(int i = 0; i < list.size(); i++) { 
			System.out.println(list.get(i));
			System.out.println();
		}
		
		list.clear();
		System.out.println("list.size() " + list.size() + "\n");
		
		// LinkedList<E>
		LinkedList<String> list2 = new LinkedList<String>();
		
		list2.add("A");
		list2.add("B");
		list2.add("C");

		for(String e : list) {
			 System.out.println(e);
			 System.out.println();
		}
		
		// Iterable<E>의 Iterator Method 
		Iterator<String> itr = list2.iterator();
		while(itr.hasNext() ) {
			String a = itr.next();
			
			if(a.equals("B")) {
				System.out.println("The a equals B");
				break;
			}
		}
		
		System.out.println("End of Programming");
		
		// 배열 -> ArrayList<E>
		List<String> list3 = Arrays.asList("C", "JAVA", "C#", "Spring");
		list3 = new ArrayList<String>(list3); // ArrayLisy<E> 형태로 변경.

		Iterator<String> itr2 = list3.iterator();
		
		while(itr2.hasNext()) {
			System.out.println("itr2 : " + itr2.next());
		}
		
		System.out.println("End of Programming2");
		
	}
	
}