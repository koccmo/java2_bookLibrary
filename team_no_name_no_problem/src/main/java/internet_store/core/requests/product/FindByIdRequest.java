package internet_store.core.requests.product;

public class FindByIdRequest {

    private Long id;

    public FindByIdRequest(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }
}
