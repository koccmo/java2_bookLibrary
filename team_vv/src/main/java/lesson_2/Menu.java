package lesson_2;

import lesson_2.console_ui.*;
import lesson_2.database.Database;
import lesson_2.database.TargetListImpl;
import lesson_2.services.*;

import java.util.Scanner;

public class Menu {

    Database targetsDatabase = new TargetListImpl();
    GetAllTargetsService getAllTargetsService = new GetAllTargetsService(targetsDatabase);
    AddTargetService addTargetService = new AddTargetService(targetsDatabase);
    DeleteTargetService deleteTargetService = new DeleteTargetService(targetsDatabase);
    ChangeTargetNameService changeTargetNameService = new ChangeTargetNameService(targetsDatabase);
    ChangeTargetDescriptionService changeTargetDescriptionService = new ChangeTargetDescriptionService(targetsDatabase);
    ChangeTargetDeadlineService changeTargetDeadlineService = new ChangeTargetDeadlineService(targetsDatabase);
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
