package dental_clinic.UI;

import dental_clinic.services.GetSpecificPatientHistoryService;

import java.util.Optional;

class PrintSpecificPatientHistoryUIAction implements UIAction {

    private final GetSpecificPatientHistoryService printSpecificPatientHistory;

    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public PrintSpecificPatientHistoryUIAction(GetSpecificPatientHistoryService printSpecificPatientHistory) {
        this.printSpecificPatientHistory = printSpecificPatientHistory;
    }

    public void execute(){
        long id = inputCheckUtility.inputValidLong("Please enter patient id");

        if (printSpecificPatientHistory.execute(id).equals(Optional.empty())){
            System.out.println("Database doesn't contain patient with id " + id);
        }else{
            System.out.println(printSpecificPatientHistory.execute(id).get().toString());
        }
    }

}

