package dental_clinic.UI;

import dental_clinic.CardDatabase;
import dental_clinic.domain.Patient;

class AddPatientUIAction implements UIAction {

    private CardDatabase cardDatabase;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public AddPatientUIAction(CardDatabase cardDatabase){
        this.cardDatabase = cardDatabase;
    }

    public void execute(){
        String name = inputCheckUtility.inputValidString("Please enter name");
        String surname = inputCheckUtility.inputValidString("Please enter surname");
        String phone = inputCheckUtility.inputValidPhone("Please enter phone");
        String personalCode = inputCheckUtility.inputValidPersonalCode("Please enter personal code");
        String doctor = inputCheckUtility.inputValidString("Please enter doctor's surname");

        Patient patient = new Patient(name, surname, phone, personalCode, doctor);

        if (cardDatabase.addPatient(patient)){
            System.out.println("Patient added");
        }else{
            System.out.println("The same patient is in database");
        }

    }

}

