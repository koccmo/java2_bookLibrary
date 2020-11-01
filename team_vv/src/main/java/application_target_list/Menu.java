package application_target_list;

import java.util.Scanner;

class Menu {

    TargetListImpl targetsDatabase = new TargetListImpl();
    Scanner src = new Scanner(System.in);


    public void start(){
        boolean quit = false;
        while (!quit) {
        printActionsToConsole();
        printTextToConsole("Choose action: ");
                switch (getNumberFromUser()) {
                    case 1:
                        printTargetsList();
                        break;
                    case 2:
                        addTarget();
                        break;
                    case 3:
                       deleteTarget();
                        break;
                    case 4:
                        if (isTargetsListEmpty()){
                            printTextToConsole("Targets list is empty");
                            break;
                        }
                        changeTargetParametersActions();
                        break;
                    case 0:
                        quit = true;
                        break;
                }
            }
        }

    private void changeTargetParametersActions(){
        boolean backToMenu = false;
        while (!backToMenu){
            printTargetChangesMenu();
            printTextToConsole("Choose action: ");
            switch (getNumberFromUser()){
                case 1:
                    printTargetsListToConsole();
                    changeTargetName();
                    break;
                case 2:
                    printTargetsListToConsole();
                    changeTargetDescription();
                    break;
                case 3:
                    printTargetsListToConsole();
                    changeTargetDeadline();
                    break;
                case 0:
                    backToMenu = true;
                    break;
            }
        }
    }

    private boolean isTargetsListEmpty(){
        return targetsDatabase.targetsList.size() == 0;
    }

    private void printTextToConsole(String text){
        System.out.println("----------");
        System.out.println(text);
        System.out.println("----------");
    }

    private void changeTargetDeadline(){
        while (true) {
            System.out.print("Enter target ID: ");
            Long targetId = getTargetIdFromUser();
            if (isIdInList(targetId)) {
                System.out.print("Enter new target deadline: ");
                src.nextLine();
                int newTargetDeadline = getNumberFromUser();
                targetsDatabase.changeTargetDeadline(targetId, newTargetDeadline);
                break;
            } else {
                printTextToConsole("Incorrect ID, try again!");
            }
        }
            printTextToConsole("Target deadline changed!");
    }



    private void changeTargetDescription(){
        while (true) {
            System.out.print("Enter target ID: ");
            Long targetId = getTargetIdFromUser();
            if (isIdInList(targetId)) {
                System.out.print("Enter new target description: ");
                src.nextLine();
                String newTargetDescription = getTextFromUser();
                targetsDatabase.changeTargetDescription(targetId, newTargetDescription);
                break;
            } else {
                printTextToConsole("Incorrect ID, try again!");
            }
        }
        printTextToConsole("Target description changed!");
    }

    private void changeTargetName(){
        while (true) {
        System.out.print("Enter target ID: ");
        Long targetId =  getTargetIdFromUser();
            if (isIdInList(targetId)) {
                 System.out.print("Enter new target name: ");
                 src.nextLine();
                 String newTargetName = getTextFromUser();
                 targetsDatabase.changeTargetName(targetId, newTargetName);
                 break;
            } else {
                 printTextToConsole("Incorrect ID, try again!");
            }
        }
        printTextToConsole("Target name changed!");
    }

    private void deleteTarget(){
        while (true) {
        printTargetsListToConsole();
        System.out.print("Enter target ID: ");
        Long targetId =  getTargetIdFromUser();
            if (isIdInList(targetId)) {
                targetsDatabase.deleteTarget(targetId);
                break;
            } else {
                printTextToConsole("Incorrect ID, try again!");
            }
        }
        printTextToConsole("Target deleted!");
    }

    private boolean isIdInList(Long id){
        for (Target target : targetsDatabase.targetsList){
            return target.getId().equals(id);
        }
        return false;
    }

    private void addTarget(){
        src.nextLine();
        System.out.print("Enter target name: ");
        String targetName = getTextFromUser();
        System.out.print("Enter target description: ");
        String targetDescription = getTextFromUser();
        System.out.print("Enter target deadline(days): ");
        int targetDeadline = getNumberFromUser();
        targetsDatabase.addTarget(new Target(targetName, targetDescription, targetDeadline));
        printTextToConsole("Target added!");
    }

    private void printTargetsList(){
        if (isTargetsListEmpty()) {
            printTextToConsole("Targets list is empty");
        } else {
            printTargetsListToConsole();
            System.out.println("----------");
        }
    }

    private Long getTargetIdFromUser(){
        return src.nextLong();
    }

    private String getTextFromUser(){
        return src.nextLine();
    }

    private int getNumberFromUser(){
        return src.nextInt();
    }

    private void printActionsToConsole(){
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

    private void printTargetsListToConsole(){
        for (Target target : targetsDatabase.targetsList){
            System.out.println(target.getId() + ". " +
                    target.getName() + " [" + target.getDescription() + "] " +
                    target.getDeadline() + " days");
        }
    }
}
