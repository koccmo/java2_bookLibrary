package java2.application_target_list.core.responses.board;

import java2.application_target_list.core.domain.Record;

import java.util.List;

public class GetAllRecordsResponse extends RecordCoreResponse{

    private List<Record> recordList;

    public GetAllRecordsResponse(List<Record> recordList) {
        this.recordList = recordList;
    }

    public List<Record> getRecordList() {
        return recordList;
    }
}
