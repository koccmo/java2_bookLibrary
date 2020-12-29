package dental_clinic;

import dental_clinic.config.DentalClinicConfiguration;
import dental_clinic.console_ui.ProgramMenu.ProgramMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DentalClinic {

    public static void main(String [] args){

        ApplicationContext applicationContext =
                createApplicationContext();

        ProgramMenu programMenu = applicationContext.getBean(ProgramMenu.class);
        programMenu.printLogo();

        while(true) {

            programMenu.print();

            int userSelectedMenuNumber = programMenu.inputValidInteger();

            programMenu.executeMenuUIAction(userSelectedMenuNumber);
        }
    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(DentalClinicConfiguration.class);
    }

}
