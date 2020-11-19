package internet_store.core.responses;

import java.util.List;

public class DeleteProductByIdResponse extends CoreResponse {

    private boolean productDeleted;

    public DeleteProductByIdResponse(boolean productDeleted) {this.productDeleted = productDeleted; }

    public DeleteProductByIdResponse(List<CoreError> errors) { super(errors); }

    public boolean isProductDeleted() { return productDeleted; }
}
