package lv.javaguru.java2.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lv.javaguru.java2.library.config.BookListConfiguration;
import lv.javaguru.java2.library.console_ui.ProgramMenu;

public class BookListApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(BookListConfiguration.class);
		ProgramMenu programMenu = applicationContext.getBean(ProgramMenu.class);
		while (true) {
			programMenu.print();
			int menuNumber = programMenu.getMenuNumberFromUser();
			programMenu.executeSelectedMenuItem(menuNumber);
		}
	}

}