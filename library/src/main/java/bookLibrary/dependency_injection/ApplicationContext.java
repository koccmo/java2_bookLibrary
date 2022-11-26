package bookLibrary.dependency_injection;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {
    private Map<Class, Object> beans = new HashMap<>();

//    public ApplicationContext() {
//        beans.put(DataBase.class, new InMemoryDatabaseImpl());
//
//        beans.put(AddBookValidator.class, new AddBookValidator());
//        beans.put(AddBookService.class, new AddBookService(getBean(DataBase.class), getBean(AddBookValidator.class)));
//        beans.put(AddBookUIAction.class, new AddBookUIAction(getBean(AddBookService.class)));
//
//        beans.put(DeleteBookValidation.class, new DeleteBookValidation());
//        beans.put(DeleteBookService.class, new DeleteBookService(getBean(DataBase.class), getBean(DeleteBookValidation.class)));
//        beans.put(DeleteBookUIAction.class, new DeleteBookUIAction(getBean(DeleteBookService.class)));
//
//        beans.put(FindBookByAuthorService.class, new FindBookByAuthorService(getBean(DataBase.class)));
//        beans.put(FindBookByAuthorUIAction.class, new FindBookByAuthorUIAction(getBean(FindBookByAuthorService.class)));
//
//        beans.put(FinishWorkService.class, new FinishWorkService(getBean(DataBase.class)));
//        beans.put(FinishWorkUIAction.class, new FinishWorkUIAction(getBean(FinishWorkService.class)));
//
//        beans.put(PrintAllBookTitleService.class, new PrintAllBookTitleService(getBean(DataBase.class)));
//        beans.put(PrintAllBooksTitleUIAction.class, new PrintAllBooksTitleUIAction(getBean(PrintAllBookTitleService.class)));
//
//        beans.put(SearchBooksRequestFieldValidator.class, new SearchBooksRequestFieldValidator());
//        beans.put(OrderingValidator.class, new OrderingValidator());
//        beans.put(PagingValidator.class, new PagingValidator());
//        beans.put(SearchBooksValidator.class, new SearchBooksValidator(getBean(SearchBooksRequestFieldValidator.class),
//                getBean(OrderingValidator.class),
//                getBean(PagingValidator.class)));
//        beans.put(SearchBooksService.class, new SearchBooksService(getBean(DataBase.class), getBean(SearchBooksValidator.class)));
//        beans.put(SearchBooksUIAction.class, new SearchBooksUIAction(getBean(SearchBooksService.class)));
//
//    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }

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
}
