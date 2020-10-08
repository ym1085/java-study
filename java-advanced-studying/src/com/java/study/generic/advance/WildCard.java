package com.java.study.generic.advance;

/**
 * 
 * @since 20201008
 * @content WildCard
 * @author ymkim
 *
 */
public class WildCard {

	public static void main(String[] args) {
		// Section 01 - Call peekBox01 Method
		System.out.println("------------Section01------------");
		Box<Integer> box = new Box<Integer>();
		
		// Set Data
		box.setO(123);
		
		// Output Data
		peekBox01(box);
	
		// Section 02 - Call peekBox02 Method
		System.out.println("------------Section02------------");
		Box<Double> box2 = new Box<>();
		
		// Set Data
		box2.setO(1.3434);
		
		// Output Data
		peekBox02(box2);
		
		// Section 03 - Call peekBox03 Method
		System.out.println("------------Section03------------");
		Box<Integer> box3 = new Box<>();
		
		// Set Data
		box3.setO(555555);
		
		// Output data
		peekBox03(box3);
		
	}
	
	// 예제01 : 와일드카드 기본
	// 제네릭과 비슷하지만 가독성이 훨씬 뛰어난 것 같다.
	public static void peekBox01(Box<?> box) {
		System.out.println("box : " + box.getO() + "\n\n");
	}

	// 예제02 : 와일드카드 상한 제한 -> 상한 제한은 해당 Class 기준 아래 방향.
	// Number이거나 Number를 상속한 친구만 '?'에 올 수 있다.
	public static void peekBox02(Box<? extends Number> box) {
		System.out.println("box : " + box.getO() + "\n\n");
	}
	
	// 예제03 : 와일드카드 하한 제한 -> 하한 제한은 해당 Class 기준 위쪽 방향.
	// Integer를 기준으로 Integer가 직접적으로 혹은 간접적으로 상속한 클래스만 '?' 올 수 있다.
	public static void peekBox03(Box<? super Integer> box) {
		System.out.println("box : " + box.getO() + "\n\n");
	}
	
	// 상한 제한의 예
	// 상한 제한은 꺼내는 것이 가능하지만, 셋팅하는 것은 불가능하다?
	// 간단하게 box 참조 변수를 통해 세팅은 가능하지만, 꺼내는 것은 불가능하다.
	public static void outBox(Box<? extends Toy> box) {
		box.getO(); // 꺼내는 것 OK
		// box.setO(new Toy());	// 넣는 것 Error
	}
	
	// 하한 제한의 예
	// 하한 제한은 넣는것이 가능하지만, 꺼내는 것은 불가능하다.
	public static void InBox(Box<? super Toy> box, Toy n) {
		box.setO(n);
		// box.setO(new Toy()); // 넣는 것 Error
	}
	
}

class Toy {
	private int num;
	private String fruit;
}

class Box<T> extends Toy{
	private T o;
	
	public void setO(T o) {
		this.o = o;
	}
	
	public T getO() {
		return o;
	}
}


