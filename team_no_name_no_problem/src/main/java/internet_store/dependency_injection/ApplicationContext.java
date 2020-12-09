package internet_store.dependency_injection;

import internet_store.core.services.customer.*;
import internet_store.core.services.customer.validators.*;
import internet_store.core.services.order.GetOrdersService;
import internet_store.core.services.order.validators.GetOrdersRequestValidator;
import internet_store.core.services.product.*;
import internet_store.core.services.product.validators.*;
import internet_store.database.customer.CustomerDatabase;
import internet_store.database.customer.CustomerDatabaseImpl;
import internet_store.database.order.OrderDatabase;
import internet_store.database.order.OrderDatabaseImpl;
import internet_store.database.product.ProductDatabase;
import internet_store.database.product.ProductDatabaseImpl;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {}

    public void addBean(Class beanClass, Object beanInstance) {
        beans.put(beanClass, beanInstance);
        Class[] instanceInterfaces = beanClass.getInterfaces();
        for (int i = 0; i < instanceInterfaces.length; i++) {
            Class instanceInterface = instanceInterfaces[i];
            Object instance = getBean(instanceInterface);
            if (instance == null) {
                beans.put(instanceInterface, beanInstance);
            }
        }
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }

    public Map<Class, Object> getBeans() {
        return beans;
    }
}
