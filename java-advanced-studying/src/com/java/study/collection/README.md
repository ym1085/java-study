# Section 01

### Goal

 `Collection Framework`에 대한 부분을 공부 해보자.

</br>

### Collection Framework(`자료 구조`)

<img width="740" alt="_2020-09-29__8 18 54" src="https://user-images.githubusercontent.com/53969142/96258069-6e30de80-0ff6-11eb-8f2b-05343e8f577d.png">

- Collection Framework가 제공하는 `Class`들은 `Map`, `Set`, `List` 인터페이스 중 한 가지를 구현하게 된다.
- 구현하는 `인터페이스`에 따라 `사용 방법`과 `특성`이 결정 된다.

**List<E> 인터페이스를 구현하는 대표적인 컬렉션 클래스 둘은 다음과 같다.**

- ArrayList<E>
    - `배열 기반` 자료 구조, `내부적으로 배열`을 이용하여 Instance 저장.
    - 배열은 공간을 미리 생성하여 데이터를 저장 한다.
    - 배열은 접근 속도가 빠르며, 순차적 접근에 있어 좋은 성능을 갖는다.
    - 하지만 배열은 한번 공간이 할당되면 `메모리 공간`을 추가 할 수 없다.

- LinkedList<E>
    - `리스트 기반` 자료 구조, `내부적으로 리스트`를 구성하여 Instance 저장.
    - 공간을 미리 생성하지 않고, 들어오는 데이터에 따라 메모리 공간을 할당 한다.
    - 리스트 기반은 배열에 비해 속도가 느리지만, 배열에 비해 `삭제`에 능하다.
    - Instance의 저장 → 해당 데이터를 `추가`한다.
    - Instance의 삭제 → 해당 데이터를 `삭제`한다.
        - 리스트 기반 자료 구조는 `포인터`를 통해 삭제를 진행 하기에 가능하다.

</br>

**List<E> 인터페이스를 구현하는 컬렉션 클래스의 공통 특성**

- Instnace의 `저장 순서 유지`
- 동일 Instance의 `중복 저장`을 허용한다.

</br>

ArrayList<E>의 **단점**

- `저장 공간을 늘리는 과정`에서 `많은 시간`이 `소요`된다.
- `Instnace`의 `삭제` 과정에서 `엄청나게 많은 연산`이 `필요` 할 수 있다.

ArrayList<E>의 **장점**

- 저장된 Instnace의 참조가 빠르다.
- `인덱스`로 데이터에 접근하기 때문에 리스트 기반에 비해 `속도가 빠르다.`

</br>

LinkedList<E>의 단점

- 저장된 Instnace의 `참조 과정`이 `배열에 비해 복잡`하다. 따라서 느리다.
- List는 소프트웨어적 관점에서 `첫번째 데이터만` 잡고 있기 때문에 순차적으로 데이터를 찾는다.

LinkedList<E>의 장점

- 저장 공간을 늘리는 과정이 간단하다.
- 저장된 Instance의 삭제 과정이 단순하다.

</br>

### Enhanced for Loop

```java
for(Stirng e : list) {
    System.out.println("e : " + e);
}
```

</br>

### 반복자

<img width="396" alt="_2020-09-29__9 02 50" src="https://user-images.githubusercontent.com/53969142/96264575-50b44280-0fff-11eb-940e-536c3cbd9c53.png">

 최 상단 부모인 `Collection<E>`가 `Iterable<E> 인터페이스`를 `상속`하기 때문에 하위에 존재하는 

`Set<E>`, `List<E>`, `Queue<E>`는 `Iterator Method`를 사용 할 수 있다. 

 자료 구조 마다 사용하는 방식도 다르고 형식도 다르기 때문에 `Iterable<E>` 를 사용하여 반복자를 사용한다.

</br>

### Iterator 반복자의 세 가지 메소드

```java
while(itr.hasNext()) {
  Stirng str = itr.next();
  if(str.equals("Box")) {
      itr.remove();
  }
}
```

- 처음에는 0번째 인덱스를 바라보고 있지 않고 `0번째 전`을 바라보고 있다.
- E `next`() → 다음 Instance의 참조 값을 반환.
- boolean `hasNext`() → next 메소드 호출 시 참조 값 반환 가능 여부 확인.
- void `remove`() → next 메소드 호출을 통해 반환 했던 Instance 삭제.

