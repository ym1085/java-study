# Section 01

### Goal

열거형, 가변 인자, 어노테이션에 대해 간략히 정리 해보자.

</br>

### 인터페이스 기반 상수의 정의

```java
interface Scale {
    int DO = 0; int RE = 1; int MI = 2; int FA = 3;
    int SO = 4; int RA = 5; int TI = 6;
}
```

- Interface는 `pulic`, `static`, `final`을 생략 하여도 내부적으로는 선언이 되 있다 봐도 무방하다.

</br>

### 이전 방식의 문제점 - Java Version 5

```java
interface Animal {
    int DOG = 1;
    int CAT = 2;
}

interface Person {
    int MAN = 1;
    int WOMAN = 2;
}

class NonSafeConst {
  public static void main(String[] args) {
      who(Person.MAN); // 정상적인 메소드 호출
      who(Animal.DOG); // 비정상적인 메소드 호출

      // 컴파일 및 실행 과정에서 발견되지 않는 심각한 오류
  }

  public static void who(int man) {
      // man으로 전달되는 값이 int형이기에 오류가 발생하지 않는다.

      switch(man) {
        case Person.MAN : 
            System.out.println("남성 손님입니다.");
            break;
        case Person.WOMAN : 
            System.out.println("여성 손님입니다.");
            break;
      }
    }
}

```

- 매개 변수 인자로 들어오는 값이 int형 → 1, 2 이기 때문에 오류가 발생하지 않는다.
- 위 같은 상황은 좋은 케이스라 볼 수 없다.

</br>

### 자료형의 부여를 돕는 열거형

```java
enum Scale {
	  DO, RE, MI, FA, SO, RA, TI
}

// 열거형 값(Enmerated Values)

public static void main(String[] args) {
    Scale sc = Scale.DO;
    System.out.println(sc);

    switch(sc)
      case DO : 
          System.out.println("도~");			
          break;
      case RE : 
          System.out.println("레~");			
          break;
      case MI : 
          System.out.println("미~");			
          break;
      case FA : 
          System.out.println("파~");			
          break;
      default : 
          System.out.println("솔~ 라~ 시~");			
}
```

- 열거형을 선언할 때는 **`enum`**을 선언 해줘야 한다.
- `**enum**` 안에는 열거형 값을 선언 해준다.
- `**열거형 값**`은 상수가 아니다.
- 열거형 참조 변수 `**Scale sc = Scale.DO;**` 를 선언 해줄 수 있다.

</br>

### 열거형 기반 수정 결과

```java
enum Animal {
	  DOG, CAT
}

enum Person {
	  MAN, WOMAN
}

class SafeEnum {
  public static void main(String[] args) {
		  who(Person.MAN); // 정상적인 메소드 호출
		  who(Animal.DOG); // **비정상적인 메소드 호출**

		  // **컴파일 과정에서 자료형 불일치로 인한 오류 발생**
  }

  public static void who(Person man) {
		  // man으로 전달되는 값이 int형이기에 오류가 발생하지 않는다.
		  // man 참조 변수는 Person 형이기 때문에 컴파일 오류가 발생 한다.
	
      switch(man) {
        case MAN : 
            System.out.println("남성 손님입니다.");
            break;
        case WOMAN : 
            System.out.println("여성 손님입니다.");
            break;
		}
	}
}
```

</br>

### 클래스 내에 열거형 정의 가능

```java
Class Customer {
  enum Gender {
      MALE, FEMALE // 클래스 내에 정의된 열거형 Gender
  }	

  private String name;
  private Gender gen;

  Customer(String n, String g) {
      name = n;

      if(g.equals("man")) 
        gen = Gender.MALE;

      else 
        gen = Gender.FEMALE;
  }
}
```

- 열거형은 클래스 내에서 선언이 가능하다.
- 클래스 내에서만 열거형을 사용 하고자 할때 위 같이 작성을 한다.

</br>

### 열거형 값은 무엇인가?

```java
gen = Gender.MAEL;     // 어떤 값?
gen = Gender.FEMALE;   // 상동
```

- 아래 예제를 참고 해보자.

</br>

### Example 01  (열거형 값의 정체)

```java
class Person {
  public static final Person Man = new Person();
  public static final Person Man = new Person();

  @override
  public String toString() {
    return "I am a dog person"; // 나는 개를 사랑하는 사람 입니다.
  } 
}

class InClassInst {
  public static void main(String[] args) {
      System.out.println(Person.MAN);
      System.out.println(Person.WOMAN);
  }
}
```

- Person 클래스 안에서 `**Person형 Instance 참조 변수**` 선언이 가능하다.
- 위 상황은 Java 기반 자료 구조를 공부할 경우 위 같은 코드를 볼 수 있다.
- 즉, 가능하다는 점만 인지하고 있으면 된다.

- static이 붙어 있기 때문에 위 같은 코드 선언이 가능 하다.
- static이 붙어 있지 않으면 재귀적으로 Instance를 생성 하기에 문제가 된다.

