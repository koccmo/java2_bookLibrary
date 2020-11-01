package team_static_startup.application;

import java.util.InputMismatchException;
import java.util.Scanner;

class MenuUI {

    public void menu() {
        System.out.println("\nTo enter menu, press 9.");
        System.out.println("ENTER YOUR ACTION:");
        try {
            boolean quit = false;
            while (!quit) {
                switch (askForMenuAction()) {
                    case 1:
                        System.out.println("added!");
                        break;
                    case 2:
                        System.out.println("removed!");
                        break;
                    case 3:
                        System.out.println("printed!");
                        break;
                    case 9:
                        menuList();
                        break;
                    case 0:
                        quit = true;
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("\nWrong input, please enter an integer.");
            menu();
        }
    }

    private int askForMenuAction() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void menuList() {
        System.out.println("-----------------------");
        System.out.println("Printing out the menu:");
        System.out.println("1. add product");
        System.out.println("2. remove product");
        System.out.println("3. print products list to console");
        System.out.println("9. print out the menu");
        System.out.println("0. end program");
    }

}
