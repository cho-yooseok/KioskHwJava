package Lv1; // 현재 파일이 속한 패키지(폴더) 이름

import java.util.Scanner; // 사용자 입력을 받기 위해 Scanner 클래스를 사용

// 키오스크(무인 주문기) 프로그램
public class Kiosk {
    public static void main(String[] args) {
        // 사용자 입력을 받을 Scanner 객체 생성
        Scanner scanner = new Scanner(System.in);

        // 사용자가 선택한 메뉴 번호를 저장할 변수
        int choice;

        // 사용자가 종료할 때까지 계속 반복
        while (true) {
            // 메뉴를 화면에 출력하는 기능 호출
            printMenu();

            // 사용자의 입력을 문자열로 받음 (입력값 앞뒤 공백 제거)
            String input = scanner.nextLine().trim();

            // 문자열로 받은 입력값을 정수(int)로 변환
            choice = Integer.parseInt(input);

            // 사용자가 입력한 번호에 따라 다른 동작을 수행
            switch (choice) {
                case 1 -> System.out.println("-> ShackBurger를 선택하셨습니다!");
                case 2 -> System.out.println("-> SmokeShack을 선택하셨습니다!");
                case 3 -> System.out.println("-> Cheeseburger를 선택하셨습니다!");
                case 4 -> System.out.println("-> Hamburger를 선택하셨습니다!");

                // 사용자가 0을 입력하면 프로그램 종료
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    break; // 반복문을 빠져나감
                }

                // 사용자가 1~4 또는 0이 아닌 다른 숫자를 입력한 경우
                default -> System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }

            // 사용자가 0을 입력한 경우 반복문을 종료하여 프로그램 종료
            if (choice == 0) break;
        }

        // Scanner를 닫아 메모리를 절약
        scanner.close();
    }

    // 메뉴를 화면에 출력하는 기능 (메서드)
    private static void printMenu() {
        System.out.println("\n[ SHAKESHACK MENU ]"); // 줄 바꿈 후 메뉴 제목 출력
        System.out.println("1. ShackBurger   | W 6.9  | 토마토, 양상추, 쉑소스가 토핑된 치즈버거");
        System.out.println("2. SmokeShack    | W 8.9  | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
        System.out.println("3. Cheeseburger  | W 6.9  | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
        System.out.println("4. Hamburger     | W 5.4  | 비프패티를 기반으로 야채가 들어간 기본버거");
        System.out.println("0. 종료          | 프로그램을 종료합니다.");
        System.out.print("메뉴 번호를 입력하세요: "); // 사용자 입력 요청
    }
}
