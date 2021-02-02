package internet_store.core.request.product;

import lombok.Getter;

public class CheckDuplicateRecordRequest {
    @Getter
    private final Object productDatabase;

    public CheckDuplicateRecordRequest(Object productDatabase) {
        this.productDatabase = productDatabase;
    }
}