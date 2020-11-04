package dental_clinic.UI;

import dental_clinic.PatientDatabase;
import dental_clinic.domain.PersonalData;

import java.util.List;

class FindPatientBySurnameUIAction implements UIAction {

    private PatientDatabase patientDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindPatientBySurnameUIAction(PatientDatabase patientDatabase){
        this.patientDatabase = patientDatabase;
    }

    public void execute(){
        String surname = inputCheckUtility.inputValidString("Please enter surname");

        List<PersonalData> result = patientDatabase.findPatientBySurname(surname);

        if (result.isEmpty()){
            System.out.println("Database doesn't contain patient with surname " + surname);
        }else{
            result.forEach(System.out::println);
        }

    }

}

