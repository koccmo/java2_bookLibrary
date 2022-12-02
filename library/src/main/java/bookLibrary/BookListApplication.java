package bookLibrary;


import bookLibrary.config.BookListConfiguration;
import bookLibrary.console.ui.ProgramMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BookListApplication {
//    private static ApplicationContext context = new DIApplicationContextBuilder().build("bookLibrary");




    public static void main(String[] args) {
        ApplicationContext context = createApplicationContext();

        ProgramMenu programMenu = context.getBean(ProgramMenu.class);
        while (true) {
            programMenu.printMenu();
            int selectMenuField = programMenu.getMenuFieldFromCustomer();
            programMenu.executeSelectedMenuItem(selectMenuField);
            System.out.println("");
        }
    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(BookListConfiguration.class);
    }




}
