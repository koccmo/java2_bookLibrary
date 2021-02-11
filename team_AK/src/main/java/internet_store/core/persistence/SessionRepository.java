package internet_store.core.persistence;

import internet_store.core.domain.Client;
import internet_store.core.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Boolean existsBySessionId(String sessionId);

    @Query(value = "SELECT * FROM session WHERE session_id=? GROUP BY client_id",nativeQuery = true)
    Client getClientBySessionId(String sessionId);
}