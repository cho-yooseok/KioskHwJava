

# 트러블 슈팅 (TroubleShooting)

### 문제 1: 숫자가 아닌 값을 입력하면 프로그램이 종료됨

#### 배경
Kiosk 프로그램에서 사용자가 메뉴를 선택할 때 숫자를 입력해야 하지만, 문자나 특수문자를 입력하면 프로그램이 예상치 못한 동작을 하거나 종료되는 문제가 발생했습니다.

#### 발단
사용자가 메뉴 번호 대신 문자나 특수문자를 입력했을 때 `NumberFormatException`이 발생하여 프로그램이 비정상적으로 종료될 가능성이 있음을 발견했습니다.

#### 전개
`scanner.nextLine()`을 사용하여 사용자 입력을 받는 과정에서 문자열을 정수로 변환하는 `Integer.parseInt(input)`에서 예외가 발생함을 확인했습니다.

#### 위기
예외 처리를 하지 않으면 프로그램이 멈추고 종료되며, 사용자는 왜 종료되었는지 알 수 없었습니다. 단순히 예외 메시지만 출력하는 방식으로는 사용자 경험이 좋지 않았습니다.

#### 절정
예외 발생 시 사용자에게 적절한 안내 메시지를 출력하고, 다시 입력을 받을 수 있도록 예외 처리 코드를 추가했습니다.
```java
private int getUserInput() {
    String input = scanner.nextLine().trim();
    try {
        return Integer.parseInt(input);
    } catch (NumberFormatException e) {
        System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
        return -1; // 잘못된 입력을 나타내는 값 반환
    }
}
```
이렇게 하면 숫자가 아닌 입력이 들어와도 프로그램이 종료되지 않고 다시 입력을 받을 수 있도록 개선되었습니다.

#### 결말
입력 오류 발생 시 사용자에게 명확한 메시지를 제공하고, 올바른 입력을 유도하는 방식으로 개선하여 사용자 경험을 향상시켰습니다.

---

### 문제 2: 존재하지 않는 메뉴 번호 입력 시 프로그램이 멈추는 문제

#### 배경
사용자가 존재하지 않는 메뉴 번호(예: 99)를 입력하면 아무 반응이 없거나 프로그램이 비정상적으로 동작하는 문제가 있었습니다.

#### 발단
메뉴 선택 시 `menuChoice` 값이 리스트 범위를 벗어나는 경우가 발생하여 `IndexOutOfBoundsException`이 발생하는 것을 발견했습니다.

#### 전개
사용자가 메뉴에 없는 번호를 입력했을 때도 프로그램이 종료되지 않고 다시 입력할 수 있도록 개선해야 했습니다.

#### 위기
`menuList.get(menuChoice - 1)`을 호출할 때 `menuChoice`가 리스트 범위를 벗어나면 예외가 발생하여 프로그램이 종료되었습니다.

#### 절정
사용자 입력을 검증하는 로직을 추가하여 유효한 범위 내의 숫자인지 확인 후, 잘못된 입력이면 다시 입력을 요청하도록 수정했습니다.
```java
if (menuChoice > 0 && menuChoice <= menuList.size()) {
    MenuItem chosenMenu = menuList.get(menuChoice - 1);
    System.out.println("-> 선택한 메뉴: " + chosenMenu.getName());
    System.out.println("가격: W " + chosenMenu.getPrice() + " | 설명: " + chosenMenu.getDescription());
} else {
    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
}
```

#### 결말
유효하지 않은 메뉴 번호 입력 시 사용자에게 명확한 안내 메시지를 제공하고 다시 입력을 받을 수 있도록 하여 안정적인 프로그램 실행을 보장했습니다.

---

### 문제 3: 숫자가 아닌 값을 입력하면 예외 발생 (`NumberFormatException`)

#### 배경
사용자가 메뉴를 선택할 때 숫자가 아닌 값을 입력하면 `NumberFormatException`이 발생하여 프로그램이 멈추는 문제가 있었습니다.

#### 발단
사용자가 `abc`, `!@#`, `1.5` 등의 숫자가 아닌 값을 입력할 경우, `Integer.parseInt(input)`에서 예외가 발생하여 프로그램이 중단되었습니다.

#### 전개
`try-catch` 구문을 사용하여 예외를 처리하고, 오류 메시지를 출력한 후 다시 입력을 받을 수 있도록 수정하였습니다.

#### 절정
`try-catch`를 사용하여 숫자가 아닌 값이 입력될 경우 적절한 메시지를 출력하고, 반복문을 계속 진행하도록 수정했습니다.
```java
try {
    choice = Integer.parseInt(input);
} catch (NumberFormatException e) {
    System.out.println("\n숫자가 아닌 값이 입력되었습니다.");
    System.out.println("정확한 메뉴 번호 입력 부탁드립니다. 다시 입력해주세요");
    continue;
}
```

#### 결말
숫자가 아닌 값이 입력되더라도 프로그램이 종료되지 않고, 사용자에게 다시 입력할 기회를 제공하는 방식으로 안정성을 높였습니다.

