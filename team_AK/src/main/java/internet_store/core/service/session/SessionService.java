package internet_store.core.service.session;

import internet_store.core.domain.Client;
import internet_store.core.domain.Session;
import internet_store.core.persistence.SessionRepository;
import internet_store.core.request.session.RestoreRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Getter
    private String sessionId;
    @Getter
    private Client sessionClient;

    public void restoreClientLastSession(RestoreRequest request) {
        Session session = new Session();
        sessionId = request.getSessionId();

        boolean isSessionExists = sessionRepository.existsBySessionId(sessionId);

        if (!(isSessionExists)) {
            sessionClient = new Client();
            session.setSessionId(sessionId);
            sessionRepository.save(session);
        }
    }
}