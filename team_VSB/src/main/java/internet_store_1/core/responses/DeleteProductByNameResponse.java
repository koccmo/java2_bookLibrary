package internet_store_1.core.responses;

import java.util.List;

public class DeleteProductByNameResponse extends CoreResponse {

    private boolean productDeleted;

    public DeleteProductByNameResponse(boolean productDeleted) {this.productDeleted = productDeleted; }

    public DeleteProductByNameResponse(List<CoreError> errors) { super(errors); }

    public boolean isProductDeleted() { return productDeleted; }


}
