package lv.javaguru.app;

import lv.javaguru.app.config.TicketServiceConfiguration;
import lv.javaguru.app.console_ui.modes.InitMode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Main {
	private static final ApplicationContext applicationContext =
			new AnnotationConfigApplicationContext(TicketServiceConfiguration.class);

	public static void main (String[] args) {
		InitMode initMode = new InitMode(applicationContext);
		initMode.execute();
	}


}

