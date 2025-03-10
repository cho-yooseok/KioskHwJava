package Lv2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// MenuItem 클래스: 각 메뉴 항목을 관리하는 클래스
class MenuItem {
    private String menuName; // 메뉴 이름
    private double menuPrice; // 메뉴 가격
    private String menuDescription; // 메뉴 설명

    // 생성자: 메뉴 이름, 가격, 설명을 설정
    public MenuItem(String menuName, double menuPrice, String menuDescription) {
        this.menuName = menuName; // 메뉴 이름 설정
        this.menuPrice = menuPrice; // 메뉴 가격 설정
        this.menuDescription = menuDescription; // 메뉴 설명 설정
    }

    // 메뉴 정보를 출력하는 메서드 (메뉴 번호도 함께 출력)
    public void printMenuInfo(int menuIndex) {
        System.out.println(menuIndex + ". " + menuName + " | W " + menuPrice + " | " + menuDescription);
    }

    // 메뉴 이름을 반환하는 메서드
    public String getMenuName() {
        return menuName;
    }

    // 메뉴 가격을 반환하는 메서드
    public double getMenuPrice() {
        return menuPrice;
    }

    // 메뉴 설명을 반환하는 메서드
    public String getMenuDescription() {
        return menuDescription;
    }
}






// Kiosk 프로그램 (메인 클래스)
public class Kiosk {
    public static void main(String[] args) {
        // MenuItem 객체들을 넣을 메뉴 리스트를 만들
        List<MenuItem> menuList = new ArrayList<>();

        // 메뉴를 리스트에 추가함
        menuList.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuList.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuList.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuList.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        // 사용자 입력을 받기 위한 스캐너
        Scanner inputScanner = new Scanner(System.in);

        // 사용자가 메뉴를 고를 때까지 계속 반복
        while (true) {
            // 메뉴를 화면에 출력
            displayMenu(menuList);

            // 사용자 입력 받기 (입력 값 앞뒤 공백 제거)
            String userInput = inputScanner.nextLine().trim();

            // 입력한 값이 숫자인지 확인하고 처리
            int menuChoice;
            try {
                menuChoice = Integer.parseInt(userInput); // 숫자로 변환
            } catch (NumberFormatException e) {
                // 숫자가 아니면 잘못된 입력 메시지를 보여줌
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                continue; // 다시 메뉴로 돌아감
            }

            // 사용자가 고른 메뉴에 따라 동작
            if (menuChoice == 0) {
                // 0을 입력하면 프로그램 종료
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 종료
            } else if (menuChoice > 0 && menuChoice <= menuList.size()) {
                // 사용자가 고른 메뉴가 있으면 그 메뉴를 보여줌
                MenuItem chosenMenu = menuList.get(menuChoice - 1); // 사용자가 선택한 메뉴 가져오기
                System.out.println("-> 선택한 메뉴: " + chosenMenu.getMenuName()); // 선택한 메뉴 이름 출력
                System.out.println("가격: W " + chosenMenu.getMenuPrice() + " | 설명: " + chosenMenu.getMenuDescription()); // 가격과 설명 출력
            } else {
                // 잘못된 메뉴 번호를 입력한 경우
                System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }

        // 더 이상 스캐너가 필요 없으니 닫음
        inputScanner.close();
    }

    // 메뉴를 화면에 출력하는 메서드
    private static void displayMenu(List<MenuItem> menuList) {
        System.out.println("\n[ SHAKESHACK MENU ]"); // 메뉴 제목 출력
        // 메뉴를 하나씩 출력
        for (int i = 0; i < menuList.size(); i++) {
            menuList.get(i).printMenuInfo(i + 1); // 메뉴 번호와 함께 메뉴 정보 출력
        }
        System.out.println("0. 종료 | 프로그램을 종료합니다."); // 0번은 프로그램 종료 옵션
        System.out.print("메뉴 번호를 입력하세요: "); // 사용자가 메뉴를 입력할 수 있게 안내
    }
}
