package dental_clinic.UI;

import dental_clinic.database.PatientDatabase;
import dental_clinic.services.DeletePatientServices;

class DeletePatientUIAction implements UIAction {

    private DeletePatientServices deletePatientServices;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public DeletePatientUIAction(DeletePatientServices deletePatientServices) {
        this.deletePatientServices = deletePatientServices;
    }

    public void execute(){

        long id = inputCheckUtility.inputValidLong("Please enter patients id");

        if (deletePatientServices.deletePatient(id)){
            System.out.println("Patient with id " + id + " is deleted");
        }else{
            System.out.println("Database doesn't contain patient with id " + id);
        }
    }

}

