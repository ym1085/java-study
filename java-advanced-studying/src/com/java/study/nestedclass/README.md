# Section 01

### Goal

네스티드 클래스와 람다의 소개에 대해 간략히 정리 해보자.

</br>

### 네스티드 클래스와 이너 클래스

 알 필요가 있는 부분이지만 강제로 기억 할 필요가 없는 부분이다.

</br>

### 네스티드 클래스

<img width="799" alt="_2020-10-18__2 10 29" src="https://user-images.githubusercontent.com/53969142/96363550-19b16e80-1170-11eb-9984-2145533b615d.png">

```java
class Outer { // 외부 클래스
	class Nested {...} // 네스티드 클래스
}

class OuterClass {
	static class StaticNestedClass {...} // Static 네스티드 클래스
}

class OuterClass {
	class InnerCLass {...} // Non-Static 네스티드 클래스, 이너 클래스
}
```

- 클래스 **안에 정의**된 클래스를 **네스티드 클래스**라 말한다.
- 또한 네스티드 클래스를 감싸고 있는 클래스를 외부 클래스라 한다.

</br>

**네스티드 클래스는 두 가지로 분류가 된다.**

- static이 붙은 네스티드 클래스.             → Static 네스티드 클래스
- staitc이 붙지 않은 네스티드 클래스.     → 이너 클래스 또는 내부 클래스

</br>

**또한 이너 클래스는 세 가지로 분류가 된다.**

- 맴버 (이너) 클래스 (Member Inner Class)
- 로컬 (이너) 클래스 (Local Inner Class)
- 익명 (이너) 클래스 (Anonymous Inner Class)

</br>

### Static Nested Class

```java
Class Outer {
	private static int num = 0;
	
	static class Nested1 { // **Static 네스티드 클래스**
		void add(int n) {
			num+=n; // **Outer 클래스의 static 변수 공유!**
		}
	}
		
	static class Nested2 { // **Static 네스티드 클래스**
		int get() {
			return num; // **Outer 클래스의 static 변수 공유!**
		}
	}
} // Outer

class StaticNested {
	public static void main(String[] args) {
		Outer.Nested1 nst1 = new Outer.Nested1(); // 인스턴스 생성 방법!
		nst1.add(5);
		
		Outer.Nested2 nst2 = new Outer.Nested2();
		System.out.println(nst2.get());
	}
}
```

- 클래스 안에서 static이 붙으면 자리를 빌려 들어 왔다 생각하고 해당 클래스와 아무 관계가 없다.
- 위 코드에서 Outer 클래스와  네스티드 클래스는 별개로 바라봐야 한다.
- **인스턴스 생성 방법에 있어 기존 클래스의 객체를 생성하는 것 과는 차이가 있다.**

</br>

### 출력

```java
5
```

</br>

### 내부 클래스

 **우선 들어가기에 앞서 내부 클래스(이너 클래스-Innver Class)는 Static Nested 클래스와는 다른 클래스로 바라봐야 한다.**

</br>

### 내부 클래스의 구분

**맴버 클래스 (Member Class)**

- **인스턴스 변수**, **인스턴스 메소드**와 **동일한 위치**에 정의.
- 인스턴스 생성 시 존재 하는 변수를 **인스턴스 변수**라 지칭 한다.
- 인스턴스 생성 시 존재 하는 메소드를 **인스턴스 메소드**라 지칭 한다.
    - 즉, **맴버 클래스**는 인스턴스 생성 시 인스턴스 변수, 메소드와 **대등한 위치**에 존재하는 클래스를 의미 한다.

</br>

**로컬 클래스 (Local Class)**

- **중괄호 내**에, 특히 **메소드 내에 정의**.
- 특정 메소드 내로 해당 클래스를 완전히 숨김으로써, 보이는 **범위**(접근 할 수 있는 범위)를 메소드 내로 제한 한 것이다.

</br>

**익명 클래스 (Anonymous Class)**

- 클래스인데 이름이 없어! 😂

</br>

### 내부 클래스 예제 01

```java
class Outer {
	class MemberInner {
		... // 맴버 클래스
	}

	void method() {
		class LocalInner {
			... // 로컬 클래스
		}
	}
}
```

</br>

### 맴버 클래스

<img width="813" alt="_2020-10-18__3 24 56" src="https://user-images.githubusercontent.com/53969142/96363564-3188f280-1170-11eb-90b7-36e2e55eb630.png">

```java
class Outer {
	private int num = 0;

	class Member { // 내부 클래스 혹은 이너 클래스 -> Class 내에 선언된 클래스
		void add(int n) { num+=n;}
		int get() {return num;}	
	}

	// 맴버 클래스의 인스턴스는 외부 클래스의 인스턴스에 종속적이다.
}

class MemberInner {
	public static void main(String[]args) {
		Outer o1 = new Outer();
		Outer o2 = new Outer();
	
		// o1 기반으로 두 인스턴스 생성
		Outer.Member o1m1 = o1.new Member();
		Outer.Member o1m2 = o1.new Member();
	
		// o2 기반으로 두 인스턴스 생성
		Outer.Member o2m1 = o2.new Member();
		Outer.Member o2m2 = o2.new Member();

		// o1 기반으로 생성된 두 인스턴스의 메소드 호출
		o1m1.add(5);
		System.out.println(o1m2.get());
	
		// o2 기반으로 생성된 두 인스턴스의 메소드 호출
		o2m1.add(5);
		System.out.println(o2m2.get());
		
	}
}
```

