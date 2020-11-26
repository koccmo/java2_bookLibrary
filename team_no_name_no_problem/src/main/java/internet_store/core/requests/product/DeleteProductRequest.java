package internet_store.core.requests.product;

public class DeleteProductRequest {

    private Long id;

    public DeleteProductRequest(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

}
