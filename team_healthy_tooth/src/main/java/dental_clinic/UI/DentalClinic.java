package dental_clinic.UI;

import dental_clinic.database.PatientDatabase;
import dental_clinic.database.PatientDatabaseImpl;
import dental_clinic.services.*;

import java.util.HashMap;
import java.util.Map;

public class DentalClinic {

    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    private Map<Integer, UIAction> menuNumberToAction;

    public DentalClinic() {
        PatientDatabase patientDatabase = new PatientDatabaseImpl();
        AddPatientService addPatientService = new AddPatientService(patientDatabase);
        DeletePatientService deletePatientService = new DeletePatientService(patientDatabase);
        GetPatientsService getPatientsService = new GetPatientsService(patientDatabase);
        GetSpecificPatientHistoryService getSpecificPatientHistoryService =
                new GetSpecificPatientHistoryService(patientDatabase);
        FindPatientBySurnameService findPatientBySurnameService = new FindPatientBySurnameService(patientDatabase);
        FindPatientByPersonalCodeService findPatientByPersonalCodeService =
                new FindPatientByPersonalCodeService(patientDatabase);

        menuNumberToAction = new HashMap();

        //TODO exit class
        menuNumberToAction.put(1, new AddPatientUIAction(addPatientService));
        menuNumberToAction.put(2, new DeletePatientUIAction(deletePatientService));
        menuNumberToAction.put(3, new PrintPatientDatabaseUIAction(getPatientsService));
        menuNumberToAction.put(4, new PrintPatientCardUIAction(getSpecificPatientHistoryService));
        menuNumberToAction.put(5, new FindPatientBySurnameUIAction(findPatientBySurnameService));
        menuNumberToAction.put(6, new FindPatientByPersonalCodeUIAction(findPatientByPersonalCodeService));
        menuNumberToAction.put(7, new AddVisitUIAction(patientDatabase));
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
                "5   Find patient by surname\n" +
                "6   Find patient by personal code\n" +
                "7   Update patient's jowl data in database\n" +
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