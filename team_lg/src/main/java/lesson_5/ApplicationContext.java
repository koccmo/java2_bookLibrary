package lesson_5;

import lesson_5.core.database.ElectronicLibrary;
import lesson_5.core.database.ElectronicLibraryImpl;
import lesson_5.core.services.*;
import lesson_5.core.services.validators.*;
import lesson_5.ui.*;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private final Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(ElectronicLibrary.class, new ElectronicLibraryImpl());

        beans.put(AddBookValidator.class, new AddBookValidator());
        beans.put(DeleteBookByTitleValidator.class, new DeleteBookByTitleValidator());
        beans.put(DeleteBookByAuthorValidator.class, new DeleteBookByAuthorValidator());
        beans.put(DeleteBookByIdValidator.class, new DeleteBookByIdValidator());
        beans.put(GetAllBooksValidator.class, new GetAllBooksValidator());
        beans.put(FindBooksRequestValidator.class, new FindBooksRequestValidator());
        beans.put(FindBookByIdValidator.class, new FindBookByIdValidator());

        beans.put(AddBookService.class, new AddBookService(getBean(ElectronicLibrary.class),
                getBean(AddBookValidator.class)));

        beans.put(DeleteBookByTitleService.class, new DeleteBookByTitleService(getBean(ElectronicLibrary.class),
                getBean(DeleteBookByTitleValidator.class)));

        beans.put(DeleteBookByAuthorService.class, new DeleteBookByAuthorService(getBean(ElectronicLibrary.class),
                getBean(DeleteBookByAuthorValidator.class)));

        beans.put(DeleteBookByIdService.class, new DeleteBookByIdService(getBean(ElectronicLibrary.class),
                getBean(DeleteBookByIdValidator.class)));

        beans.put(GetAllBooksService.class, new GetAllBooksService(getBean(ElectronicLibrary.class),
                getBean(GetAllBooksValidator.class)));

        beans.put(FindBooksService.class, new FindBooksService(getBean(ElectronicLibrary.class),
                getBean(FindBooksRequestValidator.class)));

        beans.put(FindBookByIdService.class, new FindBookByIdService(getBean(ElectronicLibrary.class),
                getBean(FindBookByIdValidator.class)));

        beans.put(AddBookUICommand.class, new AddBookUICommand(getBean(AddBookService.class)));

        beans.put(DeleteBookByTitleUICommand.class, new DeleteBookByTitleUICommand(
                getBean(DeleteBookByTitleService.class)));

        beans.put(DeleteBookByAuthorUICommand.class, new DeleteBookByAuthorUICommand(
                getBean(DeleteBookByAuthorService.class)));

        beans.put(DeleteBookByIdUICommand.class, new DeleteBookByIdUICommand(
                getBean(DeleteBookByIdService.class)));

        beans.put(GetAllBooksUICommand.class, new GetAllBooksUICommand(
                getBean(GetAllBooksService.class)));

        beans.put(FindBookByTitleUICommand.class, new FindBookByTitleUICommand(
                getBean(FindBooksService.class)));

        beans.put(FindBookByAuthorUICommand.class, new FindBookByAuthorUICommand(
                getBean(FindBooksService.class)));

        beans.put(FindBookByIdUICommand.class, new FindBookByIdUICommand(
                getBean(FindBookByIdService.class)));

        beans.put(ExitUICommand.class, new ExitUICommand());
    }
    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
