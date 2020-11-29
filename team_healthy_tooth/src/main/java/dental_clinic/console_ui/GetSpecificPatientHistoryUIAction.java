package dental_clinic.console_ui;

import dental_clinic.core.requests.GetSpecificPatientHistoryRequest;
import dental_clinic.core.responses.GetSpecificPatientHistoryResponse;
import dental_clinic.core.services.GetSpecificPatientHistoryService;

import java.util.Scanner;

class GetSpecificPatientHistoryUIAction implements UIAction {

    private final GetSpecificPatientHistoryService printSpecificPatientHistory;

    public GetSpecificPatientHistoryUIAction(GetSpecificPatientHistoryService printSpecificPatientHistory) {
        this.printSpecificPatientHistory = printSpecificPatientHistory;
    }

    public void execute(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter patient id");
        Long id = in.nextLong();

        GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest = new GetSpecificPatientHistoryRequest(id);
        GetSpecificPatientHistoryResponse getSpecificPatientHistoryResponse = printSpecificPatientHistory.execute(getSpecificPatientHistoryRequest);

        if (getSpecificPatientHistoryResponse.hasErrors()){
            getSpecificPatientHistoryResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println(getSpecificPatientHistoryResponse.getSpecificPatient().toString());
        }
    }

}

