package teacher.applications.book_library.ui;

import teacher.applications.book_library.Book;
import teacher.applications.book_library.services.GetAllBooksService;

public class GetAllBooksUIAction implements UIAction {

	private final GetAllBooksService getAllBooksService;

	public GetAllBooksUIAction(GetAllBooksService getAllBooksService) {
		this.getAllBooksService = getAllBooksService;
	}

	@Override
	public void execute() {
		System.out.println("Book list: ");
		for (Book book : getAllBooksService.getAllBooks()) {
			System.out.println(book);
		}
		System.out.println("Book list end.");
	}
}
