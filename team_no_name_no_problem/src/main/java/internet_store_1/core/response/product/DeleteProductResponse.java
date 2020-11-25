package internet_store_1.core.response.product;

import internet_store_1.core.response.CoreError;
import internet_store_1.core.response.CoreResponse;

import java.util.List;

public class DeleteProductResponse extends CoreResponse {

    private long id;

    public DeleteProductResponse(List<CoreError> errors){
        super(errors);
    }

    public DeleteProductResponse(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

}
