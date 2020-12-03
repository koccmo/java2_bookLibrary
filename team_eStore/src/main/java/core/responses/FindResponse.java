package core.responses;

import domain.Product;

public class FindResponse extends CoreResponse{
    private Product product;

    public FindResponse(final Errors errors) {
        super(errors);
    }

    public FindResponse(final Product product) {
        this.product = product;
    }

    public Product productFound(){
        return product;
    }
}
