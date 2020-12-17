package com.java.study.methodref;

import java.util.function.ToIntBiFunction;

class Box {
	private int n;
	public Box(int i) {
		this.n = i;
	}
	
	public int larger(Box b) {
		if (n > b.n) {
			return n;
		} else {
			return b.n;
		}
	}
	
}

public class IBox {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Box b1 = new Box(5);
		Box b2 = new Box(7);
		
		// 아래와 같은 상황에서는 첫번째 인자의 메소드를 호출하는 동시에
		// 두번째로 들어온 인자를 첫번째 메소드의 매개 변수로 전달 해준다.

		// 사용되는 상황 예측 : 두 인스턴스가 가지고 있는 값을 비교할 때 사용 하면 될 듯.
		ToIntBiFunction<Box, Box> t = (f1, f2) -> f1.larger(f2);
		int bigNum = t.applyAsInt(b1, b2);
		System.out.println("bigNum : " + bigNum + "\n");
		System.out.println("----경계선----\n");

		Box b3 = new Box(23);
		Box b4 = new Box(22);
		
		ToIntBiFunction<Box, Box> t2 = Box::larger;
		int bigNum2 = t2.applyAsInt(b3, b4);
		System.out.println("bigNum2 : " + bigNum2);
	}
}
