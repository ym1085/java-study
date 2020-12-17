package com.java.study.optional;

import java.util.Optional;

public class StringOptional {

	public static void main(String[] args) {
		// Optional 클래스 예제
		Optional<String> os1 = Optional.of(new String("Apple")); 			// null을 허용하지 않는다
		Optional<String> os2 = Optional.ofNullable(new String("Robot"));    // null을 허용한다
	
		if(os1.isPresent()) {
			String osData = os1.get();
			System.out.println("osData : " + osData);
		}
		
		if(os2.isPresent()) {
			String osData2 = os2.get();
			System.out.println("osData2 : " + osData2);
		}
		
		// ifPresent : 만약 내용물이 존재한다면 오른쪽에 있는 람다식을 수행 하라.
		os1.ifPresent(s -> {
			System.out.println("? : " + s);
			
			if (s.equals("Apple")) {
				System.out.println("The s is fruit");
			} else {
				System.out.println("There is no data");
			}
		});
	}
}
