package com.java.study.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @since 20.12.15
 * @category 스트림의 생성, 중간 연산, 최종 연산
 * @version JDK 1.8.0
 * @author youngminkim
 */
public class StreamOf {

	public static void main(String[] args) {
		/**
		 	Stream의 of 메서드 활용
		 	1. 기존에 존재하는 데이터를 통해 스트림을 생성하는 방법 두 가지.
		 		- 배열 기반
		 		- 컬렉션 인스턴스 기반
		 	2. 하지만 지금은 기존에 존재하는 데이터를 통해 스트림을 생성하는 것이 아닌,
		 	of() 메서드를 통해 스트림을 생성한다.
		 */
		
		// Example 01 (기본 스트림의 사용)
		Stream
			.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)				// 데이터 저장
			.filter(a -> a % 2 == 0)						// 중간 연산
			.forEach(a -> System.out.print(a + "\t"));		// 최종 연산
		System.out.println("\n");
		
		// Example 02 (IntStream의 사용)
		// peek 같은 경우는, 반복문을 통해 값을 출력함과 동시에, 합계를 출력할 때 사용
		int sum = IntStream
					.of(1, 3, 7, 38, 29 ,999, 23, 4)		// 데이터 저장
					.peek(a -> System.out.print(a + "\t"))	// 중간 연산
					.sum();									// 최종 연산
		System.out.println("\n");
		System.out.println("sum : " + sum + "\n");
	
		// Example 03 (IntStream의 사용)
		// IntStream의 count()의 경우 long 타입의 값을 반환 한다.
		long count = IntStream
					.of(1, 4, 5, 7, 8, 3, 5, 7)
					.count();
		System.out.println("count : " + count + "\n");
		
		// Example 04 (IntStream의 사용)
		IntStream
				.of(7, 5, 4, 5, 3, 5, 6)
				.average()									// 반환형이 OptionalDouble이기에 ifPresent 메서드 사용 가능
				.ifPresent(a -> System.out.println(a));		// TODO 만약 값이 없다면?? -> null이 나오는 것 같다 
		System.out.println("\n");
		
		// Example 05 (IntStream의 사용) 
		IntStream
				.of(123, 553, 434, 232, 567, 887, 123, 678, 534, 222, 333, 444, 823, 113, 545)
				.max()
				.ifPresent(a -> System.out.println("Max : " + a));
		System.out.println("\n");	

		// Example 06 (IntStream의 사용) 
		IntStream
				.of(123, 553, 434, 232, 567, 887, 123, 678, 534, 222, 333, 444, 823, 113, 545)
				.min()
				.ifPresent(a -> System.out.println("Min : " + a));
		System.out.println("\n");	
		
	}
}
