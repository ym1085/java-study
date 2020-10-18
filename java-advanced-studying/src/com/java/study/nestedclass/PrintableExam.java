package com.java.study.nestedclass;

/**

	1. 맴버 클래스 사용예제.
	2. 반복자에서 아래와 같은 형식으로 사용이 된다.

	**  맴버 클래스는 외부에 클래스의 정의르르 감추어야 할 떄 유용하게 사용이 된다.
 */

interface Printable {
	void print();
}

class Papers {
	private String con;				// 카피를 원하는 내용
	
	public Papers(String s) {
		this.con = s;
	}

	// ** 핵심 이 부분이 가장 중요하다
	public Printable getPrinter() { //  반환 하는건 new Printer(자식 인스턴스) 반환값 Printable(부모 인스턴스)이다
		return new Printer();
	}
	
	// ** 핵심 이 부분이 가장 중요하다
//	public Printer getPrint() { // Printer 자체를 넘겨줘도 이상은 없는 것 같다.
//		return new Printer();
//	}

	// 내부 클래스(Inner Class 선언)
	// 외부에 Printer 클래스를 숨긴다.
	private class Printer implements Printable {

		@Override
		public void print() {
			// TODO Auto-generated method stub
			System.out.println(con);
		}
		
	}
}

public class PrintableExam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Papers 클래스를 인스턴스화 진행하는데, 생성자에 데이터를 초기화 해준다.
		Papers p = new Papers("서류 내용 : 행복합니다.");
		
		// return new Printer();
		// Printable prn -> 인터페이스
		
		/**

			포함된 것이 포함 시킨 것을 가르킬 수 있다.
			1. 현재 Printable 인터페이스를 Printer Class가 구현하고 있는 상황이기에,
			부모 클래스(인터페이스)로 자식 클래스 를 참조 할 수 있다.
			
			2. 다형성의 원리를 좀 더 고려 해볼것.
		 */
		Printable prn = p.getPrinter();
		
		// 메소드 호출 
		prn.print();
		
	}

}
