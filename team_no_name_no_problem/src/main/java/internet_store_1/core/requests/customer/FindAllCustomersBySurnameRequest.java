package internet_store_1.core.requests.customer;

public class FindAllCustomersBySurnameRequest {

    private String surname;

    public FindAllCustomersBySurnameRequest(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

}
