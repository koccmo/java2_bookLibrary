package dental_clinic_tests.acceptance_tests;

import dental_clinic.config.DentalClinicSpringCoreConfiguration;
import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.doctor.AddDoctorRequest;
import dental_clinic.core.requests.doctor.FillDoctorsWorkGraphicRequest;
import dental_clinic.core.requests.doctor.GetDoctorListRequest;
import dental_clinic.core.requests.patient.AddPatientRequest;
import dental_clinic.core.requests.plannedVisit.AddPlannedVisitRequest;
import dental_clinic.core.requests.plannedVisit.GetPlannedVisitsRequest;
import dental_clinic.core.responses.doctor.GetDoctorListResponse;
import dental_clinic.core.responses.planned_visit.GetPlannedVisitsResponse;
import dental_clinic.core.services.doctor.AddDoctorService;
import dental_clinic.core.services.doctor.FillDoctorsWorkGraphicService;
import dental_clinic.core.services.doctor.GetDoctorListService;
import dental_clinic.core.services.patient.AddPatientService;
import dental_clinic.core.services.planned_visit.AddPlannedVisitService;
import dental_clinic.core.services.planned_visit.GetPlannedVisitsService;
import dental_clinic.core.DatabaseCleanerClinic;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/*
import static org.junit.Assert.assertTrue;

public class AcceptanceTest8 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(DentalClinicSpringCoreConfiguration.class);
        getDatabaseCleaner().clean();
    }

    @Test
    public void test(){
        PersonalData personalData1 = new PersonalData("Name", "Surname", "12345678", "25024512348");
        PersonalData personalData2 = new PersonalData("NameM", "SurnameM", "12345675", "25024512345");

        AddPatientRequest addPatientRequest1 = new AddPatientRequest(personalData1);
        addPatientService().execute(addPatientRequest1);

        AddPatientRequest addPatientRequest2 = new AddPatientRequest(personalData2);
        addPatientService().execute(addPatientRequest2);

        AddDoctorRequest addDoctorRequest = new AddDoctorRequest(new Doctor("Doctor", "Hausss", "12345678"));
        addDoctorService().execute(addDoctorRequest);

        GetDoctorListRequest getDoctorListRequest = new GetDoctorListRequest();
        GetDoctorListResponse getDoctorListResponse = getDoctorListService().execute(getDoctorListRequest);
        Long id = getDoctorListResponse.getDoctorAndGraphic().stream()
                .filter(doctorAndGraphic -> doctorAndGraphic.getDoctor().getSurname().equals("Hausss"))
                .map(doctorAndGraphic -> doctorAndGraphic.getDoctor().getId())
                .findAny().get();

        FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest = new FillDoctorsWorkGraphicRequest(id, 3, "14:00", "18:00");
        fillDoctorsWorkGraphicService().execute(fillDoctorsWorkGraphicRequest);

        fillDoctorsWorkGraphicRequest = new FillDoctorsWorkGraphicRequest(id, 6, "14:00", "18:00");
        fillDoctorsWorkGraphicService().execute(fillDoctorsWorkGraphicRequest);

        AddPlannedVisitRequest addPlannedVisitRequest1 = new AddPlannedVisitRequest(true, "25-08-2021 14:30", personalData1, id);
        AddPlannedVisitRequest addPlannedVisitRequest2 = new AddPlannedVisitRequest(true,"25-09-2021 15:30", personalData2, id);
        AddPlannedVisitRequest addPlannedVisitRequest3 = new AddPlannedVisitRequest(false,"25-08-2021 14:30", personalData2, id);

        addPlannedVisitService().execute(addPlannedVisitRequest1);
        addPlannedVisitService().execute(addPlannedVisitRequest2);
        addPlannedVisitService().execute(addPlannedVisitRequest3);

        GetPlannedVisitsRequest getPlannedVisitsRequest = new GetPlannedVisitsRequest();
        GetPlannedVisitsResponse getPlannedVisitsResponse = getPlannedVisitsService().execute(getPlannedVisitsRequest);

        assertTrue(getPlannedVisitsResponse.getPlannedVisits().size() == 3);////StrangeTest
    }

    private AddPatientService addPatientService() {
        return appContext.getBean(AddPatientService.class);
    }

    private AddPlannedVisitService addPlannedVisitService() {
        return appContext.getBean(AddPlannedVisitService.class);
    }

    private FillDoctorsWorkGraphicService fillDoctorsWorkGraphicService() {
        return appContext.getBean(FillDoctorsWorkGraphicService.class);
    }

    private GetPlannedVisitsService getPlannedVisitsService() {
        return appContext.getBean(GetPlannedVisitsService.class);
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
}*/
