# Section 01

### Goal

 Algorithm for Collection Framework 간략히 정리.

</br>

### 정렬

 `List<E>`를 구현한 `컬렉션 클래스`들은 저장된 Instance를 정렬된 상태로 유지하지 않는다.

대신에 정렬을 해야 한다면 다음 메소드를 사용할 수 있다.

```java
// 제네릭 메소드는 호출되는 순간 <T>가 초기화 된다.
// sort Method가 제네릭 메소드임을 나타내는 구문이다.
public static <T extends Comparable<T>> void sort(List<T> list)

// String 기반으로 List가 초기화 된다 가정 했을 때.
// 왼쪽에 있는 <T ..> 는 제네릭 메소드임을 표현하기 위해 사용이 되는 것이다.
public static <T extends Comparable<String>> void sort(List<String> list)
```

- `Collections 클래스`에 정의되어 있는 `sort 메소드`.
- 인자로 List<T>의 Instance는 모두 전달 가능.
- **단, T는 Comparable<T> 인터페이스를 구현한 클래스의 Instance만 올 수 있다.**
- `List<T>`가 `Comparable<T>`를 구현한 `Collection Framework만 올 수 있다는 의미`.
- 제네릭 메소드를 호출할 때 컴파일러에 의해 `<T>`가 결정이 된다.
- `String Class`는 `Comparble<> 인터페이스`를 구현하고 있다.

</br>

### <T extends Comparable<T>> 아니고 ...

<img width="725" alt="_2020-10-13__9 28 06" src="https://user-images.githubusercontent.com/53969142/96254999-2b203c80-0ff1-11eb-98d5-15e7924b095d.png">

```java
// 실제로 sort Method의 형태는 아래와 같이 선언이 되어 있다.
public static <T extends Comparable<? super T>> void sort(List<T> list)
```

</br>

### Comparable<? super T>

```java
class Car implements Comparable<Car> {
	private int disp; // 배기량
	
	public Car(int d) {
			this.disp = d;
	}
	
	@Override
	public int compareTo(Car o) {
			return disp - o.disp;
	}
}

public class ComparableSuperT {
	
	public static void main(String[] args) {
			List<Car> list = new ArrayList<Car>();
			list.add(new Car(1200));
			list.add(new Car(3000));
			list.add(new Car(1800));
			
			Collections.sort(list);
			
			Iterator<Car> itr = list.iterator();
			while(itr.hasNext()) {
					System.out.println(itr.next().toString() + "\t");
			}	
	}
}
```

- void sort(List<T> list) → void sort(List<**Car**> list) 형식이 된다.
- Comparable Interface를 구현함으로써 compareTo() Method를 오버라이딩 하여 사용 한다.

</br>

### 예외 상황(`Car Class를 확장하여 사용하고 싶다?`)

<img width="724" alt="_2020-10-13__9 40 29" src="https://user-images.githubusercontent.com/53969142/96255093-51de7300-0ff1-11eb-9149-e7f9a4035c99.png">

- void sort(List<T> list) → void sort(List<**Ecar**> list)
- 간접적으로 Comparable<Car>를 구현하고 있지만, 위 소스는 `컴파일 에러`가 `발생` 한다.
    - **`예외 상황`** : public static <T extends Comparable<T>> void sort(List<T> list)
        - public static <**Ecar** extends Comparable<**Ecar**>> void sort(List<T> list)
        - 현재 `Comparable<Car>`이기에 컴파일 에러가 발생 한다.
            - 즉 Comparable<Ecar>를 구현하는 Class는 존재하지 않는다.

    - **`해결 방안`** : public static <T extends Comparable<`? super T`>> void sort(List<T> list)
        - public static <**Ecar** extends Comparable<? super **Ecar**>> void sort(List<T> list)
        - Comparable<? super Ecar>와 같이 하한 제한을 선언 함으로써 Ecar, Ecar가 상속하는 Class 허용.

</br>

### 번외 → 생각나는 대로 적기

```java
// 예제 01.
public static <T extends Comparable<? super T>> void sort(List<T> list)

// 만약 List<**Ecar**> list = new ArrayList<>(); 이런 형태로 했을떄?
// 아래와 같은 형태를 나타나게 된다. -> 하한 제한 : Ecar나 Ecar가 상속한 Class가 올 수 있다.
// 현재 Ecar가 상속하는 Class는 **Car** **클래스** 와 **Object 클래스**가 있다.
public static <Ecar extends Comparable<? super Ecar>> void sort(List<Ecar> list)
```

> 상당히 어렵군.. 위 Car → Ecar 예제를 항상 기억하고 있자.

</br>

### 정렬 : Comparator<T> 기반

 Collection Class에는 호출 시 정렬의 기준을 결정할 수 있는 다음 메소드가 정의되어 있다.

```java
public static <T> void sort(List<T> list, Comparator<? super T> c)
```

- sort Method는 첫번째로 `List<T>` 인스턴스를 인자로 전달 받는다.
- 두번째로 인자로 전달된 정렬 기준을 근거로 하려 정렬을 합니다 → `Comparator<? super T>`
- Comparator<Car> 인터페이스에는 `compare()` Method가 존재한다.

</br>

### 해석 순서

1. public static <T> void sort(List<T> list, Comparator<**? super T**> c) 
2. public static <T> void sort(List<T> list, Comparator<**T**> c)  → **? super 지우고 T만 남긴다.**
3. public static <T> void sort(List<**Car**> list, Comparator<**Car**> c) → 문제 없음 😇
4. public static <T> void sort(List<**Ecar**> list, Comparator<**Ecar**> c) → 문제는 얘임 💦

