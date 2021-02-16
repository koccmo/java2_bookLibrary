package dental_clinic.web_ui.controllers;


import dental_clinic.core.database.patient.PatientRepository;
import dental_clinic.core.requests.patient.ChangePersonalDataRequest;
import dental_clinic.core.responses.patient.ChangePersonalDataResponse;
import dental_clinic.core.services.patient.ChangePersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ChangePersonalDataController  {

    @Autowired
    private ChangePersonalDataService changePersonalDataService;
    @Autowired
    PatientRepository patientRepository;

    @GetMapping(value = "/changePersonalData")
    public String showChangePersonalDataPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new ChangePersonalDataRequest() );
        return "changePersonalData";
    }

    @PostMapping("/changePersonalData")
    public String processChangePersonalDataRequest(@RequestParam(value = "patientIdNumber") Long patientIdNumber,
                                                   @RequestParam(value = "updatedSurname") String updatedSurname,
                                                   @RequestParam(value = "updatedPhoneNumber") String updatedPhoneNumber,ModelMap modelMap) {

        ChangePersonalDataRequest changePersonalDataRequest = new ChangePersonalDataRequest(patientIdNumber,updatedSurname,updatedPhoneNumber);
        ChangePersonalDataResponse changePersonalDataResponse = changePersonalDataService.execute(changePersonalDataRequest);
        if (changePersonalDataResponse.hasErrors()) {
            modelMap.addAttribute("errors", changePersonalDataResponse.getErrors());
            return "changePersonalData";
        } else {
            return "redirect:/";
        }
    }




}