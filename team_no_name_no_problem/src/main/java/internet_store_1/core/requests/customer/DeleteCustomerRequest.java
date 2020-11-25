package internet_store_1.core.requests.customer;

public class DeleteCustomerRequest {

    private Long id;

    public DeleteCustomerRequest(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

}
