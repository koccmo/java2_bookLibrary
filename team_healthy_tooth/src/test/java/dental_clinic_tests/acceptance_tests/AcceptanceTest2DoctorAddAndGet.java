package dental_clinic_tests.acceptance_tests;

import dental_clinic.core.DatabaseCleanerClinic;
import dental_clinic.config.DentalClinicSpringCoreConfiguration;
import dental_clinic.core.domain.Doctor;
import dental_clinic.core.requests.doctor.AddDoctorRequest;
import dental_clinic.core.requests.doctor.GetDoctorListRequest;
import dental_clinic.core.responses.doctor.GetDoctorListResponse;
import dental_clinic.core.services.doctor.AddDoctorService;
import dental_clinic.core.services.doctor.GetDoctorListService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class AcceptanceTest2DoctorAddAndGet {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(DentalClinicSpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void test(){
        Doctor doctor1 = new Doctor("Name", "Surname", "12345678");
        Doctor doctor2 = new Doctor("NameM", "SurnameM", "12345675");
        AddDoctorRequest addDoctorRequest1 = new AddDoctorRequest(doctor1);
        AddDoctorRequest addDoctorRequest2 = new AddDoctorRequest(doctor2);
        addDoctorService().execute(addDoctorRequest1);
        addDoctorService().execute(addDoctorRequest2);


        GetDoctorListRequest getDoctorListRequest = new GetDoctorListRequest();
        GetDoctorListResponse getDoctorListResponse = getDoctorListService().execute(getDoctorListRequest);

        assertTrue(getDoctorListResponse.getDoctorAndGraphic().size() == 2);
    }

    private AddDoctorService addDoctorService() {
        return appContext.getBean(AddDoctorService.class);
    }

    private GetDoctorListService getDoctorListService() {
        return appContext.getBean(GetDoctorListService.class);
    }

    private DatabaseCleanerClinic getDatabaseCleaner() {
        return appContext.getBean(DatabaseCleanerClinic.class);
    }
}
