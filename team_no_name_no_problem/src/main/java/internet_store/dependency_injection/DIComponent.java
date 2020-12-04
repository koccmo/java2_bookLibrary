package internet_store.dependency_injection;


import internet_store.core.services.customer.*;
import internet_store.core.services.product.*;
import internet_store.database.customer.CustomerDatabase;
import internet_store.database.customer.CustomerDatabaseImpl;
import internet_store.database.product.ProductDatabase;
import internet_store.database.product.ProductDatabaseImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DIComponent {
}

//@Retention(RetentionPolicy.RUNTIME) - означает, что информация об
//этой аннотации будет доступна в момент выполнения приложения.
//
//@Target(ElementType.TYPE) - означает, что эту аннотацию можно
//использовать только для классов.