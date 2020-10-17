package com.java.study.enumexam;

enum Animal1 {
	DOG, CAT
}

enum Person1 {
	MAN, WOMAN
}

public class SageEnum {

	public static void main(String[] args) {
		// NoneSafeConst.java 파일에서 문제가 되었던 부분을 다시 구현 해보자
		
		// CAT - DOG || WOMAN MAN
		// 문제가 뭐였징??
		// int 자료형에 대한 부분을 에러없이 처리 해버림
			
		who(Person1.MAN);
		//who(Animal1.DOG); 옆 메소드에서는 에러가 발생 한다.... 강한 타입 체크 및 인스턴스가 동일하지 않기 때문에
	}

	public static void who(Person1 man) {
		// man으로 전달되는 값이 int형이기에 오류가 발생하지 않는다.
		// man 참조 변수는 Person 형이기 때문에 컴파일 오류가 발생 한다.
	
		switch(man) {
			case MAN : 
				System.out.println("남성 손님입니다.");
				break;
			case WOMAN : 
				System.out.println("여성 손님입니다.");
				break;
		}
	}
	
}
