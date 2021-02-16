package lv.estore.app.core.request.deals;

public class UpdateDealByIdRequest {
    private String id;
    private String userId;
    private String productId;
    private String status;

    public UpdateDealByIdRequest(){

    }

    public UpdateDealByIdRequest(String id, String userId, String productId, String status) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
