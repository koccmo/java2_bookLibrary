package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;

public class RegistrationRequest {
    private final User user;

    public RegistrationRequest(User user) {
        this.user = user;
    }

    public User getUser () {
        return user;
    }
}
