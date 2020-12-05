package application_target_list.console_ui;

import application_target_list.dependency_injection.ApplicationContext;
import application_target_list.console_ui.actions.*;

import java.util.Scanner;

public class Menu {

    private static final ApplicationContext applicationContext = new ApplicationContext();
    private final Scanner src = new Scanner(System.in);

    public void start(){
        while (true) {
        MenuUIAction menuUIAction = applicationContext.getBean(MenuUIAction.class);
        menuUIAction.execute();
            printText();
            switch (getNumberFromUser()) {
                case 1 -> {
                    GetAllTargetsUIAction getAllTargetsUIAction = applicationContext.getBean(GetAllTargetsUIAction.class);
                    getAllTargetsUIAction.execute();
                }

                case 2 -> {
                    AddTargetUIAction addTargetUIAction = applicationContext.getBean(AddTargetUIAction.class);
                    addTargetUIAction.execute();
                }
                case 3 -> {
                    GetAllTargetsUIAction getAllTargetsUIAction = applicationContext.getBean(GetAllTargetsUIAction.class);
                    getAllTargetsUIAction.execute();
                    DeleteUIAction deleteUIAction = applicationContext.getBean(DeleteUIAction.class);
                    deleteUIAction.execute();
                }
                case 4 -> changeTargetParametersActions();
                case 5 -> searchTargetActions();
                case 0 -> {
                    ExitUIAction exitUIAction = applicationContext.getBean(ExitUIAction.class);
                    exitUIAction.execute();
                }
            }
        }
    }

    private void searchTargetActions(){
        boolean backToMenu = false;
        while (!backToMenu){
            SearchTargetMenuUIAction searchTargetMenuUIAction = applicationContext.getBean(SearchTargetMenuUIAction.class);
            searchTargetMenuUIAction.execute();
            printText();
            switch (getNumberFromUser()){
                case 1 -> {
                    SearchTargetByNameUIAction searchTargetByNameUIAction = applicationContext.getBean(SearchTargetByNameUIAction.class);
                    searchTargetByNameUIAction.execute();
                }
                case 2 -> {
                    SearchTargetByDescriptionUIAction searchTargetByDescriptionUIAction = applicationContext.getBean(SearchTargetByDescriptionUIAction.class);
                    searchTargetByDescriptionUIAction.execute();
                }
                case 0 -> backToMenu = true;
            }
        }

    }

    private void changeTargetParametersActions(){
        boolean backToMenu = false;
        while (!backToMenu){
            TargetChangesMenuUIAction targetChangesMenuUIAction = applicationContext.getBean(TargetChangesMenuUIAction.class);
            targetChangesMenuUIAction.execute();
            printText();
            switch (getNumberFromUser()) {
                case 1 -> {
                    GetAllTargetsUIAction getAllTargetsUIAction = applicationContext.getBean(GetAllTargetsUIAction.class);
                    getAllTargetsUIAction.execute();
                    ChangeTargetNameUIAction changeTargetNameUIAction = applicationContext.getBean(ChangeTargetNameUIAction.class);
                    changeTargetNameUIAction.execute();
                }
                case 2 -> {
                    GetAllTargetsUIAction getAllTargetsUIAction = applicationContext.getBean(GetAllTargetsUIAction.class);
                    getAllTargetsUIAction.execute();
                    ChangeTargetDescriptionUIAction changeTargetDescriptionUIAction = applicationContext.getBean(ChangeTargetDescriptionUIAction.class);
                    changeTargetDescriptionUIAction.execute();
                }
                case 3 -> {
                    GetAllTargetsUIAction getAllTargetsUIAction = applicationContext.getBean(GetAllTargetsUIAction.class);
                    getAllTargetsUIAction.execute();
                    ChangeTargetDeadlineUIAction changeTargetDeadlineUIAction = applicationContext.getBean(ChangeTargetDeadlineUIAction.class);
                    changeTargetDeadlineUIAction.execute();
                }
                case 0 -> backToMenu = true;
            }
        }
    }

    private void printText(){
        System.out.println("----------");
        System.out.println("Choose action: ");
        System.out.println("----------");
    }

    private int getNumberFromUser(){
        return src.nextInt();
    }

}
