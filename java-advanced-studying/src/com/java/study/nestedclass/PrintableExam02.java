package com.java.study.nestedclass;

/**

	1. 익명 클래스 사용예제.
	2. 반복자에서 아래와 같은 형식으로 사용이 된다.

	**  익명 클래스는 로컬 클래스 -> 해당 클래스를 간소화 하기 위해 사용이 된다.
 */

interface Printable02 {
	void print();
}

class Papers02 {
	private String con;	// 카피를 원하는 내용
	
	public Papers02(String s) {
		this.con = s;
	}
	
	// 익명 클래스의 사용형태
	public Printable02 getPrinter02() {
		// Printable02를 구현하고 있는 클래스 인스턴스를 생성해줘.
		// 근데 해당 인스턴스에는 아래와 같은 몸체를 같이 넣어서 생성해줘.
		
		/*
			라는 의미를 가지고 있다.
			public void print() {
				System.out.println(con);
			}
		 */
		
		return new Printable02() {
			public void print() {
				System.out.println(con);
			}
		};
	}

}

public class PrintableExam02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Papers 클래스를 인스턴스화 진행하는데, 생성자에 데이터를 초기화 해준다.
		Papers02 p = new Papers02("서류 내용 : 행복 하지 않습니다.");
		
		// return new Printer();
		// Printable prn -> 인터페이스
		
		/**

			포함된 것이 포함 시킨 것을 가르킬 수 있다.
			1. 현재 Printable 인터페이스를 Printer Class가 구현하고 있는 상황이기에,
			부모 클래스(인터페이스)로 자식 클래스 를 참조 할 수 있다.
			
			2. 다형성의 원리를 좀 더 고려 해볼것.
		 */
		Printable02 prn02 = p.getPrinter02();
		
		// 메소드 호출 
		prn02.print();
		
	}

}
