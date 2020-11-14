package dental_clinic.console_ui;

import dental_clinic.core.requests.GetPatientRequest;
import dental_clinic.core.responses.GetPatientsResponse;
import dental_clinic.core.services.GetPatientsService;

class PrintPatientDatabaseUIAction implements UIAction {

    private GetPatientsService getPatientsService;

    public PrintPatientDatabaseUIAction(GetPatientsService getPatientsService) {
        this.getPatientsService = getPatientsService;
    }

    public void execute(){

        GetPatientRequest getPatientRequest = new GetPatientRequest();
        GetPatientsResponse getPatientsResponse = getPatientsService.execute(getPatientRequest);

        if (getPatientsResponse.hasErrors()){
            getPatientsResponse.getErrors().forEach(System.out::println);
        }else{
            getPatientsResponse.getPatients().forEach(System.out::println);
        }

    }

}

