package dental_clinic;

import dental_clinic.config.DentalClinicConfiguration;
import dental_clinic.console_ui.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DentalClinic {

    public static ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(DentalClinicConfiguration.class);

    public static void main(String [] args){
        while(true) {

            printMenu();

            int userSelectedMenuNumber = inputValidInteger();

            executeUIAction(userSelectedMenuNumber);
        }
    }

    private static void printMenu(){
        System.out.println("\nMenu\n" +
                "1   Add patient\n" +
                "2   Delete by id\n" +
                "3   Print patients database\n" +
                "4   Print specific patient information\n" +
                "5   Search by name / surname\n" +
                "6   Find patient by personal code\n" +
                "7   Add visit\n" +
                "8   Print patients card\n" +
                "9   Change patient's personal data\n" +
                "0   Exit");
    }

    private static int inputValidInteger(){
        Integer input;
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("Please enter menu number: ");
            while (true){
                try {
                    input = Integer.parseInt(in.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("It's not valid number! Please input valid number!");
                }
            }
            if (input >= 0){
                break;
            }else{
                System.out.println("Please enter valid value!");
            }
        }
        return input;
    }

    private static void executeUIAction (int userSelectedMenuNumber) {

        Map<Integer, UIAction> menuNumberToAction;

        menuNumberToAction = new HashMap();
        menuNumberToAction.put(1, applicationContext.getBean(AddPatientUIAction.class));
        menuNumberToAction.put(2, applicationContext.getBean(DeletePatientUIAction.class));
        menuNumberToAction.put(3, applicationContext.getBean(GetAllPatientsUIAction.class));
        menuNumberToAction.put(4, applicationContext.getBean(GetSpecificPatientHistoryUIAction.class));
        menuNumberToAction.put(5, applicationContext.getBean(SearchPatientUIAction.class));
        menuNumberToAction.put(6, applicationContext.getBean(SearchPatientByPersonalCodeUIAction.class));
        menuNumberToAction.put(7, applicationContext.getBean(AddVisitUIAction.class));
        menuNumberToAction.put(8, applicationContext.getBean(GetPatientCardUIAction.class));
        menuNumberToAction.put(9, applicationContext.getBean(ChangePersonalDataUiAction.class));
        menuNumberToAction.put(0, applicationContext.getBean(ExitUIAction.class));

        UIAction uiAction = menuNumberToAction.get(userSelectedMenuNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu item does not exist: " + userSelectedMenuNumber);
        }
    }

}

//Одним из способов поднятия аппликационного контекста в Spring является класс
//AnnotationConfigApplicationContext, которому в качестве параметра конструктора
//подаётся класс с конфигурацией.