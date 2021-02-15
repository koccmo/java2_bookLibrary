package lv.javaguru.java2.library.web_ui.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.requests.GetBookRequest;
import lv.javaguru.java2.library.core.responses.AddBookResponse;
import lv.javaguru.java2.library.core.responses.GetBookResponse;
import lv.javaguru.java2.library.core.services.AddBookService;
import lv.javaguru.java2.library.core.services.GetBookService;

@RestController
@RequestMapping("/book")
public class BookRestController {

	@Autowired private GetBookService getBookService;
	@Autowired private AddBookService addBookService;

	@GetMapping(path = "/{id}", produces = "application/json")
	public GetBookResponse getBook(@PathVariable Long id) {
		GetBookRequest request = new GetBookRequest(id);
		return getBookService.execute(request);
	}

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public AddBookResponse addBook(@RequestBody AddBookRequest request) {
		return addBookService.execute(request);
	}

}