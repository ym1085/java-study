package com.java.study.enumexam;


/**

	Interface
		1. Interface는 기본적으로 public static final 이 생략이 되 있다.
		2. 사용자로부터 기능을 재 구현함과 동시에 메소드의 구현을 강제 하기 위해 사용됨.
 */
interface Animal {
	int DOG = 1;
	int CAT = 2;
}

interface Person {
	int MAN = 1;
	int WOMAN = 2;
}

public class NoneSafeConst {
	public static void main(String[] args) {
		who(Person.MAN);
		who(Animal.DOG); // 자료형이 int로 동일하기에 컴파일 에러가 발생하지 않는다.
	}
	
	public static void who(int man) {
		
		// 자료형이 int로 동일하기에 컴파일 에러가 발생하지 않는다.
		// 인터페이스에서 선언된 상수의 이름은 다르지만 값은 int형으로 같기에 문제가 되지 않는다.
		// 하지만 who Method는 프로그래머가 의도한 방식으로 호출이 되지 않았다.
		
		// TODO 다음 클래스는 -> EnumScale Class를 참고 해주세요.
		
		System.out.println("1. Person : " + man);
		System.out.println("1. Animal : " + man);
		
		switch (man) {
		case Person.MAN : 
			System.out.println("남성 손님입니다." + "\n");
			break;
			
		case Person.WOMAN : 
			System.out.println("여성 손님입니다." + "\n");
			break;
		}
	}
}
