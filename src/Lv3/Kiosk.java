package Lv3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 메뉴 아이템 클래스 (메뉴의 정보가 담긴 클래스)
class MenuItem {
    private String name;  // 메뉴 이름
    private double price; // 메뉴 가격
    private String description; // 메뉴 설명

    // 생성자: 메뉴 이름, 가격, 설명을 설정함
    public MenuItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // 메뉴 정보를 출력하는 메서드 (메뉴 번호도 함께 출력)
    public void printMenuInfo(int index) {
        System.out.println(index + ". " + name + " | W " + price + " | " + description);
    }

    // 메뉴 이름을 가져오는 메서드
    public String getName() {
        return name;
    }

    // 메뉴 가격을 가져오는 메서드
    public double getPrice() {
        return price;
    }

    // 메뉴 설명을 가져오는 메서드
    public String getDescription() {
        return description;
    }
}

// 키오스크 클래스 (전체 프로그램의 흐름을 관리)
public class Kiosk {
    private List<MenuItem> menuList; // 메뉴 리스트를 저장하는 변수 //MenuItem을 관리하는 리스트가 필드로 존재
    private Scanner scanner; // 사용자 입력을 받는 도구

    // 생성자: 메뉴 리스트와 스캐너를 설정 //Kiosk 생성자 , menuItems 값을 넘겨 받아 초기화
    public Kiosk(List<MenuItem> menuList) {
        this.menuList = menuList;   //넘겨받은 값을 필드에 할당
        this.scanner = new Scanner(System.in);
    }

    // 키오스크 프로그램을 시작하는 메서드
    public void start() {   //입력과 반복문 로직 start 함수에서 관리
        // 사용자가 0을 입력할 때까지 반복
        while (true) {
            displayMenu(); // 메뉴를 보여줌

            int userChoice = getUserInput(); // 사용자가 입력한 값을 userChoice라는 변수에 저장

            // 0을 입력하면 프로그램을 종료
            if (userChoice == 0) {
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문을 끝냄
            }
            // 사용자가 선택한 번호가 유효하면 해당 메뉴를 보여줌
            else if (userChoice > 0 && userChoice <= menuList.size()) {
                showSelectedMenu(userChoice);
            }
            // 유효하지 않은 번호를 입력하면 오류 메시지를 보여줌
            else {
                System.out.println("정확한 메뉴 번호 입력 부탁드립니다. 다시 입력해주세요.");
            }
        }
        scanner.close(); // 더 이상 입력을 받지 않으므로 스캐너를 닫음
    }

    // 메뉴를 화면에 보여주는 메서드
    private void displayMenu() {
        System.out.println("\n[ SHAKESHACK MENU ]");
        // 메뉴 리스트에 있는 모든 메뉴를 하나씩 출력
        for (int i = 0; i < menuList.size(); i++) {
            menuList.get(i).printMenuInfo(i + 1); // 메뉴 번호를 함께 출력
        }
        System.out.println("0. 종료 | 프로그램을 종료합니다.");
        System.out.print("메뉴 번호를 입력하세요: "); // 사용자에게 입력을 요청
    }

    // 사용자 입력을 받아 처리하는 메서드
    private int getUserInput() {
        String input = scanner.nextLine().trim(); // 사용자 입력을 받아 공백을 제거해요
        try {
            return Integer.parseInt(input); // 입력값을 숫자로 변환
        } catch (NumberFormatException e) {
            // 숫자가 아닌 값을 입력했을 때 오류 메시지를 보여줌
            System.out.println("숫자가 아닌 값이 입력되었습니다.");
            return -1; // 잘못된 입력을 나타내는 값으로 -1을 반환 //프로그래밍오류처리하지 못하는 상황을 방지 잘못된값으로 계속 실행안되게하기위해
        }
    }

    // 사용자가 선택한 메뉴를 보여주는 메서드
    private void showSelectedMenu(int menuIndex) {
        MenuItem selectedMenu = menuList.get(menuIndex - 1); // 선택한 메뉴를 가져옴
        System.out.println("-> 선택한 메뉴: " + selectedMenu.getName());
        System.out.println("가격: W " + selectedMenu.getPrice() + " | 설명: " + selectedMenu.getDescription());
    }

    // 프로그램 시작점 (메인 메서드)
    public static void main(String[] args) {
        // MenuItem 리스트를 준비  , 메뉴 항목들을 추가
        List<MenuItem> menuList = new ArrayList<>();
        menuList.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuList.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuList.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuList.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        // Kiosk 객체를 만들고 키오스크 프로그램을 시작  //Kiosk 객체를 생성할 때 menuItems 리스트를 넘겨줌
        Kiosk kiosk = new Kiosk(menuList);    //이때 menuList 라는 값을 생성자에 전달해서 Kiosk객체가 생성될때 메뉴목록을 초기화
        kiosk.start(); // 프로그램을 실행
    }
}
