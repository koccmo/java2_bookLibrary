package internet_store.core.requests.product;

public class DeleteProductByOtherRequest {

    private String title;
    private String description;
    private Integer startPrice;
    private Integer endPrice;

    public DeleteProductByOtherRequest(String title, String description, Integer startPrice, Integer endPrice) {
        this.title = title;
        this.description = description;
        this.startPrice = startPrice;
        this.endPrice = endPrice;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public Integer getStartPrice () {
        return startPrice;
    }

    public Integer getEndPrice() {
        return endPrice;
    }
}
