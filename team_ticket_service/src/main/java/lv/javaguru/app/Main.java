package lv.javaguru.app;

import lv.javaguru.app.config.TicketServiceConfiguration;
import lv.javaguru.app.console_ui.modes.InitMode;
import lv.javaguru.app.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Main {
	private static final ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringWebConfiguration.class);

	//private static final ApplicationContext applicationContext =
	//		new AnnotationConfigApplicationContext(TicketServiceConfiguration.class);

	public static void main (String[] args) {
		InitMode initMode = new InitMode(applicationContext);
		initMode.execute();
	}


}

