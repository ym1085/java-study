### Goal

이번 Chapter에서는 Optional 클래스에 대해 정리 해보자.

### 01 Optional 클래스

- `Optional 클래스` 역시 `Wrapper 클래스`.
- Optional 클래스는 If ~ else문의 로직 과정을 대체 할 수 있다?
- `Optional 클래스는 상자다`?

```java
// 상자에 값을 담는 예제
Optional<String> os1 = Optional.of(new String("Toy1")); // 상자에 값을 담는다.
Optional<String> os2 = Optional.ofNullable(new String("Toy2")); // 상자에 값을 담는다.
```

- `Optional.of` : 상자를 만들 때 null을 허용하지 않는다.
- `Optional.ofNullable` : 상자를 만들 때 null을 허용한다.

### 01-1 Optional 클래스 사용 예제

```java
public final class Optional<T> extends Object {
	private final T value; // 이 참조변수를 통해 저장을 하는 일종의 래퍼 클래스
}

public static void main(String[] args) {
	Optional<String> os1 = Optional.of(new String("Toy1")); // of는 null을 허용하지 않음.
	Optional<String> os2 = Optional.ofNullable(new String("Toy2")); // ofNullable은 null 허용
	
	if(os1.isPresent()) [
		System.out.println(os1.get());
	}

	if(os2.isPresent()) {
		System.out.println(os2.get());
	}
}
```

- `os1.isPresent` : 값 또는 내용물이 저장 되었는가?
    - True / false 반환.
- `os1.get` : 값이 저장 되었으면 해당 값의 참조 값을 반환.

### 출력 결과

```java
Toy1
Toy2
```

### 01-2 Optional 클래스 사용 예제

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

```java
// 만약에 값이 존재한다면??
public void ifPresent(Consumer<? super T> consumer)
	// Consumer<T> void accept(T t);
```

- `ifPresent` : 만약 값 또는 내용물이 존재 한다면, 해당 내용물을 대상으로 람다식을 수행 하라.
- `isPresent는 True 혹은 false를 반환하는 메서드이지만, ifPresent는 람다식을 수행하기 위한 메서드이다.`

### 출력 결과

```java
Toy1
Toy2
```

### 01-3 Optional 클래스 If ~ else문 대체?

```java
class CompanyInfo {
	String phone;      // null 일 수 있음 (가정)
	String address;    // null 일 수 있음 (가정)

	public CompanyInfo(String ph, String ad) [
		this.phone = ph;
		this.address = ad;
	}
	
	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}
}

public static void main(String[] args) {
	CompanyInfo ci = new CompanyInfo(null, "Republic of Korea");
	String phone;
	String address;

	// 이 부분은 사라질수있는 로직
	if(ci.phone != null)
		phone = ci.getPhone();
	else
		phone = "There is no phone number.";
	
	if(ci.address != null)
		address = ci.getAddress();
	else
		address = "There is no address";

	System.out.println(phone);
	System.out.println(address);
}
```

- If ~ else문에서 null 체크를 할 때 주로 사용이 된다 말씀 하셨다.
- 위 코드에서는 If ~ else를 통해 null 체크를 진행 하고 있다.

### 01-4 Optional 클래스의 map 메서드

```java
public static void main(String[] args) {
	Optional<String> os1 = Optional.of("Optional String");
	Optional<String> os2 = Optional.map(s -> s.toUpperCase());
	System.out.println(os2.get());

	Optional<String> os3 = os1.map(s -> s.replace(' ', '_')).map(s -> s.toLowerCase());
	System.out.println(os3.get());
}

// public <U> Optional<U> map(Function<? super T, ? extends U> mappter) 하한제한, 상한제한
// Function<T, U>    U apply(T t)
```

- Optional 인스턴스 참조변수가 가지고 있는 `값` 혹은 `내용물`을 꺼내 `람다식에 전달`한 후 `그 결과를 반환` 한다.
- 또한 `map 메서드는` `반드시 람다식에 대한 결과를 반드시 반환` 해야 한다.
    - 하지만 여기서 그냥 반환 하는것이 아니라 `반환형`을 `Optional<T> 참조 변수로 반환` 한다.
    - Optional<T>로 반환하기에 이어서 map 메서드를 호출 할 수 있다.

> Optional 인스턴스 참조변수 박스에 반환 값을 넣는다 생각 하자.

### 출력 결과

```java
OPTIONAL STRING
optional_string
```

### 01-5 Optional 클래스의 orElse 메서드

