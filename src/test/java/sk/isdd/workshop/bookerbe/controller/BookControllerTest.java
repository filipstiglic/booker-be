package sk.isdd.workshop.bookerbe.controller;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sk.isdd.workshop.bookerbe.constants.AuthorConstants.COUNTRY;
import static sk.isdd.workshop.bookerbe.constants.AuthorConstants.SURNAME;
import static sk.isdd.workshop.bookerbe.constants.BookConstants.DESCRIPTION;
import static sk.isdd.workshop.bookerbe.constants.BookConstants.NUMBER_OF_PAGES;
import static sk.isdd.workshop.bookerbe.constants.BookConstants.TITLE;
import static sk.isdd.workshop.bookerbe.constants.BookConstants.YEAR_PUBLISHED;
import static sk.isdd.workshop.bookerbe.constants.UserConstants.NAME;
import static util.JsonUtils.toJsonString;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import sk.isdd.workshop.bookerbe.data.jpa.entity.Author;
import sk.isdd.workshop.bookerbe.data.jpa.entity.Book;
import sk.isdd.workshop.bookerbe.data.jpa.entity.BookDetail;
import sk.isdd.workshop.bookerbe.data.jpa.repository.AuthorRepository;
import sk.isdd.workshop.bookerbe.data.jpa.repository.BookRepository;
import sk.isdd.workshop.bookerbe.dto.BookDTO;
import sk.isdd.workshop.bookerbe.dto.BookDetailDTO;

/**
 * @Book Filip Stiglic
 */

@Tag("ControllerTest")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;



	private Author createAuthor(){
		Author author = new Author();
		author.setName(NAME);
		author.setSurname(SURNAME);
		author.setCountry(COUNTRY);

		author = authorRepository.save(author);
		return author;
	}

	private BookDetailDTO createBookDetailDTO(){
		BookDetailDTO bookDetailDTO = new BookDetailDTO();
		bookDetailDTO.setNumberOfPages(NUMBER_OF_PAGES);
		bookDetailDTO.setDescription(DESCRIPTION);
		return bookDetailDTO;
	}

	private BookDTO createBookDTO(Author author) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setTitle(TITLE);
		bookDTO.setYearPublished(YEAR_PUBLISHED);
		bookDTO.setAuthorId(author.getId());
		bookDTO.setBookDetailDTO(createBookDetailDTO());
		return bookDTO;
	}

	private BookDetail createBookDetail(){
		BookDetail bookDetail = new BookDetail();
		bookDetail.setDescription(DESCRIPTION);
		bookDetail.setNumberOfPages(NUMBER_OF_PAGES);
		return bookDetail;
	}

	private Book createBook(Author author) {
		Book book = new Book();
		book.setTitle(TITLE);
		book.setYearPublished(YEAR_PUBLISHED);
		book.setAuthor(author);
		book.setBookDetail(createBookDetail());
		return book;
	}


	@Test
	public void testCreateBook() throws Exception {
		Author author = createAuthor();
		BookDTO bookDTO = createBookDTO(author);

		mockMvc.perform(
			post("/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJsonString(bookDTO)))
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$.id", is(notNullValue())))
			.andExpect(jsonPath("$.title", is(bookDTO.getTitle())))
			.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void testgetBookById() throws Exception {
		Author author = createAuthor();
		Book book = bookRepository.save(createBook(author));

		mockMvc.perform(
			get("/books/{id}", book.getId()))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$.id", is(book.getId().intValue())))
			.andExpect(jsonPath("$.title", is(book.getTitle())));


	}

	@Test
	public void testGetBooks() throws Exception {
		Author author = createAuthor();
		Book book = bookRepository.save(createBook(author));

		mockMvc.perform(get("/books"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].id", is(book.getId().intValue())))
			.andExpect(jsonPath("$[0].title", is(book.getTitle()))
			);
	}

	@Test
	public void testDeleteBookById() throws Exception {
		Author author = createAuthor();
		Book book = bookRepository.save(createBook(author));

		mockMvc.perform(
			delete("/books/{id}", book.getId()))
			.andExpect(status().isAccepted());


	}
}
