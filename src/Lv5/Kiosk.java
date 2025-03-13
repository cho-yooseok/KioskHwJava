package Lv5;

import java.util.ArrayList; // 목록을 저장할 수 있는 ArrayList 기능을 가져옴
import java.util.List; // 목록 관련 기능을 가져옴
import java.util.Scanner; // 사용자 입력을 받을 수 있는 Scanner 기능을 가져옴

// 메뉴 아이템 클래스 (각 개별 메뉴 정보를 저장하는 클래스)
// 예: "ShackBurger", 가격 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"와 같은 정보를 담습니다.
class MenuItem {
    // 메뉴 이름 (예: "ShackBurger", "Coca Cola" 등)
    private String name;        // 메뉴 이름
    // 메뉴 가격 (예: 6.9, 2.5 등)
    private double price;       // 메뉴 가격
    private String description; // 메뉴 설명 (예: "토마토, 양상추, 쉑소스가 토핑된 치즈버거")

    // 생성자: 새로운 메뉴 아이템을 생성할 때 사용
    // 생성자란 객체가 처음 만들어질 때 실행되는 특별한 메서드입니다.
    public MenuItem(String name, double price, String description) {
        this.name = name; // this.name은 클래스의 변수, name은 매개변수(입력값)
        this.price = price; // 입력받은 가격 정보를 저장
        this.description = description; // 입력받은 설명 정보를 저장
    }

    // 메뉴 정보를 출력하는 메서드
    // index는 메뉴 번호를 의미합니다 (1, 2, 3, ...)
    public void printMenuInfo(int index) {
        // 콘솔에 메뉴 번호, 이름, 가격, 설명을 출력합니다.
        System.out.println(index + ". " + name + " | W " + price + " | " + description);
    }

    // Getter 메서드들 (각 변수에 접근할 수 있도록 제공)
    // private으로 선언된 변수는 외부에서 직접 접근할 수 없어, 이 메서드들을 통해 값을 가져옵니다.

    // 메뉴 이름을 반환하는 메서드
    public String getName() {
        return name;
    }

    // 메뉴 가격을 반환하는 메서드
    public double getPrice() {
        return price;
    }

    // 메뉴 설명을 반환하는 메서드
    public String getDescription() {
        return description;
    }
}

// 카테고리별 메뉴를 관리하는 클래스
// 예: "Burgers" 카테고리에는 여러 버거 메뉴들이 포함됩니다.
class Menu {
    private String categoryName;     // 메뉴 카테고리 이름 (예: "Burgers", "Drinks", "Desserts" 등)
    private List<MenuItem> menuItems; // 해당 카테고리에 속한 메뉴 아이템들의 목록 (ArrayList로 구현)

    // 생성자: 새로운 카테고리를 생성할 때 사용
    public Menu(String categoryName) {
        this.categoryName = categoryName; // 카테고리 이름 설정
        this.menuItems = new ArrayList<>(); // 빈 메뉴 목록 생성 (나중에 메뉴들을 추가할 준비)
    }

    // 새로운 메뉴 아이템을 카테고리에 추가하는 메서드
    // 예: 버거 카테고리에 "ShackBurger" 메뉴 추가
    public void addMenuItem(MenuItem item) {
        menuItems.add(item); // 메뉴 목록에 새 메뉴 추가
    }

    // 해당 카테고리의 메뉴 리스트를 출력하는 메서드
    public void displayMenu() {
        // 카테고리 이름을 대문자로 출력 (예: "BURGERS MENU")
        System.out.println("\n[ " + categoryName.toUpperCase() + " MENU ]");

        // 모든 메뉴 아이템을 번호와 함께 출력
        for (int i = 0; i < menuItems.size(); i++) {
            menuItems.get(i).printMenuInfo(i + 1); // i는 0부터 시작하므로 +1하여 메뉴 번호는 1부터 시작
        }

        System.out.println("0. 뒤로가기"); // 0번을 선택하면 메인 메뉴로 돌아감
        System.out.print("어떠한 메뉴를 고르시겠습니까? 메뉴번호를 눌러주세요: "); // 사용자 입력 안내 메시지
    }

    // Getter 메서드들

    // 카테고리 이름을 반환하는 메서드
    public String getCategoryName() {
        return categoryName;
    }

    // 메뉴 아이템 목록 전체를 반환하는 메서드
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}

// 키오스크 클래스 (메인 프로그램의 흐름을 관리하는 클래스)
// 프로그램의 중심 클래스로, 모든 메뉴를 관리하고 사용자 입력을 처리합니다.
public class Kiosk {
    private List<Menu> menus; // 전체 메뉴 카테고리 목록 (버거, 음료, 디저트 등)
    private Scanner scanner;  // 사용자 입력을 받기 위한 Scanner 객체

    // 생성자: 키오스크를 초기화하고 메뉴를 설정함
    public Kiosk() {
        this.menus = new ArrayList<>(); // 메뉴 카테고리 목록 초기화
        this.scanner = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner 생성
        setupMenus(); // 메뉴 초기화 메서드 호출 (메뉴 데이터 설정)
    }

