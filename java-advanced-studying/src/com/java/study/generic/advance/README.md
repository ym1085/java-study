# GENERIC

### 제네릭(Generic)이란
- **타입을 파라미터화해서** **컴파일시** **구체적인 타입이 결정되도록 하는 것**
    - **자바 5**부터 **추가된 기능**.
    - 컬렉션, 람다식(함수적 인터페이스), 스트링, NIO에서 널리 사용된다.
    - 제네릭을 모르면 도큐먼트를 해석할 수 없다.

</br>

### 예제 1-1

```java
Class ArrayList<E>

default BiConsumer<T,U> andThen(BiConsumer<? super T,? super I> after)
```

</br>

## 제네릭을 사용하므로서 얻는 이점

- 컴파일시 강한 **타입 체크**를 할 수 있다.
    - 실행 시 타입 에러가 나는 것보다, **컴파일시 미리 타입을 강하게 체크해 에러를 사전에 방지.**
- **타입 변환을 제거**할 수 있다.
    - **타입 변환이 많을수록 전체 애플리케이션 성능이 떨어진다.**

</br>

### 예제 1-2

```java
List list = new ArrayList();
list.add("hello");
String str = (String) list.get(0);
```

- 위의 코드에서는 강제 타입 변환이 두번 일어난다.
- 아래는 제네릭 적용 후 코드의 변화된 결과다.

</br>

### 예제 1-3

```java

List<**String**> list = new ArrayList<**String**>();
list.add("hello");
String str = list.get(0);
```

- 타입 인자로 <String>을 선언하여 Down Casting을 수행하지 않게된다.
- Down Casting을 수행하는 것은 JVM의 간섭을 없애기에 좋지 않다.

</br>

# 제네릭의 기본 문법

### 다중 매개변수 기반 제네릭 클래스의 정의

<img width="792" alt="_2020-08-01__1 11 33" src="https://user-images.githubusercontent.com/53969142/96256635-fc579580-0ff3-11eb-8369-01ee9cd3f612.png">

- 다중 매개변수 기반 제네릭 클래스 정의도 가능하다.
- DBox<String, Integer> box = new DBox<String, Integer>();

</br>

## 타입 매개변수의 이름 규칙

### 일반적인 관례

- **한 문자**로 이름을 짓는다.
- **대문자**로 이름을 짓는다.

</br>

### 보편적인 선택

- E - Element
- K - Key
- N - Number
- T - Type
- V - Value

</br>

## 기본 자료형에 대한 제한 그리고 래퍼 클래스

<img width="787" alt="_2020-08-01__1 17 59" src="https://user-images.githubusercontent.com/53969142/96256695-15604680-0ff4-11eb-97b8-5c8aad6427cd.png">

- **제네릭의 타입 인자로 기본 자료형이 올 수 없으므로, 컴파일 오류 발생**
- 기본 자료형의 값이 전달되면 **Auto Boxing, Auto UnBoxing**을 수행 한다.

</br>

## 매개변수화 타입을 타입 인자로 전달

<img width="740" alt="_2020-08-01__1 27 42" src="https://user-images.githubusercontent.com/53969142/96256734-227d3580-0ff4-11eb-9cc0-4bea7c62636f.png">

매개변수 타입에 매개변수화 타입을 넣는다.

- <String> - 타입 인자
- <T> - 타입 매개변수
- Box<T> - 매개변수화 타입

</br>

## 제네릭 클래스의 타입 인자 제한하기

<img width="757" alt="_2020-08-01__1 37 14" src="https://user-images.githubusercontent.com/53969142/96256778-37f25f80-0ff4-11eb-9aac-ef897158bdae.png">

- class Box<T extends Number> { ... }
    - 인스턴스 생성 시 타입 인자로 Number 또는 이를 상속하는 클래스만 올 수 있음.
    - T로 전달이 되는 인자는 Number Class를 상속 또는 Number Class의 Instance만 가능.

</br>

## 타입 인자 제한의 효과

<img width="746" alt="_2020-08-01__1 43 09" src="https://user-images.githubusercontent.com/53969142/96256801-43de2180-0ff4-11eb-8f70-a6e693011768.png">

**중요한 부분**

- 제한을 하는 이유가 무엇인가??
    - 제한하는데 끝나는 것이 아닌, 부가적으로 Number Class에 있는 메소드를 호출하는 효과
    - 제네릭 클래스를 정의할때 **extends**를 통해 **제한**을 하는 경우가 많다.

</br> 

## 제네릭 클래스의 타입 인자를 인터페이스로 제한

<img width="728" alt="_2020-08-01__1 49 11" src="https://user-images.githubusercontent.com/53969142/96256829-50627a00-0ff4-11eb-87ed-7903fa8223ee.png">

- T로 전달되는 이름은 Eatable Interface를 직접적으로, 혹은 간접적으로 구현한 이름만 올 수 있다.

</br>

## 하나의 클래스 하나의 인터페이스에 대해 동시 제한

### 예제 1-1

```java
class Box<T extends **Number** & **Eatable**> { ... } // Class, Interface 동시 제한
```

</br>

## 제네릭 메소드의 정의

