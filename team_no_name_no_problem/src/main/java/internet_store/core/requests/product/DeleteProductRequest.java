package internet_store.core.requests.product;

public class DeleteProductRequest {

    private long id;

    public DeleteProductRequest(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

}
