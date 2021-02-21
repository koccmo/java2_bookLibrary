package internet_store.core.requests.product;

public class SearchProductByIdRequest {

    private Long id;

    public SearchProductByIdRequest() { }

    public SearchProductByIdRequest(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
