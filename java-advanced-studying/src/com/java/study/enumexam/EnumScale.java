package com.java.study.enumexam;

/**

	enum 
		1. 열겨형을 표현하기 위해 사용이 된다.
		2. 클래스의 범주에 속한다 해도 무방하다.
		3. 또한 enum 이름 {
			열거형 값(인스턴스)를 의미한다.
		}
 */

// 변경 전 --> Java Version 5
interface ScaleTest {
	int DO = 0; int RE = 1; int MI = 2; int FA = 3;
	int SO = 4; int RA = 5; int TI = 6;
}


// 변경 후 --> Java Version 8
enum Scale {
	DO, RE, MI, FA, SO, RA, TI	// 아주 특이한 형식이징???? 
}


public class EnumScale {

	public static void main(String[] args) {
		//  NonoSafeConst에서 int 자료형 문제로 해결하지 못핶던 문제를 
		// 열겨형 enum을 통해 해결 할 수 있다.
		
		Scale sc = Scale.DO;					// 여기서 sc는 인스턴스다 
		System.out.println("sc : " + sc);
		
		// 상수인지 테스트 해보자
		
		//sc.MI;
		//sc.RE;
		//sc.FA;

		// 위 코드 경고 또는 에러 나오는 듯.
		
		switch (sc) {
		case DO :
			System.out.println("도~");
			break;
		
		case RE :
			System.out.println("레~");
			break;
		
		case MI :
			System.out.println("미~");
			break;
		
		case FA :
			System.out.println("파~");
			break;
			
		default:
			System.out.println("솔~ 라~ 시~");
		}
	}

}
