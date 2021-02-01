package internet_store.controller.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Client;
import internet_store.core.request.client.AddClientRequest;
import internet_store.core.response.client.AddClientResponse;
import internet_store.core.service.client.AddClientService;
import internet_store.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddClientController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AddClientService addClientService;

    @GetMapping(value = "add_client")
    public String showAddClient(ModelMap modelMap) {
        modelMap.addAttribute("client", new Client());
        modelMap.addAttribute("errors", "start");
        return "client/add_client";
    }

    @GetMapping(value = "/back_client")
    public String backButtonClientFormPressed() {
        return "service/service";
    }

    @PostMapping(value = "/add_client")
    public String addNewClient(@ModelAttribute(value = "client") Client client, ModelMap modelMap) {
        StringBuilder allErrors = new StringBuilder();

        AddClientRequest request = new AddClientRequest(client, clientRepository);
        AddClientResponse response = addClientService.execute(request);

        for (CoreError error : response.getErrors()) {
            allErrors.append(error.getField()).append(" ").append(error.getMessage()).append(" ");
        }
        modelMap.addAttribute("errors", allErrors.toString());
        modelMap.addAttribute("client", client);
        return "client/add_client";
    }
}