</br>

### Example 02 (열거형 값의 정체)

```java
enum Person {
  MAN, WOMAN   // **열거형 값의 정체는 인스턴스다?**

  @override
  public String toString() {
      return "I am a dog person";
  }
}

class EnumConst {
  public static void main(String[] args) {
      System.out.println(Person.MAN);   // toString 메소드의 반환 값 출력
      System.out.println(Person.WOMAN); // toString 메소드의 반환 값 출력
  }
}
```

- 모든 열거형은 java.lang.Enum<E> 클래스를 상속한다.
- 그리고 Enum<E>는 Object 클래스를 상속한다. 이런 측면에서 볼 때 `**열거형**`은 `**클래스**`라 볼 수 있다.
- Enum<E>은 Object 클래스를 상속 하기에, Object 클래스의 toString() 호출이 가능하다.
- System.out.println()에 인스턴스가 전달되면 해당 인스턴스의 toString()를 호출 한다.
    - MAN → println에 전달이 가능하기에 인스턴스라 볼 수 잇다.
    - WOMAN → println에 전달이 가능하기에 인스턴스라 볼 수 잇다.

</br>

### Example 03 (열거형 값의 정체)

```java
enum Person {
  MAN, WOMAN   // **열거형 값의 정체는 인스턴스다?**

  private Person() {
      System.out.println("Person constructor called");
  }

  @override
  public String toString() {
      return "I am a dog person";
  }
}

class EnumConst {
  public static void main(String[] args) {
      System.out.println(Person.MAN);   // toString 메소드의 반환 값 출력
      System.out.println(Person.WOMAN); // toString 메소드의 반환 값 출력
  }
}
```

- private 생성자가 등장 했다?
    - 열거형은 클래스의 범주에 속한다.
    - 생성자 정의를 안하면 default 생성자가 들어간다.
    - 하지만 무조건 private 생성자가 선언이 된다.
    - 또한 열거형 클래스는 인스턴스 생성이 불가능하다.
    - 열거형 값은 `**해당 클래스**`의 `**참조 변수**`라 생각하면 쉬울 것 같다.

</br>

### 출력

```java
Person constructor called
Person constructor called
I am a dog Person
I am a dog Person
```

</br>

### Example 04 (열거형 생성자에 인자 전달하기)

```java
enum Person {
  MAN(29), WOMAN(15)   // **열거형 값의 정체는 인스턴스다?**

  private Person() {
      System.out.println("Person constructor called");
  }

  @override
  public String toString() {
      return "I am a dog person";
  }
}

class EnumConst {
  public static void main(String[] args) {
      System.out.println(Person.MAN);   // toString 메소드의 반환 값 출력
      System.out.println(Person.WOMAN); // toString 메소드의 반환 값 출력
  }
}
```

</br>

### 출력

```java
I am 29 years old
I am 15 years old
```

</br>

### 결론

열거형 값은 `**인스턴스**` 또는 해당 클래스의 `**참조 변수**`다.

</br>

### 매개 변수의 가변 인자 선언

```java
class Varargs {
  public static void showAll(**String...vargs**) {         // 가변 인자 선언 
      System.out.println("LEN : " + vargs.length);

      for(String s : vargs)
        System.out.println(s + "\t");
      System.out.println();		
  }	

  public static void main(String[] args) {
      showAll("Box");
      showAll("Box", "Toy");
      showAll("Box", "Toy", "Apple");
  }
}
```

- (**`String...vargs`**) 위 코드는 가변 인자를 받을 때 사용 되는 코드 형식이다.
- `**String 인스턴스 몇 개든지 다 받아 주겠다**`, 근데 `**vargs 참조 변수**`를 통해 받아 주겠다.
- 배열의 참조 변수 값이 showAll("`여기`")에 들어가게 된다.
- 또한 `**vargs`** 는 배열의 참조 변수를 의미한다.

- **참고 이미지**
<img width="729" alt="_2020-10-17__11 01 59" src="https://user-images.githubusercontent.com/53969142/96327723-db845400-1076-11eb-9a79-0cb317fcda7d.png">

</br>

### 출력

```java
LEN : 1
BOX
LEN : 2
BOX   TOY
LEN : 3
BOX   TOY  APPLE
```

</br>

### 가변 인자 선언에 대한 컴파일러 관점에서의 처리

```java
// Before 사용자가 보는 코드

public static void showAll(**String...vargs**) {         // 가변 인자 선언 
  System.out.println("LEN : " + vargs.length);

  for(String s : vargs)
      System.out.println(s + "\t");
  System.out.println();		
}	

public static void main(String[] args) {
  showAll("Box");
  showAll("Box", "Toy");
  showAll("Box", "Toy", "Apple");
}

// 컴파일러에 의해 변경된 위 코드
**// 컴파일러 관점에서 아래와 같이 위 코드를 처리 한다.**

public static void showAll(**String[] vargs**) {
		System.out.println("LEN : " + vargs.length);
		
		for(String s : vargs)
				System.out.println(s + "\t");
		System.out.println();		
}	

public static void main(String[] args) {
    showAll(new String[]{"Box"});
    showAll(new String[]{"Box", "Toy"});
    showAll(new String[]{"Box", "Toy", "Apple"});
}
```

