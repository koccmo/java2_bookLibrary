package team_VK.application.database;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team_VK.application.core.domain.Book;
import team_VK.application.core.domain.BookingPeriod;

import java.util.List;

@Component
@Transactional
public class OrmBookingPeriodsRepositoryImpl implements BookingPeriodsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addBooking(BookingPeriod bookingPeriod) {
        sessionFactory.getCurrentSession().save(bookingPeriod);
    }

    @Override
    public boolean removeBooking(BookingPeriod bookingPeriod) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM bookings where id = :id");
        query.setParameter("id", bookingPeriod.getBookID());
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<BookingPeriod> getBookingsList(Book book) {

        return sessionFactory.getCurrentSession()
                .createQuery("SELECT * FROM bookings WHERE book_id = :book_id", BookingPeriod.class)
                .setParameter("id", book.getID())
                .getResultList();
    }
}
