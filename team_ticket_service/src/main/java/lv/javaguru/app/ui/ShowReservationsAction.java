package lv.javaguru.app.ui;

import lv.javaguru.app.businesslogic.GetAllReservationsService;

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
