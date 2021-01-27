package java2.application_target_list.core.matchers;

import java2.application_target_list.core.domain.Target;
import org.mockito.ArgumentMatcher;

import java.math.BigInteger;

public class TargetMatcher implements ArgumentMatcher<Target> {

    private String name;
    private String description;
    private Long deadline;


    public TargetMatcher(String name, String description, Long deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public boolean matches(Target target) {
        return target.getName().equals(name)
                && target.getDescription().equals(description)
                && target.getDeadline().equals(deadline);
    }
}
