package dental_clinic_tests.core.services_tests.patient;


import dental_clinic.core.requests.patient.ChangePersonalDataRequest;
import dental_clinic.core.responses.patient.ChangePersonalDataResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.services.patient.ChangePersonalDataService;
import dental_clinic.core.validators.patient.ChangePersonalDataValidator;
import dental_clinic.core.database.patient.PatientRepository;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
 public class ChangePersonalDataServiceTest {
    @Mock private PatientRepository patientRepository;
    @Mock private ChangePersonalDataValidator changePersonalDataValidator;
    @InjectMocks private ChangePersonalDataService changePersonalDataService;

   @Test
   public void testChangeName()  {

       String updatedName = "NewName";
       CoreError expectedError = new CoreError("", "");
       List<CoreError> errors = new ArrayList<>();
       errors.add(expectedError);;

       ChangePersonalDataRequest changePersonalDataRequest = new ChangePersonalDataRequest(1L,updatedName,"");
       Mockito.when(changePersonalDataValidator.validate(changePersonalDataRequest)).thenReturn(errors);
       ChangePersonalDataResponse changePersonalDataResponse = changePersonalDataService.execute(changePersonalDataRequest);

       assertTrue(changePersonalDataResponse.getErrors().equals(errors));
       Mockito.verifyNoInteractions(patientRepository);
    }

   @Test
   public void testChangePhone()  {

    String updatedPhone = "NewPhone";
    CoreError expectedError = new CoreError("", "");
    List<CoreError> errors = new ArrayList<>();
    errors.add(expectedError);;

    ChangePersonalDataRequest changePersonalDataRequest = new ChangePersonalDataRequest(1L,"",updatedPhone);
    Mockito.when(changePersonalDataValidator.validate(changePersonalDataRequest)).thenReturn(errors);
    ChangePersonalDataResponse changePersonalDataResponse = changePersonalDataService.execute(changePersonalDataRequest);

    assertTrue(changePersonalDataResponse.getErrors().equals(errors));
    Mockito.verifyNoInteractions(patientRepository);
   }

 }