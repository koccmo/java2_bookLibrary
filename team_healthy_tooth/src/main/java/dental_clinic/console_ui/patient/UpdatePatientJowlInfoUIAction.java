package dental_clinic.console_ui.patient;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.requests.ContainsDatabaseIdRequest;
import dental_clinic.core.requests.patient.UpdatePatientsJowlInfoRequest;
import dental_clinic.core.responses.ContainsDatabaseIdResponse;
import dental_clinic.core.responses.patient.UpdatePatientJowlInfoResponse;
import dental_clinic.core.services.ContainsDatabaseIdService;
import dental_clinic.core.services.patient.UpdatePatientJowlInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Map;


@Component
public class UpdatePatientJowlInfoUIAction implements UIAction {

    @Autowired
    private InputFormatsValidator inputFormatsValidator;
    @Autowired
    private UpdatePatientJowlInfoService updatePatientJowlInfoService;
    @Autowired
    private ContainsDatabaseIdService containsDatabaseIdService;

    @Override
    public void execute() {

        Long id = inputFormatsValidator.inputLong("Please input id");

        ContainsDatabaseIdRequest containsDatabaseIdRequest = new ContainsDatabaseIdRequest(id);
        ContainsDatabaseIdResponse containsDatabaseIdResponse = containsDatabaseIdService.execute(containsDatabaseIdRequest);

        if (containsDatabaseIdResponse.hasErrors()){
            containsDatabaseIdResponse.getErrors().forEach(System.out::println);
        } else {
            inputJowlData(id);
        }
    }

    private void inputJowlData(Long id) {

        Integer toothNumber = inputFormatsValidator.inputInteger("Please enter tooth number");

        printToothStatuses();

        Integer variant = inputFormatsValidator.inputInteger("Please enter tooth status");
        ToothStatus toothStatus = inputToothStatus(variant);

        UpdatePatientsJowlInfoRequest updatePatientsJowlInfoRequest = new UpdatePatientsJowlInfoRequest(id, toothNumber, toothStatus);
        UpdatePatientJowlInfoResponse updatePatientJowlInfoResponse = updatePatientJowlInfoService.execute(updatePatientsJowlInfoRequest);

        if (updatePatientJowlInfoResponse.hasErrors()) {
            updatePatientJowlInfoResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Patient's with id " + updatePatientJowlInfoResponse.getId() +
                    " jowl info is updated");
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
