package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.services.EditReservationService;

public class EditReservationAction implements UIActions {

    private final EditReservationService editReservationService;

    public EditReservationAction(EditReservationService editReservationService) {
        this.editReservationService = editReservationService;
    }

    @Override
    public void execute() {

    }
}
