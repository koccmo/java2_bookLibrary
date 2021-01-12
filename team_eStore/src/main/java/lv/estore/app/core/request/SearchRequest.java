package lv.estore.app.core.request;

public class SearchRequest {

    private String name;
    private String price;

    private Ordering ordering;
    private Paging paging;

    public SearchRequest(final String name, final String price) {
        this.name = name;
        this.price = price;
    }

    public SearchRequest(final String name,
                         final String price,
                         final Ordering ordering) {
        this.name = name;
        this.price = price;
        this.ordering = ordering;
    }

    public SearchRequest(final String name,
                         final String price,
                         final Paging paging) {
        this.name = name;
        this.price = price;
        this.paging = paging;
    }

    public SearchRequest(final String name,
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

    public boolean isNameProvided(){
        return this.name != null && !this.name.isEmpty();
    }

    public boolean isPriceProvided(){
        return this.price != null;
    }
}
