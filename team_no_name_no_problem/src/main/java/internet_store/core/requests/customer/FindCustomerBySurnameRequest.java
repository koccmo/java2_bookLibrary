package internet_store.core.requests.customer;

public class FindCustomerBySurnameRequest {

    private String surname;

    public FindCustomerBySurnameRequest(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
