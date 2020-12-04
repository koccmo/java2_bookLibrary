package dental_clinic.console_ui;

import dental_clinic.dependency_injection.ApplicationContext;
import dental_clinic.core.services.*;

import java.util.HashMap;
import java.util.Map;

public class DentalClinic {

    ApplicationContext applicationContext = new ApplicationContext();
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    private Map<Integer, UIAction> menuNumberToAction;

    public DentalClinic() {

        menuNumberToAction = new HashMap();

        menuNumberToAction.put(1, new AddPatientUIAction(applicationContext.getBean(AddPatientService.class)));
        menuNumberToAction.put(2, new DeletePatientUIAction(applicationContext.getBean(DeletePatientService.class)));
        menuNumberToAction.put(3, new GetAllPatientsUIAction(applicationContext.getBean(GetAllPatientsService.class)));
        menuNumberToAction.put(4, new GetSpecificPatientHistoryUIAction(applicationContext.getBean(GetSpecificPatientHistoryService.class)));
        menuNumberToAction.put(5, new SearchPatientUIAction(applicationContext.getBean(SearchPatientService.class)));
        menuNumberToAction.put(6, new SearchPatientByPersonalCodeUIAction(applicationContext.getBean(SearchPatientsByPersonalCodeService.class)));
        menuNumberToAction.put(7, new AddVisitUIAction(applicationContext.getBean(AddVisitService.class), applicationContext.getBean(ContainsDatabaseIdService.class)));
        menuNumberToAction.put(8, new GetPatientCardUIAction(applicationContext.getBean(GetPatientCardService.class)));
        menuNumberToAction.put(9, new ChangePersonalDataUiAction(applicationContext.getBean(ChangePersonalDataService.class), applicationContext.getBean(ContainsDatabaseIdService.class)));
        menuNumberToAction.put(0, new ExitUIAction());
    }

    public void run(){

        while(true) {

            printMenu();

            int userSelectedMenuNumber = inputCheckUtility.inputValidInteger("Please enter menu number: ");

            executeUIAction(userSelectedMenuNumber);
        }
    }

    private void printMenu(){
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

    private void executeUIAction (int userSelectedMenuNumber) {
        UIAction uiAction = menuNumberToAction.get(userSelectedMenuNumber);
        if (uiAction != null) {
            uiAction.execute();
        } else {
            System.out.println("Menu item does not exist: " + userSelectedMenuNumber);
        }
    }

}