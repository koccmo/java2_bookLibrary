package java2.application_target_list.core.responses.board;

import java2.application_target_list.core.domain.Record;

import java.util.List;

public class GetUnfinishedRecordsResponse extends RecordCoreResponse{


    private List<Record> recordList;

    public GetUnfinishedRecordsResponse(List<Record> recordList) {
        this.recordList = recordList;
    }

    public List<Record> getRecordList() {
        return recordList;
    }
}
