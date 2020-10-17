package com.java.study.enumexam;

public class Varargs {

	/**

		가변 인자 선언은 꽤 괜찮은 것 같다 유용하게 응용할 수 있을듯.

	 */
	public static void showAll(String...vargs) {
		System.out.println("LEN : " + vargs.length);
	
		for(String s : vargs) {
			System.out.print("s : " + s + "\t");
		}
		System.out.println();
		System.out.println();
	}
	
	public static void main(String[] args) {
		// 매개변수의 가변 인자 선언
		showAll("Box");
		showAll("Box", "Toy");
		showAll("Box", "Toy", "Apple");
	
		/**
		컴파일러 관점에서의 가변 인자 선언
		
		showAll(new String[]{"Box"});
		showAll(new String[]{"Box", "Toy"});
		showAll(new String[]{"Box", "Toy", "Apple"});	

		 */
		
	}

}
