package internet_store.application.core.requests.order;

public class FindOrderByIdRequest {

    private Long id;

    public FindOrderByIdRequest() { }

    public FindOrderByIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
