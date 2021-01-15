package lv.estore.app.matchers;

import org.mockito.ArgumentMatcher;

public class NameMatcher implements ArgumentMatcher<String> {

    private String name;

    public NameMatcher(String name) {
        this.name = name;
    }

    @Override
    public boolean matches(String argument) {
        return argument.equals(name);
    }
}
