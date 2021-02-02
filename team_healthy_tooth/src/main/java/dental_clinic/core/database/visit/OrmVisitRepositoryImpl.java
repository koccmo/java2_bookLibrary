package dental_clinic.core.database.visit;

import dental_clinic.core.domain.Visit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class OrmVisitRepositoryImpl implements VisitRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addVisit(Visit visit) {
        sessionFactory.getCurrentSession().save(visit);
    }

    @Override
    public List<Visit> searchVisitByPatientId(Long patientsId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE patient_id = :patient_id");
        query.setParameter("patient_id", patientsId);
        return query.getResultList();
    }

    @Override
    public List<Visit> searchVisitByDate(int dayFrom, int dayTo, int monthFrom, int monthTo) {
        Date dateFrom = new Date(2021, monthFrom, dayFrom);
        Date dateTo = new Date(2021, monthTo, dayTo);
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT v FROM Visit v WHERE dateAndTime > :dateFrom AND" +
                        "dateAndTime < :dateTo");
        query.setParameter("dateFrom", dateFrom);
        query.setParameter("dateTo", dateTo);
        return query.getResultList();
    }
}
