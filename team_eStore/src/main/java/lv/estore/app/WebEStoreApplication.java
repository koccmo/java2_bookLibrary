package lv.estore.app;

import lv.estore.app.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class WebEStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebConfiguration.class);
    }
}