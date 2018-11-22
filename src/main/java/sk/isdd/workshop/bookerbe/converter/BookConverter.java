package sk.isdd.workshop.bookerbe.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.isdd.workshop.bookerbe.data.jpa.entity.Book;
import sk.isdd.workshop.bookerbe.data.jpa.repository.AuthorRepository;
import sk.isdd.workshop.bookerbe.dto.BookDTO;

/**
 * @Book Filip Stiglic
 */

@Service
public class BookConverter {

	@Autowired
	AuthorRepository authorRepository;

	public BookDTO toDto(Book book){

		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setYearPublished(book.getYearPublished());
		bookDTO.setRecordVersion(book.getRecordVersion());

		return bookDTO;

	}

	public Book toEntity(BookDTO bookDTO){

		Book book = new Book();

		book.setTitle(bookDTO.getTitle());
		book.setAuthor(authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found")));
		book.setBookDetail(BookDetailConverter.toEntity(bookDTO.getBookDetailDTO()));
		book.setYearPublished(bookDTO.getYearPublished());
		book.setRecordVersion(bookDTO.getRecordVersion());
		return book;

	}

}
