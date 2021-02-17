package internet_store.core.response.product;

import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class DeleteProductByIdResponse extends CoreResponse {

    private Long id;

    public DeleteProductByIdResponse(List<CoreError> errors){
        super(errors);
    }

    public DeleteProductByIdResponse(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

}
