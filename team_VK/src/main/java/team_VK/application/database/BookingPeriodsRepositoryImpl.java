package team_VK.application.database;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team_VK.application.core.domain.Book;
import team_VK.application.core.domain.BookingPeriod;

import java.util.List;

@Component
@Transactional
public class BookingPeriodsRepositoryImpl implements BookingPeriodsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addBooking(BookingPeriod bookingPeriod) {
        sessionFactory.getCurrentSession().save(bookingPeriod);
    }

    @Override
    public boolean removeBooking(BookingPeriod bookingPeriod) {
        return false;
    }

    @Override
    public List<BookingPeriod> getBookingsList(Book book) {
        return null;
    }

    public BookingPeriod getById(Long id) {
        return sessionFactory.getCurrentSession()
                .get(BookingPeriod.class, id);
    }

}
