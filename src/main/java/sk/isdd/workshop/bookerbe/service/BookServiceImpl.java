package sk.isdd.workshop.bookerbe.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.isdd.workshop.bookerbe.converter.BookConverter;
import sk.isdd.workshop.bookerbe.data.jpa.entity.Book;
import sk.isdd.workshop.bookerbe.data.jpa.repository.BookRepository;
import sk.isdd.workshop.bookerbe.dto.BookDTO;

/**
 * @Book Filip Stiglic
 */
@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookConverter bookConverter;

	@Transactional(readOnly = true)
	@Override
	public ResponseEntity<List<BookDTO>> getBooks() {

		List<BookDTO> booksDTO = StreamSupport.stream(bookRepository.findAll().spliterator(), false).map(book ->bookConverter.toDto(book)).collect(Collectors.toList());
		return new ResponseEntity<>(booksDTO, HttpStatus.OK);

	}

	@Transactional(readOnly = true)
	@Override
	public ResponseEntity<BookDTO> getBook(long id) {

		Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity with was not found"));
		return new ResponseEntity<>(bookConverter.toDto(book), HttpStatus.OK);
	}

	@Transactional
	@Override
	public ResponseEntity<BookDTO> createBook(BookDTO bookDTO) {
		Book savedBook =  bookRepository.save(bookConverter.toEntity(bookDTO));
		return new ResponseEntity<>(bookConverter.toDto(savedBook),HttpStatus.CREATED);
	}

	@Transactional
	@Override
	public ResponseEntity<Void> deleteBook(long id) {
		bookRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
