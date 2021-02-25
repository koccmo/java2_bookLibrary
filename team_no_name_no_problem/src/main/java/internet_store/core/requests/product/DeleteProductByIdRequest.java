package internet_store.core.requests.product;

public class DeleteProductByIdRequest {

    private Long id;

    public DeleteProductByIdRequest() {}

    public DeleteProductByIdRequest(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
