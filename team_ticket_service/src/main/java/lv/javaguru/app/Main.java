package lv.javaguru.app;

import lv.javaguru.app.console_ui.modes.InitMode;
import lv.javaguru.app.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Main {
	private static final ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringWebConfiguration.class);


	public static void main (String[] args) {
		InitMode initMode = new InitMode(applicationContext);
		initMode.execute();
	}


}

