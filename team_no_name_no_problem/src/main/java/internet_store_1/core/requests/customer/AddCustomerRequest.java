package internet_store_1.core.requests.customer;

import internet_store_1.core.domain.Customer;

public class AddCustomerRequest {

        private Customer customer;

        public AddCustomerRequest(Customer customer){
            this.customer = customer;
        }

        public Customer getCustomer(){
            return customer;
        }
}
