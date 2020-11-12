package team_VK.application.ui;

import team_VK.application.Book;
import team_VK.application.services.GetBooksListService;

public class GetBooksListUIAction implements UIActions {

    private final GetBooksListService getBooksListService;

    public GetBooksListUIAction(GetBooksListService etBooksListService) {
        this.getBooksListService = etBooksListService;
    }

    @Override
    public void execute() {

        System.out.println("Book list:");

        for (Book book : getBooksListService.getBooksList()) {
            System.out.println(book);
        }
        System.out.println("End of list");
        System.out.println();

    }
}
