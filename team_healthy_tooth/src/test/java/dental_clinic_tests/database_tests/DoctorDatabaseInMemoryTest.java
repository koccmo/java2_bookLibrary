package dental_clinic_tests.database_tests;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.database.doctor.DoctorDatabase;
import dental_clinic.core.database.doctor.DoctorDatabaseInMemory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DoctorDatabaseInMemoryTest {

    DoctorDatabase doctorDatabase = new DoctorDatabaseInMemory();

    @Before
    public void init(){
        doctorDatabase.addDoctor(new Doctor("Dok", "Dokis", "12345678"));
    }

    @Test
    public void testGetDoctorList(){
        assertTrue(doctorDatabase.getDoctorList().size() == 4);
        assertTrue((doctorDatabase.getDoctorList().get(3).getName().equals("Dok")));
    }

    @Test
    public void addDoctor() {
        doctorDatabase.addDoctor(new Doctor("Name", "Surname", "12345678"));
        assertTrue(doctorDatabase.getDoctorList().size() == 5);
        assertTrue((doctorDatabase.getDoctorList().get(4).getName().equals("Name")));
        assertTrue(doctorDatabase.getDoctorList().get(4).getId().equals(5L));
    }

    @Test
    public void testDeleteDoctorById() {
        doctorDatabase.deleteDoctorById(4L);
        assertTrue(doctorDatabase.getDoctorList().size() == 4);
        assertFalse(doctorDatabase.getDoctorList().get(3).getIsEmployed());
    }

    @Test
    public void testContainsDoctor() {
        assertTrue(doctorDatabase.containsDoctor(new Doctor("Dok", "Dokis", "12345678")));
        assertFalse(doctorDatabase.containsDoctor(new Doctor("Dokis", "Dokiss", "12345678")));
    }

    @Test
    public void testContainsId() {
        assertTrue(doctorDatabase.containsId(1L));
        assertFalse(doctorDatabase.containsId(8L));
    }
}
