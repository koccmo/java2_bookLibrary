package dental_clinic.UI;

import dental_clinic.PatientDatabase;
import dental_clinic.PatientDatabaseImpl;

import java.util.HashMap;
import java.util.Map;

public class DentalClinic {

    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    private Map<Integer, UIAction> menuNumberToAction;

    public DentalClinic() {
        PatientDatabase patientDatabase = new PatientDatabaseImpl();

        menuNumberToAction = new HashMap();

        menuNumberToAction.put(1, new AddPatientUIAction(patientDatabase));
        menuNumberToAction.put(2, new DeletePatientUIAction(patientDatabase));
        menuNumberToAction.put(3, new PrintCardBaseUIAction(patientDatabase));
        menuNumberToAction.put(4, new PrintPatientCardUIAction(patientDatabase));
        menuNumberToAction.put(5, new FindPatientBySurnameUIAction(patientDatabase));
        menuNumberToAction.put(6, new FindPatientByPersonalCodeUIAction(patientDatabase));
        menuNumberToAction.put(7, new UpdateJowlDataUIAction(patientDatabase));
        menuNumberToAction.put(8, new PrintPatientCardForVisitUIAction(patientDatabase));
    }

    public void run(){

        boolean isWorking = true;
        while(isWorking) {

            printMenu();

            int userSelectedMenuNumber = inputCheckUtility.inputValidInteger("Please enter menu number: ");

            isWorking = nullIsNotPressed(userSelectedMenuNumber);

        }
    }

    private void printMenu(){
        System.out.println("\nMenu\n" +
                "1   Add patient\n" +
                "2   Delete by id\n" +
                "3   Print card database\n" +
                "4   Print patient card database\n" +
                "5   Find patient by surname database\n" +
                "6   Print patient by personal code database\n" +
                "7   Update patient's jowl data in database\n" +
                "8   Print patient card for visit\n" +
                "0   Exit");
    }

    private boolean nullIsNotPressed(int userSelectedMenuNumber){
        if (userSelectedMenuNumber == 0) {
            System.out.println(":) End of work day!");
            return false;
        } else {
            executeUIAction(userSelectedMenuNumber);
            return true;
        }
    }

    private void executeUIAction (int userSelectedMenuNumber) {
        UIAction uiAction = menuNumberToAction.get(userSelectedMenuNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu item does not exist: " + userSelectedMenuNumber);
        }
    }

}