package lv.estore.app.userInterface;

import lv.estore.app.core.request.GetAllRequest;
import lv.estore.app.core.responses.GetAllResponse;
import lv.estore.app.core.services.GetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllUIAction implements UIAction{

    @Autowired
    private GetAllService getAllService;

    public void execute() {
        System.out.println("The list of the products store contains: ");

        GetAllRequest request = new GetAllRequest();
        GetAllResponse response = getAllService.execute(request);
        response.getProducts().forEach(System.out::println);

        System.out.println("Press 'Enter' to continue.");
    }
}
