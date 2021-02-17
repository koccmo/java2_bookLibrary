package lv.javaguru.app.web_ui.controllers;

import lv.javaguru.app.console_ui.FlightShowUsersAction;
import lv.javaguru.app.core.request.FlightShowAllRequest;
import lv.javaguru.app.core.response.FlightShowAllResponse;
import lv.javaguru.app.core.services.FlightShowAllService;
import lv.javaguru.app.core.services.FlightShowOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowUsersFlightsController {

		@Autowired
		private FlightShowAllService flightShowAllService;


		@GetMapping(value = "user_mode/showflights")
		public String showUserFlights(ModelMap modelMap) {
			FlightShowAllResponse<?> response = flightShowAllService.execute();
			modelMap.addAttribute("flights", response.getResponse());
			return "user_mode/showflights";
		}

	}


