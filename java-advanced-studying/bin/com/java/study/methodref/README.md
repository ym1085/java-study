### Goal

메소드 참조의 종류와, 사용법에 대해 알아보자.

### 메소드 참조와 Optional

메소드 참조의 종류

- `static 메소드`의 참조
- `참조변수`를 통한 `인스턴스 메소드` 참조
- `클래스 이름`을 통한 `인스턴스 메소드` 참조
- `생성자` 참조

메소드 참조는 기본적으로 **람다식보다 조금 더 코드를 간결**하게 할 수 있다는 장점이 있다.

일부 람다식을 메소드 참조로 대신하게 할 수 있다.

### 01 static 메소드의 참조 : 람다식 기반의 예제

```java
// Collections 클래스의 reverse 메소드 기반 예제
public static void reverse(List<?> list) // 저장 순서를 뒤집는다.

class ArrangeList {
	public static void main(String[] args) {
		List<Integer> ls = Arrays.asList(1, 3, 5, 7, 9);
		ls = new ArrayList(ls);
		
		Consumer<List<Integer>> c = l -> Collections.reverse(l); // 저장 결과를 뒤집는 메소드
		c.accept(ls); // 순서 뒤집기 진행
		System.out.println(ls); // 출력	
	}	
}
```

**복습 차원에서 한번 더**

- Consumer<T>의 메소드 : void accept(T t);
    - void accept(T t) 메소드는 프로그래머에 따라 메소드의 기능이 달라진다.

### **01-1 위 예제에서 Consumer<T> 인터페이스를 구현하기 위해 람다식이 정의 되어야 한다.**

```java
Consumer<List<Integer>> c = l -> {
	// Consumer<T> 인터페이스를 구현하기 위한 람다식 내용 or 몸체 자리. 
}
```

- 기본적으로 함수형 인터페이스를 사용할 때 위 같이 로직을 짜는 것이 일반적이다.

### **01-2 하지만....**

```java
Consumer<List<Integer>> c = l -> Collections.reverse(l); // 람다식이 아닌 웬 메소드....??
```

- 람다식이 와야 하는 자리에 reverse() 호출문만 덩그러니 구현해 두었다..

### 01-3 **한번 정리를 해보자**

```java
Consumer<List<Integer>> c = l -> Collections.reverse(l); // 인자가 무엇인지 잘 체크 해보자.
c.accept(ls);

1. accept Method에 List ls 값이 전달 된다.
2. accept Method에서 ls 값을 통해 로직을 구현.
3. Collections.reverse("인자"); 가 실행이 된다.
핵심 : 이때 위에서 전달한 l이 reverse()의 인자로 전달이 된다.
```

- 대입 연산자 오른편에 **메소드의 정보만 오면** 인자로 전달되는 인자를 그대로 전달 하면서 메소드 호출.
- 메소드 참조는 Java 진영에서의 약속이다.

### 01-4 메소드 참조 기반으로 수정

```java
before : Consumer<List<Integer>> c = l -> Collections.reverse(l);

after : Consumer<List<Integer>> c = Collections::reverse; // 변경된 코드
```

- accept 메소드 호출 시 전달되는 인자를 reverse 메소드를 호출하면서 그래도 전달한다는 약속에 근거한 수정.

### 순서

- void accept(T t)
- void reverse(List<?> list)
- accept로 전달된 것 reverse로 그대로 또 전달이 된다.

### 01-5 솔직히 왜 써야하지?

- 자바 진영에서 결정한 약속에 대한 지식만 있으면, 람다식에 비해 더욱 더 간결한 코드 작성이 가능하기 때문이다.

### 02 인스턴스 메소드 참조1 : effectively final

```java
class JustSort {
	public void sort(List<?> lst) { // 인스턴스 메소드
		Collections.reverse(lst);
	}
}

class ArrangeList3 {
	public static void main(String[] args) {
		List<Integer> ls = Arrays.asList(1, 3, 5, 7, 9);
		ls = new ArrayList<>(ls);
		JustSort js = new JustSort(); // js는 effectively final
		
		Consumer<List<Integer>> c = e -> js.sort(e); // 람다식 기반
		c.accept(ls);
		System.out.println(ls);
	}
}
```

