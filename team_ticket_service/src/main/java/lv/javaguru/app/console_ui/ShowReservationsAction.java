package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.services.GetAllReservationsService;

public class ShowReservationsAction implements UIActions {

    private final GetAllReservationsService getAllReservationsService;

    public ShowReservationsAction(GetAllReservationsService getAllReservationsService) {
        this.getAllReservationsService = getAllReservationsService;
    }

    @Override
    public void execute() {
        getAllReservationsService.getAll();
    }
}
