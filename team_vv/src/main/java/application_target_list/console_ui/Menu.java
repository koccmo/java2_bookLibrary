package application_target_list.console_ui;

import application_target_list.core.services.SearchTargetByNameService;
import application_target_list.core.validators.*;
import application_target_list.console_ui.actions.*;
import application_target_list.core.database.Database;
import application_target_list.core.database.TargetListImpl;
import application_target_list.core.services.*;

import java.util.Scanner;

public class Menu {

    Database targetsDatabase = new TargetListImpl();

    AddTargetValidator addTargetValidator = new AddTargetValidator();
    DeleteTargetValidator deleteTargetValidator = new DeleteTargetValidator();
    ChangeTargetNameValidator changeTargetNameValidator = new ChangeTargetNameValidator();
    ChangeTargetDescriptionValidator changeTargetDescriptionValidator = new ChangeTargetDescriptionValidator();
    ChangeTargetDeadlineValidator changeTargetDeadlineValidator = new ChangeTargetDeadlineValidator();
    SearchTargetByNameValidator searchTargetByNameValidator = new SearchTargetByNameValidator();
    SearchTargetByDescriptionValidator searchTargetByDescriptionValidator = new SearchTargetByDescriptionValidator();


    GetAllTargetsService getAllTargetsService = new GetAllTargetsService(targetsDatabase);
    AddTargetService addTargetService = new AddTargetService(targetsDatabase, addTargetValidator);
    DeleteTargetService deleteTargetService = new DeleteTargetService(targetsDatabase, deleteTargetValidator);
    ChangeTargetNameService changeTargetNameService = new ChangeTargetNameService(targetsDatabase, changeTargetNameValidator);
    ChangeTargetDescriptionService changeTargetDescriptionService = new ChangeTargetDescriptionService(targetsDatabase, changeTargetDescriptionValidator);
    ChangeTargetDeadlineService changeTargetDeadlineService = new ChangeTargetDeadlineService(targetsDatabase, changeTargetDeadlineValidator);
    SearchTargetByNameService searchTargetByNameService = new SearchTargetByNameService(targetsDatabase, searchTargetByNameValidator);
    SearchTargetByDescriptionService searchTargetByDescriptionService = new SearchTargetByDescriptionService(targetsDatabase, searchTargetByDescriptionValidator);

    UIAction menuUiAction = new MenuUIAction();
    UIAction targetChangesMenuUIAction = new TargetChangesMenuUIAction();
    UIAction changeTargetDeadlineUIAction = new ChangeTargetDeadlineUIAction(changeTargetDeadlineService);
    UIAction changeTargetDescriptionUIAction = new ChangeTargetDescriptionUIAction(changeTargetDescriptionService);
    UIAction changeTargetNameUIAction = new ChangeTargetNameUIAction(changeTargetNameService);
    UIAction deleteUIAction = new DeleteUIAction(deleteTargetService);
    UIAction addTargetUIAction = new AddTargetUIAction(addTargetService);
    UIAction exitUIAction = new ExitUIAction();
    UIAction getAllTargetsUIAction = new GetAllTargetsUIAction(getAllTargetsService);
    UIAction searchTargetByNameUIAction = new SearchTargetByNameUIAction(searchTargetByNameService);
    UIAction searchTargetByDescriptionUIAction = new SearchTargetByDescriptionUIAction(searchTargetByDescriptionService);
    UIAction searchTargetMenuUIAction = new SearchTargetMenuUIAction();


    Scanner src = new Scanner(System.in);


    public void start(){
        while (true) {
        menuUiAction.execute();
            printText();
            switch (getNumberFromUser()) {
                case 1 -> getAllTargetsUIAction.execute();
                case 2 -> addTargetUIAction.execute();
                case 3 -> {
                    getAllTargetsUIAction.execute();
                    deleteUIAction.execute();
                }
                case 4 -> changeTargetParametersActions();
                case 5 -> searchTargetActions();
                case 0 -> exitUIAction.execute();
            }
        }
    }

    private void searchTargetActions(){
        boolean backToMenu = false;
        while (!backToMenu){
            searchTargetMenuUIAction.execute();
            printText();
            switch (getNumberFromUser()){
                case 1 -> searchTargetByNameUIAction.execute();
                case 2 -> searchTargetByDescriptionUIAction.execute();
                case 0 -> backToMenu = true;
            }
        }

    }

    private void changeTargetParametersActions(){
        boolean backToMenu = false;
        while (!backToMenu){
            targetChangesMenuUIAction.execute();
            printText();
            switch (getNumberFromUser()) {
                case 1 -> {
                    getAllTargetsUIAction.execute();
                    changeTargetNameUIAction.execute();
                }
                case 2 -> {
                    getAllTargetsUIAction.execute();
                    changeTargetDescriptionUIAction.execute();
                }
                case 3 -> {
                    getAllTargetsUIAction.execute();
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
