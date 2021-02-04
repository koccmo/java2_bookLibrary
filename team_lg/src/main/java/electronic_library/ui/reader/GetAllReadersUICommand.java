package electronic_library.ui.reader;

import electronic_library.core.requests.reader.GetAllReadersRequest;
import electronic_library.core.responses.reader.GetAllReadersResponse;
import electronic_library.core.services.reader.GetAllReadersService;
import electronic_library.ui.UICommand;
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
