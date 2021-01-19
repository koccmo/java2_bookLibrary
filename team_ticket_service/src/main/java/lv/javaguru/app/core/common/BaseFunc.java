package lv.javaguru.app.core.common;

import lv.javaguru.app.core.domain.User;

import java.util.Scanner;

public class BaseFunc {
    public static void printLineSeparator() {
        System.out.println(multiplyChar(50, '='));
    }


    public static String multiplyChar(int count, char ch) {
        return "".concat(new String(new char[count]).replaceAll("", String.valueOf(ch)));
    }


    public static void printHeader(String header) {
        printHeader(header, null);
    }


    public static void printHeader(String header, User user) {
        String userName;
        if (user != null)
            userName = user.getName();
        else
            userName = "";

        int spaceCount = 50 - header.length() - userName.length();

        System.out.println(header + multiplyChar(spaceCount, ' ') + userName);
    }


    public static int getMenuNumberFromUser() {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        input = input.replaceAll("[^\\d]", "");
        if (!input.trim().isEmpty())
            try {
                return Integer.parseInt(input);
            } catch (Exception exception) {
                System.out.println("Wrong input!");
            }
        return -1;
    }
}
