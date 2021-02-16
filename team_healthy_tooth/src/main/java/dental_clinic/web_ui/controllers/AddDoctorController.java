package dental_clinic.web_ui.controllers;

import dental_clinic.core.domain.Doctor;
import dental_clinic.core.requests.doctor.AddDoctorRequest;
import dental_clinic.core.responses.doctor.AddDoctorResponse;
import dental_clinic.core.services.doctor.AddDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddDoctorController {

    @Autowired
    private AddDoctorService addDoctorService;

    @GetMapping(value = "/addDoctor")
    public String showAddDoctorPage(ModelMap modelMap) {
        Doctor doctor = new Doctor();
        modelMap.addAttribute("request",new AddDoctorRequest(doctor));
        return "addDoctor";
    }

    @PostMapping("/addDoctor")
    public String processAddDoctorRequest(@RequestParam(value = "doctor.name") String name,
                                          @RequestParam(value = "doctor.surname") String surname,
                                          @RequestParam(value = "doctor.phone") String phone, ModelMap modelMap) {
        Doctor doctor = new Doctor(name,surname,phone);
        AddDoctorResponse addDoctorResponse = addDoctorService.execute(new AddDoctorRequest(doctor));
        if (addDoctorResponse.hasErrors()) {
            modelMap.addAttribute("errors", addDoctorResponse.getErrors());
            return "addDoctor";
        } else {
            return "redirect:/";
        }
    }
}

