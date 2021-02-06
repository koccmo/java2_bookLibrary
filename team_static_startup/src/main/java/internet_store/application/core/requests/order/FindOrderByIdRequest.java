package internet_store.application.core.requests.order;

public class FindOrderByIdRequest {

    private Long id;

    public FindOrderByIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
