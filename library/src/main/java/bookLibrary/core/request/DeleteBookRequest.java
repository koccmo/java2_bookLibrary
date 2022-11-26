package bookLibrary.core.request;

public class DeleteBookRequest {
    private String id;

    public DeleteBookRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
