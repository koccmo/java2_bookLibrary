package lesson_8.mysql_spring.console;

import lesson_8.mysql_spring.ClientDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteRecordConsole {
    @Autowired
    private ClientDb clientDb;
    @Autowired
    private RecordId recordId;

    public void deleteRecord() {
        recordId.idMenu();
        Long id = recordId.getRecordId();

        if (clientDb.isRecordExist(id)) {
            clientDb.deleteRecord(id);
        } else {
            System.out.println("Record no founded");
        }
    }
}