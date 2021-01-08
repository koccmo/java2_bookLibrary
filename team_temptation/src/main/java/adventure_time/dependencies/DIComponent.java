package adventure_time.dependencies;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // момент жизни программы, когда доступна эта аннотация, в д.с. runtime

@Target(ElementType.TYPE)           // аннотацию можно использовать только для классов (TYPE)

public @interface DIComponent {
}