```java
class CarComp implements Comparator<Car> {

		@Override
		public int compare(Car arg0, Car arg1) {
				return 0;
		}
}

public class ComparaTor {

	public static void main(String[] args) {
			List<Car> clist = new ArrayList<Car>();
			clist.add(new Car(1800));
			clist.add(new Car(1200));
			
			List<Ecar> elist = new ArrayList<Ecar>();
			elist.add(new Ecar(3000));
			elist.add(new Ecar(2500));
			
			CarComp comp = new CarComp();
			
			// public static <T> void sotr(List<T> list, Comparator<T> d)
			// public static <T> void sotr(List<T> list, Comparator<? super T> d) 
			// - 하한 제한 
			
			Collections.sort(clist, comp); // comp를 기준으로 정렬을 해라.
			Collections.sort(elist, comp); // comp를 기준으로 정렬을 해라.
			
			// ........
			
	}
}
```

</br>

### 찾기

<img width="719" alt="_2020-10-13__10 15 10" src="https://user-images.githubusercontent.com/53969142/96255175-70446e80-0ff1-11eb-91d3-28b95ac806b2.png">

### binarySearch(...)

 위 Method는 `이진 탐색` 이라는 `알고리즘`을 `기반`으로 `탐색을 진행`한다. 그런데 이 알고리즘을 적용하기 위해서는 해당 `컬렉션` 

`Instance`가 `정렬된 상태` 이어야 한다. **이진 탐색은 정렬된 리스트 자료 구조를 대상으로 적용하는 Algorithm**이기 때문이다. 

따라서 다음 예제에서 보이듯이 `binarySearch의 호출에 앞서 정렬`의 `과정`이 `선행`되어야 한다.

public static `<T>` int binarySearch(List<? extends Comparable<? super T>> list, T key)

- list에서 key를 찾아 그 인덱스 값 반환, 못 찾으면 음의 정수 반환.
- Comparable, Comparator `<T>`만 남기고 나머지를 지워서 해석을 한다.
- 하지만 위 예제 에서는 `?`만 남기고 다 지워서 해석 해라.

</br>

### 해석 순서

1. Step 1. public static <T> int binarySearch(**List<?>** list, T key)
2. Step 2. public static <T> int binarySearch(**List<? extends Comparable<T>**> list, T key)
3. Step 3. public static <T> int binarySearch(**List<? extends Comparable<? super T>>** list, T key)

```java
public static void main(String[] args) {
			List<String> list = new ArrayList<String>();
			list.add("Box");
			list.add("Robot");
			list.add("Apple");
			
			Collections.sort(list);
			
			int idx = Collections.binarySearch(list, "Robot"); // 탐색
			System.out.println(list.get(idx)); // 탐색 결과 출력			
}
```

- public static <T> int binarySearch(List<? extends Comparable<? super **String**>> list, **String** key)
- Car → Ecar 예제를 기본으로 Remind 해야 한다.
- Chapter 24는 여러번 공부 해서 이해를 해야 한다.
- 위에서도 말했지만 String Class는 Comparable<T> 인터페이스를 내부적으로 구현하고 있다.

</br>

### 찾기 : Comparator<T> 기반

<img width="734" alt="_2020-10-13__10 26 56" src="https://user-images.githubusercontent.com/53969142/96255224-82bea800-0ff1-11eb-8317-db389d8883bf.png">

- public static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c)

```java
class StrComp implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
			return s1.compareToIgnoreCase(s2);
	}
	
}

class StringComparaTorbnSearch {
	public static void main(String[] args) {
			List<String> list = new ArrayList<String>();
			list.add("Robot");
			list.add("Apple");
			list.add("BOX");
				
			StrComp cmp = new StrComp();
			Collections.sort(list);
			
			// 정렬된 리스트에서 오브젝트의 위치를 반환하는 java.util.Collections 클래스 메소드입니다.
			// 반드시 정렬 된 상태여야 합니다. 이진 탐색으로 값을 찾기 때문에 정렬이 되어 있지 않으면 이진 탐색을 할 수가 없습니다.
			int idx = Collections.binarySearch(list, "Robot", cmp);
			
			/**
				public static <T> int binarySearch(List<?  Extends Comparable<? super T>> list, T key)
	     			-> list에서 key를 찾아 그 인덱스 값 반환, 못 찾으면 음의 정수 반환		
			 */
			System.out.println("idx : " + idx);
			System.out.println("list.get : " + list.get(idx));
	}
}
```

</br>

### 복사하기

- public static <T> void copy(**List<? super T>** dest, **List<? extends T>** src)
    - src의 내용을 dest로 복사.

**List<T> dest 아닌 List<? super T> dest인 이유는?**

- `하한 제한`
- dest에 T형 Instance를 넣는 것만 허용 하겠다. 꺼내면 컴파일 에러!

**List<T> src** 아닌 **List<? extends T>** src 인 이유는?

- `상한 제한`
- src로부터 T형 Instance 꺼내는 것만 허용 하겠다. 넣으면 컴파일 에러!

</br>

### 번외

- <**?** super **T**>       → 하한 제한으로 넣는 것만 가능하다.
- <**?** extends **T**>   → 상한 제한으로 꺼내는 것 만 가능하다.
