package lv.estore.app.core.request.products;

public class SearchProductByNamePriceRequest {

    private String name;
    private String price;

    private Ordering ordering;
    private Paging paging;

    public SearchProductByNamePriceRequest(){

    }

    public SearchProductByNamePriceRequest(final String name, final String price) {
        this.name = name;
        this.price = price;
    }

    public SearchProductByNamePriceRequest(final String name,
                                           final String price,
                                           final Ordering ordering) {
        this.name = name;
        this.price = price;
        this.ordering = ordering;
    }

    public SearchProductByNamePriceRequest(final String name,
                                           final String price,
                                           final Paging paging) {
        this.name = name;
        this.price = price;
        this.paging = paging;
    }

    public SearchProductByNamePriceRequest(final String name,
                                           final String price,
                                           final Ordering ordering,
                                           final Paging paging) {
        this.name = name;
        this.price = price;
        this.ordering = ordering;
        this.paging = paging;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public boolean isNameProvided(){
        return this.name != null && !this.name.isEmpty();
    }

    public boolean isPriceProvided(){
        return this.price != null;
    }
}
