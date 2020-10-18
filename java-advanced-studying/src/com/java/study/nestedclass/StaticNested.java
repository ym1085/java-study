package com.java.study.nestedclass;

class Outer {
	private static int num = 0;
	
	static class Nested1 { // 스태틱 내부 클래스
		void add(int n) {
			num+=n; // Outer 클래스의 static 변수 공유! 
		}
	}
	
	static class Nested2 { 
		int get() {
			return num;
		}
	}
}

public class StaticNested {
	public static void main(String[] args) {
		// [중요]
		// 내부 클래스는 반드시 외부 클래스에 종속되어 인스턴스 생성이 진행 되어야 한다.
		// 즉 내부 클래스(Inner Class)는 독립적으로 인스턴스 생성이 불가능하다.
		
		
		// TODO 값을 넣는건 nst1 객체로 하고 있다는 걸 체크 해야 한다.
		Outer.Nested1 nst1 = new Outer.Nested1(); // 인스턴스 생성 방법!!
		nst1.add(5);
		
		// TODO 값을 가져오는건 nst2 인스턴스로 수행 하고 있다.
		// - 즉 static 변수는 하나만 생성이 되어 공유하는 목적을 갖는다.
		Outer.Nested2 nst2 = new Outer.Nested2(); 
		System.out.println("nst2 : " + nst2.get()); // 5가 출력 될 것 이다.
		
		// static 변수는 해당 클래스와 연관이 하나도 없기 때문에 위 같이 출력이 가능하다.
		// static이 붙으면 자리를 빌려 들어왔다 생각하면 된다.
		
	}

}
