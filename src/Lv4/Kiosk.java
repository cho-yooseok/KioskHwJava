package Lv4; // 이 코드가 속한 패키지 이름입니다.

import java.util.ArrayList; // 여러 개의 데이터를 저장할 수 있는 ArrayList 기능을 가져옵니다.
import java.util.List; // 데이터를 목록 형태로 관리할 수 있는 List 기능을 가져옵니다.
import java.util.Scanner; // 사용자로부터 입력을 받을 수 있는 Scanner 기능을 가져옵니다.

// 메뉴 아이템 클래스 (각 개별 메뉴 정보를 저장하는 클래스)
// 이 클래스는 햄버거, 음료수 같은 각 메뉴 하나하나의 정보를 담고 있습니다.
class MenuItem {
    private String name;        // 메뉴 이름 (예: "ShackBurger", "Coca Cola" 등)
    private double price;       // 메뉴 가격 (예: 6.9, 2.5 등)
    private String description; // 메뉴 설명 (예: "토마토, 양상추, 쉑소스가 토핑된 치즈버거")

    // 생성자: 새로운 메뉴 아이템을 생성할 때 사용
    // 메뉴의 이름, 가격, 설명을 입력받아 새로운 메뉴 항목을 만듭니다.
    public MenuItem(String name, double price, String description) {
        this.name = name; // 입력받은 이름을 이 객체의 name 변수에 저장합니다.
        this.price = price; // 입력받은 가격을 이 객체의 price 변수에 저장합니다.
        this.description = description; // 입력받은 설명을 이 객체의 description 변수에 저장합니다.
    }

    // 메뉴 정보를 출력하는 메서드
    // 메뉴 번호, 이름, 가격, 설명을 화면에 보여줍니다.
    public void printMenuInfo(int index) {
        System.out.println(index + ". " + name + " | W " + price + " | " + description);
        // 예: "1. ShackBurger | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거"
    }

    // Getter 메서드들 (각 변수에 접근할 수 있도록 제공)
    // private으로 설정된 변수들의 값을 가져올 수 있게 해주는 메서드들입니다.
    public String getName() { return name; } // 메뉴 이름을 반환합니다.
    public double getPrice() { return price; } // 메뉴 가격을 반환합니다.
    public String getDescription() { return description; } // 메뉴 설명을 반환합니다.
}



// 카테고리별 메뉴를 관리하는 클래스
// 이 클래스는 "버거", "음료", "디저트"와 같은 카테고리와 그 안에 속한 여러 메뉴들을 관리합니다.
class Menu {
    private String categoryName;     // 메뉴 카테고리 이름 (예: "Burgers", "Drinks" 등)
    private List<MenuItem> menuItems; // 해당 카테고리에 속한 메뉴 리스트 (여러 개의 MenuItem을 저장)

    // 생성자: 새로운 카테고리를 생성할 때 사용
    // 카테고리 이름을 입력받아 새로운 카테고리를 만듭니다.
    public Menu(String categoryName) {
        this.categoryName = categoryName; // 입력받은 카테고리 이름을 저장합니다.
        this.menuItems = new ArrayList<>(); // 메뉴 항목들을 저장할 빈 리스트를 생성합니다.
    }

    // 새로운 메뉴 아이템을 카테고리에 추가하는 메서드
    // 예: 버거 카테고리에 "ShackBurger"라는 메뉴를 추가
    public void addMenuItem(MenuItem item) {
        menuItems.add(item); // 입력받은 메뉴 항목을 리스트에 추가합니다.
    }

    // 해당 카테고리의 메뉴 리스트를 출력하는 메서드
    // 카테고리 이름과 그 안에 있는 모든 메뉴를 보여줍니다.
    public void displayMenu() {
        System.out.println("\n[ " + categoryName.toUpperCase() + " MENU ]"); // 카테고리 이름을 대문자로 출력 (예: "BURGERS MENU")
        for (int i = 0; i < menuItems.size(); i++) { // 카테고리 안의 모든 메뉴를 하나씩 반복
            menuItems.get(i).printMenuInfo(i + 1); // 각 메뉴의 정보를 출력 (번호는 1부터 시작)
        }
        System.out.println("0. 뒤로가기"); // 뒤로 가기 옵션 제공
        System.out.print("어떠한 메뉴를 고르시겠습니까? 메뉴번호를 눌러주세요: "); // 사용자에게 선택 요청
    }

    // Getter 메서드들
    // private으로 설정된 변수들의 값을 가져올 수 있게 해주는 메서드들입니다.
    public String getCategoryName() { return categoryName; } // 카테고리 이름을 반환합니다.
    public List<MenuItem> getMenuItems() { return menuItems; } // 카테고리 안의 모든 메뉴 항목을 리스트로 반환합니다.
}

// 키오스크 클래스 (메인 프로그램의 흐름을 관리하는 클래스)
// 이 클래스는 프로그램 전체를 관리하고 사용자와 상호작용합니다.
public class Kiosk {
    private List<Menu> menus; // 전체 메뉴 리스트 (버거, 음료, 디저트 등 모든 카테고리를 저장)
    private Scanner scanner;  // 사용자 입력을 받기 위한 Scanner 객체

    // 생성자: 키오스크를 초기화하고 메뉴를 설정함
    // 프로그램이 시작될 때 필요한 기본 설정을 합니다.
    public Kiosk() {
        this.menus = new ArrayList<>(); // 카테고리들을 저장할 빈 리스트 생성
        this.scanner = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner 생성
        setupMenus(); // 메뉴 초기화 메서드 호출 (기본 메뉴 데이터 설정)
    }