<img width="732" alt="_2020-08-01__1 52 54" src="https://user-images.githubusercontent.com/53969142/96256886-6708d100-0ff4-11eb-8976-b2b92129397c.png">

- 클래스 전체를 제네릭으로 선언하는 것이 아닌, 하나의 메소드에 대해 제네릭 선언을 하겠다.
- 제네릭 클래스에서의 타입 인자 <T>는 인스턴스를 생성하는 순간 초기화가 되었다.
- 제네릭 메소드에서의 타입 인자 <T>는 메소드가 호출 되는 순간 초기화 된다.
- 타입 인자 **T** 에는 **기본 자료형**이 올 수 없다.

</br>

### 예제 1-1

```java
// **제네릭 메소드**
class BoxFactory {
	public static <**T**> Box<**T**> makeBox(**T** o){
		Box<**T**> box = new Box<T>();
		box.set(o);
		return box;
	}
}

// **제네릭 메소드의 T는 메소드 호출 시점에 결정한다.**
	Box<String> sBox = BoxFactory.<**String**>makeBox("Sweet");
	Box<Double> sBox = BoxFactory.<**Double**>makeBox(7.59); // 오토 박싱

// **다음과 같이 타입 인자 생략 가능**
	Box<String> sBox = BoxFactory.makeBox("Sweet");
	Box<Double> sBox = BoxFactory.makeBox(7.59); // 오토 박싱
```

여기서 집중할 부분은 static <T> 이 부분이다.
- 메소드에 <T> 이러한 표시를 하는 것은 제네릭 메소드임을 의미 하는 것이다. 
- public <타입 파라미터 . . . > 리턴타입 메소드명 (매개변수, . . . ) { . . . }

</br>

## 제네릭 메소드의 제한된 타입 매개변수 선언

<img width="812" alt="_2020-08-01__2 10 33" src="https://user-images.githubusercontent.com/53969142/96256913-70923900-0ff4-11eb-9059-8b55e891f726.png">

### <T extends Number> 
- T는 Number를 상속하거나, Number이어야 한다.
- 하나의 메소드만을 대상으로 제네릭 선언을 하기 위해 Class에서 내려온거라 생각하면 된다. 
- 즉, 제네릭 메소드임을 말하기 위해 위 같이 선언을 한 것이다.

</br>

# 제네릭 2-1

### 제네릭의 심화 문법

<img width="767" alt="_2020-08-03__8 47 30" src="https://user-images.githubusercontent.com/53969142/96257179-e0a0bf00-0ff4-11eb-9d24-cab97026d699.png">

- 제네릭 클래스 역시 `상속`이 가능하다.
- Instance생성 시 <T>는 어떻게 해야 하는가?
- 상속 관계로 이루어진 상황에서, 부모 클래스의 자료형이 자식 클래스의 자료형과 같아야 한다.
    - 즉, 자료형이 같아야 파생되는 상속 관계가 생성이 된다.

</br>

## 타켓 타입

<img width="774" alt="_2020-08-03__8 53 40" src="https://user-images.githubusercontent.com/53969142/96257235-f615e900-0ff4-11eb-9791-853f0131bbe3.png">

- **참조 변수의 형 Box<Integer>를 기반**으로 **makeBox 메소드의 T를 결정**하게 된다.
- 따라서 이를 가리켜 타켓 타입이라 한다.

</br>

## 와일드카드(제네릭 메소드 vs 일반 메소드)

<img width="786" alt="_2020-08-03__9 17 41" src="https://user-images.githubusercontent.com/53969142/96257267-029a4180-0ff5-11eb-8302-0005f28e5276.png">

- <T>로 들어오는 인자가 다르면, 상속 관계가 형성 될 수 없다.
- <T>는 컴파일러가 결정해주는 것이다.
    - 제네릭 클래스는 Instance화를 진행 할때 결정이 된다.
    - 제네릭 메소드는 메소드 호출 시 결정이 된다.

</br>

## 와일드카드

<img width="778" alt="_2020-08-03__9 22 32" src="https://user-images.githubusercontent.com/53969142/96257329-21003d00-0ff5-11eb-8d31-c3709a11cab9.png">

- 와일드카드는 제네릭과 유사한 점이 많다, 또한 제네릭 메소드와의 결합을 통해 막강한 파워 생성.
- 와일드카드<?>는 어떠한 자료형도 받을 수 있다는 것을 의미한다.
- **와일드카드 기반 메소드의 정의가 더 간결하다는 장점이 있다.**

</br>

### 기능적으로는 두 메소드 완전 동일

```java
public static <T> void peekBox(Box<T> box){
	System.out.println(box);
} // 제네릭 메소드의 정의

public static void peekBox(Box<**?**> box){
	System.out.println(box);
} // 와일드카드 기반 메소드 정의
```

</br>

## 와일드 카드의 상한과 하한의 제한 : Bounded Wildcards

<img width="748" alt="_2020-08-03__9 29 27" src="https://user-images.githubusercontent.com/53969142/96257422-48570a00-0ff5-11eb-9ddf-fb972d472c26.png">

