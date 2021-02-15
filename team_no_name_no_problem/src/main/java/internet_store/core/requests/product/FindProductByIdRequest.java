package internet_store.core.requests.product;

public class FindProductByIdRequest {

    private Long id;

    public FindProductByIdRequest() { }

    public FindProductByIdRequest(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
