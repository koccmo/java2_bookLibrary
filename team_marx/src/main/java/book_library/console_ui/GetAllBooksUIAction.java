package book_library.console_ui;

import book_library.services.GetAllBooksService;

public class GetAllBooksUIAction implements UIAction {

    GetAllBooksService getAllBooksService;

    public GetAllBooksUIAction(GetAllBooksService getAllBooksService) {
        this.getAllBooksService = getAllBooksService;
    }

    @Override
    public void execute() {
        System.out.println("Book list:");
        System.out.println("***************************************************************************");
        getAllBooksService.execute().forEach(System.out::println);
        System.out.println("***************************************************************************");
        System.out.println("Book list end.");
    }
}
