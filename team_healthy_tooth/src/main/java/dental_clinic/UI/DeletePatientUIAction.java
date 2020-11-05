package dental_clinic.UI;

import dental_clinic.database.PatientDatabase;

class DeletePatientUIAction implements UIAction {

    private PatientDatabase patientDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public DeletePatientUIAction(PatientDatabase patientDatabase){
        this.patientDatabase = patientDatabase;
    }

    public void execute(){

        long id = inputCheckUtility.inputValidLong("Please enter patients id");

        if (patientDatabase.deletePatient(id)){
            System.out.println("Patient with id " + id + " is deleted");
        }else{
            System.out.println("Database doesn't contain patient with id " + id);
        }
    }

}

