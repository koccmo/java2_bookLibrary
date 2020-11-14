package dental_clinic.console_ui;

import dental_clinic.core.services.DeletePatientService;

class DeletePatientUIAction implements UIAction {

    private DeletePatientService deletePatientService;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public DeletePatientUIAction(DeletePatientService deletePatientService) {
        this.deletePatientService = deletePatientService;
    }

    public void execute(){

        long id = inputCheckUtility.inputValidLong("Please enter patients id");

        if (deletePatientService.deletePatient(id)){
            System.out.println("Patient with id " + id + " is deleted");
        }else{
            System.out.println("Database doesn't contain patient with id " + id);
        }
    }

}

