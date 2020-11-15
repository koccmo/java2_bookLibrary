package internet_store.core.requests.product;

public class FindAnyByTitleRequest {

    private String title;

    public FindAnyByTitleRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
