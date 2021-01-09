package adventure_time.dependencies;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)          // аннтотация применима к свойствам (Fields) класса
// при этом из всех классов убирается конструктор
public @interface DIDependency {


}
