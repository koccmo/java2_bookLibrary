package internet_store.web_ui.servlet;

import internet_store.core.request.session.RestoreRequest;
import internet_store.core.service.session.SessionService;
import internet_store.web_ui.cookies.Cookies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@WebServlet(name = "estore", urlPatterns = {"/"}, loadOnStartup = 1)
public class EstoreServlet extends HttpServlet {
    @Autowired
    SessionService session;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Cookies cookies = new Cookies(request, response);

        if (request.getSession().isNew()) {
            session.restoreClientLastSession(new RestoreRequest(cookies.getSessionId()));
        }
    }
}