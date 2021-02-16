package lv.estore.app.core.database.deals;

import lv.estore.app.core.domain.Deal;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrmDealsRepository implements DealsRepository{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Long addDeal(Deal deal) {
        return (Long) sessionFactory.getCurrentSession().save(deal);
    }

    @Override
    public Deal findDealById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Deal d where id = :id");
        query.setParameter("id", id);
        return (Deal) query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public List<Deal> findDealsByUserId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Deal d where user_id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Deal> findDealsByProductId(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "SELECT d FROM Deal d where product_id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Deal> getAllDeals() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT d FROM Deal d", Deal.class)
                .getResultList();
    }

    @Override
    public boolean removeDealByID(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE Deal where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public boolean updateDealById(Long id, Long userId, Long productId, String status) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "UPDATE Deal SET user_id = :userId, " +
                        "product_id = :productId, status = :status WHERE id = :id");
        query.setParameter("id", id);
        query.setParameter("userId", userId);
        query.setParameter("productId", productId);
        query.setParameter("status", status);
        int result = query.executeUpdate();
        return result == 1;
    }
}
