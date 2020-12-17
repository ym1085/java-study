package com.java.study.methodref;

import java.util.Arrays;
import java.util.List;

public class ForEachDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> lst = Arrays.asList(1, 3, 5, 7, 9);
		
		// forEach(Consumer<? super T>) 이기에 가능한 메소드 참조 형식이다.
		lst.forEach(e -> System.out.print(e + "\t"));
		lst.forEach(System.out::println);
		
		/**
			default void forEach(Consumer<? super T> action)
				for (T t : this)
					action.accept(t); */
	}
}