- 인스턴스 메소드 참조를 기반으로 한 로직 정의.

### 02-1 다시 한번 순서를 정리 해보자.

- List<Integer> ls = Arrays.asList(1, 3, 5, 7, 9)
    - List형 ls 변수에 1, 3, 5, 7, 9라는 값을 담는다.
- ls = new ArrayList<>(ls);
    - ls를 ArrayList<>()형으로 사용하기 위해 new ArrayList<>(**ls**) 선언
- 핵심→ Consumer<List<Integer>> c = e → js.sort(e);
    - accept 메소드가 호출 되면서 인자 '**e**'를 전달.
    - accept 내에서의 로직 처리가 끝난 후, js.sort 메소드 호출.
    - 이때!! 전달된 '**e**'를 인자로 다시 한번 전달 해준다.
        - 이것은 위에서 말했다시피 Java 진영에서의 **약속**.

### 02-2 인스턴스 메소드 참조1: 변경된 코드

```java
Consumer<List<Integer>> c = js::sort; // 왼쪽 같이 변경이 될 수 있다.
```

- 위 코드를 해석 하자면, js 참조 변수가 참조하는 인스턴스의 sort 메소드를 의미 한다.
- accept 메소드 호출 후 sort 메소드를 호출하는데, 매개 변수 인자를 그대로 흘려 보내는 것을 메소드 참조라 한다.

### 02-2 문제점

```java
class JustSort {
	public void sort(List<?> lst) { // 인스턴스 메소드
		Collections.reverse(lst);
	}
}

class ArrangeList3 {
	public static void main(String[] args) {
		List<Integer> ls = Arrays.asList(1, 3, 5, 7, 9);
		ls = new ArrayList<>(ls);
		JustSort js = new JustSort(); // js는 effectively final
		
		Consumer<List<Integer>> c = e -> js.sort(e); // 람다식 기반
		c.accept(ls);
		System.out.println(ls);
	}
}
```

- Consumer<List<Integer>> c = e → js.sort(e);
    - 위 코드에서 **js**는 참조 변수 이며, sort 메소드 호출 시 특정 메모리의 인스턴스의 메소드를 호출한다.
    - 즉, JustSort js = new JustSort(); 는 **main()** 안에서 유효한 참조 변수이다.
        - 음.. 지역을 벗어나 다른 지역에 존재하는 sort()를 호출한 것이 문제라는 것.

### 02-3 effectively final

```java
JustSort js = new JustSort(); // js는 effectively final
```

**effectively final**

- **한번 참조하는 대상을** **변경하지 않는 경우를** **effectively final**이라 정의 할 수 있다.
- 람다식 기반으로 메소드 참조를 구현 할 시 effectively final인 경우에만 구현이 가능하다.

### 02-4 effectively final2

```java
class ArrangeList3 {
	public static void main(String[] args) {
		List<Integer> ls = Arrays.asList(1, 3, 5, 7, 9);
		ls = new ArrayList<>(ls);
		JustSort js = new JustSort(); // js는 effectively final
		
		Consumer<List<Integer>> c = e -> js.sort(e); // 람다식 기반
		c.accept(ls);
		System.out.println(ls);

		js = null; // js에 null을 삽입
	}
}
```

**위 같이 프로그램이 종료되는 시점에 null을 넣는 경우 effectively final이라 할 수 있을까?**

> 강의에서는 컴파일이 되지 않는다 했지만, 필자의 IDE에서는 컴파일이 가능했다..

- 정답은 : 컴파일이 되지 않습니다.
- **effectively final**의 **조건**을 만족시키려면 프로그램이 종료될때까지 참조하는 인스턴스가 변경되면 안된다.

### 02-5 인스턴스 메소드 참조1 : forEach 메소드

