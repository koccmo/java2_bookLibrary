package internet_store;

import internet_store.console_ui.UIAction;
import internet_store.console_ui.actions.*;
import internet_store.core.database.Database;
import internet_store.core.database.TargetListImpl;
import internet_store.core.services.*;
import internet_store.core.services.validators.*;

import java.util.Scanner;

public class Menu {

    Database targetsDatabase = new TargetListImpl();

    AddTargetValidator addTargetValidator = new AddTargetValidator();
    DeleteTargetValidator deleteTargetValidator = new DeleteTargetValidator();
    ChangeTargetNameValidator changeTargetNameValidator = new ChangeTargetNameValidator();
    ChangeTargetDescriptionValidator changeTargetDescriptionValidator = new ChangeTargetDescriptionValidator();
    ChangeTargetDeadlineValidator changeTargetDeadlineValidator = new ChangeTargetDeadlineValidator();


    GetAllTargetsService getAllTargetsService = new GetAllTargetsService(targetsDatabase);
    AddTargetService addTargetService = new AddTargetService(targetsDatabase, addTargetValidator);
    DeleteTargetService deleteTargetService = new DeleteTargetService(targetsDatabase, deleteTargetValidator);
    ChangeTargetNameService changeTargetNameService = new ChangeTargetNameService(targetsDatabase, changeTargetNameValidator);
    ChangeTargetDescriptionService changeTargetDescriptionService = new ChangeTargetDescriptionService(targetsDatabase, changeTargetDescriptionValidator);
    ChangeTargetDeadlineService changeTargetDeadlineService = new ChangeTargetDeadlineService(targetsDatabase, changeTargetDeadlineValidator);

    UIAction menuUiAction = new MenuUIAction();
    UIAction targetChangesMenuUIAction = new TargetChangesMenuUIAction();
    UIAction changeTargetDeadlineUIAction = new ChangeTargetDeadlineUIAction(changeTargetDeadlineService);
    UIAction changeTargetDescriptionUIAction = new ChangeTargetDescriptionUIAction(changeTargetDescriptionService);
    UIAction changeTargetNameUIAction = new ChangeTargetNameUIAction(changeTargetNameService);
    UIAction deleteUIAction = new DeleteUIAction(deleteTargetService);
    UIAction addTargetUIAction = new AddTargetUIAction(addTargetService);
    UIAction exitUIAction = new ExitUIAction();
    UIAction getAllTargetsUIAction = new GetAllTargetsUIAction(getAllTargetsService);

    Scanner src = new Scanner(System.in);


    public void start(){
        while (true) {
        menuUiAction.execute();
        System.out.println("----------");
        System.out.println("Choose action: ");
        System.out.println("----------");
            switch (getNumberFromUser()) {
                case 1 -> getAllTargetsUIAction.execute();
                case 2 -> addTargetUIAction.execute();
                case 3 -> {
                    getAllTargetsUIAction.execute();
                    deleteUIAction.execute();
                }
                case 4 -> changeTargetParametersActions();
                case 0 -> exitUIAction.execute();
            }
        }
    }

    private void changeTargetParametersActions(){
        boolean backToMenu = false;
        while (!backToMenu){
            targetChangesMenuUIAction.execute();
            System.out.println("----------");
            System.out.println("Choose action: ");
            System.out.println("----------");
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

    private int getNumberFromUser(){
        return src.nextInt();
    }

}
