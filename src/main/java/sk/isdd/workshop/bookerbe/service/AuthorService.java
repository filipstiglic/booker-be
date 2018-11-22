package sk.isdd.workshop.bookerbe.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import sk.isdd.workshop.bookerbe.dto.AuthorDTO;

/**
 * @Author Filip Stiglic
 */
public interface AuthorService {

	ResponseEntity<List<AuthorDTO>> getAuthors();

	ResponseEntity<AuthorDTO> getAuthor(long id);

	ResponseEntity<AuthorDTO> createAuthor(AuthorDTO authorDTO);

	ResponseEntity<Void> deleteAuthor(long id);

}
