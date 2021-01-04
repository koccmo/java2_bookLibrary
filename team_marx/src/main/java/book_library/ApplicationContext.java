package book_library;

import book_library.console_ui.*;
import book_library.core.database.Database;
import book_library.core.database.InMemoryDataBaseImpl;
import book_library.core.services.AddBookService;
import book_library.core.services.GetAllBooksService;
import book_library.core.services.RemoveBookService;
import book_library.core.services.SearchBooksService;
import book_library.core.validators.AddBookRequestValidator;
import book_library.core.validators.RemoveBookRequestValidator;
import book_library.core.validators.SearchBooksRequestValidator;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(Database.class, new InMemoryDataBaseImpl());

        beans.put(AddBookRequestValidator.class, new AddBookRequestValidator(getBean(Database.class)));
        beans.put(RemoveBookRequestValidator.class, new RemoveBookRequestValidator(getBean(Database.class)));
        beans.put(SearchBooksRequestValidator.class, new SearchBooksRequestValidator());

        beans.put(AddBookService.class, new AddBookService(getBean(Database.class), getBean(AddBookRequestValidator.class)));
        beans.put(RemoveBookService.class, new RemoveBookService(getBean(Database.class), getBean(RemoveBookRequestValidator.class)));
        beans.put(GetAllBooksService.class, new GetAllBooksService(getBean(Database.class)));
        beans.put(SearchBooksService.class, new SearchBooksService(getBean(Database.class), getBean(SearchBooksRequestValidator.class)));

        beans.put(AddBookUIAction.class, new AddBookUIAction(getBean(AddBookService.class)));
        beans.put(RemoveBookUIAction.class, new RemoveBookUIAction(getBean(RemoveBookService.class)));
        beans.put(GetAllBooksUIAction.class, new GetAllBooksUIAction(getBean(GetAllBooksService.class)));
        beans.put(ExitUIAction.class, new ExitUIAction());
        beans.put(SearchBooksUIAction.class, new SearchBooksUIAction(getBean(SearchBooksService.class)));
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