</br>

### 배열 vs 컬렉션 Instance

 다음 두 가지 이유로 배열보다 ArrayList<E>가 더 좋다.

- Instance의 `저장`과 `삭제`가 배열에 비해 편하다.
- 반복자(`Iterator`)를 쓸 수 있다.

</br>

### 배열처럼 선언과 동시에 초기화 불가능

하지만 아래와 같은 방법을 통해 컬렉션 Instance를 초기화 할 수 있다.

- 아래와 같이 생성된 리스트 Instance는 Immutable(`변할 수 없는`) Instance다.

```java
List<String> list = Arrays.asList("Toy", "Robot", "Box", "Toy"); // 중복 가능
```

위 같이 선언이 되면 Immutable Instance로 ArrayList<E>의 특성을 살릴 수 없다.

- 하지만 아래와 같이 ArrayList<>() `Constructor`에 `List<String>`을 넣어 특성을 살릴 수 있다.

```java
list = new ArrayList<>(list); 
```

</br>

### 배열 기반 리스트 → 연결 기반 리스트로?

- public ArrayList(Collection<? extends E> c)  → `ArrayList<E> 생성자 중 하나`
    - 인자로 전달된 컬렉션 Instance로부터 ArrayList<E> Instance 생성

- public LinkedList(Collection<? extends E> c)  → `LinkedList<E> 생성자 중 하나`
    - 인자로 전달된 컬렉션 Instance로부터 LinkedList<E> Instance 생성

```java
public static void main(String [] args) [
  List<String> list = Arrays.asList("Toy", "Box", "Robot", "Box");
  list = new ArrayList<>(list);

  // ArrayList<E> 인스턴스 기반으로 LinkedList<E> 인스턴스 생성
  list = new LinkedList<>(list);
}
```

</br>

### 리스트만 갖는 양방향 반복자

public ListIterator<E> listIterator() // List<E> 인터페이스의 메소드

- ListIterator<E>는 Iterator<E>를 상속한다.
- `단 방향 반복자`는 다음 메모리를 가르킨 후 `이전 메모리`로 되돌릴 수 없다.

</br>

# Section 02

### Goal
Set<E>, Tree<E>, HashSet<E> 

</br>

### Set<E> 인터페이스를 구현하는 컬렉션 클래스

- 집합의 특징을 갖는다.
- `중복`을 허용하지 않는 특징을 갖는다.
- `순서` 가 유지되지 않는 특징을 갖는다.

</br>

### HashSet<E>
```java
public static void main(String[] args) {
  // TODO Auto-generated method stub

  Set<String> set = new HashSet<>();
  set.add("Toy");
  set.add("Robot");

  System.out.println("인스턴스의 수 : " + set.size());

  for(Iterator<String> itr = set.iterator(); itr.hasNext();) {
      System.out.println(itr.next() + '\t');
      System.out.println();
  }

  for(String s : set) {
      System.out.println(s + "\t");
      System.out.println();
  }
}
```

</br>

### 동일 Instance가 되는 기준은 무엇인가?

- public boolean `equals`(Object obj)
    - `Object` 클래스의 `equals 메소드` 호출 결과를 근거로 동일 Instance를 판단 한다.

- public int `hashCode`()
    - 그런데 그에 앞서 `Object 클래스의 hashCode 메소드` 호출 결과가 같아야 한다.
    - Object 클래스의 `hashCode() 메소드`를 통해 저장되는 데이터의 부류를 나눈다.
        - 쉽게 말해 `분류`를 A, B, C, D로 구분한 후 들어오는 값을 Object의 equals 메소드를 통해 구분.

</br>

### Hash 알고리즘(`분류 기법 알고리즘`)

<img width="730" alt="_2020-09-30__4 19 39" src="https://user-images.githubusercontent.com/53969142/96265231-2747e680-1000-11eb-9e0a-b3e96ae2d21d.png">

- `분류 대상` 이 하나의 집합이 된다.
- 해당 집합에 데이터를 추가할 시 하나의 Instance와 들어오는 값을 일일이 비교 해야 한다면?
    - 적용 Hash 알고리즘  : `num % 3`
        - 여기서 3은 몇 가지의 부류로 나눌지 결정하는 숫자를 의미한다
            - ex : 3 → `3 부류`
            - ex : 100 → `100 부류`
    - 위 같이 분류를 하면 탐색의 `속도`가 매우 빨라진다.
    - 저장되는 Instance의 수는 제어할 수 없지만, `Hash 알고리즘`을 통해 탐색의 `속도 증가`.

