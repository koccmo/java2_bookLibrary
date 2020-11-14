package internet_store.core.requests.customer;

public class FindCustomerByNameRequest {

    private String name;

    public FindCustomerByNameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
