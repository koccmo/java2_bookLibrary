package dental_clinic.UI;

import dental_clinic.PatientDatabase;
import dental_clinic.domain.PersonalData;

class AddPatientUIAction implements UIAction {

    private PatientDatabase patientDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public AddPatientUIAction(PatientDatabase patientDatabase){
        this.patientDatabase = patientDatabase;
    }

    public void execute(){
        String name = inputCheckUtility.inputValidString("Please enter name");
        String surname = inputCheckUtility.inputValidString("Please enter surname");
        String phone = inputCheckUtility.inputValidPhone("Please enter phone");
        String personalCode = inputCheckUtility.inputValidPersonalCode("Please enter personal code");


        PersonalData personalData = new PersonalData(name, surname, phone, personalCode);

        if (patientDatabase.addPatient(personalData)){
            System.out.println("Patient added");
        }else{
            System.out.println("The same patient is in database");
        }

    }

}

