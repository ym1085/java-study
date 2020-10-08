package com.java.study.collection;

import java.util.LinkedList;
import java.util.Queue;

class QueueTest {

	public static void main(String[] args) {
		/*
			[복습] 
			
			Queue
			- FIFO (First In First Out)
			- 가장 먼저 들어간 데이터가 가장 먼저 출력 된다.
			- 선입선출의 개념을 활용.

			- 프로그래밍 레벨보다, 알고리즘 관점에서 많이 사용이 된다.

			offer()
			- add()와 동일한 기능으로 데이터를 삽입할때 사용.
			
			peek()
			- 다음에 나올 데이터의 값을 출력 한다.
			
			poll()
			- 인스턴스를 출력한다, 저장된 순서대로 출력을 한다.
				- A, B, C 순으로

		 */
		
		Queue<String> que = new LinkedList<String>();
		
		// 
		que.offer("A");
		que.offer("B");
		que.offer("C");
		
		// 무엇이 다음에 나올지 확인
		// 확인할 대상이 없으면 null 반환
		System.out.println("next : " + que.peek());
		
		// 첫 번째, 두 번째 인스턴스 꺼내기
		// 꺼낼 대상이 없으면 null 반환.
		System.out.println(que.poll());
		System.out.println(que.poll());
		
		// 무엇이 다음에 나올지 확인
		System.out.println("next : " + que.peek());
	
		// 마지막 인스턴스 꺼내기
		System.out.println(que.poll());
	
	}
}
