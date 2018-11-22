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
import sk.isdd.workshop.bookerbe.dto.AuthorDTO;
import sk.isdd.workshop.bookerbe.service.AuthorService;

/**
 * @Author Filip Stiglic
 */

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AuthorDTO>> getAuthors(){
		return authorService.getAuthors();
	}

	@PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO authorDTO){
		return authorService.createAuthor(authorDTO);
	}

	@GetMapping(path="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") Long id){
		return authorService.getAuthor(id);
	}

	@DeleteMapping(path="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Long id){
		return authorService.deleteAuthor(id);
	}




}
