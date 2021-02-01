package lv.javaguru.app.core.common;

import lv.javaguru.app.core.domain.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class BaseFunc {
	public static void printLineSeparator () {
		System.out.println(multiplyChar(60, '='));
	}


	public static String multiplyChar (int count, char ch) {
		return "".concat(new String(new char[count]).replaceAll("", String.valueOf(ch)));
	}


	public static void printHeader (String header) {
		printHeader(header, null);
	}


	public static void printHeader (String header, String str) {
		if (str == null)
			str = "";

		int spaceCount = 60 - header.length() - str.length();

		System.out.println(header + multiplyChar(spaceCount, ' ') + str);

		printLineSeparator();
	}

	public static Long getMenuNumberFromUserAsLong () {
		return (long) getMenuNumberFromUser();
	}

	public static int getMenuNumberFromUser () {
		Scanner scanner = new Scanner(System.in);

		String input = scanner.nextLine();
		input = input.replaceAll("[^\\d]", "");
		if (!input.trim().isEmpty())
			try {
				return Integer.parseInt(input);
			}
			catch (Exception exception) {
				System.out.println("Wrong input!");
			}
		return -1;
	}

	public static Date getDateInFormat (String date, String format) {
		try {
			return new SimpleDateFormat(format).parse(date);
		}
		catch (ParseException e) {
			return null;
		}
	}


	public static String capitalize (String str) {
		if (Character.isLowerCase(str.charAt(0)) && str.length() > 1)
			str = str.substring(0, 1).toUpperCase() + str.substring(1);
		else
			str = str.toUpperCase();

		return str;
	}
}
