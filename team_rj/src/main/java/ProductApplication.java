public class ProductApplication {
    public static void main(String[] args) {
        UserAction ua = new UserAction();
        ua.printGreeting();
        ua.printUserMenu();
        System.out.println("User input: " + ua.getUserInput());
    }
}
