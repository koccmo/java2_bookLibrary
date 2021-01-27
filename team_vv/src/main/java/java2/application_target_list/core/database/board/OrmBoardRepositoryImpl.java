package java2.application_target_list.core.database.board;

import java2.application_target_list.core.domain.Record;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Transactional
public class OrmBoardRepositoryImpl implements BoardRepository{

    @Autowired private SessionFactory sessionFactory;

    @Override
    public void addToBoard(Record record) {
        sessionFactory.getCurrentSession().save(record);
    }

    @Override
    public boolean deleteFromBoard(Long recordId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Record WHERE id = :recordId");
        query.setParameter("recordId", recordId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Record> getAllRecordsList() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Record", Record.class)
                .getResultList();
    }

    @Override
    public boolean setRecordCompleteDate(Long recordId) {
        Query query = sessionFactory.getCurrentSession().createQuery("UPDATE Record SET target_date_of_completion =: date " +
                "WHERE id =: recordId");
        query.setParameter("date", getDate());
        query.setParameter("recordId", recordId);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean isIdInBoardList(Long boardId) {
        List<Record> records = getAllRecordsList();

        for (Record tempRecord : records) {
            if (tempRecord.getRecordId().equals(boardId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Record> getFullInfoAboutRecords() {
//        return sessionFactory.getCurrentSession()
//                .createQuery("SELECT t.target_name, t.target_description, t.target_deadline, u.user_first_name," +
//                        "u.user_last_name, r.target_added_date, r.target_date_of_completion from Record r JOIN Target JOIN User")
//                .list();
        return null;
    }

    private String getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(myFormatDate);
    }
}
