package online_schedule.ui;

import online_schedule.database.ClientDatabase;
import online_schedule.database.ClientDatabaseImpl;

import java.util.Map;

public class OnlineSchedule {

    InputUtility inputUtility = new InputUtility();

    private Map<Integer, UIAction> menuNumberToAction;

    public OnlineSchedule (){
        ClientDatabase clientDatabase = new ClientDatabaseImpl();


    }

    public void run(){

        while(true) {

            printMenu();

            int userSelectedMenuNumber = inputUtility.inputValidInteger("Please enter menu number: ");

            executeUIAction(userSelectedMenuNumber);
        }
    }

    private void printMenu(){
        System.out.println("\nMenu\n" +
                "1   Add client\n" +
                "2   Delete by id\n" +
                "3   Print clients database\n" +
                "4   Select a master\n" +
                "5   Select a procedure\n" +
                "6   Select a date\n" +
                "7   Select a time\n" +
                "8   Find Client by mobile number\n" +
                "0   Exit");
    }

    private void executeUIAction (int userSelectedMenuNumber) {
        UIAction uiAction = menuNumberToAction.get(userSelectedMenuNumber);
        if (uiAction != null) uiAction.execute();
        else {
            System.out.println("Menu item does not exist: " + userSelectedMenuNumber);
        }
    }

}
