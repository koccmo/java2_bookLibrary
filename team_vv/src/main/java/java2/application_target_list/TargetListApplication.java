package java2.application_target_list;

import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.console_ui.Menu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public  class TargetListApplication {


     public static void main(String[] args) {
         ApplicationContext applicationContext = createApplicationContext();
         Menu menu = applicationContext.getBean(Menu.class);
         menu.start();
     }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(TargetListConfiguration.class);
    }
}
