package internet_store.core.response.customer;

import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class DeleteCustomerResponse extends CoreResponse {

        private Long id;

        public DeleteCustomerResponse(List<CoreError> errors){
            super(errors);
        }

        public DeleteCustomerResponse(Long id){
            this.id = id;
        }

        public Long getId(){
            return id;
        }
}
