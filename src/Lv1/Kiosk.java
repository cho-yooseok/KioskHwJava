package Lv1;

import java.util.Scanner;

public class Kiosk {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            printMenu();
            String input = scanner.nextLine().trim();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\n숫자가 아닌 값이 입력되었습니다.");
                System.out.println("정확한 메뉴 번호 입력 부탁드립니다. 다시 입력해주세요");
                continue;
            }

            switch (choice) {
                case 1 -> System.out.println("-> ShackBurger를 선택하셨습니다!");
                case 2 -> System.out.println("-> SmokeShack을 선택하셨습니다!");
                case 3 -> System.out.println("-> Cheeseburger를 선택하셨습니다!");
                case 4 -> System.out.println("-> Hamburger를 선택하셨습니다!");
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                }
                default -> {
                    System.out.println("\n잘못된 입력입니다. 0~4 사이의 숫자를 입력해주세요.");
                    continue;
                }
            }

            if (choice == 0) break;
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n[ SHAKESHACK MENU ]");
        System.out.println("1. ShackBurger   | W 6.9  | 토마토, 양상추, 쉑소스가 토핑된 치즈버거");
        System.out.println("2. SmokeShack    | W 8.9  | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
        System.out.println("3. Cheeseburger  | W 6.9  | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
        System.out.println("4. Hamburger     | W 5.4  | 비프패티를 기반으로 야채가 들어간 기본버거");
        System.out.println("0. 종료          | 프로그램을 종료합니다.");
        System.out.print("메뉴 번호를 입력하세요: ");
    }
}