package book_library.console_ui.Reader;

import book_library.console_ui.UIAction;
import book_library.core.requests.Reader.GetAllReaderRequest;
import book_library.core.responses.Reader.GetAllReadersResponse;
import book_library.core.services.Reader.GetAllReadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllReadersUIAction implements UIAction {

    @Autowired
    GetAllReadersService getAllReadersService;

    @Override
    public void execute() {
        System.out.println("Reader list:");
        System.out.println("***************************************************************************");
        GetAllReaderRequest request = new GetAllReaderRequest();
        GetAllReadersResponse response = getAllReadersService.execute(request);
        response.getReaders().forEach(System.out::println);
        System.out.println("***************************************************************************");
        System.out.println("Reader list end.");

    }
}
