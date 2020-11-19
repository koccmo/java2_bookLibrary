package internet_store.application.core.responses;

import internet_store.application.core.domain.Product;

import java.util.List;

public class FindByProductNameResponse extends CoreResponse {

    private final List<Product> productNameList;
    private final List<CoreError> coreErrorList;


    public FindByProductNameResponse(ResponseBuilder builder) {
        this.productNameList = builder.foundProductList;
        this.coreErrorList = builder.foundErrorList;
    }

    public List<Product> getProductNameList() {
        return productNameList;
    }

    public List<CoreError> getCoreErrorList() {
        return coreErrorList;
    }

    public static class ResponseBuilder {
        private List<Product> foundProductList;
        private List<CoreError> foundErrorList;

        public ResponseBuilder foundProductList() {
            return this;
        }

        public ResponseBuilder withListOfFoundProducts(List<Product> listOfFoundProducts) {
            this.foundProductList = listOfFoundProducts;
            return this;
        }

        public ResponseBuilder withListOfErrors(List<CoreError> errors) {
            this.foundErrorList = errors;
            return this;
        }

        public FindByProductNameResponse build() {
            return new FindByProductNameResponse(this);
        }
    }

}

