package dental_clinic.UI;

import dental_clinic.PatientDatabase;

class PrintPatientCardUIAction implements UIAction {

    private PatientDatabase patientDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public PrintPatientCardUIAction(PatientDatabase patientDatabase){
        this.patientDatabase = patientDatabase;
    }

    public void execute(){
        long id = inputCheckUtility.inputValidLong("Please enter patient id");

        if (!patientDatabase.printSpecificPatientHistory(id)){
            System.out.println("Database doesn't contain patient with id " + id);
        }

    }

}

