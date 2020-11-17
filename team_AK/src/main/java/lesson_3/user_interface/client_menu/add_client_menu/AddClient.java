package lesson_3.user_interface.client_menu.add_client_menu;


import lesson_3.core.domain.Client;

public class AddClient {
    private final Client client = new Client();

    public Client addClient() {

        AddClientSurname addClientSurname = new AddClientSurname();
        AddClientName addClientName = new AddClientName();
        AddClientPhone addClientPhone = new AddClientPhone();
        AddClientMail addClientMail = new AddClientMail();

        addClientSurname.showMenuClientSurname();
        client.setSurname(addClientSurname.getUserClientSurnameInput());

        addClientName.showMenuClientName();
        client.setName(addClientName.getUserClientNameInput());

        addClientPhone.showMenuClientPhone();
        client.setPhoneNumber(addClientPhone.getUserClientPhoneInput());

        addClientMail.showMenuClientMail();
        client.setEmail(addClientMail.getUserClientMailInput());

        return client;
    }
}