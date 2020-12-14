package dental_clinic_tests.core.services_tests;


import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.AddPatientRequest;
import dental_clinic.core.requests.ChangePersonalDataRequest;
import dental_clinic.core.responses.ChangePersonalDataResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.services.AddPatientService;
import dental_clinic.core.services.ChangePersonalDataService;
import dental_clinic.core.services.validators.ChangePersonalDataValidator;
import dental_clinic.database.PatientDatabase;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

//@RunWith(MockitoJUnitRunner.class)
 public class ChangePersonalDataServiceTest {
   /* @Mock private PatientDatabase patientDatabase;
    @Mock private ChangePersonalDataValidator changePersonalDataValidator;
    @InjectMocks private ChangePersonalDataService changePersonalDataService;

   @Test
   public void testChangeName()  {
       PersonalData personalData = new PersonalData("Name", null, "12345678", "12345678900");
       AddPatientRequest addPatientRequest = new AddPatientRequest(personalData);
       AddPatientService addPatientService = new AddPatientService();
       addPatientService.execute(addPatientRequest);
       patientDatabase.addPatient(personalData);

       String updatedName = "NewName";
       CoreError expectedError = new CoreError("", "");
       List<CoreError> errors = new ArrayList<>();
       errors.add(expectedError);;

       ChangePersonalDataRequest changePersonalDataRequest = new ChangePersonalDataRequest(1L,updatedName,"");
       Mockito.when(changePersonalDataValidator.validate(changePersonalDataRequest)).thenReturn(errors);
       ChangePersonalDataResponse changePersonalDataResponse = changePersonalDataService.execute(changePersonalDataRequest);

       assertTrue(changePersonalDataResponse.getErrors().equals(errors));
       Mockito.verifyNoInteractions(patientDatabase);
    }*/

 }