</br>

### 변하지 않는 기본 원칙
```java
class Num {
  private int num;

  public Num(int n) {
      this.num = n;
  }

  @Override
  public int hashCode() {
      return num % 3; // num의 값이 같으면 부류도 같다.
  }

  @Override
  public boolean equals(Object obj) {
      if(num == ((Num)obj).num) {
        return true;
      } else {
        return false;
      }
  }
}

class car {
  private String model;
  private String color;

  @Override
  public int hashCode() {
    return (model.hashCode() + color.hashCode()) / 2;
  }

  /**
   * 		모든 인스턴스 변수의 정보를 다 반영하여 해쉬 값을 얻으려는 노력이 깃든 문장.
   * 		결과적으로 더 세밀하게 나뉘고, 따라서 그만큼 탐색 속도가 높아진다.
   */

}
```

- `모든 정보를 전부 반영하여 HashCode의 값을 반영하라?`

> 무슨 소리야...??

</br>

### Hash 알고리즘을 일일이 정의하기 조금 그렇다면

- public static int `hash`(Object...values)
    - `java.util.Objects`에 정의된 메소드, 전달된 인자 기반의 해쉬 값 반환.

```java
@Override
public int hashCode() {
  return Objects.hash(model, color); 
}
```

</br>

### TreeSet<E>

set<E> 인터페이스를 구현하는 Tree<E> 클래스

</br>

### TreeSet<E>의 특징

- `중복`을 허용하지 않는다.
- `순차적인 저장`을 지원하지 않는다.
- HashSet<E>과 마찬가지로 `집합`을 통해 데이터를 저장 한다.

</br>

### TreeSet<E>의 값 참조 순서

<img width="759" alt="_2020-09-30__4 49 40" src="https://user-images.githubusercontent.com/53969142/96265295-375fc600-1000-11eb-9f4c-b16947210361.png">

- `오름 차순` 기준으로 값의 참조가 이루어진다 → 즉 오름 차순으로 데이터를 가져온다.
- 하지만 `Instance`가 저장이 되는데 어떤 `기준`으로 데이터의 오름 차순을 결정 할 것 인가?
    - 이러한 상황에서의 정렬 기준은 프로그래머가 정하기 나름이다.
    - `Comparable Interface`의 구현을 통하여 `두 Instance의 크고 작음`을 `결정` 할 수 있다.
        - Comparable Interface가 구현하고 있는 `compareTo`(Object o)를 구현하여 사용.

</br>

### Comparable Interface's compareTo()

interface Comparable  →  interface Comparable<T>

- int compareTo(Object o)  →  int compareTo(T o)
    - 인자로 전달된 o가 작다면 `양의 정수` 반환.
    - 인자로 전달된 o가 크다면 `음의 정수` 반환.
    - 인자로 전달된 o가 같다면 `0`을 반환.

 따라서 TreeSet<T>에 저장할 Instance들은 모두 Comparable<T> 인터페이스를 구현한 클래스의

Instance이어야 한다. 아니면 예외 발생!!

</br>

### Comparable<T> 가 아닌 ComparaTor<T> 인터페이스?

- String 클래스의 정렬 기준은 사전 편찬 순으로 compareTo()를 구현 하고 있다.
- 하지만 정렬 기준을 변경하고 싶은 상황에서는?
    - TreeSet<E> 인터페이스는 위 같은 상황을 해결하기 위한 `생성자`를 제공 한다.
    - public TreeSet( Comparator<? super E> comparator )

- 위 같은 기능을 제공 받기 위해서 해당 클래스는 `ComparaTor<T> 인터페이스`를 구현해야 한다.

```java
class PersonComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        return p2.age - p1.age;
        // p2 : 50 - p1 : 4
    }
}

public static void main(Stirng[] args) {
    TreeSet<Person> tree = new TreeSet<>(new PersonComparator());
    tree.add(new Person("Yoon", 37));
    tree.add(new Person("Hong", 53));
    tree.add(new Person("Park", 22));

    for(Person p : tree) {
        System.out.println(p);
    }
}
```

