package Lv2;

import java.util.ArrayList; // 리스트(ArrayList) 기능을 사용하기 위해 import
import java.util.List; // 리스트(List) 인터페이스 사용
import java.util.Scanner; // 사용자 입력을 받기 위해 Scanner 사용

// 개별 메뉴 항목을 관리하는 클래스 (햄버거 한 개의 정보를 저장)
class MenuItem {
    private String name;    // 햄버거 이름
    private double price;   // 가격
    private String description; // 메뉴 설명

    // 생성자 (메뉴 아이템을 생성할 때 기본 정보를 설정)
    public MenuItem(String name, double price, String description) {
        this.name = name; // 전달받은 이름을 설정
        this.price = price; // 전달받은 가격을 설정
        this.description = description; // 전달받은 설명을 설정
    }

    // 메뉴 정보를 출력하는 메서드
    public void printInfo(int index) {
        System.out.println(index + ". " + name + " | W " + price + " | " + description);
    }

    // 메뉴 이름을 반환하는 메서드
    public String getName() {
        return name;
    }
}

// 키오스크(무인 주문기) 클래스 (메뉴 출력 및 사용자 입력 처리)
public class Kiosk {
    private List<MenuItem> menuItems; // 여러 개의 메뉴를 저장하는 리스트
    private Scanner scanner; // 사용자 입력을 받을 Scanner 객체

    // 생성자 (키오스크 객체를 생성할 때 실행됨)
    public Kiosk() {
        menuItems = new ArrayList<>(); // 메뉴 리스트를 생성
        scanner = new Scanner(System.in); // 사용자 입력을 받을 Scanner 생성
        initializeMenu(); // 메뉴 추가 메서드 실행
    }

    // 메뉴 데이터를 추가하는 메서드
    private void initializeMenu() {
        // 리스트에 여러 개의 메뉴를 추가
        menuItems.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));
    }

    // 키오스크 실행 메서드 (메인 프로그램 로직)
    public void run() {
        while (true) { // 사용자가 종료할 때까지 반복 실행
            printMenu(); // 메뉴를 화면에 출력
            int choice = getUserInput(); // 사용자 입력을 받아서 choice 변수에 저장

            if (choice == 0) { // 0을 입력하면 프로그램 종료
                System.out.println("프로그램을 종료합니다.");
                break; // while 반복문 종료
            } else if (choice >= 1 && choice <= menuItems.size()) {
                // 사용자가 1~4 사이의 숫자를 입력하면 해당 메뉴 선택
                System.out.println("-> " + menuItems.get(choice - 1).getName() + "를 선택하셨습니다!");
            } else {
                // 0~4가 아닌 숫자를 입력한 경우
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
        scanner.close(); // Scanner 닫기 (메모리 절약)
    }

    // 메뉴를 화면에 출력하는 메서드
    private void printMenu() {
        System.out.println("\n[ SHAKESHACK MENU ]"); // 줄 바꿈 후 메뉴 제목 출력
        for (int i = 0; i < menuItems.size(); i++) {
            // 메뉴 리스트의 각 항목을 출력 (1번부터 번호 부여)
            menuItems.get(i).printInfo(i + 1);
        }
        System.out.println("0. 종료      | 프로그램을 종료합니다.");
        System.out.print("메뉴 번호를 입력하세요: "); // 사용자 입력 요청
    }

    // 사용자 입력을 받아 정수로 변환하는 메서드
    private int getUserInput() {
        String input = scanner.nextLine().trim(); // 입력값의 앞뒤 공백 제거
        try {
            return Integer.parseInt(input); // 입력값을 정수로 변환하여 반환
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요."); // 정수가 아닌 값이 입력되면 오류 메시지 출력
            return -1; // 유효하지 않은 값 반환
        }
    }

    // 프로그램 실행을 위한 main 메서드
    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk(); // 키오스크 객체 생성
        kiosk.run(); // 키오스크 실행
    }
}



/*전체적인 코드 동작 원리 요약
1. 프로그램이 실행되면 Kiosk 클래스의 생성자가 실행됨
2. initializeMenu() 메서드가 호출되어 메뉴 리스트가 초기화됨
3. run() 메서드가 실행되면서 메뉴가 출력됨
4. 사용자가 메뉴 번호를 입력하면 해당 메뉴가 선택됨
5. 0을 입력하면 프로그램이 종료됨
6. scanner.close()를 호출하여 메모리를 절약하고 프로그램을 끝냄

Lv1 -> Lv2 ref 사항
객체 분리 →MenuItem 클래스를 생성하여 햄버거 정보를 객체로 관리
리스트 활용 →List<MenuItem>을 사용하여 메뉴 데이터 저장
역할 분리 →printMenu(), getUserInput() 등의 메서드로 기능을 분리
입력 예외 처리 →try-catch로 숫자가 아닌 입력 방지

*/