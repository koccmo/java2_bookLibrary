package adventure_time.core.database.events;

import adventure_time.core.domain.Events;
import adventure_time.core.requests.events.SearchEventRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmEventRepositoryImpl implements EventRepository {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public boolean add(Events event) {
        sessionFactory.getCurrentSession().save(event);
        return true;
    }

    @Override
    public boolean removeByName(String eventName) {
        return false;
    }

    @Override
    public boolean removeById(Long id) {
        return false;
    }

    @Override
    public List<Events> getEventsList() {
        return null;
    }

    @Override
    public Optional<Events> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean updateEvent(Events event) {
        return false;
    }

    @Override
    public List<Events> findByEventKind(SearchEventRequest request) {
        return null;
    }

    @Override
    public List<Events> findByEventKindAndRoute(SearchEventRequest request) {
        return null;
    }

    @Override
    public List<Events> findByEventKindAndDuration(SearchEventRequest request) {
        return null;
    }

    @Override
    public List<Events> findByEventKindAndDurationAndRoute(SearchEventRequest request) {
        return null;
    }

    @Override
    public List<Events> findByEventDuration(SearchEventRequest request) {
        return null;
    }

    @Override
    public List<Events> findByEventDurationAndRoute(SearchEventRequest request) {
        return null;
    }

    @Override
    public List<Events> findByRoute(SearchEventRequest request) {
        return null;
    }
}