- 첫번째로 외부 클래스와 Member 클래스는 깊은 관계를 가지고 있다?
- 또한 외부 클래스를 기반으로 내부 클래스를 사용 하기에, 외부 클래스에 데이터에 접근이 용이하다.
- **Member 클래스**의 인스턴스는 **외부 클래스의 인스턴스에 종속적**이다.
- 즉, **Member 클래스**(내부 클래스)는 **독립적**으로 **존재가 불가능**하다.
    - Member member = new Member(); ← 이런 문장은 선언이 불가능하다.
    - Outer.Member o1m1 = **o1**.new Member();
    - Outer.Member o1m1 = **o2**.new Member();

</br>

### 출력

```java
5
7
```

</br>

### 맴버 클래스를 언제 사용 하는가?

```java
interface Printable {
	void print();
}

class Papers {
	private String con;
	
	public Papers(String s) {
		con = s;
	}	

	public Printable getPrinter() {
		return new Printer();
	}

	// Paper Class 안에 Printer 클래스를 감춘 상태.
	private class Printer implements Printable { 
		public void print() {
			System.out.println(con);
		}
	}
}

public static void main(String[] args) {
	Papers p = new Papers("서류 내용 : 행복합니다.");
	Printable prn = p.getPrinter();
	prn.print();
}
```

- 맴버 클래스는 클래스의 정의를 감추어야 할 때 유용하게 사용이 된다.
- 클래스 사용자 입장에서 Printable 인터페이스는 알지만 Printer 클래스는 모른다!
    - 즉, Printer 클래스를 아무리 뜯어 고친다 한들 상관이 없다. 

</br>

### 로컬 클래스 (Local Class)

```java
interface Printable {
	void print();
}

class Papers {
	private String con;
	
	public Papers(String s) {
		con = s;
	}	

	public Printable getPrinter() {
		**class Printer implements Printable {
			public void print() {
				System.out.println(con);
			}
		}
		
		// 감췄어! 더 깊이 감췄어!! 메소드 안으로**

		return new Printer();
	}
}

public static void main(String[] args) {
	Papers p = new Papers("서류 내용 : 행복합니다.");
	Printable prn = p.getPrinter();
	prn.print();
}
```

- 로컬 클래스는 해당 내부 클래스를 더 깊이 감추기 위해 사용이 되는 형태이다.
- 익명 클래스는 위 같은 상황에서 클래스 이름이 필요하지 않을 때 사용이 되는 형태이다.

</br>

### 출력

```java
서류 내용 : 행복합니다.
```

</br>

### 익명 클래스 (Anonymous Class)

```java
public Printable getPrinter() {
	class Printer implements Printable { // 로컬 클래스의 Printer의 정의
		public void print() {
			System.out.println(con);
		}
	}

	return new Printer(); // Printer 인스턴스 생성
}

------------------------> **변경 후** 

public Printable getPrinter() { 
	return new Printable() { // 익명 클래스의 정의와 인스턴스 생성
		public void print() {
			System.out.println(con);
		} 
	};
}
```

- Printable Interface를 구현한 인스턴스를 생성 해라.
- 근데 인터페이스에 존재하는 void print() 메소드는 추상 메소드로 비어 있는데요??
- 그러면 익명 클래스를 사용하여 구현을 해봐 👍

> Javascript에서 사용한 ES6 문법 화살표 함수와 유사, 람다식과 유사하다

</br>

### 익명 클래스 (Anonymous Class) 02

<img width="777" alt="_2020-10-18__4 16 20" src="https://user-images.githubusercontent.com/53969142/96363574-46fe1c80-1170-11eb-8a07-fbd4d9180ee1.png">

- Printable Interface를 구현한 클래스의 인스턴스를 너가 컴파일 해줘~~~!
- public void print()의 출처가 어디인지 알려주는 것 → **return new Printable();**
    - new Printable() : Printable Interface를 구현한 클래스의 인스턴스를 생성 해라.
    - 그런데 위 인스턴스 안에는 아래 메소드의 정의를 담아라.

    ```java
    public void print() {
    	System.out.println(con);
    }
    ```

</br>

### 🌟 람다 (Lambda)

 람다의 기본 골격은 익명 클래스를 기반으로 만들어진다 봐도 무방하다, 지금부터 람다에 대해 정리 해보자.

</br>

### 익명 클래스 예제 01

```java
interface Printable {
	void print(String s);
}

class Lamda01 {
	public static void main(String[] args) {
		Printable prn = new Printable() { // 익명 클래스
			public void print(Stirng s) {
				System.out.println(s);
			} 
		};

		prn.print("What is Lamda?");
	}
}
```

</br>

### 람다 예제 01

```java
interface Printable {
	void print(String s);
}

class Lamda01 {
	public static void main(String[] args) {
		Printable prn = new Printable() { // 익명 클래스
			public void print(Stirng s) {
				System.out.println(s);
			} 
		};

		prn.print("What is Lamda?");
	}
}

-------------------------> **변경 후**

interface Printable {
	void print(String s);
}

class Lamda01 {
	public static void main(String[] args) {
		Printable prn = (s) -> { System.out.println(s); } // Lamda -> 화살표 함수 똑같음.
		prn.print("What is Lambda?");
	}
}
```

- **Printable prn = (s) → { System.out.println(s); }**
    - (s) : 컴파일러는 (s)를 → 매개변수 s가 들어오는 것으로 판단을 한다.
    - → { System.out.println(); } → 몸체를 넣어주는 영역으로 판단을 한다.

> 위에서도 말했지만 Javascript ES6 문법 화살표 함수와 똑같다.

</br>

```jsx
Javascript ES6 문법 내 포함되어 있는 화살표 함수

</br>

// 일반 함수
var foo = function () { console.log("foo") }; // foo

// 화살표 함수
var bar = () => console.log("bar"); // bar
```
