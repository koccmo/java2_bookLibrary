package dental_clinic.core.services.matchers;

import org.mockito.ArgumentMatcher;

public class IdMatcher implements ArgumentMatcher<Long> {

    private  Long id;

    public IdMatcher(Long id) {
        this.id = id;
    }


    @Override
    public boolean matches(Long id) {
        return this.id.equals(id);
    }
}
