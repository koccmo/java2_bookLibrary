package lesson_12.ui.reader;

import lesson_12.core.requests.reader.GetAllReadersRequest;
import lesson_12.core.responses.reader.GetAllReadersResponse;
import lesson_12.core.services.reader.GetAllReadersService;
import lesson_12.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllReadersUICommand implements UICommand {

    @Autowired
    private GetAllReadersService getAllReadersService;

    @Override
    public void execute() {
        GetAllReadersRequest request = new GetAllReadersRequest();
        GetAllReadersResponse response = getAllReadersService.execute(request);
        System.out.println("================================================================================================");
        System.out.println("READER'S REGISTRY LIST:");
        if (response.getReaders().isEmpty()) {
            System.out.println("Reader's registry is empty...");
        } else
            response.getReaders().forEach(System.out::println);
        System.out.println("================================================================================================");
    }
}
