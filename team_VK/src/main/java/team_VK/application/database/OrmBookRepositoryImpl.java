package team_VK.application.database;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team_VK.application.core.domain.Book;

import java.util.List;



@Component
@Transactional
public class OrmBookRepositoryImpl implements BookRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addBook(Book book) {
        sessionFactory.getCurrentSession().save(book);
    }

    @Override
    public boolean deleteBook(Book book) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM books where id = :id");
        query.setParameter("id", book.getID());
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Book> getListBooks() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }
}
