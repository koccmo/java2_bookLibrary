package internet_store.application.core.requests.product;

import java.math.BigDecimal;

public class UpdateProductRequest {

    private Long id;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;

    public UpdateProductRequest() { }

    public UpdateProductRequest(Long id, String productName, String productDescription, BigDecimal productPrice) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

}
