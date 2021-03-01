package java2.application_target_list;

import java2.application_target_list.console_ui.Menu;
import java2.application_target_list.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TargetListApplication {

     public static void main(String[] args) {
         ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);
         context.start();
//         Menu programMenu = context.getBean(Menu.class);
//         programMenu.start();
         System.out.println("Application is running");
     }
}
