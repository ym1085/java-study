### Goal

이번 Chapter에서는 스트림에 대해 정리 해보자.

### 01 스트림의 이해와 스트림의 생성

**스트림을 사용하는 이유와 배경**

 `자바 8`에서 `추가`한 `스트림`(*Streams*)은 `람다`를 활용할 수 있는 기술 중 하나입니다. 자바 8 이전에는 `배열` 또는 `컬렉션 인스턴스`를 다루는 방법은 `for` 또는 `foreach` 문을 돌면서 요소 하나씩을 꺼내서 다루는 방법이었습니다. 간단한 경우라면 상관없지만 로직이 복잡해질수록 코드의 양이 많아져 여러 로직이 섞이게 되고, 메소드를 나눌 경우 루프를 여러 번 도는 경우가 발생합니다. 

 `스트림`은 `'데이터의 흐름'`입니다. 배열 또는 컬렉션 인스턴스에 함수 여러 개를 조합해서 원하는 결과를 필터링하고 가공된 결과를 얻을 수 있습니다. 또한 람다를 이용해서 코드의 양을 줄이고 간결하게 표현할 수 있습니다. 즉, 배열과 컬렉션을 함수형으로 처리할 수 있습니다.

### 01-1 스트림 사용 배경

```java
// 컬렉션 인스턴스에 아래와 같은 데이터가 저장되 있는 상태.
-------------
| 1, 3, 5   |
| 6, 7, 8   |
-------------

List<String> list = new ArrayList(Arrays.asList("1", "3", "5"...));
```

- `컬렉션 인스턴스`에 저장된 데이터 중에서, `홀수 데이터의 합`을 계산 해보고 싶다.
- 위 같은 상황에서 어떻게 데이터를 꺼내 합을 구해야 하지??
    - for문을 돌린다.
    - 메소드를 만든다.
    - `스트림을 사용 한다.`

### 01-1 보편적인 데이터 추출

```java
// 데이터를 일일이 하나씩 꺼내서, 해당 홀수 값을 더해 총 합을 구한다.
// 컬렉션 인스턴스에 저장된 데이터 중에서 홀수들의 합을 구한다.
List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 7, 9));

for(int i = 0; i < list.size(); i++) {
	if(list.get(i) % 2 == 1)
		System.out.println("list : "  + list.get(i)); // 홀수 값 추출
}
```

- 위와 같은 과정에서 이상한 부분을 찾아볼 수 있겠는가??

**위 코드는 우리가 흔히 쓰는 코드이며, 이상한 부분은 없다. 하지만 아래 내용에 대해 생각 해보자.**

- `성능` 면에서 위 같은 코드가 우수한가??
- 다른 방법으로 컬렉션 인스턴스에 존재하는 데이터를 추출 할 수 없을까??
- 보통 필자와 같은 병아리 개발자라면 위 내용에 대해 생각 해보지는 않았을 것이다.

### **그렇다면 어떤 작업을 했는지 다시 한번 아래 그림을 보고 생각 해보자.**

### 01-2 스트림 사용 배경

```java
// 아래 작업을 하나의 파이프라 생각한 그림.
// 아래와 같은 파이프가 존재하는 상태.
// 1번째 파이프 (작업1 파이프)
--------------------------
-->	|  --> 1, 5, 7 // 홀수만 걸러서 추출.
--------------------------

// 2번째 파이프 (작업2 파이프)
--------------------------
-->	●  --> 1, 5, 7 = 13 // 위에서 걸러진 데이터의 합을 구한다.
--------------------------

List<String> list = new ArrayList<>(Arrays.asList("1", "3", "5"...));
```

1. 컬렉션 인스턴스에 존재하는 `홀수 값` 을 추출하기 위해 한번의 작업(`작업1`)이 필요하다.
2. 후에 걸러진 `홀수 값` 들을 더하기 위해 한번의 작업(`작업2`)이 더 필요하다.

### 01-3 여기서 우리가 할 일

