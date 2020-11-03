package dental_clinic;

import dental_clinic.domain.PatientPersonalData;
import dental_clinic.domain.ToothStatus;

import java.util.List;
import java.util.Optional;

public class PatientDatabaseImplTest {
    private CardDatabaseImpl impl = new CardDatabaseImpl();
    private boolean isSomeTestFailed = false;

    public static void main(String[] args) {
        PatientDatabaseImplTest test = new PatientDatabaseImplTest();

        test.testAddPatient();
        test.testFindPatientBySurname();
        test.testFindPatientByPersonalCode();
        test.testUpdateJowlData();
        test.testPrintPatientHistory();
        test.testDeletePatient();

        test.finaliseTests();

    }

    //patient1 for tests 1, 2, 3, 4, 5
    PatientPersonalData patient1 = new PatientPersonalData(
            "name", "surname", "phone", "pCode"
    );

    //Test 1 : testing duplicate patient in .addPatient
    private void testAddPatient() {
        System.out.print("Testing .addPatient ... ");

        printTestResult(impl.addPatient(patient1)
        && !impl.addPatient(patient1));
    }

    //Test 2 : testing .findPatientBySurname
    private void testFindPatientBySurname() {
        System.out.print("Testing .findPatientBySurname ... ");

        List<PatientPersonalData> resultPositive = //should find patient1
                impl.findPatientBySurname("surname");

        List<PatientPersonalData> resultNegative = //should find nothing
                impl.findPatientBySurname("invalid surname");

        printTestResult(resultPositive.contains(patient1)
        && resultNegative.isEmpty());
    }

    //Test 3 : testing .findPatientByPersonalCode
    private void testFindPatientByPersonalCode() {
        System.out.print("Testing .findPatientByPersonalCode ... ");

        List<PatientPersonalData> resultPositive = //should find patient1
                impl.findPatientByPersonalCode("pCode");

        List<PatientPersonalData> resultNegative = //should find nothing
                impl.findPatientByPersonalCode("invalid pCode");

        printTestResult(resultPositive.contains(patient1)
        && resultNegative.isEmpty());
    }


    //Test 4 : testing .updateJowlData + .printPatientCardForVisit
    private void testUpdateJowlData() {
        impl.updateJowlData(1L, 21, Optional.empty(), ToothStatus.KARIES);
        impl.printPatientCardForVisit(1L);
    }

    //Task 5 : testing .printPatientHistory
    private void testPrintPatientHistory() {
        impl.printPatientHistory(1L);
    }

    //Test 6 : testing invalid patient removal in .deletePatient
    private void testDeletePatient() {
        System.out.print("Testing .deletePatient ... ");

        printTestResult(impl.deletePatient(1L)
                && !impl.deletePatient(9999L)); //id 9999 is invalid
    }

    private void printTestResult(boolean successCondition) {
        if (successCondition) {
            System.out.println("Test OK");
        }
        else {
            System.out.println("Test FAIL");
            isSomeTestFailed = true;
        }
    }

    private void finaliseTests() {
        if (!isSomeTestFailed) {
            System.out.println("All tests OK");
        }
        else {
            System.out.println("Some tests FAILED");
        }
    }

}