</br>

### 어노테이션

- @어노테이션은 컴파일러에게 메시지를 보내는 것이다.
- **`컴파일러가 컴파일을 수행할 때, 위에서 말했다시피 메시지를 전달 하는 것이다.`**
- 또한 이러한 어노테이션은 프로그래머가 만들어 사용 할 수 있다.

</br>

### 어노테이션 관련 문서

> **굳이 어노테이션을 만들고자 한다면 아래 문서를 참고 하면 된다.**
JSR 175 "A Metadata Facility for the Java Programming Language."
JSR 250 "Common Annotation for the Java Platform"

</br>

### @Override

```java
interface Viewable {
    public void showIt(String str);
}

class Viewer implements Viewable {
    @override
    public void showIt(String str){
        System.out.println(str);
    }
}
```

- Interface는 특정 기능을 사용자에게 제공함과 동시에, 메소드 구현의 강제를 요구하기 위해 사용이 된다.
- 즉, 공통적으로 사용 되는 기능에서 사용자는 해당 인터페이스의 메소드를 강제로 구현하고 변형 하여 사용 한다.

</br>

### Example 01

```java
interface Viewable {
    public void showIt(String str);
}

class Viewer implements Viewable {
    @override
    public void showIt(int str){
        System.out.println(str);
    }
}
```

- 위와 같이 오버라이딩을 할 때 인터페이스의 메소드와 타입 인자가 다른 경우 컴파일러에 의해 에러가 발생 한다.
- 이런 이유로 인해 @override 어노테이션을 사용 한다.

</br>

### Example 02 (`**인터페이스 복습**`)

- 만약 에어컨 기능을 구현해야 하는데 회사가 다르지만 기능은 동일한 경우

```java
Interface AirCondition {
    public void runAirCondition(String str);	
}

class Samsung implemnet AirCondition {
    @override
    public void rumAirCondition(String str) {
        str = "color is red";
        System.out.println("The Condition is Red");
    } 
}

class Lg implemnet AirCondition {
    @override
    public void rumAirCondition(String str) {
        str = "color is Blue";
        System.out.println("The Condition is Red");
    } 
}
```

</br>

### @Deprecated

```java
interface Viewable {
    @**Deprecated**		
    public void showIt(String str);     // Java 5 version에서 선언된 메소드 

    public void brShowIt(String str);   // Java 8 version에서 업그레이드 된 메소드
}

class Viewer implements Viewable {
    @override
    public void showIt(String str){
        System.out.println(str);
    } // **컴파일러 경고**

    @override
    public void brShowIt(String str) {
        System.out.println('[' + str + ']');
    }
}
```

- Deprecated 된 메소드
    - `**문제의 발생 소지가 있거나 개선된 기능의 다른 것으로 대체되어서 더 이상 필요 없게 되었음을 뜻 함.**`
    - 위 말은 간략히 풀어 보자면 `**이상 있을 것 같은 기능**`, `**사용 되지 않는 기능**`에 `**@Deprecated**`를 선언 한다.
    - 또한 위 어노테이션(**`@Deprecated`**)는 `**하위 Java Version과의 호환성**`을 위해 나온 어노테이션이다.

</br>

> 프로젝트의 규모가 커질 때 위 어노테이션이 사용이 되는 상황이 나온다.

</br>

### 호출문

```java
public static void main(String[] args) {
    Viewable view = new Viewer();
    view.showIt("Hello Annotations");   // **컴파일러 경고**
    view.brShowIt("Hello Annotations");
}
```

</br>

### 출력

```java
Note : AtDeprececated. java uses or overrides a deprecated API.
Note : Recompile with -Xlint: deprecation for details.
```

</br>

### @SuppressWarnings

```java
interface Viewable {
    @**Deprecated**		
    public void showIt(String str);     // Java 5 version에서 선언된 메소드 
    public void brShowIt(String str);   // Java 8 version에서 업그레이드 된 메소드
}

class Viewer implements Viewable {
    @override
    @suppressWarnings("deprecation")    // deprecation 관련 경고 메시지를 생략하라는 의미
    public void showIt(String str){
        System.out.println(str);
    } 

    @override
    public void brShowIt(String str) {
        System.out.println('[' + str + ']');
    }
}
```

- 컴파일러에 의해 나오는 경고를 꺼라 또는 경고를 출력하지 않아도 된다.
- 위 같은 의미를 얻기 위해 사용 되는 어노테이션이다.

</br>

### 호출문

```java
@SuppressWarnings("deprecation")
public static void main(String[] args) {
    Viewable view = new Viewer();
    view.showIt("Hello Annotations");   // **컴파일러 경고**
    view.brShowIt("Hello Annotations");
}
```
