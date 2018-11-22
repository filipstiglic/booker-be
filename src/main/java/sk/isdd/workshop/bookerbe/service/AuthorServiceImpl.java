package sk.isdd.workshop.bookerbe.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.isdd.workshop.bookerbe.converter.AuthorConverter;
import sk.isdd.workshop.bookerbe.data.jpa.entity.Author;
import sk.isdd.workshop.bookerbe.data.jpa.repository.AuthorRepository;
import sk.isdd.workshop.bookerbe.dto.AuthorDTO;

/**
 * @Author Filip Stiglic
 */
@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	AuthorRepository authorRepository;

	@Transactional(readOnly = true)
	@Override
	public ResponseEntity<List<AuthorDTO>> getAuthors() {

		List<AuthorDTO> authorsDTO = StreamSupport.stream(authorRepository.findAll().spliterator(), false).map(AuthorConverter::toDto).collect(Collectors.toList());
		return new ResponseEntity<>(authorsDTO, HttpStatus.OK);

	}

	@Transactional(readOnly = true)
	@Override
	public ResponseEntity<AuthorDTO> getAuthor(long id) {

		Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity with was not found"));
		return new ResponseEntity<>(AuthorConverter.toDto(author), HttpStatus.OK);
	}

	@Transactional
	@Override
	public ResponseEntity<AuthorDTO> createAuthor(AuthorDTO authorDTO) {
		Author savedAuthor =  authorRepository.save(AuthorConverter.toEntity(authorDTO));
		return new ResponseEntity<>(AuthorConverter.toDto(savedAuthor),HttpStatus.CREATED);
	}

	@Transactional
	@Override
	public ResponseEntity<Void> deleteAuthor(long id) {
		authorRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
