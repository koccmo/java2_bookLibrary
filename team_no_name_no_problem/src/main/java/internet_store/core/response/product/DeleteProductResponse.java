package internet_store.core.response.product;

import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class DeleteProductResponse extends CoreResponse {

    private Long id;

    public DeleteProductResponse(List<CoreError> errors){
        super(errors);
    }

    public DeleteProductResponse(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

}