```java
class ForEachDemo {
	public static void main(String[] args) {
		List<String> ls = Arrays.asList("Box", "Robot");
		ls.forEach(s -> System.out.println(s)); // 람다식 기반
		ls.forEach(System.out::println); // 메소드 참조 기반 
	}
}

default void forEach(Consumer<? super T> action) {
	for (T t : this)    // this는 이 메소드가 속한 컬렉션 인스턴스를 의미함
		action.accept(t); // 모든 저장된 데이터들에 대한 이 문장 반복
}
```

- this : 이 인스턴스, 현재 Collection 인스턴스(List)가 가지고 있는 값을 의미한다.
- forEach(Consumer<? super T>)가 핵심이다.

### 02-6 예제

```java
List<String> lst = Arrays.asList("Box", "Robot", "Pen");
lst.forEach(data -> System.out.println(data));

// lst 컬렉션에 존재하는 data를 하나씩 꺼내 출력하는 예제.
```

### 02-7 인스턴스 메소드 참조2

**이번에 나오는 메소드 참조는 위에서 설명한 약속과 다른 부분이 있다 말하고 있다.**

```java
class IBox {
	private int n;
	public IBox(int i) { n = i; }
	public int larger(IBox b) {
		if(n > b.n)
			return n;
		else
			return b.n;
	}
}

public static void main(String[] args) {
	IBox ib1 = new IBox(5);
	IBox ib2 = new IBox(7);
	
	// 두 상자에 저장된 값 비교하여 더 큰 값 반환
	ToIntBiFunction<IBox, IBox> bf = (b1, b2) -> b1.larger(b2);
	// ToIntBiFunction<IBox, IBox> bf = IBox::larger; // 약속에 근거한 줄인 표현
	int bigNum = bf.applyAsInt(ib1, ib2);
	System.out.println(bigNum);
}
// ToIntBiFunction<T, U> int applyAsInt(T t, U u)
```

- **첫번째 인자로 전달된 인스턴스의 메소드를 호출한다.**
- **두번째 인자로 전달된 인스턴스를 첫번째 인스턴스의 메소드의 매개변수 인자로 전달 한다.**

### 02-7 정리

```java
// 약속에 근거한 줄인 표현
ToIntBiFunction<IBox, IBox> bf = IBox::larger;
```

**체크해야 하는 부분**

 IBox.larger → 이런 형식으로 메소드를 호출 하려면 해당 메소드가 static Method로 선언이 되어야 한다.

하지만 현재 static Method가 아닌 인스턴스 메소드로 선언이 되있는 상태.

 **약속**?? → 위와 같이 선언이 되었을때는 첫번째 인자로 들어오는 인스턴스의 메소드를 호출하고, 두번째 인자를 

첫번째 메소드의 매개변수 인자로 전달하겠다는 약속이 내포되어 있다.

> 람다식을 메소드 참조로 변경, 메소드 참조를 람다식으로 변경

### 03 생성자 참조

```java
class StringMaker {
	public static void main(String[] args) {
		Function<char[], String> f = ar -> {
			// char[]을 받아 String으로 반환 한다.
			return new String(ar);	
		};
		
		char[] src = {'R', 'o', 'b', 'o', 't'};
		String str = f.apply(src);
		System.out.println(str);
	}
}
// Function<T , R>         R apply(T t); 
```

- 람다식 작성 시 인스턴스 생성 후 이의 참조 값을 반환하는 경우가 있다. 이 경우 메소드 참조 방식을 쓸 수 있다.

### 03-1 생성자 참조 상황

```java
// 예제 01
R apply(T t) {
	// t -> char[] 
	return new R(t); 
}

// 결론 01
before : Function<char[], String> f = ar -> new String(ar);

after : Function<char[], String> f = String::new; // 이런 형태로 사용이 가능
```

- 위 같은 상황에서 생성자 참조를 사용 할 수 있다.
- String 인스턴스를 생성 하겠다는 거구나??
- 첫번째 인자 char[]을 new String("**인자**")로 전달 하겠다는 거구나??
