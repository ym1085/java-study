package com.java.study.nestedclass;

import com.java.study.nestedclass.Outer2.Member;

class Outer2 {
	private int num = 0;
	
	class Member {
		void add(int n) {
			num+=n;
		}
		
		int get() {
			return num;
		}
	}
}

public class MemberInner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 객체를 생성할때마다 참조하는 메모리 주소가 달라진다는점은 알고 있겠죠??
		
		Outer2 o1 = new Outer2(); // com.java.study.nestedclass.Outer2@1e965684
		Outer2 o2 = new Outer2(); // com.java.study.nestedclass.Outer2@4d95d2a2
		
		// o1 기반으로 두 인스턴스르르 생성한다.
		Outer2.Member o1m1 = o1.new Member();
		Outer2.Member o1m2 = o1.new Member();
		
		// o2 기반으로 두 인스턴스르르 생성한다.
		Outer2.Member o2m1 = o2.new Member();
		Outer2.Member o2m2 = o2.new Member();

		// 동일한 인스턴스를 바라보고 있기에 아래와 같이 선언이 가능한것이다.
		o1m1.add(5);
		System.out.println(o1m2.get());
		
		// 동일한 인스턴스를 바라보고 있기에 아래와 같이 선언이 가능한것이다.
		o2m1.add(10);
		System.out.println(o2m2.get());
		
	}

}
