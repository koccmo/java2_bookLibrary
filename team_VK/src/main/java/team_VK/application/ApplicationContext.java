package team_VK.application;

import team_VK.application.core.services.*;
import team_VK.application.database.*;
import team_VK.application.ui.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {

        beans.put(Database.class, new DatabaseInMemory());
        beans.put(DatabaseClients.class, new DatabaseClientsInMemory());

        beans.put(AddBookServiceValidator.class, new AddBookServiceValidator());
        beans.put(AddBookService.class, new AddBookService(getBean(Database.class), getBean(AddBookServiceValidator.class)));
        beans.put(AddBookUIAction.class, new AddBookUIAction(getBean(AddBookService.class)));

        beans.put(RemoveBookServiceValidator.class, new RemoveBookServiceValidator());
        beans.put(RemoveBookService.class, new RemoveBookService(getBean(Database.class), getBean(RemoveBookServiceValidator.class)));
        beans.put(RemoveBookUIAction.class, new RemoveBookUIAction(getBean(RemoveBookService.class)));

        beans.put(GetBooksListServiceValidator.class, new GetBooksListServiceValidator());
        beans.put(GetBooksListService.class, new GetBooksListService(getBean(Database.class), getBean(GetBooksListServiceValidator.class)));
        beans.put(GetBooksListUIAction.class, new GetBooksListUIAction(getBean(GetBooksListService.class)));

        beans.put(BookBookServiceValidator.class, new BookBookServiceValidator());
        beans.put(BookBookService.class, new BookBookService(getBean(Database.class), getBean(BookBookServiceValidator.class)));
        beans.put(BookBookUIAction.class, new BookBookUIAction(getBean(BookBookService.class)));

        beans.put(ShowBookService.class, new ShowBookService(getBean(Database.class)));
        beans.put(ShowBookUIActions.class, new ShowBookUIActions(getBean(ShowBookService.class)));

        beans.put(AddClientServiceValidator.class, new AddClientServiceValidator());
        beans.put(AddClientService.class, new AddClientService(getBean(DatabaseClients.class), getBean(AddClientServiceValidator.class)));
        beans.put(AddClientUIActions.class, new AddClientUIActions(getBean(AddClientService.class)));

        beans.put(ExitProgramUIAction.class, new ExitProgramUIAction());




        DataBaseFiller DBFiller = new DataBaseFiller(getBean(Database.class));
        DBFiller.fill();
        DataBaseClientFiller DBClientFiller = new DataBaseClientFiller(getBean(DatabaseClients.class));
        DBClientFiller.fill();

    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
