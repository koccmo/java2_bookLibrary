package internet_store.core.requests;

public class FindProductRequest {

    private String productName;
    private String productDescription;
    private Ordering ordering;
    private Paging paging;

    public FindProductRequest(String productName, String productDescription, Ordering ordering, Paging paging) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getProductName() { return productName; }

    public String getProductDescription() { return productDescription; }

    public Ordering getOrdering() { return ordering; }

    public Paging getPaging() { return paging; }

    public boolean isNameProvided() { return this.productName != null && !this.productName.isEmpty(); }

    public boolean isDescriptionProvided() { return this.productDescription != null && !this.productDescription.isEmpty(); }
}
