package java2.application_target_list.web_ui.controllers.board;

import java2.application_target_list.core.requests.board.GetFullInfoAboutRecordsRequest;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.board.GetFullInfoAboutRecordsResponse;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.services.board.GetFullInfoAboutRecordsService;
import java2.application_target_list.core.services.board.SetRecordCompleteDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SetRecordCompleteDateController {

    @Autowired private SetRecordCompleteDateService setRecordCompleteDateService;
    @Autowired private GetFullInfoAboutRecordsService getFullInfoAboutRecordsService;

    @GetMapping(value = "/board/setRecordCompleteDate")
    public String showSetRecordCompleteDatePage(ModelMap modelMap) {
        GetFullInfoAboutRecordsResponse getFullInfoAboutRecordsResponse = getFullInfoAboutRecordsService.execute(new GetFullInfoAboutRecordsRequest());
        modelMap.addAttribute("records", getFullInfoAboutRecordsResponse.getRecordList());
        modelMap.addAttribute("request", new SetRecordCompleteDateRequest());
        return "board/setRecordCompleteDate";
    }

    @PostMapping("/board/setRecordCompleteDate")
    public String processSetRecordCompleteDateRequest(@ModelAttribute(value = "request") SetRecordCompleteDateRequest setRecordCompleteDateRequest,
                                   ModelMap modelMap) {

        SetRecordCompleteDateResponse response = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);

        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrorList());
            return "board/setRecordCompleteDate";
        } else {
            return "redirect:/";
        }
    }
}
