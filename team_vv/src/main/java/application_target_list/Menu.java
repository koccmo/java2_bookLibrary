package application_target_list;

import application_target_list.console_ui.*;
import application_target_list.database.Database;
import application_target_list.database.TargetListImpl;
import application_target_list.services.*;

import java.util.Scanner;

public class Menu {

    Database targetsDatabase = new TargetListImpl();
    GetAllTargetsService getAllTargetsService = new GetAllTargetsService(targetsDatabase);
    AddTargetService addTargetService = new AddTargetService(targetsDatabase);
    DeleteTargetService deleteTargetService = new DeleteTargetService(targetsDatabase);
    ChangeTargetNameService changeTargetNameService = new ChangeTargetNameService(targetsDatabase);
    ChangeTargetDescriptionService changeTargetDescriptionService = new ChangeTargetDescriptionService(targetsDatabase);
    ChangeTargetDeadlineService changeTargetDeadlineService = new ChangeTargetDeadlineService(targetsDatabase);
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
        printMenuToConsole();
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
            printTargetChangesMenu();
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

    private void printMenuToConsole(){
        System.out.println("Actions: ");
        System.out.println("[1] Show targets list");
        System.out.println("[2] Add target");
        System.out.println("[3] Delete target");
        System.out.println("[4] Change target parameters");
        System.out.println("[0] Quit");
    }

    private void printTargetChangesMenu(){
        System.out.println("Choose target parameter: ");
        System.out.println("[1] Change target name");
        System.out.println("[2] Change target description");
        System.out.println("[3] Change target deadline");
        System.out.println("[0] Back to Menu");
    }
}