    // 초기 메뉴 데이터를 설정하는 메서드
    // 프로그램에서 사용할 모든 메뉴와 카테고리를 미리 설정합니다.
    private void setupMenus() {
        // 버거 카테고리 생성 및 추가
        Menu burgerMenu = new Menu("Burgers"); // "Burgers" 카테고리 생성
        // 버거 카테고리에 여러 버거 메뉴 추가
        burgerMenu.addMenuItem(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgerMenu.addMenuItem(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgerMenu.addMenuItem(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgerMenu.addMenuItem(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        // 음료 카테고리 생성 및 추가
        Menu drinkMenu = new Menu("Drinks"); // "Drinks" 카테고리 생성
        // 음료 카테고리에 여러 음료 메뉴 추가
        drinkMenu.addMenuItem(new MenuItem("Coca Cola", 2.5, "시원한 코카콜라"));
        drinkMenu.addMenuItem(new MenuItem("Sprite", 2.5, "청량한 스프라이트"));

        // 디저트 카테고리 생성 및 추가
        Menu dessertMenu = new Menu("Desserts"); // "Desserts" 카테고리 생성
        // 디저트 카테고리에 여러 디저트 메뉴 추가
        dessertMenu.addMenuItem(new MenuItem("Chocolate Cake", 4.5, "진한 초콜릿 케이크"));
        dessertMenu.addMenuItem(new MenuItem("MintChoco Cookie", 5.5, "달콤시원한 민트초코 쿠키"));

        // 전체 메뉴 리스트에 카테고리 추가
        // 생성한 모든 카테고리를 menus 리스트에 추가
        menus.add(burgerMenu);
        menus.add(drinkMenu);
        menus.add(dessertMenu);
    }

    // 프로그램 시작 메서드
    // 키오스크 프로그램의 메인 실행 흐름을 담당합니다.
    public void start() {
        while (true) { // 무한 반복 (사용자가 종료할 때까지 계속 실행)
            displayMainMenu(); // 메인 메뉴 출력 (카테고리 목록)
            int categoryChoice = getUserInput(); // 사용자 입력 받기 (선택한 카테고리 번호)

            if (categoryChoice == 0) { // 종료 조건 (0번 선택 시)
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 종료하고 프로그램 종료
            } else if (categoryChoice > 0 && categoryChoice <= menus.size()) { // 유효한 카테고리 번호 선택 시
                showCategoryMenu(categoryChoice - 1); // 선택한 카테고리의 메뉴 보여주기 (인덱스는 0부터 시작하므로 -1)
            } else { // 잘못된 입력 시
                System.out.println("정확한 메뉴 번호 입력 부탁드립니다. 다시 입력해주세요.");
            }
        }
        scanner.close(); // 프로그램 종료 시 Scanner 닫기 (메모리 누수 방지)
    }

    // 메인 메뉴 출력 메서드
    // 모든 카테고리 목록을 보여줍니다.
    private void displayMainMenu() {
        System.out.println("\n[ MAIN MENU ]"); // 메인 메뉴 제목 출력
        for (int i = 0; i < menus.size(); i++) { // 모든 카테고리를 하나씩 반복
            System.out.println((i + 1) + ". " + menus.get(i).getCategoryName()); // 카테고리 번호와 이름 출력
        }
        System.out.println("0. 종료"); // 종료 옵션 제공
        System.out.print("메뉴 번호를 입력하세요: "); // 사용자에게 선택 요청
    }

    // 선택한 카테고리의 메뉴를 보여주는 메서드
    // 사용자가 선택한 카테고리(버거, 음료 등)의 상세 메뉴 목록을 보여줍니다.
    private void showCategoryMenu(int index) {
        Menu selectedMenu = menus.get(index); // 선택한 카테고리 가져오기
        while (true) { // 뒤로가기 선택할 때까지 반복
            selectedMenu.displayMenu(); // 선택한 카테고리의 메뉴 목록 표시
            int menuChoice = getUserInput(); // 사용자가 선택한 메뉴 번호 입력받기

            if (menuChoice == 0) return; // 0번(뒤로가기) 선택 시 메인 메뉴로 돌아감
            else if (menuChoice > 0 && menuChoice <= selectedMenu.getMenuItems().size()) { // 유효한 메뉴 번호 선택 시
                MenuItem item = selectedMenu.getMenuItems().get(menuChoice - 1); // 선택한 메뉴 항목 가져오기
                System.out.println("선택한 메뉴: " + item.getName() + " | W " + item.getPrice() + " | " + item.getDescription());
                // 선택한 메뉴의 정보 출력 (이름, 가격, 설명)
            } else { // 잘못된 입력 시
                System.out.println("정확한 메뉴 번호 입력 부탁드립니다. 다시 입력해주세요.");
            }
        }
    }

    // 사용자 입력을 안전하게 처리하는 메서드
    // 사용자 입력을 받고 숫자로 변환합니다. 잘못된 입력 처리도 함께 수행합니다.
    private int getUserInput() {
        String input = scanner.nextLine().trim(); // 사용자 입력을 받고 앞뒤 공백 제거
        try {
            return Integer.parseInt(input); // 입력값을 정수로 변환 시도
        } catch (NumberFormatException e) { // 숫자가 아닌 입력이 들어왔을 경우
            System.out.println("숫자가 아닌 값이 입력되었습니다.");
            return -1; // 오류 표시로 -1 반환 (유효하지 않은 선택으로 처리됨)
        }
    }

    // 프로그램 실행을 위한 main 메서드
    // 자바 프로그램의 시작점으로, 키오스크 프로그램을 실행합니다.
    public static void main(String[] args) {
        Kiosk kiosk = new Kiosk(); // 키오스크 객체 생성
        kiosk.start(); // 키오스크 프로그램 시작
    }
}