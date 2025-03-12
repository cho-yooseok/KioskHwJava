package Lv5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 메뉴 아이템 클래스 (각 개별 메뉴 정보를 저장하는 클래스)
class MenuItem {
    // 메뉴 이름
    private String name;        // 메뉴 이름
    // 메뉴 가격
    private double price;       // 메뉴 가격
    private String description; // 메뉴 설명

    // 생성자: 새로운 메뉴 아이템을 생성할 때 사용
    public MenuItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // 메뉴 정보를 출력하는 메서드
    public void printMenuInfo(int index) {
        System.out.println(index + ". " + name + " | W " + price + " | " + description);
    }

    // Getter 메서드들 (각 변수에 접근할 수 있도록 제공)
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
}

// 카테고리별 메뉴를 관리하는 클래스
class Menu {
    private String categoryName;     // 메뉴 카테고리 이름 (예: Burgers, Drinks 등)
    private List<MenuItem> menuItems; // 해당 카테고리에 속한 메뉴 리스트

    // 생성자: 새로운 카테고리를 생성할 때 사용
    public Menu(String categoryName) {
        this.categoryName = categoryName;
        this.menuItems = new ArrayList<>();
    }

    // 새로운 메뉴 아이템을 카테고리에 추가하는 메서드
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    // 해당 카테고리의 메뉴 리스트를 출력하는 메서드
    public void displayMenu() {
        System.out.println("\n[ " + categoryName.toUpperCase() + " MENU ]");
        for (int i = 0; i < menuItems.size(); i++) {
            menuItems.get(i).printMenuInfo(i + 1);
        }
        System.out.println("0. 뒤로가기");
        System.out.print("어떠한 메뉴를 고르시겠습니까? 메뉴번호를 눌러주세요: ");
    }

    // Getter 메서드들
    public String getCategoryName() { return categoryName; }
    public List<MenuItem> getMenuItems() { return menuItems; }
}

// 키오스크 클래스 (메인 프로그램의 흐름을 관리하는 클래스)
public class Kiosk {
    private List<Menu> menus; // 전체 메뉴 리스트 (카테고리별 저장)
    private Scanner scanner;  // 사용자 입력을 받기 위한 Scanner 객체

    // 생성자: 키오스크를 초기화하고 메뉴를 설정함
    public Kiosk() {
        this.menus = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        setupMenus(); // 메뉴 초기화 메서드 호출
    }

    // 초기 메뉴 데이터를 설정하는 메서드
    private void setupMenus() {
        // 버거 카테고리 생성 및 추가
        Menu burgerMenu = new Menu("Burgers");
        burgerMenu.addMenuItem(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgerMenu.addMenuItem(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgerMenu.addMenuItem(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgerMenu.addMenuItem(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));
        menus.add(burgerMenu);

        // 음료 카테고리 생성 및 추가
        Menu drinkMenu = new Menu("Drinks");
        drinkMenu.addMenuItem(new MenuItem("Coca Cola", 2.5, "시원한 코카콜라"));
        drinkMenu.addMenuItem(new MenuItem("Sprite", 2.5, "청량한 스프라이트"));
        menus.add(drinkMenu);

        // 디저트 카테고리 생성 및 추가
        Menu dessertMenu = new Menu("Desserts");
        dessertMenu.addMenuItem(new MenuItem("Chocolate Cake", 4.5, "진한 초콜릿 케이크"));
        dessertMenu.addMenuItem(new MenuItem("MintChoco Cookie", 5.5, "달콤시원한 민트초코 쿠키"));
        menus.add(dessertMenu);
    }

    // 프로그램 시작 메서드
    public void start() {
        while (true) {
            displayMainMenu(); // 메인 메뉴 출력
            int categoryChoice = getUserInput(); // 사용자 입력 받기

            if (categoryChoice == 0) { // 종료 조건
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (categoryChoice > 0 && categoryChoice <= menus.size()) {
                showCategoryMenu(categoryChoice - 1);
            } else {
                System.out.println("정확한 메뉴 번호 입력 부탁드립니다. 다시 입력해주세요.");
            }
        }
        scanner.close(); // 프로그램 종료 시 Scanner 닫기
    }

    // 메인 메뉴 출력 메서드
    private void displayMainMenu() {
        System.out.println("\n[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getCategoryName());
        }
        System.out.println("0. 종료");
        System.out.print("메뉴 번호를 입력하세요: ");
    }

    // 선택한 카테고리의 메뉴를 보여주는 메서드
    private void showCategoryMenu(int index) {
        Menu selectedMenu = menus.get(index);
        while (true) {
            selectedMenu.displayMenu();
            int menuChoice = getUserInput();

            if (menuChoice == 0) return;
            else if (menuChoice > 0 && menuChoice <= selectedMenu.getMenuItems().size()) {
                MenuItem item = selectedMenu.getMenuItems().get(menuChoice - 1);
                System.out.println("선택한 메뉴: " + item.getName() + " | W " + item.getPrice() + " | " + item.getDescription());
            } else {
                System.out.println("정확한 메뉴 번호 입력 부탁드립니다. 다시 입력해주세요.");
            }
        }
    }

    // 사용자 입력을 안전하게 처리하는 메서드
    private int getUserInput() {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("숫자가 아닌 값이 입력되었습니다.");
            return -1;
        }
    }

    // 프로그램 실행을 위한 main 메서드
    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk();
        kiosk.start();
    }
}