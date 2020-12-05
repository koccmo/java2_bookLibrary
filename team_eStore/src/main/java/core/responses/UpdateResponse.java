package core.responses;

public class UpdateResponse extends CoreResponse{
    private boolean productUpdated;

    public UpdateResponse(boolean productUpdated) {
        this.productUpdated = productUpdated;
    }

    public UpdateResponse(Errors errors) {
        super(errors);
    }

    public boolean isProductUpdated() {
        return productUpdated;
    }
}
