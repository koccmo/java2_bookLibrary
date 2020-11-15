package internet_store.core.requests.product;

public class FindByIdRequest {

    private long id;

    public FindByIdRequest(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }
}
