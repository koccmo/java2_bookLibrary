package application_target_list.core.matchers;

import application_target_list.core.database.Target;
import org.mockito.ArgumentMatcher;

public class TargetMatcher implements ArgumentMatcher<Target> {

    private String name;
    private String description;
    private Integer deadline;


    public TargetMatcher(String name, String description, Integer deadline) {
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
