package internet_store.core.requests.customer;

public class DeleteCustomerRequest {

    private Long id;

    public DeleteCustomerRequest(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

}