- 컬렉션 인스턴스에 존재하는 데이터를 나란히(1→ 7) 정렬.
- 후에 파이프에 해당 데이터를 통과 시킨다.
    - 위 그림에서 `파이프` 는 실제로 `메서드` 를 의미한다.
    - `스트림은 인스턴스라 봐도 무방하다.`

### 01-4 스트림(Stream)의 이해

```java
// before
public static void main(String[] args) {
	int [] ar = {1, 2, 3, 4, 5};
	IntStream stm1 = Arrays.stream(ar); // 배열 ar로부터 스트림 생성
	IntStream stm2 = stm1.filter(n -> n % 2 == 1); // 중간 연산 진행
	int sum = stm2.sum(); // 최종 연산 진행
	System.out.println(sum);
}

// after
public static void main(String[] args) {
	int [] ar = {1, 2, 3, 4, 5};

	// 스트림 역시 인스턴스이기에 아래와 같이 연속으로 호출이 가능하다.
	int sum = Arrays.stream(ar)
			.fiter(n -> n % 2 == 1)
			.sum(); // sum 통과 결과 반환.

	System.out.println(sum);
}
```

- 01-2 그림에서 말한 것처럼 스트림을 생성하고 파이프에 해당 데이터를 통과시키는 예제.
    - `stream` : 스트림 생성 (배열을 대상으로 스트림이 생성된다.)
    - `filter` : 중간 연산 (생성된 스트림에 파이프가 연결된다.)
    - `sum` : 최종 연산

### 출력 결과

```java
9
```

### 02 본서에서 스트림을 설명하는 방향

**스트림 관련 전체 내용의 구분**

- `스트림의 생성 방법`
- `중간 연산의 종류와 내용`
- `최종 연산의 종류와 내용`

### 02-1 스트림 생성하기 : 배열

```java
public static void main(String[] args) {
	String [] name = {"YOON", "LEE", "PARK"};
	Arrays.stream(name).forEach(e -> System.out.println(e));
}
```

- `중간 연산`은 `생략`이 되어도 상관이 없지만, `최종 연산`은 `생략`이 될 수 없다.
- 위 코드에서 forEach() 메소드는 최종 연산에 해당 된다.
- 배열을 대상으로 스트림을 생성할 시 → `Arrays.stream()` 을 선언하면 된다.

### 02-2 스트림 생성하기 : 컬렉션 인스턴스

```java
// 컬렉션 인스턴스를 대상으로 스트림 생성 시 호출하는 메소드
default Stream<E> stream()
	// java.util.Collection<E>의 디폴트 메소드

public static void main(String[] args) {
	List<String> list = Arrays.asList("Toy", "Robot", "Box");
	list.stream().forEach(s -> System.out.println(s));
}
```

- Collection<E> 인터페이스의 디폴트 메소드로 선언이 되 있다는점이 중요하기에 정리.

### 복습 및 결론

- Java 8 이후에 나온 Stream은 기존에 사용해왔던 for문, for ~ each문을 대체 할 수 있는 기술이다.
- 또한 람다식을 활용하여 코드가 더욱 더 간결해지는 장점을 가지고 있다.
- 하지만 스트림을 사용하는것이 무조건 옳다 볼수는 없는 것 같다. 애플리케이션의 환경을 고려하여,
상황에 맞게 적적하게 사용하는것이 좋을 것 같다.

### 03 필터링과 맵핑(`중간 연산자`)

### 03-1 필터링

```java
Stream<T> filter(Predicate<? super T> predicate) // Stream<T>에 존재
// Predicate<T> boolean test(T t)

public static void main(String[] args) {
	int [] ar = {1, 2, 3, 4, 5};
	Arrays.stream(ar)
		.filter(n -> n % 2 == 1) // 홀수만 통과시킨다.
		.forEach(n -> System.out.println(n + "\t"));
	System.out.println();

	List<String> s1 = Arrays.asList("Toy", "Robot", "Box");
		s1.stream() // 컬렉션 인스턴스 기반 스트림 생성
		.filter(s -> s.length() == 3) // 길이가 3이면 통과시킨다.
		.forEach(s -> System.out.println(s + "\t"));
	System.out.println();
}
```

