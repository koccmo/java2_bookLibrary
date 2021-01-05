package lesson_8.mysql_spring.persistence;

import lesson_8.mysql_spring.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByName(String name);
}