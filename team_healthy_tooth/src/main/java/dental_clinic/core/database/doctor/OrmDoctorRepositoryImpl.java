package dental_clinic.core.database.doctor;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.WorkGraphic;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmDoctorRepositoryImpl implements DoctorRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Doctor> getDoctorList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT d FROM Doctor d", Doctor.class)
                .getResultList();
    }

    @Override
    public void addDoctor(Doctor doctor) {
        sessionFactory.getCurrentSession().save(doctor);
    }

    @Override
    public boolean deleteDoctorById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Doctor WHERE id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean containsDoctor(Doctor doctor) {
        return sessionFactory.getCurrentSession().contains(doctor);
    }

    @Override
    public boolean containsId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Doctor WHERE id = :id");
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }

    @Override
    public boolean specificDoctorIsEmployed(Doctor doctor) {
        String sql = "SELECT d FROM Doctor WHERE name = " + doctor.getName() +" AND surname = " + doctor.getSurname();
        return sessionFactory.getCurrentSession()
                .createQuery(sql, Doctor.class)
                .getResultList().get(0).isEmployed();
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        Doctor doctor = (Doctor) sessionFactory.getCurrentSession().createCriteria(Doctor.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(doctor);
    }

    @Override
    public void updateWorkGraphicForSpecificDate(Long id, Integer day, String timeFrom, String timeTo) {
        String sql = "UPDATE WorkGraphic SET " + getDayNameStart(day) + " = : timeFrom WHERE doctor_id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);
        query.setParameter("timeFrom", timeFrom);
        query.setParameter("id", id);
        query.executeUpdate();
        String sqlTo = "UPDATE WorkGraphic SET " + getDayNameEnd(day) + " = : timeTo WHERE doctor_id = :id";
        Query queryTo = sessionFactory.getCurrentSession().createQuery(sqlTo);
        queryTo.setParameter("timeTo", timeTo);
        queryTo.setParameter("id", id);
        queryTo.executeUpdate();
    }

    @Override
    public WorkGraphic getWorkGraphic(Doctor doctor) {
        String sql = "SELECT w FROM WorkGraphic w WHERE doctor_id = " + doctor.getId();
        return sessionFactory.getCurrentSession()
                .createQuery(sql, WorkGraphic.class)
                .getSingleResult();
    }

    private String getDayNameStart(int day) {
        switch (day) {
            case 1: return "monday_start";
            case 2: return "tuesday_start";
            case 3: return "wednesday_start";
            case 4: return "thursday_start";
            case 5: return "friday_start";
            case 6: return "saturday_start";
            default: return "sunday_start";
        }
    }

    private String getDayNameEnd(int day) {
        switch (day) {
            case 1: return "monday_end";
            case 2: return "tuesday_end";
            case 3: return "wednesday_end";
            case 4: return "thursday_end";
            case 5: return "friday_end";
            case 6: return "saturday_end";
            default: return "sunday_end";
        }
    }
}