- 제네릭에서는 제한하는 방법이 **한 가지**밖에 없었다.
    - extends
- **하지만 와일드카드는 두 가지를 통해 제한을 할 수 있다.**
    - extends
    - super

</br>

## 하한 제한된 와일드카드

### 예시 1-1

```java
public static void peekBox(Box<? super Integer> box){
	System.out.println(box);
}
```

- **extends**는 **상한 제한**으로, Integer이거나 Integer를 상속하는 **하위 계층**으로 제한 한다.
- **super**는 **하한 제한**으로, Integer이거나 Integer가 상속하는 **상위 계층**으로 제한 한다.

</br>

## 와일드카드 제한의 이유 설명을 위한 도입

### 예시 1-1

```java
class Box<T> {
		private T ob;
		
		public void set(T o) {
			ob = o;
		}
		
		public T get() {
			return ob;
		}
}

class Toy {
		public String toString(){
			return "I am a Toy";
		}
}

class BoxHandler {
		public static void outBox(Box<Toy> box){
			Toy t = box.get();  // 상자에서 꺼내기
			System.out.println(t);
		}
	
		public static void inBox(Box<Toy> box, Toy n){
			box.set(n) // 상자에 넣기
		}
}
```

</br>

## 상한 제한의 목적

### 예시 1-1

```java
class Box<T> {
		private T ob;
		public void set(T o ) { ob = o; }
		public T get() { return ob; }
}
...

public static void outBox(Box<? extends Toy> box){
		box.get(); // 꺼내는 것! OK
		box.set(new Toy()); //넣는 것! ERROR
}

class Car extends Toy {...}   // 자동차 장난감
class Robot extends Toy {...} // 로봇 장난감

// 다음과 같이 정리하자!
**Box<? extends Toy> box 대상으로 넣는 것 불가!!**
```

상한 제한을 하면 꺼내는 것은 가능하지만, 값 셋팅은 불가능하다.

- 왜 값을 꺼낼수는 있고, 셋팅은 불가능한가?
- 개인적으로 생각 해보길.

</br>

## 하한 제한의 목적

### 예시 1-1

```java
class Box<T> {
	private T ob;
	public void set(T o ) { ob = o; }
	public T get() { return ob; }
}
...

public static void inBox(Box<Toy> box, Toy n){
		box.set(n) // 상자에 넣기
		Toy myToy = box.get(); // **꺼내는 것! Error**
}
	
// 다음과 같이 정리하자!
Box<? super Toy> box 대상으로 꺼내는 것 불가!
```

하한 제한을 하면 셋팅은 가능하지만, 꺼내는 것은 불가능하다.

- 왜 값을 셋팅할 수 있고, 꺼내는 것은 불가능한가?
- 개인적으로 생각 해보길.
- 자식 클래스의 참조 변수로, 부모 클래스의 인스턴스를 참조한다.
    - 어떤 인자가 들어올지 모르는 상황이다.
    - 또한 무엇이 반환 될지 모르는 상황이다.

</br>

## 상한 제한과 하한 제한의 좋은 예

```java
class BoxcontentsMover {
	// from에 저장된 내용물을 to로 이동
	
	public static void moveBox(Box<? super Toy>to, Box<? extends Toy> from){
		to.set(from.get());
	}
}

// <? super Toy> : 하한 제한
// <? extends Toy> : 상한 제한
```

</br>

## 제한된 와일드카드 선언을 갖는 제네릭 메소드 : 도입

<img width="756" alt="_2020-08-03__10 19 49" src="https://user-images.githubusercontent.com/53969142/96257537-80f6e380-0ff5-11eb-83df-df62250424c1.png">

## 다음 형태로 메소드 오버로딩 불가능하다

<img width="739" alt="_2020-08-03__10 25 14" src="https://user-images.githubusercontent.com/53969142/96257574-90762c80-0ff5-11eb-86c5-8246a91fb27c.png">

- 기존 제네릭에 영향을 주지않고 업데이트를 하기 위해 불편한 점을 감안한다.
- 기존 코드를 유지하기 위해 살짝 불편한 부분이 생겼다.
- Box<? extends Toy> box
- Box<? extends Robot> box
    - 메소드 오버로딩을 지원하지 않는다.
    - 컴파일러가 위 부분을 싹 지워버린다.
    - **Type Erasure! 라는 용어로 알려져있다.**

</br>

## 와일드 카드 선언을 갖는 메서드를 제네릭으로

<img width="745" alt="_2020-08-03__10 33 11" src="https://user-images.githubusercontent.com/53969142/96257614-9ff57580-0ff5-11eb-807f-8cbf6cf43a56.png">

- ? extends 선언이 들어가면 상한 제한 → Box<T>
    - 상한 제한은 꺼내는 것만 가능하다.
    - 하한 제한은 셋팅하는 것만 가능하다.

</br>

## 제네릭 인터페이스의 정의와 구현

<img width="759" alt="_2020-08-03__10 36 03" src="https://user-images.githubusercontent.com/53969142/96257645-adaafb00-0ff5-11eb-91ff-49f0608fe538.png">

- 핵심 : 제네릭으로 인터페이스도 구현이 가능하다.