```java
public static void main(String[] args) {
	Optional<String> os1 = Optional.empty();
	Optional<String> os2 = Optional.of("So Basic");

	String s1 = os1.map(s -> s.toString()).orElse("Empty");
	String s2 = os2.map(s -> s.toString()).orElse("Empty");

	System.out.println(s1);
	System.out.println(s2);	
}
```

- `orElse` : get() 메소드와 똑같지만, 빈 상자를 대상으로 호출이 되면 ("Empty")가 전달이 된다.
- 즉, 해석을 해보자면 반환 해라 근데 값이 없어?? 그럼 인자("Empty")를 넣어서 반환 해라.
    - `Optional 인스턴스에 값이 없는 상황에서 대신할 값을 넣을 수 있는 메소드.`

### 출력 결과

```java
Empty
So Basic
```

### 01-6 최종 결론

**Before**

```java
public static void main(String[] args) {
	CompanyInfo ci = new CompanyInfo(null, "Republic of Korea");
	String phone;
	String address;

	// 이 부분은 사라질수있는 로직
	if(ci.phone != null)
		phone = ci.getPhone();
	else
		phone = "There is no phone number.";
	
	if(ci.address != null)
		address = ci.getAddress();
	else
		address = "There is no address";

	System.out.println(phone);
	System.out.println(address);
}
```

**After**

```java
public static void main(String[] args) {
	Optional<ContInfo> ci 
		= Optional.of(new ContInfo(null, "Republic of Korea"));

	String phone = ci.map(c -> c.getPhone()).orElse("There is no phone number.");
	String addr = ci.map(c -> c.getAdrs()).orElse("There is no address");

	System.out.println(phone);
	System.out.println(addr);	
}
```

### 출력 결과

```java
There is no phone number. // orElse에 의해 왼쪽 문구가 출력됨
Republic of Korea
```

### 01-7 showCompanyAddress 메소드의 개선

**Before**

```java
public static void showCompanyAddress(Friend f) {
	String addr = null;
	if(f != null) {
		company com = f.getCmp();
		
		if(com != null)
			CompanyInfo info = com.getCompanyInfo();

			if(info != null)
				addr = info.getAdrs();
	}

	if(addr != null) 
		System.out.println(addr);
	else
		System.out.println("There's no...");
}
```

**After**

```java
public static void showCompanyAddress(Optional<Friend> f) {
	String address = f.map(Friend::getCmp)
                   f.map(Company::getCompanyInfo)
		   f.map(ContInfo::getAdrs)
		   .orElse("There's no...");

	String address = f.map(() -> Friend.getCmp());
	String address = f.map(() -> Friend.getCompany());
	String address = f.map(() -> Friend.getAdrs());

	System.out.println(address);
}
```

### 02-1 Optional 클래스의 flatMap 메소드

```java
public static void main(String[] args) {
	Optional<String> os1 = Optional.of("Optional String");
	Optional<String> os2 = os1.map(s -> s.toUpperCase());
	System.out.println(os2.get());
	
	Optional<String> os3 = os1.flatMap(s -> Optional.of(s.toLowerCase())); // 강제적으로 Optional 반환
	System.out.println(os3.get());
}
```

map

- 람다식이 반환하는 내용물을 `Optional로 감싸서` 반환 한다.

flatMap

- 람다식이 반환하는 내용물을 그냥 반환한다, `즉 Optional에 감싸서 반환 하지 않는다`.
- 필요에 따라 map과 마찬가지로 Optional에 감싸서 반환 할 수 있다.

### 출력 결과

```java
OPTIONAL STRING
optional string
```

### 02-2 Optiona 클래스의 flatMap 메소드

```java
class ContInfo {
	Optional<String> phone; // null 일 수 있음
	Optional<String> adrs;  // null 일 수 있음

	public ContInfo(Optional<String> ph, Optional<String> ad) {
		phone = ph;
		adrs = ad;
	}
	public Optional<String> getPhone() {
		return phone;
	}

	public Optional<String> getAdrs() {
		return adrs;
	}
}

public static void main(String[] args) [
	Optional<ContInfo> ci = Optional.of(
		new ContInfo(Optional.ofNullable(null), Optional.of("Koread"))
	);
	
	String phone = ci.flatMap(c -> c.getPhone()).orElse("There is no phone number.");
	String addr = ci.flatMap(c -> c.getAdrs()).orElse("There is no address.");

	System.out.println(phone);
	System.out.println(addr);
}
```
### OptionalInt, OptionalLong, OptionalDouble 클래스
![](https://images.velog.io/images/ym1085/post/ce236260-0ab8-4830-99e6-4a67ac77fc0c/_2020-11-16__8.19.52.png)
