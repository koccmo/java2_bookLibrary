package lv.estore.app.matchers;

import org.mockito.ArgumentMatcher;

public class IdMatcher implements ArgumentMatcher<Long> {

    private Long id;

    public IdMatcher(Long id) {
        this.id = id;
    }

    @Override
    public boolean matches(Long argument) {
        return argument.equals(id);
    }
}
