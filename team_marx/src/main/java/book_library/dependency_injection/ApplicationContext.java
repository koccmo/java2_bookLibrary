package book_library.dependency_injection;

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

    public ApplicationContext() { }

    public void addBean(Class beanClass, Object beanInstance) {
        beans.put(beanClass, beanInstance);
        Class[] instanceInterfaces = beanClass.getInterfaces();
        for (int i = 0; i < instanceInterfaces.length; i++) {
            Class instanceInterface = instanceInterfaces[i];
            Object instance = getBean(instanceInterface);
            if (instance == null) {
                beans.put(instanceInterface, beanInstance);
            }
        }
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
