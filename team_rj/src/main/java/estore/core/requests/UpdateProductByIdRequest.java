package estore.core.requests;

public class UpdateProductByIdRequest {

    private Long productId;
    private String productName;
    private String productDescription;
    private String productCategory;
    private String productQuantity;
    private String productPrice;

    public UpdateProductByIdRequest(Long productId, String productName,
                                    String productDescription, String productCategory,
                                    String productQuantity, String productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public String getProductPrice() {
        return productPrice;
    }
}
