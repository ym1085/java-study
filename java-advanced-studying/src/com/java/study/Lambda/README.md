### Goal
람다 표현식에 대해 다시 한번 정리 해보자.

### 람다와 함수형 인터페이스

- **추상 메서드**가 **딱 하나**만 존재하는 인터페이스를 **함수형 인터페이스**라 한다.
- 인터페이스 내에 추상 메서드가 한 개 이상일 시 함수형 인터페이스라 할 수 없다.

</br>

### 인스턴스보다 기능 하나가 필요한 상황을 위한 람다

```java
class SLenComp implements Comparator<String> {
	@Override
	public int compare(String s1, String s2) {
		return s1.length() - s2.length();
	}
}

class SLenComparator {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("Robot");
		list.add("Lambda");
		list.add("Box");

		Collections.sort(list, new SLenComp()); // 첫번째 매개변수는 정렬할 자료 구조, 두번째는 정렬 기준이다.
		
		for(String s : list) 
			System.out.println(s);
	}
}
```

- 메소드 하나가 필요한 상황이라 생각하는게 쉽다.
- **위 상황에서 SLenComp안에 있는 compare 메서드를 사용하기 위해, 인스턴스를 생성 해야만 한다.**
    - 즉, 위에서 말했다시피 메서드(기능) 하나를 호출 하기 위해 인스턴스를 생성하는 것은 낭비라 할 수 있다.

</br>

### 매개변수가 있고 반환하지 않는 람다식

```java
interface Printable {
	void print(String s); // 매개변수 하나, 반환형 void
}

class OneParamNoReturn {
	public static void main(String[] args) {
		Printable p;
		p = (String s) -> { System.out.println(s); } // 줄임 없는 표현
		p.print("Lambda exp one.");

		p = (String s) -> System.out.println(s); // 중괄호 생략
		p.print("Lambda exp two.");

		p = (s) -> System.out.println(s); // 매개변수 형 생략
		p.print("Lambda exp three.");

		p = s -> System.out.println(s); // 매개변수 소괄호 생략
		p.print("Lambda exp four.");
	}
}
```

**중괄호, 소괄호 생략이 불가능한 상황**

- 메소드 몸체가 둘 이상의 문장.
- 매개 변수의 수가 둘 이상인 경우.
- 람다식은 코드를 최대한 줄여서 사용하는걸 추천한다.

</br>

### 매개변수가 둘 인 람다식

```java
interface Calculate {
	void cal(int a, int b);
}

class TwoParamNoReturn {
	public static void main(String[] args) {
		Calculate c; // 인터페이스
		c = (a, b) -> System.out.println(a + b);
		c.cal(4, 5);

		c = (a, b) -> System.out.println(a - b);
		c.cal(4, 3);

		c = (a, b) -> System.out.println(a * b);
		c.cal(4, 3);
	}
}
```

</br>

### 매개변수가 있고 반환하는 람다식1

```java
interface Calculate {
	int cal(int a, int b);
}

class TwoParamAndReturn {
	public static vodi main(String[] args) {
		Calculate c;
		c = (a, b) -> { return a + b; }; // return문의 중괄호는 생략 불가!
		System.out.println(c.cal(4, 3));

		c = (a, b) -> a + b; // **연산 결과가 남으면, 별도로 명시하지 않아도 반환 대상이 됨!**
		System.out.println(c.cal(4, 3));
	}
}
```

- 위에서 말했다시피 연산 결과가 남으면, 자동으로 값을 return 하게 된다.
- **또한 return문은 { } 중괄호 생략이 불가능하다.**

</br>

### 매개변수가 있고 반환하는 람다식2

```java
interface HowLong {
	int len(String s); // 값을 반환하는 메소드
}

class OneParamAndReturn {
	public static void main(String[] args) {
		HowLong h1 = s -> s.length();
		System.out.println(h1.len("I am so happy"));
	}
}
```

</br>

### 매개변수가 없는 람다식

```java
interface Generator [
	int rand(); // 매개변수가 없는 메소드
}

class NoParamAndReturn {
	public static void main(String[] args) {
		Generator gen = () -> {
			Random rand = new Random();
			return rand.nextInt(50);
		};
		
		System.out.println(gen.rand());
	}
}
```

</br>

### 함수형 인터페이스(Functional Interfaces)와 어노테이션

