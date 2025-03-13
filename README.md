# Kiosk 프로젝트

## 개요
본 프로젝트는 Java로 개발된 키오스크 애플리케이션으로, 사용자가 메뉴를 선택하고 주문할 수 있도록 구성되어 있습니다.

## 개발 환경
- **Java 버전**: OpenJDK 17.0.14
- **개발 도구**: IntelliJ IDEA
- **사용된 기술**: Java, OOP (객체 지향 프로그래밍)

## 실행 방법
```sh
javac Kiosk.java
java Kiosk
```

## 프로젝트 구조
```
KioskProject/
│── src/
│   ├── Lv1/
│   │   ├── Kiosk.java
│   ├── Lv2/
│   │   ├── Kiosk.java
│   │   ├── MenuItem.java
│   ├── Lv3/
│   │   ├── Kiosk.java
│   │   ├── MenuItem.java
│   ├── Lv4/
│   │   ├── Kiosk.java
│   │   ├── MenuItem.java
│   │   ├── Menu.java
│   ├── Lv5/
│   │   ├── Kiosk.java
│   │   ├── MenuItem.java
│   │   ├── Menu.java
│── README.md
```

## 예외 처리
각 레벨에서 발생할 수 있는 예외를 방지하기 위해 다양한 예외 처리가 적용되었습니다.

### Lv1
- **NumberFormatException**: 사용자 입력이 숫자가 아닐 경우 예외 처리하여 안내 메시지를 출력함

### Lv2
- **NumberFormatException**: 사용자 입력값이 숫자가 아닐 경우 예외 메시지 출력 후 다시 입력받도록 처리함

### Lv3
- **NumberFormatException**: 잘못된 숫자 입력 시 `-1`을 반환하여 다시 입력받도록 설계함
- **범위를 벗어난 숫자 입력 방지**: 메뉴 번호가 존재하지 않을 경우 적절한 메시지를 출력하고 다시 입력받도록 처리함

### Lv4
- **NumberFormatException**: 숫자가 아닌 값 입력 시 오류 메시지 출력 후 재입력 요구
- **메뉴 번호 유효성 검사**: 존재하지 않는 메뉴 번호 선택 시 예외 처리

### Lv5
- **NumberFormatException**: 숫자가 아닌 값이 입력될 경우 오류 메시지를 출력하고 다시 입력받도록 구현
- **올바른 메뉴 번호 선택 유도**: 사용자가 존재하지 않는 메뉴 번호를 선택하면 경고 메시지를 출력함
- **카테고리 선택 예외 처리**: 존재하지 않는 카테고리를 선택할 경우 재입력을 유도함

## 라이선스
Free