package lesson_3.core.domain;

import lombok.Data;

@Data
public class Client {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Cart clientCart;
}