- 스트림을 구성하는 데이터 중 일부를 조건에 따라 걸러내는 연산.
- `filter() 메서드`를 통해 구현.
- `필터링`을 한다 생각하면 편하다.
- `뜰채`를 사용하여 내가 원하는 데이터만 추출.

### 03-1 출력 결과

```java
1   3   5
Toy  Box
```

### 03-2 **맵핑**

- `map`은 요소들을 특정 조건에 해당하는 값으로 변환해 줍니다.
- 요소들을 대, 소문자 변형 등의 작업을 하고 싶을때 사용 가능 합니다.
- 즉, `각각의 아이템`을 `변경`하여 `새로운 컨텐츠`를 `생성`하는 기능을 의미 한다.

```java
// 아래와 같은 컬렉션 인스턴스 존재
---------------
|  Box, Robot |    --->  3, 5, 6
|   ,Simple   |
---------------

// 예제 01
public static void main(String[] args) {
	List<String> ls = Arrays.asList("Box", "Robot", "Simple");
	ls.stream()
		.map(s -> s.length())
		.forEach(n -> System.out.println(n + "\t"));
	System.out.println();
}

// map 메서드 형태 
<R> Stream<R> map(Function<? super T, ? extends R> mapper)
// Function<T, R>   R apply(T t
```

 

### 03-2 출력 결과

```java
3   5   6 
```

### 03-3 맵핑: map의 친구들

아래의 메서드를 통해 불필요한 Boxing, Unboxing을 피할수 있다.

- IntStream `mapToInt`(ToIntFunction<? super T> mapper)
- LongStream `mapToLong`(ToLongFunction<? super T> mapper)
- DoubleStream `mapToDouble`(ToDoubleFunction<? super T> mapper)

```java
public static void main(String[] args) {
	List<String> ls = Arrays.asList("Box", "Robot" ,"Simple");

	ls.stream()
		.mapToInt(s -> s.length())
		.forEach(n -> System.out.println(n + "\t"));
	System.out.println();
}
```

### 03-3 출력 결과

```java
3   5   6 
```

### 03-4 맵핑: 필터링 후 맵핑의 예

```java
class ToyPriceInfo {
	private String model;
	private int price;

	public ToyPriceInfo(String m, int p) {
		this.model = m;
		this.price = p;
	}

	public int getPrice() {
		return price;
	}
}

public static void main(String[] args) {
	List<ToyPriceInfo> ls = new Arraylist<>();
	ls.add(new ToyPriceInfo("GUN_LR_45", 200));
	ls.add(new ToyPriceInfo("TEDDY_BEAR", 350));
	ls.add(new ToyPriceInfo("CAR_TRANSFORM_VER_7719", 550));
	
	int sum = ls.stream()
		.filter(p -> p.getPrice() < 500)
		.mapToInt(t -> t.getPrice()) // 맵핑 : 솔직히 맵핑은 왜 하는지 정리가 아직 안됬음.
		.sum(); // 최종 연산

	System.out.println("sum : " + sum);
}
```

