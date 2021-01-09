package internet_store.database.orm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ORMRepo {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session session(){
        return sessionFactory.getCurrentSession();
    }
}
