package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.services.ShowReservationsService;

public class ShowReservationsAction implements UIActions {

    private final ShowReservationsService showReservationsService;

    public ShowReservationsAction(ShowReservationsService showReservationsService) {
        this.showReservationsService = showReservationsService;
    }

    @Override
    public void execute() {
        showReservationsService.getAll();
    }
}
