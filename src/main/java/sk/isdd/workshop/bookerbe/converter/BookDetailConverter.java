package sk.isdd.workshop.bookerbe.converter;

import sk.isdd.workshop.bookerbe.data.jpa.entity.BookDetail;
import sk.isdd.workshop.bookerbe.dto.BookDetailDTO;

/**
 * @Author Filip Stiglic
 */
public class BookDetailConverter {

	public static BookDetailDTO toDTO(BookDetail bookDetail){

		BookDetailDTO bookDetailDTO = new BookDetailDTO();
		bookDetailDTO.setDescription(bookDetail.getDescription());
		bookDetailDTO.setNumberOfPages(bookDetail.getNumberOfPages());

		return bookDetailDTO;

	}

	public static BookDetail toEntity(BookDetailDTO bookDetailDTO){

		BookDetail bookDetail = new BookDetail();
		bookDetail.setDescription(bookDetailDTO.getDescription());
		bookDetail.setNumberOfPages(bookDetailDTO.getNumberOfPages());

		return bookDetail;
	}

}
