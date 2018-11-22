package sk.isdd.workshop.bookerbe.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

/**
 * @Author Filip Stiglic
 */

@Tag("RepositoryTest")
@DataJpaTest
public class AuthorRepositoryTest {

	public static final String NAME = "Stephen";
	public static final String SURNAME = "King";
	public static final String COUNTRY = "USA";


	@Autowired
	AuthorRepository authorRepository;



	private BookDetail createBookDetail(){
		BookDetail bookDetail = new BookDetail();
		bookDetail.setDescription(DESCRIPTION);
		bookDetail.setNumberOfPages(NUMBER_OF_PAGES);
		return bookDetail;
	}

	private Book createBook(){
		Book book = new Book();
		book.setTitle(TITLE);
		book.setYearPublished(YEAR_PUBLISHED);
		book.setBookDetail(createBookDetail());
		return book;
	}

	private Author createAuthor(){
		Author author = new Author();
		author.setName(NAME);
		author.setSurname(SURNAME);
		author.setCountry(COUNTRY);

		author.addBook(createBook());
		author = authorRepository.save(author);
		return author;
	}

	@Test
	public void deleteAuthorTest(){
		Author createdAuthor = createAuthor();
		authorRepository.deleteById(createdAuthor.getId());
		assertEquals(0,authorRepository.count());
	}

	@Test
	public void validateAuthorTest(){

		Author createdAuthor = createAuthor();

		Author author = authorRepository.findById(createdAuthor.getId()).get();
		assertEquals(NAME, author.getName());
		assertEquals(SURNAME, author.getSurname());
		assertEquals(COUNTRY, author.getCountry());

	}

	@Test
	public void findAllAuthorsTest(){
		createAuthor();
		assertEquals(1,authorRepository.count());
    }


}