![](https://images.velog.io/images/ym1085/post/afb82669-583f-469e-b11c-d4f57b057d6d/_2020-11-03__8.58.31.png)

**@FunctionalInterface**

- **함수형 인터페이스의 조건을 갖추었는지에 대한 검사를 컴파일러에게 요청!**

### Example 01

```java
@FunctionalInterface
interface Calculate {
	int cal(int a, int b);
	default int add(int a, int b) { return a + b; }
	static int sub(int a, int b) { return a - b; }
}
```

- default 메소드, static 메소드는 반드시 구현하지 않아도 된다.
- 위 예제에서 추상 메서드는 int cal(int a, int b)다.
    - 추상 메서드는 반드시 구현하여야 한다.

> 추상 메서드란 구현부는 작성하지 않고 선언부만 작성해둔 메소드를 의미한다.

### 람다식과 제네릭

```java
@FunctionalInterface
interface Calculate <T> {
	T cal(T a, T b)
}

class LambdaGeneric {
	Calculate<Integer> ci = (a, b) -> a + b;
	System.out.println(ci.cal(4, 3));

	Calculate<Double> cd = (a, b) -> a + b;
	System.out.println(ci.cal(4.32, 3.45));
}
```

- 인터페이스 역시 제네릭 기반으로 작성이 될 수 있다.
- 인터페이스형 **참조 변수**를 선언할 때 **제네릭 타입**을 정해주는 것만 신경 쓰면 된다.
- 매개 변수로 들어오는 a, b는 Integer, Double형으로 구성이 된다.
- 즉, 타입 인자만 결정하면 끝이다!!

### 정의되어 있는 함수형 인터페이스

```java
default boolean removeIf(Predicate<? super E> filter)
 -> Collection< E > // 인터페이스에 정의되어 있는 디폴트 메소드

Predicate // 인터페이스의 추상 메소드는 다음과 같이 정의해 두었다.
	boolean test(T t);

// 미리 정의해 두었으므로 Predicate라는 이름만으로 통한다!

@FunctionalInterface
public interface Predicate< T > {
	boolean test(T t);
}
```

- 여기서 Predicate<? super E>는 인터페이스다??
    - 자바 진영에서는 이러한 함수형 인터페이스를 많이 제공 해준다.

### 대표적인 함수형 인터페이스

**Predicate< T >** 

- boolean test(T t) : 전달 인자를 근거로 참 또는 거짓을 반환.
- Predicate형 참조 변수를 선언 하였을 때 어떻게 대처할지 생각을 해보면 좋을 듯 하다.

### Example 01

```java
// 아래 코드는 실제로 작성 해보면 좋을 듯 하다.
public static int sum(Predicate<Integer> p, List<Integer> lst) { // 매개변수를 유심히 보자~~!!
	int s = 0;
	for(int n : lst) {
		if(p.test(n)) {
			s += n;
		}
	}
	return s;
}

public static void main(String[] args) {
	List<Integer> list = Arrays.asList(1, 5, 7, 9, 11, 12);
	int s;

	s = sum(n -> n % 2 == 0, list);      // test() 메소드의 몸체가 되는 부분이다.
	System.out.println("짝수 합 : " + s);

	s = sum(n -> n % 2 != 0, list);
	System.out.println("홀수 합 : " + s);
}
```

- 첫번째 인자로 Predicate<Integer> 형식 매개 변수를 받는다.
    - 즉, sum() 메소드를 호출 하는 사람이 필요에 맞게 조건을 결정하게 되는 코드.
    - 또한, 람다식을 매개 변수로 전달 할 수 있다는 것이 중요하다.
    - Predicate p = sum(n → n % 2 == 0) 위 같은 코드가 가능하기에, 매개 변수로 받을 수 있음.

### Predicate<T>를 구체화하고 다양화 한 인터페이스들.

autoBoxing, autoUnboxing 과정을 없애기 위해 아래 인터페이스를 만들었다.

**IntPredicate**

- boolean test(int value)
- **int**형 인자를 매개 변수로 받는다.

**LongPredicate**

- boolean test(long value)
- **long**형 인자를 매개 변수로 받는다.

**DoublePredicate**

- boolean test(double value)
- **double**형 인자를 매개 변수로 받는다.

**BiPredicate<T, U>**

- boolean test(T t, U u)
- 기본적으로 Predicate<T> 인터페이스의 메소드는 인자를 하나만 받지만, 위 메소드를 통해 **두 가지를 받을 수 있다**.
- Bi가 붙으면 두 가지 정보를 받을 수 있다.

**Supplier< T >** 

- T get() : 메소드 호출 시 무엇인가를 제공함.
- **기부자**라 생각을 하자, 받는 것 없이 주기만 한다.

### Example 02

```java
public static List<Integer> makeIntList(Supplier<Integer> s, int n) {
	List<Integer> list = new ArrayList<>();
	for(int i = 0; i < n; i++) {
		list.add(s.get()); // 난수를 생성해 담는다.
	}
	return list;
}

public static void main(String[] args) {
	Supplier<Integer> spr = () -> {
		Random rand = new Random();
		return rand.nextInt(50);
	}

	List<Integer> list = makeIntList(spr, 5);
	System.out.println(list);

	list = makeIntList(spr, 10);
	System.out.println(list);
}
```

### Supplier<T>를 구체화하고 다양화 한 인터페이스들.

autoBoxing, autoUnboxing 과정을 없애기 위해 아래 인터페이스를 만들었다.

**IntSupplier**

- int getAsInt()

**LongSupplier**

- long getAsLong()

**DoubleSupplier**

- double getAsDouble()

**BooleanSupplier**

- boolean getAsBoolean()

**Consumer< T >**

- void accept(T t) : 소비만 한다.
- 반환값이 없고 전달 인자만 있는 함수형 인터페이스를 구현 하여야 한다.

## Example03

```java
class ConsumerDemo {
	public static void main(String[] args) [
		Consumer<String> c = s -> System.out.println(s);
		c.accept("Pineapple");    // 출력이라는 결과를 보임
		c.accept("Strawberry");
	}
}
```

### Consumer<T>를 구체화하고 다양화 한 인터페이스들

autoBoxing, autoUnboxing 과정을 없애기 위해 아래 인터페이스를 만들었다.

**IntConsumer**

- void accept(int value)

Obj**IntConsumer<T>**

- void accept(T t, int value)

**LongConsumer**

- void accept(long value)

Obj**LongConsumer**

- void accept(T t, long value)

**DoubleConsumer**

- void accept(double value)

Obj**DoubleConsumer**

- void accept(T t, double value)

**BiConsumer**<T, U>

- void accept(T t, U u)

**Function< T, R >** 

- R apply(T t) : 입출력 출력이 있음(수학적으로는 함수).
- 쉽게 말하자면 T형 인자를 받아서, R형 값을 반환 해준다 생각하자.

### Example 04

```java
class FunctionDemo {
	public static void main(String[] args) {
		Function<String, Integer> f = s -> s.length();
		System.out.println(f.apply("Robot"));
		System.out.println(f.apply("System"));
	}
}
```

### Function<T, R>을 구체화하고 다양화 한 인터페이스들

autoBoxing, autoUnboxing 과정을 없애기 위해 아래 인터페이스를 만들었다.

> 외우려고 하지말고 찾아서 쓰면 된다.

**IntToDoubleFunction**

- double applyAsDouble(int value)
- **int**형 인자를 받아서 **double**형으로 반환 해준다.

**DoubleToIntFunction**

- int applyAsInt(double value)
- **double**형 인자를 받아서 **int**형으로 반환 해준다.

**IntUnaryOperator**

- int applyAsInt(int operand)
- **int**로 받아서 **int**로 반환한다 생각 하면 된다. → **Unary**

**DoubleUnaryOperator**

- double applyAsDouble(double operand)
- **Double**로 받아서 **Double**로 반환한다 생각 하면 된다. → **Unary**

**BiFunction(T, U,  R)**

- R apply(**T** t, **U** u)
- **T, U**를 인자로 받아서 **R**을 반환 해준다.

**IntFunction<R>**

- R apply(int value)
- **int**로 받아서 **R**을 받환 해준다.

**DoubleFunction<R>**

- R apply(double value)
- **double**로 받아서 **R**로 반환 해준다.

**ToIntFunction<T>**

- int applyAsInt(T value)

**ToDoubleFunction<T>**

- double applyAsDouble(T value)

**ToIntBiFunction<T, U>**

- int applyAsInt(T t, U u)

**ToDoubleBiFunction<T, U>**

- double applyAsDouble(T t, U u)

### removeIf 메소드

- **Collection<E> 인터페이스의 디폴트 메소드**
    - default boolean removeIf(Predicate<? super E> filter) { ... }
- **ArrayList<**Integer**> 인스턴스의 removeIf**
    - public boolean removeIf(Predicate<? super Integer> filter)
- **removeIf 메소드의 기능**
    - "Removes all of the elements of this collection that satisfy the given predicate"
- 컬렉션 인터페이스를 구현하는 **컬렉션 인스턴스**를 대상으로 **removeIf()를 호출 할 수 있다는 것이 핵심**.

### Example 05

```java
public static void main(String[] args) {
	List<Integer> ls1 = Arrays.asList(1, -2, 3, -4, 5);
	ls1 = new ArrayLsit<>(ls1);

	List<Double> ls2 = Arrays.asList(-1.1, 2.2, 3.3, -4.4, 5.5);
	ls2 = new ArrayList<>(ls2);

	Predicate<Number> p = n -> n.doubleValue() < 0.0; // 삭제의 조건 (이 라인이 핵심)
	ls1.removeIf(p);
	ls2.removeIf(p);

	System.out.println(ls1);
	System.out.println(ls2);
}
```

- 저장된 컬렉션 인스턴스를 대상으로 조건에 부합하면 해당 인스턴스를 삭제 하겠다는 의미가 내포 되 있다.
