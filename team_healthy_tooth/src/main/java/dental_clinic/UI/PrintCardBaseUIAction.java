package dental_clinic.UI;

import dental_clinic.PatientDatabase;

class PrintCardBaseUIAction implements UIAction {

    private PatientDatabase patientDatabase;

    public PrintCardBaseUIAction(PatientDatabase patientDatabase){
        this.patientDatabase = patientDatabase;
    }

    public void execute(){

        if (!patientDatabase.printDatabase()){
            System.out.println("Database is empty");
        }

    }

}

