package internet_store.core.requests.customer;

public class FindAllCustomersByNameAndSurnameRequest {

    private String name;
    private String surname;

    public FindAllCustomersByNameAndSurnameRequest(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}
