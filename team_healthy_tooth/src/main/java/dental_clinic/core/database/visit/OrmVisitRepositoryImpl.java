package dental_clinic.core.database.visit;

import dental_clinic.core.domain.Visit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateFromText= getDayFromInStringFormat(dayFrom) + "-" + getMonthFromInStringFormat(monthFrom) + "-2021";
        String dateToText = getDayToInStringFormat(dayTo) + "-" + getMonthToInStringFormat(monthTo) + "-2021";

        try {
            Date dateFrom = simpleDateFormat.parse(dateFromText);
            Date dateTo = simpleDateFormat.parse(dateToText);
            List <Visit> visitList = new ArrayList<>();
            for (Visit visit : searchAllVisits()) {
                if (visit.getDate().after(dateFrom) && visit.getDate().before(dateTo)) {
                    visitList.add(visit);
                }
            }
            return visitList;
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    private List<Visit> searchAllVisits() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT v FROM Visit v", Visit.class)
                .getResultList();
    }

    private String getDayFromInStringFormat(int dayFrom) {
        String dayFromText;
        if (dayFrom <10) {
            dayFromText = "0" + dayFrom;
        } else {
            dayFromText = "" + dayFrom;
        }
        return dayFromText;
    }

    private String getMonthFromInStringFormat(int monthFrom) {
        String monthFromText;
        if (monthFrom < 10) {
            monthFromText = "0" + monthFrom;
        } else {
            monthFromText = "" + monthFrom;
        }
        return monthFromText;
    }

    private String getDayToInStringFormat(int dayTo) {
        String dayToText;
        if (dayTo < 10) {
            dayToText = "0" + dayTo;
        } else {
            dayToText = "" + dayTo;
        }
        return dayToText;
    }

    private String getMonthToInStringFormat(int monthTo) {
        String monthToText;
        if (monthTo < 10) {
            monthToText = "0" + monthTo;
        } else {
            monthToText = "" + monthTo;
        }
        return monthToText;
    }


}
