package core.services;

import core.request.CoreRequest;
import core.responses.CoreResponse;
import core.responses.FindAllResponse;
import repository.iDatabase;

import java.util.ArrayList;

public class FindAllService implements iService {

    private iDatabase database;

    public FindAllService(final iDatabase database) {
        this.database = database;
    }

    /**
     * Method to find all products.
     * @param request CoreRequest
     * @return List<Product>
     */
    @Override
    public CoreResponse execute(final CoreRequest request) {
        CoreResponse response;
        if (database.getAllProducts().isEmpty()) {
            response =  new FindAllResponse(new ArrayList<>());
        } else {
            response = new FindAllResponse(database.getAllProducts());
        }
        return response;
    }
}