- p1이 p2보다 크면 `양의 정수` 반환.
- p1이 p2보다 작으면 `음의 정수` 반환.
- p1과 p2가 같다면 `0`을 반환.
- `기존 Comparable<T> Interface의 compareTo()를 무시 한다.`

</br>

### 중복된 Instance의 삭제

```java
public static void main(String[] args) [
    List<String> lst = Arrays.asList("A", "B", "C", "A");
    ArrayList<String> list = new ArrayList<>(lst);

    for(String s : list)
      System.out.println(s.toString() + "\t");
    System.out.println();

    // 중복된 인스턴스를 걸러 내기 위한 작업
    HashSet<String> set = new HashSet<>(list);

    // 원래대로 ArrayList<String> 인스턴스로 저장물을 옮긴다.
    list = new ArrayList<>(set);

    for(String s : list)
      System.out.println(s.toString() + "\t");
    System.out.println();
}
```

</br>

# Section 03

### Goal

 Stack과 Queue에 대해 간략히 정리 해보자.

</br>

### Queue Interface를 구현하는 Collection Class

<img width="732" alt="_2020-10-05__9 06 16" src="https://user-images.githubusercontent.com/53969142/96266447-9f62dc00-1001-11eb-912c-7706eff8e69d.png">

**Stack**

- LIFO(Last In First Out)
    - `선입 후출`의 개념을 가진다.
    - 먼저 저장된 데이터가 `가장 마지막`에 빠져 나가는 자료 구조 형태

</br>

**Queue**

- FIFO(First In First Out)
    - `선입 선출`의 개념을 가진다.
    - 먼저 저장된 데이터가 가장 먼저 빠져 나간다.
    - 즉, 들어간 순서대로 데이터가 출력이 된다.

> Stack과 Queue는 특정 알고리즘에서 사용이 된다.

</br>

### Queue 인터페이스

<img width="734" alt="_2020-10-05__9 12 20" src="https://user-images.githubusercontent.com/53969142/96266646-d6d18880-1001-11eb-8666-928d00741172.png">

**기본 Method 제공**

```markdown
위 세 가지 Method는 예외 처리 메커니즘이 제공 되며, 실제로 예외가 발생 한다.
```

</br>

- boolean `add`(E e)
    - 데이터 넣기.
    - 해당 데이터 공간에 데이터를 `삽입` 한다.
- E `remove`()
    - 데이터 꺼내기.
    - 해당 Instance의 `참조 값`을 `반환` 해준다.
- E `element`()
    - 데이터 확인하기.
    - 데이터를 꺼내지 않고 그 자리에 저장 한다.

</br>

**추가 Method 제공**

```markdown
Queue 자료구조는 다른 자료 구조나, 알고리즘을 구현하는 도구가 되기도 한다.
예외 상황 자체가 알고리즘의 일부, 또는 해당 상황을 활용 할 수 도 있기 때문에. 
```

- boolean `offer`(E e)
    - 넣기, 넣을 공간이 부족하면 `false` 반환
- E `poll`()
    - 꺼내기, 꺼낼 대상이 없으면 `null` 반환
- E `peek`()
    - 확인하기, 확인할 대상이 없으면 `null` 반환

</br>

### Example

```java
package com.java.study.Queue;

import java.util.LinkedList;

public class Queue {
  public static void main(String[] args) {
      java.util.Queue<String> que = new LinkedList<String>();
      que.offer("A");
      que.offer("B");
      que.offer("C");

      // 우엇이 다음에 나올지 확인
      System.out.println("next : " + que.peek());

      // 첫 번째, 두 번째 인스턴스 꺼내기
      System.out.println(que.poll());
      System.out.println(que.poll());

      // 무엇이 다음에 나올지 확인
      System.out.println("next : " + que.peek());

      // 마지막 인스턴스 꺼내기
      System.out.println(que.poll());
  }
}
```

- `LinkedList<E>`는 List<E>와 동시에 Queue<E>를 구현하는 Collection Class다.
- 어떠한 타입의 참조 변수로 참조 하느냐에 따라서 `리스트`, `큐`로 동작한다.'

</br>

### 스택(Stack)의 구현

<img width="747" alt="_2020-10-05__9 53 26" src="https://user-images.githubusercontent.com/53969142/96266766-fa94ce80-1001-11eb-9145-815351471149.png">

