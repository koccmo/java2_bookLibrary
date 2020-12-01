package internet_store.core.requests.customer;

import internet_store.core.domain.Customer;

public class AddCustomerRequest {

        private Customer customer;

        public AddCustomerRequest(Customer customer){
            this.customer = customer;
        }

        public Customer getCustomer(){
            return customer;
        }


}
