package adventure_time.core.database.events;

import adventure_time.core.domain.Events;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class EventRepositoryImpl implements BasicEventRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public boolean add(Events event) {
        sessionFactory.getCurrentSession().save(event);
        return true;
    }
}
