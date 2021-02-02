package dental_clinic.core.database.planned_visit;

import dental_clinic.core.domain.PlannedVisit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class OrmPlannedVisitRepositoryImpl implements PlannedVisitsRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<PlannedVisit> getPlannedVisits() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT p FROM PlannedVisit p", PlannedVisit.class)
                .getResultList();
    }

    @Override
    public void addPlannedVisit(PlannedVisit plannedVisit) {
        sessionFactory.getCurrentSession().save(plannedVisit);
    }

    @Override
    public void cancelPlannedVisit(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE PlannedVisit WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void changePlannedVisitTime(Long id, Date visitTime) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE PlannedVisit SET dateAndTime = :visitTime " +
                "WHERE person_id = :id");
        query.setParameter("visitTime", visitTime);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean containsId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PlannedVisit WHERE id = :id");
        query.setParameter("id", id);
        return !query.getResultList().isEmpty();
    }

    @Override
    public boolean containsPlannedVisitInTheSameTimeTheSameDoctor(PlannedVisit plannedVisit) {
        return sessionFactory.getCurrentSession().contains(plannedVisit);
    }

    @Override
    public List<PlannedVisit> searchPlannedVisitsByPersonalCode(String personalCode) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PlannedVisit WHERE personalCode = :personalCode");
        query.setParameter("personalCode", personalCode);
        return query.getResultList();
    }

    @Override
    public List<PlannedVisit> searchPlannedVisitsByDate(int dayFrom, int dayTo, int monthFrom, int monthTo) {
        Date dateFrom = new Date(2021, monthFrom, dayFrom);
        Date dateTo = new Date(2021, monthTo, dayTo);
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT p FROM PlannedVisit p WHERE dateAndTime > :dateFrom AND" +
                        "dateAndTime < :dateTo");
        query.setParameter("dateFrom", dateFrom);
        query.setParameter("dateTo", dateTo);
        return query.getResultList();
    }
}