- Java에는 기본적으로 `Stack 클래스`가 존재 한다.
- 과거에 작성된 Code의 `호환성`을 위해 구현되어 있는 클래스.

</br>

**Deque<E> 인터페이스의 메소드**

```java
// Deque<E> 자료 구조의 경우 입구, 출구가 뚫려 있는 그림이기에 위치에 상관없이 데이터를 꺼낼 수 있다
```

</br>

**앞으로 넣고, 꺼내고, 확인하기**

- boolean `offerFirst`(E e)
    - 넣기, 공간 부족하면 false 반환.
- E `pollFirst`()
    - 꺼내기, 꺼낼 대상 없으면 null 반환.
- E `peekFirst`()
    - 확인하기, 확인할 대상 없으면 null 반환.

</br>

**뒤로 넣고, 꺼내고, 확인하기**

- boolean `offerLast`(E e)
    - 넣기, 공간 부족하면 false 반환.
- E `pollLast`()
    - 꺼내기, 꺼낼 대상 없으면 null 반환.
- E `peekLast`()
    - 확인하기, 확인할 대상 없으면 null 반환.

> 위 두 세트를 한 세트로 바라봐야 한다. 즉 들어가는 위치, 출력되는 위치만 다른 것이다.

</br>

**내부적으로 예외 처리를 제공해주는 Method**

- 앞으로 넣고, 꺼내고, 확인하기
    - void `addFirst`(E e) : 넣기
    - E `removeFirst`() : 꺼내기
    - E `getFirst`() : 확인하기

</br>

- 뒤로 넣고, 꺼내고, 확인하기
    - void `addLast`(E e) : 넣기
    - E r`emoveLast`() : 꺼내기
    - E `getLast`() : 확인하기

</br>

### Map<k, v> 인터페이스를 구현하는 Collection 클래스

`Map<key, value>는 실무에서 가장 많이 사용 되는 자료 구조로 중요하다.`

</br>

**Map<k, v>의 특징**

- Map은 일종의 자료 구조인데, `반복자`를 제공하지 않는다.
- Key - Value 한 쌍으로 데이터를 저장 한다.
    - Key : 데이터를 `참조`하는데 `필요한 정보` 또는 찾기 쉽도록 `책갈피` 역할을 해주는 것.
    - Value : 해당 Key의 `실제 데이터`를 의미한다.
- Key는 `중복`을 허용하지 않고, Value는 `중복`을 허용 한다.

</br>

**Map의 순차적인 접근?**

- Map은 Set<E>와 좀 더 적합하다.
- Set<E>는 `중복`을 허용하지 않기 때문이다.

</br>

**HashMap<K, V>의 순차적 접근 방법**

 HashMap<k, v> 클래스는 Iterable<T> 인터페이스를 구현하지 않으니 for-each문을 통해서, 혹은

반복자를 얻어서 순차적 접근을 진행 할 수 없다.

</br>

 대신 다음 메소드 호출을 통해서 Key를 따로 모아 놓은 Collection Instance를 얻을 수 있다. 

그리고 이때 반환된 Collection Instance를 대상으로 반복자를 얻을 수 있다.

```java
public Set<K> **keySet**()
```

</br>

### Example

```java
package com.java.study;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTest {
  public static void main(String[] args) {
      /*

        [복습]
        Map
        - 


       */

      // [1] Map<k, v>
      Map<String, Object> map = new HashMap<String, Object>();

      map.put("a", "A");
      map.put("b", "B");
      map.put("c", "C");

      System.out.println("a : " + map.get("a"));
      System.out.println("b : " + map.get("b"));
      System.out.println("c : " + map.get("c"));
      System.out.println();

      map.remove("a");

      System.out.println("a : " + map.get("a"));
      System.out.println();

      // [2] HashMap<k, v>
      HashMap<String, Object> paramMap = new HashMap<String, Object>();

      paramMap.put("a", "Brown");
      paramMap.put("b", "Jane");
      paramMap.put("c", "Aplil");
      paramMap.put("d", "Kane");
      paramMap.put("e", "Mike");
      paramMap.put("f", "Michel");


      Set<String> paramKey = paramMap.keySet();
      for(String key : paramKey) {
          System.out.print(key + "\t");
      }

      System.out.println();

      for(String key : paramKey) {
          System.out.print(paramMap.get(key) + "\t");
      }
  }
}
```

</br>
