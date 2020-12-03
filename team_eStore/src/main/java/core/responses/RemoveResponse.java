package core.responses;

public class RemoveResponse extends CoreResponse{
    private boolean productRemoved;

    public RemoveResponse(boolean productRemoved) {
        this.productRemoved = productRemoved;
    }

    public RemoveResponse(Errors errors) {
        super(errors);
    }

    public boolean isProductRemoved() {
        return productRemoved;
    }
}
