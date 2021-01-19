package java2.application_target_list.core.responses.board;

import java2.application_target_list.core.domain.Record;

import java.util.List;

public class GetFullInfoAboutRecordsResponse extends RecordCoreResponse{

    private List<Record> recordList;

    public GetFullInfoAboutRecordsResponse(List<Record> recordList) {
        this.recordList = recordList;
    }

    public List<Record> getRecordList() {
        return recordList;
    }
}
