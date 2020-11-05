package dental_clinic.UI;

import dental_clinic.database.PatientDatabase;
import dental_clinic.domain.Patient;

import java.util.List;

class FindPatientBySurnameUIAction implements UIAction {

    private PatientDatabase patientDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindPatientBySurnameUIAction(PatientDatabase patientDatabase){
        this.patientDatabase = patientDatabase;
    }

    public void execute(){
        String surname = inputCheckUtility.inputValidString("Please enter surname");

        List<Patient> result = patientDatabase.findPatientBySurname(surname);

        if (result.isEmpty()){
            System.out.println("Database doesn't contain patient with surname " + surname);
        }else{
            result.forEach(System.out::println);
        }

    }

}