### 04 리덕션
![](https://images.velog.io/images/ym1085/post/f8a02274-a7c1-4b51-ae7a-6185801f2fd6/_2020-11-23__8.02.31.png)

- 리덕션은 데이터를 축소하는 연산이다. (별로 와 닿는 느낌이 없음)
- `데이터`를 `변환하지 않고`, `더하거나 빼는` 등의 `연산`을 `수행`하여 하나의 값을 만들 때 `reduce 메서드`가 사용이 된다.

### 04-1 예시

```java
// 리덕션 파이프(기능 : sum)
------------------
         ●            <-    1, 2, 3
------------------

// reduce 메서드
T reduce(T identify, BinaryOperator<T> accumulator) // Stream<T>에 존재
// Binaryoperator<T>  T apply(T t1, T t2)

// 리덕션 예제
public static void main(String[] args) {
	List<String> ls 
		= Arrays.asList("Box", "Simple", "Complex", "Robot");
	
	// BinaryOperator 메서드
	BinaryOperator<String> lc = (s1, s2) -> {
		if(s1.length() > s2.length()) {
			return s1;
		} else {
			return s2;
		}
	};

	String str = ls.stream()
			.reduce("", lc); // 스트림 빈 경우 "" 반환
	
	System.out.println(str);
}
```

- `reduce()`의 `첫번째 매개변수 인자로는` stream 데이터가 null일 경우 지정할 데이터를 지정 한다.
- .reduce("") → "" 역시 데이터로 바라본다.
- .reduce("1234567") → 1234567도 스트림의 0번째로 붙는다는 사실을 인지하고 해야 한다.

    ```java
    // 아래와 같이 스트림에 존재하는 데이터와 비교를 하게 된다.
    // if(s1.length() > s2.length()) return 와 같은 상황에서.
    ----------------------------------------
    | 1234567, Box, Simple, Complex, Robot |
    ----------------------------------------
    ```

### 04-2 예시

```java
Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
Optional<Integer> sum = numbers.reduce((x, y) -> x + y);
sum.ifPresent(s -> System.out.println("sum : " + sum));
```

### 05 병렬 스트림

**병렬 처리**

```java
// 병렬 처리 예제
|----------------------------|
 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
```

- 하나의 작업을 둘 이상의 코어가 처리하도록 하는 것을 병렬 처리라 한다.
- 1 ~ 3 까지는 첫번째 코어, 4 ~ 6 까지는 두번째 코어, 나머지는 세번째 코어가 연산한다.
- 하지만 위 같이 하는 구역을 나누어 연산하는 것이 효율적인가?

### 05-1 병렬 스트림 예제

```java
public static void main(String[] args) [
	List<String> ls = Arrays.asList("Box", "Complex", "Robot");
	
	BinaryOperator<String> lc = (s1, s2) -> {
		if(s1.length() > s2.length()) 
			return s1;
		else
			return s2;
	};

	String str = ls.parallelStream() // 병렬 처리를 위한 스트림 생성
			.reduce("", lc);

	System.out.println(str);
}
```

- `parallelStream()` 메서드 호출 시 `병렬 처리`를 통해 스트림 데이터를 처리 한다.

### 05-1 출력 결과

```java
Complex
```

### 참고

> - [Java 스트림 Stream (1) 총정리](https://futurecreator.github.io/2018/08/26/java-8-streams/)

<br>

## Goal

이번에는 `스트림의 생성과 연결`, `중간 연산`, `최종 연산`에 대해 정리 해보자.

### 01 스트림의 생성과 연결

**기존에 스트림을 생성하던 두 가지 방식**

- <span style="color:red;">**배열 기반**</span> 스트림 생성.
- <span style="color:red;">**컬렉션 인스턴스 기반**</span> 스트림 생성.

> 위와 같은 스트림 생성 방법은 기존에 존재하는 데이터를 기반으로 하여 스트림을 생성한다.

### 이전에 스트림을 생성하던 방식

```java
// 배열 기반
String [] arr = {"Box", "Toy", "Rogo"};
Arrays.stream(arr)
	.filter(a -> a.length > 2)
	.forEach(a -> System.out.println(a + "\t"));
System.out.println();

// 컬렉션 인스턴스 기반
List<String> arr2 = Arrays.asList("Box", "Boy", "Gir");
arr2.stream()
	.filter(a -> a.length > 2)
	.forEach(a -> System.out.println(a + "\t"));
System.out.println();
```

### 01-1 다른 방식으로 스트림 생성

기존에 존재하는 데이터를 대상으로 스트림을 생성하는 것이 아닌, <span style="color:red;">**of()**</span> 메서드를 통해 새로운 스트림을 만들어보자.

```java
public static void main(String[] args) {
	Stream.of(11, 22, 33, 44) // 네 개의 값으로 이뤄진 스트림 생성
		.forEach(n -> System.out.println(n + "\t"));
	System.out.println();

	Stream.of("So Simple") // 하나의 String 인스턴스로 이뤄진 스트림 생성
		.forEach(s -> System.out.println(s + "\t"));
	System.out.println();
	
	List<String> s1 = Arrays.asList("Toy", "Robot", "Box");
	Stream.of(s1) // 하나의 컬렉션 인스턴스로 이뤄진 스트림 생성
		.forEach(w -> System.out.println(w + "\t"));
	System.out.println();
}
```

**기존에 존재하는 데이터를 기반으로 하여 스트림을 생성하지 않는다?**

- **of** : 기존에 존재하는 값이 없어도, `데이터를 of()에 넣어` 초기화가 가능하다.
- **of** : 인자로 전달된 `대상`을 가지고 스트림을 만든다.
- **of :** 인자로 전달된 `컬렉션 인스턴스`(1개)를 대상으로 스트림을 형성 했다.

### 출력 결과

```java
11   22   33   44
So Simple
[Toy, Robot, Box]
```

### 01-2 병렬 스트림으로 변경

```java
 // 병령 스트림 메서드 예제
-----------------------------------------------------------------------
Stream<T> parallel()    // Stream<T>의 메소드
DoubleStream parallel() // DoubleStream의 메소드
IntStream paralle()     // IntStream의 메소드
LongStream paralle()    // Longstream의 메소드
-----------------------------------------------------------------------

public static void main(String[] args) {
	List<String> ls = Arrays.asList("Box", "Simple", "Complex", "Robot");
	Stream<String> ss = ls.Stream(); // 스트림 생성
	
	BinaryOperator<String> lc = (s1, s2) -> {
		if(s1.length() > s2.length())
			return s1;
		else
			return s2;
	}
	
	String str = ss.parallel()      // 병렬 스트림 생성
			.reduce("", lc); // 리덕션
	
	System.out.println(str);
} 
```

- Stream<**String**> 인스턴스의 **ss.parallel()**를 호출하여 해당 스트림을 <span style="color:red;">**병렬 스트림**</span>으로 처리 하였다.
>  1. 스트림이 병렬 스트림이냐, 아니다가 중요한 것이 아니다. 
>  2. <span style="color:red;">이후에 진행이 되는 중간, 최종 연산이 **병렬**로 처리 되는것</span>.

### 출력 결과

```java
Complex
```

### 01-3 스트림의 연결

```java
public static void main(String[] args) {
	Stream<String> ss1 = Stream.of("Cake", "Milk");
	Stream<String> ss2 = Stream.of("Lemon", "Jelly");

	// 스트림을 하나로 묶은 후 출력(즉 연결 함수 -> concat을 통해)
	Stream.concat(ss1, ss2)
		.forEach(s -> System.out.println(s));
}
```

- Stream 클래스에 존재하는 `concat()`를 통해 두 인자를 연결 한다.
- 즉, 매개 변수 인자로 전달된 두 스트림 값을 → `하나의 스트림으로 생성 해준다`.

### 02 맵핑(Mapping)에 대한 추가 정리

[Stream<**T**>의 <span style="color:red;">**map**</span> 시리즈 메소드들] `1:1 맵핑` 

- <**R**> Stream<**R**> <span style="color:red;">**map**</span>(Function<T, R> mapper)
- IntStream `mapToInt`(ToIntFunction<**T**> mapper)
- LongStream `mapToLong`(ToLongFunction<**T**> mapper)
- DoubleStream `mapToDouble`(ToDoubleFunction<**T**> mapper)
    - Int, Long, Double + Stream은 AutoBoxing, AutoUnBoxing을 피할수 있다.

[Stream<**T**>의 <span style="color:red;">**flaMap**</span> 시리즈 메소드들] `1:* 맵핑` 

- <**R**> Stream<**R**> <span style="color:red;">**flatMap**</span>(Function<T, `Stream<R>`> mapper)
- IntStream `mapToInt`(ToIntFunction<**T**> mapper)
- LongStream `mapToLong`(ToLongFunction<**T**> mapper)
- DoubleStream `mapToDouble`(ToDoubleFunction<**T**> mapper)
    - Int, Long, Double + Stream은 AutoBoxing, AutoUnBoxing을 피할수 있다.

> 1. Stream<**T**>의 **map, flatMap** 메서드를 통해 **새로운 값**을 만든다 봐도 무방하다.
> 2. 또한 위 메서드를 통해 불필요한 **박싱(Boxing)**, **언박싱(UnBoxing)**을 피할 수 있다.

### 02-1 맵핑(Mapping)에 대한 추가 정리

```java
public static void main(String[] args) {
	Stream<String> ss1 = Stream.of("MY_AGE", "YOUR_LIFE");
	
	// 아래 람다식에서 스트림을 생성
	Stream<String> ss2 = ss1.flatMap(s -> Arrays.stream(s.split("_"))); // 중간 연산
	ss2.forEach(s -> System.out.println(s + "\t"))	
	System.out.println();
}
```

위 부분 설명해주실때 이해가 잘 되지 않았음 → 제대로 이해할 필요가 있음

### 02-2 맵핑(Mapping)에 대한 추가 정리

```java
// 성적표 클래스 정의
class ReportCard {
	private int kor;
	private int eng;
	private int math;

	public ReportCard(int k, int e, int m) {
		kor = k;
		eng = e;
		math = m;
	}

	public int getKor() { return kor; }
	public int getEng() { return eng; }
	public int getMath() { return math; }	 
}

public static void main(String[] args) {
	// 배열에 3개의 성적표 인스턴스를 저장
	ReportCard[] cards = {
		new ReportCard(70, 80, 90),
		new ReportCard(90, 80, 70),
		new ReportCard(80, 80, 80)
	};

	// Reportcard 인스턴스로 이루어진 스트림 생성
	Stream<String> sr = Arrays.stream(cards); 
	
	// 위 배열을 통해 스트림 생성
	// 1 : 3의 맵핑을 수행 한다.
	
	// 학생들의 점수 정보로 이루어진 스트림 생성
	IntStream si = sr.flatMapToInt(r -> IntSream.of(r.getKor(), r.getEnf(), r.getMath()));

	// 평균을 구하기 위한 최종 연산 average 진행
	double avg = si.average().getAsDouble();
	System.out.println("avg. " + avg);
}
```

- **average**() : OptionalDouble을 반환 형태로 갖는다.
- **getAsDouble**() : 실제 실수형 숫자를 반환 해준다.

### 03 스트림의 정렬

```java
// 정렬 메서드 구분
-----------------------------------------------------------------------
Stream<T> sorted(Comparator<? super T> comparator) // Stream<T>의 메서드
Stream<T> sorted()    // Stream<T>의 메서드
IntStream sorted()    // IntStream의 메서드
LongStream sorted()   // LongStream의 메서드
DoubleStream sorted() // DoubleStream의 메서드
-----------------------------------------------------------------------

public static void main(String[] args) {
	// 사전 편찬순으로 정렬을 수행 한다
	Steeam.of("Box", "Apple", "Robot")
		.sorted() // String 인스턴스는 Comparable<String> 인터페이스를 구현! 이를 기반으로 한 정렬 수행
		.forEach(s -> System.out.println(s + "\t"));
	System.out.println();

	Stream.of("Box", "Apple", "Rabit")
		.sorted((s1, s2) -> s1.length() - s2.length())
		.forEach(s -> System.out.println(s + "\t"));
	System.out.println();
}
```

- `String 클래스`는 `내부적`으로 `Compartor 인터페이스`를 `구현`하고 있다(복습)
- 위 같은 이유를 통해 자연스럽게 정렬을 수행 할 수 있다.

### 03-1 출력 결과

```java
Apple  Box  Robot
Box  Apple  Rabit
```

### 03-2 루핑(Looping)

`Stream의 forEach`같은 경우 `최종 연산`인 반면, 이번에는 `중간 연산`인 `peek() 메서드`에 대해 알아보자.

```java
public static void main(String[] args) {
	// 최종 연산이 생략된 스트림의 파이프라인
	IntSteam.of(1, 3, 5)
		.peek(d -> System.out.println(d + "\t"));
	System.out.println();

	// 최종 연산이 존재하는 스트림의 파이프 라인
	int a = IntSteam.of(1, 3, 5)
		.peek(d -> System.out.println(d + "\t"))
		.sum();
	System.out.println("a : " + a);	 
}
```

- peek() : forEach문과 기능적으로 완전히 동일하다, 하지만 `중간 연산` 이라는 차이점이 있다.
- `최종 연산`이 진행되지 않은 `중간 연산`은 아무 의미가 없다, 즉 → `실행 결과를 얻을 수 없다`.
    - ex) 나는 반복문을 통해 값을 얻는 동시에 합을 더하고 싶어.
    - `최종 연산인 forEach()`는 위 같은 실행이 불가능하기에 `peek()를 사용`한다.

> 스트림의 중간 연산을 사용한 후 반드시 최종 연산 구문을 넣어줘야 실행을 할 수 있다.

### 04 스트림의 최종 연산

스트림의 `최종 연산`을 알아보기에 앞서, **IntStream**에 존재하는 **메서드**에 대해 정리 해보자.

```java
public static void main(String[] args) {
	// 스트림에 존재하는 정수의 합 구하기
	int sum = IntStream.of(1, 3, 5, 7, 9)
			   .sum(); 
	System.out.println("sum : " + sum);

	// 스트림에 존재하는 정수의 개수 구하기
	long cnt = IntStream.of(1, 3, 5, 7, 9)
			    .count();
	System.out.println("count : " + count);

	// 평균
	IntStream.of(1, 3, 5, 7, 9)
		 .average() // 반환형이 Optional이니까 -> 이어서 ifPresent() 사용 가능하다.
		 .ifPresent(av -> System.out.println("avg : " + av));
	// 최소값
	IntStream.of(1, 3, 5, 7, 9)
		 .min()
		 .ifPresent(mn -> System.out.println("min : " + mn));
	// 최대값
	IntStream.of(1, 3, 5, 7, 9)
		 .max()
		 .ifPresent(mx -> System.out.println("max : " + mx));
}
```

- int <span style="color:red;">sum</span>()
- long <span style="color:red;">count</span>()
- OptionalDouble <span style="color:red;">average</span>()
- OptionalInt <span style="color:red;">min</span>()
- OptionalInt <span style="color:red;">max</span>()

### **Optional 클래스 복습 차원**

- **isPresent**() : 만약 값이 존재한다면 → `True, false 반환`.
- **ifPresent**() : 만약 값이 존재한다면 → `람다식 수행`.
- **Optional.of** : 상자를 만들 때 `null`을 `허용하지 않는다`.
- **Optional.ofNullable** : 상자를 만들 때 `null`을 `허용`한다.

```java
class StringOptional2 {
	public static void main(String[] args) {
		Optional<String> os1 = Optional.of(new String("Toy1"));
		Optional<String> os2 = Optional.ofNullable(new String("Toy2"));
		
		os1.ifPresent(s -> System.out.println(s)); // 람다식 버전
		os2.ifPresent(System.out::println); // 메소드 참조 버전
	}
}
```

> 참고 : [https://velog.io/@ym1085/Optional-클래스](https://velog.io/@ym1085/Optional-%ED%81%B4%EB%9E%98%EC%8A%A4)


### 04-1 allMatch, anyMatch, noneMatch

```java
public static void main(String[] args) {
	boolean b = IntStream.of(1, 2, 3, 4, 5)
			     .allMatch(n -> n % 2 == 0);
	System.out.println("모두 짝수이다. " + b);
	
	b = IntStream.of(1, 2, 3, 4, 5)
		     .anyMatch(n -> n % 2 == 0);
	System.out.ptinln("짝수가 하나는 있다. " + b);

	b = IntStream.of(1, 2, 3, 4, 5)
		     .noneMatch(n -> n % 2 == 0);
	System.out.ptinln("짝수가 하나도 없다. " + b);
}
```

- **allMatch**() : `모든 인자`가 조건에 맞아야 `True`를 반환.
- **anyMatch**() : `하나의 인자`라도 조건에 맞으면 `True`를 반환.
- **noneMatch**() : `모든 인자`가 조건에 맞지 않는다면 `True`를 반환.

### 04-2 collect: 스트림에 있는 데이터를 모아라

```java
<R> R collect(Supplier<R> supplier,
		BiConsumer<R, ? super T> accumulator,
		BiConsumer<R, R> combiner)
```

```java
// Stream의 collect 메서드 예제
public static void main(String[] args) {
	String [] words = {"Hello", "Box", "Robot", "Toy"};
	Stream<String> ss = Arrays.stream(words);
	
	List<String> ls = 
		ss.filter(s -> s.length() < 5)
		  .collect(() -> new ArrayList<>(),    // 저장소 생성
			(c, s) -> c.add(s),                  // 첫 번째 인자 통해 생성된 인스턴스 c, 스트림의 데이터 s
			(lst1, lst2) -> lst1.addAll(lst2));  // 순차 스트림에서는 의미 없음
	
	System.out.println(ls);
}
```

- `collect()` 메서드를 사용하면 `filter()` 메서드를 통해 `살아남은 데이터`, 즉 `걸러진 데이터`를 따로 저장할 수 있다.

### 시나리오 정리

1. `Box`, `Toy`만 걸러진다.
2. collect의 람다식에 전달된 `new ArrayList<>()`를 통해 `저장소 생성`.
3. 첫번째 인자 (`c`, s) → `c` : 저장소 (new ArrayList<>())를 의미.
4. 두번째 인자 (c, `s`) → `s` : 스트림에 저장된 데이터를 의미(Box, Toy).

### 04-3 이러한 방식으로도 가능하지 않을까?

```java
IntStream it = IntStream.of(1,3, 4, 5, 6);

int a = it.sum();
long b = it.count();    // 여기서 아래와 같은 컴파일 에러 발생
System.out.println(a);
System.out.println(b);
```

```java
Exception in thread "main" java.lang.IllegalStateException: stream has already 
been operated upon or closed
at java.util.stream.AbstractPipeline.<init>(AbstractPipeline.java:203)
at java.util.stream.LongPipeline.<init>(LongPipeline.java:91)
at java.util.stream.LongPipeline$StatelessOp.<init>(LongPipeline.java:574)
at java.util.stream.IntPipeline$5.<init>(IntPipeline.java:261)
at java.util.stream.IntPipeline.mapToLong(IntPipeline.java:260)
at java.util.stream.IntPipeline.count(IntPipeline.java:430)
at com.java.study.stream.StreamOf.main(StreamOf.java:72)
```

- 위 같은 선언은 절대적으로 가능하지 않다.
- `Stream`형 `참조 변수`로 `스트림`을 참조하고 있지만, `중간`, `최종 연산`이 수행된 스트림은 `소멸`이 된다.
- 위 같은 이유로 인해 한번 최종 연산을 수행한 스트림의 파이프라인을 되돌릴수는 없다.

### 04-4 병렬 스트림에서의 collect

```java
class CollectParallelStringStream {
	public static void main(String[] args) {
		String [] words = {"Hello", "Box", "Robot", "Toy"};
		Stream<String> ss = Arrays.stream(words);

		List<String> ls = ss.parallel()
				    .filter(s -> s.length() < 5)
				    .collect(() -> new ArraytList<>(),
					     (c, s) -> c.add(s),
					     (lst1, lst2) -> lst1.addAll(lst2));
	}
}
```
