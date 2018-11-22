package sk.isdd.workshop.bookerbe.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sk.isdd.workshop.bookerbe.constants.AuthorConstants.COUNTRY;
import static sk.isdd.workshop.bookerbe.constants.AuthorConstants.NAME;
import static sk.isdd.workshop.bookerbe.constants.AuthorConstants.SURNAME;
import static sk.isdd.workshop.bookerbe.constants.BookConstants.DESCRIPTION;
import static sk.isdd.workshop.bookerbe.constants.BookConstants.NUMBER_OF_PAGES;
import static sk.isdd.workshop.bookerbe.constants.BookConstants.TITLE;
import static sk.isdd.workshop.bookerbe.constants.BookConstants.YEAR_PUBLISHED;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sk.isdd.workshop.bookerbe.data.jpa.entity.Author;
import sk.isdd.workshop.bookerbe.data.jpa.entity.Book;
import sk.isdd.workshop.bookerbe.data.jpa.entity.BookDetail;
import sk.isdd.workshop.bookerbe.data.jpa.repository.AuthorRepository;
import sk.isdd.workshop.bookerbe.data.jpa.repository.BookRepository;

/**
 * @Book Filip Stiglic
 */

@Tag("RepositoryTest")
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;

	private BookDetail createBookDetail(){
		BookDetail bookDetail = new BookDetail();
		bookDetail.setDescription(DESCRIPTION);
		bookDetail.setNumberOfPages(NUMBER_OF_PAGES);
		return bookDetail;
	}

	private Author createAuthor(){
		Author author = new Author();
		author.setName(NAME);
		author.setSurname(SURNAME);
		author.setCountry(COUNTRY);
		author = authorRepository.save(author);
		return author;
	}

	private Book createBook(Author author){
		Book book = new Book();
		book.setTitle(TITLE);
		book.setYearPublished(YEAR_PUBLISHED);
		book.setBookDetail(createBookDetail());
		book.setAuthor(author);
		book = bookRepository.save(book);
		return book;
	}

	@Test
	public void deleteBookTest(){
        Author author = createAuthor();
		Book createdBook = createBook(author);
		bookRepository.deleteById(createdBook.getId());
		assertEquals(0,bookRepository.count());
	}

	@Test
	public void validateBookTest(){
		Author author = createAuthor();
		Book createdBook = createBook(author);

		Book book = bookRepository.findById(createdBook.getId()).get();
		assertEquals(TITLE, book.getTitle());
		assertEquals(YEAR_PUBLISHED, book.getYearPublished());
	}

	@Test
	public void findAllBooksTest(){
		Author author = createAuthor();
		createBook(author);
		assertEquals(1,bookRepository.count());
    }


}
