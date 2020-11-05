package teacher.applications.book_library;

import java.util.Scanner;

import teacher.applications.book_library.database.Database;
import teacher.applications.book_library.database.InMemoryDatabase;
import teacher.applications.book_library.services.AddBookService;
import teacher.applications.book_library.services.DeleteBookService;
import teacher.applications.book_library.services.GetAllBooksService;
import teacher.applications.book_library.ui.AddBookUIAction;
import teacher.applications.book_library.ui.DeleteBookUIAction;
import teacher.applications.book_library.ui.ExitProgramUIAction;
import teacher.applications.book_library.ui.GetAllBooksUIAction;

public class BookListApplication {

	public static void main(String[] args) {
		Database database = new InMemoryDatabase();
		AddBookService addBookService = new AddBookService(database);
		DeleteBookService deleteBookService = new DeleteBookService(database);
		GetAllBooksService getAllBooksService = new GetAllBooksService(database);
		AddBookUIAction addBookUIAction = new AddBookUIAction(addBookService);
		DeleteBookUIAction deleteBookUIAction = new DeleteBookUIAction(deleteBookService);
		GetAllBooksUIAction getAllBooksUIAction = new GetAllBooksUIAction(getAllBooksService);
		ExitProgramUIAction exitProgramUIAction = new ExitProgramUIAction();

		while (true) {
			showProgramMenu();
			int userChoice = getUserChoice();
			switch (userChoice) {
				case 1: {
					addBookUIAction.execute();
					break;
				}
				case 2: {
					deleteBookUIAction.execute();
					break;
				}
				case 3: {
					getAllBooksUIAction.execute();
					break;
				}
				case 4: {
					exitProgramUIAction.execute();
				}
			}
			System.out.println("");
		}

	}

	private static int getUserChoice() {
		System.out.println("Enter menu item number to execute:");
		Scanner scanner = new Scanner(System.in);
		return Integer.parseInt(scanner.nextLine());
	}

	private static void showProgramMenu() {
		System.out.println("Program menu:");
		System.out.println("1. Add book to list");
		System.out.println("2. Delete book from list");
		System.out.println("3. Show all books in the list");
		System.out.println("4. Exit");

		System.out.println("");
	}

}
