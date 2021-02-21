package java2.application_target_list.core.responses.board;

import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.responses.CoreError;

import java.util.List;

public class GetRecordResponse extends RecordCoreResponse{

    private Record record;

    public GetRecordResponse(List<CoreError> errorList) {
        super(errorList);
    }

    public GetRecordResponse(Record record) {
        this.record = record;
    }

    public Record getRecord() {
        return record;
    }
}