    // 초기 메뉴 데이터를 설정하는 메서드
    // 프로그램이 시작될 때 기본 메뉴들을 등록합니다.
    private void setupMenus() {
        // 버거 카테고리 생성 및 추가
        Menu burgerMenu = new Menu("Burgers"); // "Burgers" 카테고리 생성
        // 버거 카테고리에 4가지 메뉴 아이템 추가
        burgerMenu.addMenuItem(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgerMenu.addMenuItem(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgerMenu.addMenuItem(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgerMenu.addMenuItem(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));
        menus.add(burgerMenu); // 버거 카테고리를 전체 메뉴 목록에 추가

        // 음료 카테고리 생성 및 추가
        Menu drinkMenu = new Menu("Drinks"); // "Drinks" 카테고리 생성
        // 음료 카테고리에 2가지 메뉴 아이템 추가
        drinkMenu.addMenuItem(new MenuItem("Coca Cola", 2.5, "시원한 코카콜라"));
        drinkMenu.addMenuItem(new MenuItem("Sprite", 2.5, "청량한 스프라이트"));
        menus.add(drinkMenu); // 음료 카테고리를 전체 메뉴 목록에 추가

        // 디저트 카테고리 생성 및 추가
        Menu dessertMenu = new Menu("Desserts"); // "Desserts" 카테고리 생성
        // 디저트 카테고리에 2가지 메뉴 아이템 추가
        dessertMenu.addMenuItem(new MenuItem("Chocolate Cake", 4.5, "진한 초콜릿 케이크"));
        dessertMenu.addMenuItem(new MenuItem("MintChoco Cookie", 5.5, "달콤시원한 민트초코 쿠키"));
        menus.add(dessertMenu); // 디저트 카테고리를 전체 메뉴 목록에 추가
    }

    // 프로그램 시작 메서드
    // 키오스크 프로그램의 메인 루프(반복)를 관리합니다.
    public void start() {
        while (true) { // 무한 반복 (사용자가 종료할 때까지)
            displayMainMenu(); // 메인 메뉴 출력 (카테고리 목록)
            int categoryChoice = getUserInput(); // 사용자 입력 받기 (카테고리 번호)

            if (categoryChoice == 0) { // 0번을 선택한 경우 (종료 조건)
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 종료 (프로그램 종료)
            } else if (categoryChoice > 0 && categoryChoice <= menus.size()) {
                // 유효한 카테고리 번호를 선택한 경우 해당 카테고리의 메뉴 표시
                // menus 목록은 0부터 시작하므로 categoryChoice - 1을 인덱스로 사용
                showCategoryMenu(categoryChoice - 1);
            } else {
                // 잘못된 번호를 입력한 경우 오류 메시지 출력
                System.out.println("정확한 메뉴 번호 입력 부탁드립니다. 다시 입력해주세요.");
            }
        }
        scanner.close(); // 프로그램 종료 시 Scanner 닫기 (리소스 정리)
    }

    // 메인 메뉴 출력 메서드
    // 프로그램의 주요 카테고리 목록을 보여줍니다.
    private void displayMainMenu() {
        System.out.println("\n[ MAIN MENU ]"); // 메인 메뉴 제목 출력
        // 모든 카테고리를 번호와 함께 출력
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getCategoryName());
        }
        System.out.println("0. 종료"); // 0번은 프로그램 종료 옵션
        System.out.print("메뉴 번호를 입력하세요: "); // 사용자 입력 안내 메시지
    }

    // 선택한 카테고리의 메뉴를 보여주는 메서드
    // 사용자가 선택한 카테고리(버거, 음료 등)의 세부 메뉴 목록을 표시합니다.
    private void showCategoryMenu(int index) {
        Menu selectedMenu = menus.get(index); // 선택한 카테고리 가져오기
        while (true) { // 사용자가 뒤로가기를 선택할 때까지 반복
            selectedMenu.displayMenu(); // 선택한 카테고리의 메뉴 목록 출력
            int menuChoice = getUserInput(); // 사용자 입력 받기 (메뉴 번호)

            if (menuChoice == 0) return; // 0번(뒤로가기)을 선택한 경우 메인 메뉴로 돌아감
            else if (menuChoice > 0 && menuChoice <= selectedMenu.getMenuItems().size()) {
                // 유효한 메뉴 번호를 선택한 경우 해당 메뉴 정보 출력
                MenuItem item = selectedMenu.getMenuItems().get(menuChoice - 1);
                System.out.println("선택한 메뉴: " + item.getName() + " | W " + item.getPrice() + " | " + item.getDescription());
            } else {
                // 잘못된 번호를 입력한 경우 오류 메시지 출력
                System.out.println("정확한 메뉴 번호 입력 부탁드립니다. 다시 입력해주세요.");
            }
        }
    }

    // 사용자 입력을 안전하게 처리하는 메서드
    // 사용자가 숫자가 아닌 값을 입력했을 때 발생할 수 있는 오류를 처리합니다.
    private int getUserInput() {
        String input = scanner.nextLine().trim(); // 사용자 입력 받기 (공백 제거)
        try {
            return Integer.parseInt(input); // 입력값을 정수로 변환
        } catch (NumberFormatException e) {
            // 입력값이 숫자가 아닌 경우 오류 메시지 출력 후 -1 반환
            // -1은 유효하지 않은 메뉴 번호이므로 다시 입력받게 됨
            System.out.println("숫자가 아닌 값이 입력되었습니다.");
            return -1;
        }
    }

    // 프로그램 실행을 위한 main 메서드
    // 자바 프로그램의 시작점입니다.
    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk(); // 키오스크 객체 생성
        kiosk.start(); // 키오스크 프로그램 시작
    }
}