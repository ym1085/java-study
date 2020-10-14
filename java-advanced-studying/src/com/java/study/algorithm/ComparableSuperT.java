package com.java.study.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Car implements Comparable<Car> {
	private int dif;
	
	public Car(int d) {
		this.dif = d;
	}
	
	@Override
	public int compareTo(Car arg0) {
		return 0;
	}
	
}

class Ecar extends Car {

	public Ecar(int d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

}

public class ComparableSuperT {
	
	public static void main(String[] args) {
		/**

			Collections.sort Method의 형태
				- 1. public static <T extends Comparable<? super T>> void sort(List<T> list)
				- 2. 위와 같이 Ecar Class를 통해 Car Class를 구현 하였다.
				이때 Comparable<T>형식이면 컴파일 에러가 발생한다.
				
				- 3. 그러므로 <? super T> -> <? super Ecar> 와 같이 자바 디자이너가 디자인을 하였다.
					- Ecar이거나 Ecar가 상속하는 Class가 Comparable<? super Ecar>에 올 수 있다.
		 */
		
		List<Car> list = new ArrayList<Car>();
		list.add(new Car(15));
		list.add(new Car(15));
		list.add(new Car(15));
		
		Collections.sort(list);
		
		Iterator<Car> itr = list.iterator();
		while(itr.hasNext()) {
			System.out.print("itr : " + itr.next() + "\t");
		}
		
		System.out.println();
	
		List<Ecar> list2 = new ArrayList<Ecar>();
		list2.add(new Ecar(30));
		list2.add(new Ecar(14));
		list2.add(new Ecar(12));
		
		Collections.sort(list2);
		
		Iterator<Ecar> itr2 = list2.iterator();
		while(itr2.hasNext()) {
			System.out.print("itr2 : " + itr2.next() + "\t");
		}
		
		System.out.println();
	}

}
