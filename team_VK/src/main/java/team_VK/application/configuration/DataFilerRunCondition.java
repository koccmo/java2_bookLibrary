package team_VK.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DataFilerRunCondition implements Condition {

    @Value("${dataFiler.run}")
    private boolean toRun;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return toRun;
    }
}
