package sk.isdd.workshop.bookerbe.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import sk.isdd.workshop.bookerbe.dto.BookDTO;

/**
 * @Book Filip Stiglic
 */
public interface BookService {

	ResponseEntity<List<BookDTO>> getBooks();

	ResponseEntity<BookDTO> getBook(long id);

	ResponseEntity<BookDTO> createBook(BookDTO bookDTO);

	ResponseEntity<Void> deleteBook(long id);

}
