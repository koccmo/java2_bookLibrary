package adventure_time.core.requests.customers;

public class LoginCustomerRequest {

    private final String email;
    private final String password;

    public LoginCustomerRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
