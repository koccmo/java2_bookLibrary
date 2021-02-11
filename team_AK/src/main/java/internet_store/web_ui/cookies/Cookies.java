package internet_store.web_ui.cookies;

import lombok.Getter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class Cookies {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private Cookie[] cookies;
    @Getter
    private String sessionId = "";

    public Cookies(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        getCookies();
        findClientId();
        setClientId();
    }

    private void getCookies() {
        cookies = request.getCookies();
    }

    private void findClientId() {
        if (cookies != null) {
            Optional<Cookie> optionalCookie = Arrays.stream(cookies).filter(c -> c.getName()
                    .equals("clientId")).findFirst();
            optionalCookie.ifPresentOrElse(cookie -> sessionId = cookie.getValue(),
                    () -> sessionId = "");
        }
    }

    private void setClientId() {
        final int STORE_COOKIE_ONE_DAY = 24 * 60 * 60;

        String sessionId = request.getSession().getId();

        if (sessionId.equals("")) {

            Cookie cookie = new Cookie("clientId", sessionId);
            this.sessionId = sessionId;

            cookie.setMaxAge(STORE_COOKIE_ONE_DAY);
            response.addCookie(cookie);
        }
    }
}