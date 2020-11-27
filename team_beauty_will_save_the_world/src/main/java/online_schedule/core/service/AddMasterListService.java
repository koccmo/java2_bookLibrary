package online_schedule.core.service;

public class AddMasterListService {

    private String Name;
    private String Address;
    private String Experience;
    private String Specialization;
    private String Contact;


    public AddMasterListService(){

    }

    public AddMasterListService(String name, String address, String specialization,String contact, String experience) {
        this.Name = name;
        this.Address = address;
        this.Specialization = specialization;
        this.Contact = contact;
        this.Experience = experience;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }



    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }



    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        this.Specialization = specialization;
    }


    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        this.Experience = experience;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        this.Contact = contact;
    }

}
