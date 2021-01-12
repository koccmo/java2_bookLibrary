package lv.estore.app.core.request;

public class NameRequest {

    private final String name;

    public NameRequest(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
