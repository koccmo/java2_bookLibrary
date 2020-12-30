package estore.dependency_injection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//информация об этой аннотации будет доступна в момент инициализации приложения
@Retention(RetentionPolicy.RUNTIME)
//эту аннотацию можно использовать только для классов.
@Target(ElementType.TYPE)
public @interface DIComponent {
}
