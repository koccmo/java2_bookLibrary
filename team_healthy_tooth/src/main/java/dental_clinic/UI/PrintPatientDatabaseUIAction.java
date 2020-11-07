package dental_clinic.UI;

import dental_clinic.services.GetPatientsService;

class PrintPatientDatabaseUIAction implements UIAction {

    private GetPatientsService getPatientsService;

    public PrintPatientDatabaseUIAction(GetPatientsService getPatientsService) {
        this.getPatientsService = getPatientsService;
    }

    public void execute(){

        if (getPatientsService.execute().isEmpty()){
            System.out.println("Database is empty");
        }else{
            getPatientsService.execute().forEach(System.out::println);
        }

    }

}

