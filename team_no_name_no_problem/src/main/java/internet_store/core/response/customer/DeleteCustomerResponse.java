package internet_store.core.response.customer;

import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class DeleteCustomerResponse extends CoreResponse {

        private long id;

        public DeleteCustomerResponse(List<CoreError> errors){
            super(errors);
        }

        public DeleteCustomerResponse(long id){
            this.id = id;
        }

        public long getId(){
            return id;
        }
}
