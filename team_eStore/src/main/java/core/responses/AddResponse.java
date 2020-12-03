package core.responses;

public class AddResponse extends CoreResponse{
    private boolean isAdded;

    public AddResponse(Errors errors) {
        super(errors);
    }

    public AddResponse(boolean isAdded) {
        this.isAdded = isAdded;
    }

    public boolean isProductAdded(){
        return isAdded;
    }
}
