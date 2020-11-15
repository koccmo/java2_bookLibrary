package internet_store.core.requests.product;

public class FindAllByTitleRequest {

    private String title;

    public FindAllByTitleRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
