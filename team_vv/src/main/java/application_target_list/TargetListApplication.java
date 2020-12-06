package application_target_list;

import application_target_list.console_ui.Menu;
import application_target_list.dependency_injection.ApplicationContext;
import application_target_list.dependency_injection.DIApplicationContextBuilder;

public  class TargetListApplication {

    private static ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("application_target_list");

     public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();
     }
}
