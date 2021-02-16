package internet_store.core.requests.product;

public class FindByIdRequest {

    private Long id;

    public FindByIdRequest() { }

    public FindByIdRequest(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
