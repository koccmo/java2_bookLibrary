package dental_clinic.core.database.patient;

import dental_clinic.core.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmPatientRepositoryImpl implements PatientRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Patient> getPatients() {
        List <PersonalData> personalDataList = sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM PersonalData p", PersonalData.class)
                .getResultList();

        List<Patient> patientList = new ArrayList<>();
        for (PersonalData personalData : personalDataList) {
            String sql = "SELECT v FROM Visit v WHERE id =" + personalData.getId();
            List<Visit>visits = sessionFactory.getCurrentSession()
                    .createQuery(sql, Visit.class)
                    .getResultList();
            JowlEntity jowlEntity = sessionFactory.getCurrentSession().get(JowlEntity.class, personalData.getId());
            Patient patient = new Patient(personalData, jowlEntity,visits);
            patientList.add(patient);
        }
        return patientList;
    }

    @Override
    public void addPatient(PersonalData personalData) {
        sessionFactory.getCurrentSession().save(personalData);
    }

    @Override
    public void deletePatient(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Patient WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public PersonalData getPersonalDataById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PersonalData p WHERE id = :id");
        query.setParameter("id", id);
        return (PersonalData)query.getResultList().get(0);
    }

    @Override
    public List<PersonalData> findPatientsBySurname(String surname) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PersonalData p WHERE surname = :surname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public List<PersonalData> findPatientsByPersonalCode(String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PersonalData p WHERE personalCode = :personalCode");
        query.setParameter("personalCode", personalCode);
        return query.getResultList();
    }

    @Override
    public boolean containsPatientWithSpecificId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PersonalData p WHERE id = :id");
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }

    @Override
    public Optional<Patient> getPatientCard(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PersonalData p WHERE id = :id");
        query.setParameter("id", id);
        PersonalData personalData = (PersonalData)query.getResultList().get(0);
        String sql = "SELECT v FROM Visit v WHERE id =" + personalData.getId();
        List<Visit>visits = sessionFactory.getCurrentSession()
                .createQuery(sql, Visit.class)
                .getResultList();
        JowlEntity jowlEntity = sessionFactory.getCurrentSession().get(JowlEntity.class, personalData.getId());
        Patient patient = new Patient(personalData, jowlEntity,visits);
        return Optional.ofNullable(patient);
    }

    @Override
    public void changeSurname(Long idToSearch, String updatedSurname) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE PersonalData SET surname = :updatedSurname " +
                "WHERE id = :id");
        query.setParameter("updatedSurname", updatedSurname);
        query.setParameter("id", idToSearch);
        query.executeUpdate();
    }

    @Override
    public void changePhone(Long idToSearch, String updatedSurname) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE PersonalData SET phone = :updatedSurname " +
                "WHERE id = :id");
        query.setParameter("updatedSurname", updatedSurname);
        query.setParameter("id", idToSearch);
        query.executeUpdate();
    }

    @Override
    public boolean containsSpecificPersonalData(PersonalData personalData) {
        return sessionFactory.getCurrentSession().contains(personalData);
    }
}
