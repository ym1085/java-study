package com.java.study.collection;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

class Num {
	private int num;
	
	public Num(int n) {
		this.num = n;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public int hashCode() {
		return num % 3; // num의 값이 같으면 부류도 같다.
	}
	
	@Override
	public boolean equals(Object obj) {
		if(num == ((Num)obj).num) {
			return true;
		} else {
			return false;
		}
	}
}

class car {
	private String model;
	private String color;
	
	@Override
	public int hashCode() {
		/**
		 * 		모든 인스턴스 변수의 정보를 다 반영하여 해쉬 값을 얻으려는 노력이 깃든 문장.
		 * 		결과적으로 더 세밀하게 나뉘고, 따라서 그만큼 탐색 속도가 높아진다.
		 */
		return (model.hashCode() + color.hashCode()) / 2;
	}
	
}

class Person implements Comparable<Person> {
	String name;
	int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public int compareTo(Person o) {
		return this.age - o.age;
		
		/**
			this.age > o.age = 양의 정수 반환.
			this.age < o.age = 음의 정수 반환.
			this.age == o.age = 0을 반환.
		 */
	}
}

class PersonComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		// 1차적으로 TreeSet<E>은 오름차순으로 정렬
		return o2.age - o1.age;
	}
}

public class HashSetAndTreeSet {

	public static void main(String[] args) {
		HashSet<Integer> hs = new HashSet<Integer>();
		
		hs.add(5); hs.add(2);
		hs.add(2); hs.add(4);
		hs.add(7); hs.add(2);
	
		Iterator<Integer> itr = hs.iterator();
		while(itr.hasNext()) {
			System.out.println("First : " + itr.next());
			System.out.println("----------------------\n");	
			
			/**
			 	중복 데이터는 출력이 되지않으며, 기본적으로 오름 차순으로 정렬이 된다.
		  			First : 2
					----------------------
					
					First : 4
					----------------------
					
					First : 5
					----------------------
					
					First : 7
					---------------------- */
		}
		
		System.out.println("Chap02 HashSet<E>\n");
		for(int i=0; i<10; i++) {
			Num num = new Num(i);
			
			int res = num.hashCode();
			System.out.println("i : " + i + ", res : " + res + "\n");
			
			/**
				[HashSet -> Hash 기반 알고리즘]
				아래와 같이 인자로 전달되는 숫자에 따라 집합(바구니)가 구분이 된다.
					i : 0, res : 0
					
					i : 1, res : 1
					
					i : 2, res : 2
					
					i : 3, res : 0
					
					i : 4, res : 1
					
					i : 5, res : 2
					
					i : 6, res : 0
					
					i : 7, res : 1
					
					i : 8, res : 2
					
					i : 9, res : 0
			 */
		}
		System.out.println("Chap03 HashSet<E>\n");
		
		// Num Instance를 3개 생성 한다.
		Num num2 = new Num(3);
		 
		boolean a = num2.equals(new Num(3));
		System.out.println("a : " + a + "\n");
		
		System.out.println("Chap04 TreeSet<E>\n");
		TreeSet<Integer> tree = new TreeSet<Integer>();
		 
		tree.add(3);
		tree.add(4);
		tree.add(8);
		
		Person p1 = new Person("Woo", 14);
		Person p2 = new Person("Hong", 28);
		
		int res = p1.compareTo(p2);
		System.out.println("res : " + res  + "\n");
	
		System.out.println("Chap05 TreeSet<E> -> Comparator\n");
		TreeSet<Person> tree2 = new TreeSet<Person>(new PersonComparator());
		
		tree2.add(new Person("Yoon", 37));
		tree2.add(new Person("Hong", 53));
		tree2.add(new Person("Park", 22));
		
		for(Person p : tree2) {
			System.out.println(p);
		}
		
	}

}