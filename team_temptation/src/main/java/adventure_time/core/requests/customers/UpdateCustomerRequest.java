package adventure_time.core.requests.customers;

public class UpdateCustomerRequest {

    private String name;
    private String email;
    private String phone;
    private String passwordOne;
    private String passwordTwo;
    private Long id;

    public UpdateCustomerRequest(String name, String email, String phone, String passwordOne, String passwordTwo, Long id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.passwordOne = passwordOne;
        this.passwordTwo = passwordTwo;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPasswordOne() {
        return passwordOne;
    }

    public String getPasswordTwo() {
        return passwordTwo;
    }

    public Long getId() {
        return id;
    }
}
