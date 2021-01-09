package dental_clinic.console_ui.visit;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.console_ui.doctor.AddDoctorUIAction;
import dental_clinic.core.domain.Doctor;
import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.domain.Visit;
import dental_clinic.core.requests.doctor.AddDoctorRequest;
import dental_clinic.core.requests.doctor.GetDoctorListRequest;
import dental_clinic.core.requests.visit.AddVisitRequest;
import dental_clinic.core.requests.ContainsDatabaseIdRequest;
import dental_clinic.core.responses.doctor.AddDoctorResponse;
import dental_clinic.core.responses.doctor.GetDoctorListResponse;
import dental_clinic.core.responses.visit.AddVisitResponse;
import dental_clinic.core.responses.ContainsDatabaseIdResponse;
import dental_clinic.core.services.doctor.AddDoctorService;
import dental_clinic.core.services.doctor.GetDoctorListService;
import dental_clinic.core.services.visit.AddVisitService;
import dental_clinic.core.services.ContainsDatabaseIdService;
import dental_clinic.database.in_memory.doctor.DoctorDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

@Component
public class AddVisitUIAction implements UIAction {

    @Autowired
    private AddVisitService addVisitService;
    @Autowired
    private ContainsDatabaseIdService containsDatabaseIdService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;
    @Autowired
    private GetDoctorListService getDoctorListService;


    public void execute(){
        Scanner in = new Scanner(System.in);

        Long id = inputFormatsValidator.inputLong("Please enter patient's id");

        ContainsDatabaseIdRequest containsDatabaseIdRequest = new ContainsDatabaseIdRequest(id);
        ContainsDatabaseIdResponse containsDatabaseIdResponse = containsDatabaseIdService.execute(containsDatabaseIdRequest);

        if (containsDatabaseIdResponse.hasErrors()){
            containsDatabaseIdResponse.getErrors().forEach(System.out::println);
        } else {
            Integer toothNumber = inputFormatsValidator.inputInteger("Please input tooth number");

            System.out.println("Please input comment if necessary or press enter");
            String commentIn = in.nextLine();
            Optional<String> comment = Optional.of(commentIn);

            printToothStatuses();
            Integer variant = inputFormatsValidator.inputInteger("Please enter tooth status");
            ToothStatus toothStatus = inputToothStatus(variant);

            GetDoctorListRequest getDoctorListRequest = new GetDoctorListRequest();
            GetDoctorListResponse getDoctorListResponse = getDoctorListService.execute(getDoctorListRequest);
            System.out.println("Please enter doctor's id from DB or enter name surname to create new doctor:");
            if (!getDoctorListResponse.hasErrors()) {
                getDoctorListResponse.getDoctors().forEach(System.out::println);
            }

            String inputForDoctor = in.nextLine();

            Date date = new Date();

            Doctor doctor = new Doctor(inputForDoctor, "");

            Visit visit = new Visit(toothNumber, comment, toothStatus, doctor, date);
            AddVisitRequest addVisitRequest = new AddVisitRequest(id, visit);
            AddVisitResponse addVisitResponse = addVisitService.execute(addVisitRequest);

            if (addVisitResponse.hasErrors()) {
                addVisitResponse.getErrors().forEach(System.out::println);
            } else {
                System.out.println("Visit added successfully!");
            }
        }

    }

    private void printToothStatuses(){
        System.out.println(
            "Tooth statuses:\n" +
                "1   KARIES\n" +
                "2   PLOMBA\n" +
                "3   SAKNE\n" +
                "4   KRONITIS\n" +
                "5   KLAMERS\n" +
                "6   NAV_ZOBA\n" +
                "7   FASETE\n" +
                "8   NONEMAMA_PROTEZE\n" +
                "9   KRONITIS_AR_FAS\n" +
                "10  PLAST_KRONITIS\n" +
                "11  TILTINI\n" +
                "12  HEALTHY\n");
    }

    ToothStatus inputToothStatus(int variant){

        switch (variant) {
            case 1: return ToothStatus.KARIES;
            case 2: return ToothStatus.PLOMBA;
            case 3: return ToothStatus.SAKNE;
            case 4: return ToothStatus.KRONITIS;
            case 5: return ToothStatus.KLAMERS;
            case 6: return ToothStatus.NAV_ZOBA;
            case 7: return ToothStatus.FASETE;
            case 8: return ToothStatus.NONEMAMA_PROTEZE;
            case 9: return ToothStatus.KRONITIS_AR_FAS;
            case 10: return ToothStatus.PLAST_KRONITIS;
            case 11: return ToothStatus.TILTINI;
            case 12: return ToothStatus.HEALTHY;
        }
        return ToothStatus.OTHER;
    }

}

