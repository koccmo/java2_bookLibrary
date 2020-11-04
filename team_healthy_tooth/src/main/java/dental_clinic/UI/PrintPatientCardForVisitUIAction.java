package dental_clinic.UI;

import dental_clinic.PatientDatabase;

class PrintPatientCardForVisitUIAction implements UIAction {

    private PatientDatabase patientDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public PrintPatientCardForVisitUIAction(PatientDatabase patientDatabase){
        this.patientDatabase = patientDatabase;
    }

    public void execute(){
        long id = inputCheckUtility.inputValidLong("Please enter patient id");

        if (!patientDatabase.printPatientCardForVisit(id)){
            System.out.println("Database doesn't contain patient with id " + id);
        }

    }

}

