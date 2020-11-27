package internet_store.core.domain;

import internet_store.database.cart_database.InnerCartDatabaseImpl;
import lombok.Data;

@Data
public class Client {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private InnerCartDatabaseImpl clientCartImpl;
}