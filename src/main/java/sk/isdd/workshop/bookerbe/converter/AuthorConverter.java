package sk.isdd.workshop.bookerbe.converter;

import sk.isdd.workshop.bookerbe.data.jpa.entity.Author;
import sk.isdd.workshop.bookerbe.dto.AuthorDTO;

/**
 * @Author Filip Stiglic
 */
public class AuthorConverter {

	public static AuthorDTO toDto(Author author){

		AuthorDTO authorDTO = new AuthorDTO();
		authorDTO.setId(author.getId());
		authorDTO.setName(author.getName());
		authorDTO.setSurname(author.getSurname());
		authorDTO.setRecordVersion(author.getRecordVersion());
		authorDTO.setCountry(author.getCountry());

		return authorDTO;

	}

	public static Author toEntity(AuthorDTO authorDTO){

		Author author = new Author();

		author.setName(authorDTO.getName());
		author.setSurname(authorDTO.getSurname());
		author.setRecordVersion(authorDTO.getRecordVersion());
		author.setCountry(authorDTO.getCountry());
		return author;

	}

}
