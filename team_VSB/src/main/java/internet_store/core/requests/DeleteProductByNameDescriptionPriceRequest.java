package internet_store.core.requests;

import java.math.BigDecimal;

public class DeleteProductByNameDescriptionPriceRequest {

    private String productName;
    private String productDescription;
    private BigDecimal productPrice;

    public DeleteProductByNameDescriptionPriceRequest(String productName, String productDescription,
                                                      BigDecimal productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public String getProductName() { return productName; }

    public String getProductDescription() { return productDescription; }

    public BigDecimal getProductPrice() { return productPrice; }
}
