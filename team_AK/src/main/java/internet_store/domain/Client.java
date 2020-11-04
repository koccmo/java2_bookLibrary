package internet_store.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
}
