package dental_clinic.console_ui.doctor;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.doctor.FillDoctorsWorkGraphicRequest;
import dental_clinic.core.responses.doctor.FillDoctorsWorkGraphicResponse;
import dental_clinic.core.services.doctor.FillDoctorsWorkGraphicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FillDoctorsWorkGraphicUIAction implements UIAction {

    @Autowired
    private FillDoctorsWorkGraphicService fillDoctorsWorkGraphicService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    @Override
    public void execute() {

        while (true) {
            Long id = inputFormatsValidator.inputLong("Please enter doctor's id to update work graphic or press 0");

            if (id == 0) {
                break;
            }

            printMenu();

            Integer menuNumber = inputFormatsValidator.inputInteger("Please choose what day to update or press 0:");

            if (menuNumber < 1 || menuNumber > 7) {
                break;
            } else {
                updateTimeForSpecificDate(id, menuNumber);
            }
        }
    }

    private void printMenu() {
        System.out.println("Menu: \n" +
                "1 - monday\n" +
                "2 - tuesday\n" +
                "3 - wednesday\n" +
                "4 - thursday\n" +
                "5 - friday\n" +
                "6 - saturday\n" +
                "7 - sunday\n" +
                "0 - exit");
    }

    private void updateTimeForSpecificDate(Long id, Integer menuNumber) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please input time from in format HH:MM");
        String start = in.nextLine();
        System.out.println("Please input time to in format HH:MM");
        String end = in.nextLine();

        FillDoctorsWorkGraphicRequest fillDoctorsWorkGraphicRequest =
                new FillDoctorsWorkGraphicRequest(id, menuNumber, start, end);
        FillDoctorsWorkGraphicResponse fillDoctorsWorkGraphicResponse = fillDoctorsWorkGraphicService.execute(fillDoctorsWorkGraphicRequest);

        if (fillDoctorsWorkGraphicResponse.hasErrors()) {
            fillDoctorsWorkGraphicResponse.getErrors().stream().forEach(System.out::println);
        } else {
            System.out.println("Work graphic is updated for doctor with id " +
                    fillDoctorsWorkGraphicRequest.getId());
        }
    }
}
