package teacher.applications.book_library.ui;

import java.util.Scanner;

import teacher.applications.book_library.services.DeleteBookService;

public class DeleteBookUIAction implements UIAction {

	private final DeleteBookService deleteBookService;

	public DeleteBookUIAction(DeleteBookService deleteBookService) {
		this.deleteBookService = deleteBookService;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter book title: ");
		String bookTitle = scanner.nextLine();
		System.out.println("Enter book author: ");
		String bookAuthor = scanner.nextLine();
		deleteBookService.deleteBook(bookTitle, bookAuthor);
		System.out.println("Your book was removed from list.");
	}
}
