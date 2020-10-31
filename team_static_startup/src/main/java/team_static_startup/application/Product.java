package team_static_startup.application;

import java.util.Objects;

class Product {

    private String productName;
    private String productDescription;

    public Product(String productName, String productDescription) {
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName.toLowerCase(), product.productName.toLowerCase()) &&
                Objects.equals(productDescription.toLowerCase(), product.productDescription.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productDescription);
    }
}
