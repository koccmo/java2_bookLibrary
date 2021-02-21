package internet_store.core.requests.customer;

public class DeleteCustomerRequest {

    private Long id;

    public DeleteCustomerRequest () {}

    public DeleteCustomerRequest(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
