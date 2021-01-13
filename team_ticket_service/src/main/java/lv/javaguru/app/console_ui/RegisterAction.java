package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.request.RegistrationRequest;
import lv.javaguru.app.core.response.RegistrationResponse;
import lv.javaguru.app.core.services.RegisterService;

import java.util.Scanner;

public class RegisterAction implements UIActions {
    private final RegisterService registerService;

    public RegisterAction(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Override
    public void execute() {
        System.out.println("REGISTRATION:" + lineSeparator());
        Person newUser = fillPerson();

        RegistrationRequest request = new RegistrationRequest(newUser);
        RegistrationResponse response = registerService.execute(request);

        if (response.hasErrors())
            response.getErrorList().forEach(System.out::println);
        else
            System.out.println("Hooray! You have been registered");
    }

    private Person fillPerson() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter surname: ");
        String surname = scanner.nextLine();

        return new Person(name, surname);
    }



    private static String lineSeparator() {
        String newLine = "\n";
        return newLine.concat(new String(new char[50]).replaceAll("", "="));
    }
}
