package internet_store.database.order;

import internet_store.core.domain.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class OrmOrderRepository implements OrderDatabase{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        List<ShoppingCart> shoppingCarts = sessionFactory.getCurrentSession()
                .createQuery("SELECT s FROM Shopping_cart s", ShoppingCart.class)
                .getResultList();
        for (ShoppingCart shoppingCart : shoppingCarts){
            List<ShoppingCartItem> shoppingCartItems = (List<ShoppingCartItem>) sessionFactory.getCurrentSession()
                    .createQuery("SELECT s FROM Shopping_cart_item s WHERE shopping_cart_id = " +
                            shoppingCart.getId());
            Order order = new Order(shoppingCart.getCustomer(), saveShoppingCartToMap(shoppingCartItems), shoppingCart.getSumTotal());
            orders.add(order);
        }
        return orders;
    };

    @Override
    public void addOrder(Order order) {
        for(ShoppingCart shoppingCart : )
                sessionFactory.getCurrentSession().save(ShoppingCart);
    };

    private Map<Product, Integer> saveShoppingCartToMap(List<ShoppingCartItem> items){
        Map< Product, Integer> result = new HashMap<>();
        for (ShoppingCartItem shoppingCartItem : items){
            result.put(shoppingCartItem.getProduct(), shoppingCartItem.getQuantity());
        }
        return result;
    }
}
