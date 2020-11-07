package dental_clinic.UI;

import dental_clinic.database.PatientDatabase;
import dental_clinic.domain.Patient;
import dental_clinic.services.FindPatientBySurnameService;

import java.util.List;

class FindPatientBySurnameUIAction implements UIAction {

    private FindPatientBySurnameService findPatientBySurname;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public FindPatientBySurnameUIAction(FindPatientBySurnameService findPatientBySurname) {
        this.findPatientBySurname = findPatientBySurname;
    }

    public void execute(){
        String surname = inputCheckUtility.inputValidString("Please enter surname");

        List<Patient> result = findPatientBySurname.execute(surname);

        if (result.isEmpty()){
            System.out.println("Database doesn't contain patient with surname " + surname);
        }else{
            result.forEach(System.out::println);
        }

    }

}

