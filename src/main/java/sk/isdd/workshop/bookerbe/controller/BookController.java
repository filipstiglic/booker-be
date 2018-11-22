package sk.isdd.workshop.bookerbe.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.isdd.workshop.bookerbe.dto.BookDTO;
import sk.isdd.workshop.bookerbe.service.BookService;

/**
 * @Book Filip Stiglic
 */

@RestController
@RequestMapping(path = "/books")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookDTO>> getBooks(){
		return bookService.getBooks();
	}

	@PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO){
		return bookService.createBook(bookDTO);
	}

	@GetMapping(path="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookDTO> getBook(@PathVariable("id") Long id){
		return bookService.getBook(id);
	}

	@DeleteMapping(path="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id){
		return bookService.deleteBook(id);
	}




}
