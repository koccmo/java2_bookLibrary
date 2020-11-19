package internet_store.core.requests.customer;

public class FindAllCustomersByNameRequest {

    private String name;

    public FindAllCustomersByNameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
