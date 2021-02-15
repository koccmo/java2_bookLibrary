package lv.javaguru.java2.library.web_ui.controllers.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.javaguru.java2.library.core.database.BookRepository;
import lv.javaguru.java2.library.core.domain.Book;
import lv.javaguru.java2.library.core.requests.AddBookRequest;
import lv.javaguru.java2.library.core.responses.AddBookResponse;
import lv.javaguru.java2.library.core.services.AddBookService;

@RestController
@RequestMapping("/book")
public class BookRestController {

	@Autowired private BookRepository bookRepository;
	@Autowired private AddBookService addBookService;


	@GetMapping(path = "/{id}", produces = "application/json")
	public Book getBook(@PathVariable Long id) {
		Optional<Book> bookOpt = bookRepository.getById(id);
		if (bookOpt.isPresent()) {
			return bookOpt.get();
		} else {
			throw new IllegalArgumentException("Not found!");
		}
	}

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public AddBookResponse addBook(@RequestBody AddBookRequest request) {
		return addBookService.execute(request);
	}

}
