package sk.isdd.workshop.bookerbe.converter;

import sk.isdd.workshop.bookerbe.data.jpa.entity.User;
import sk.isdd.workshop.bookerbe.dto.UserDTO;

/**
 * @Author Filip Stiglic
 */
public class UserConverter {

	public static UserDTO toDto(User user){

		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setSurname(user.getSurname());
		userDTO.setAddress(user.getAddress());
		userDTO.setEmail(user.getEmail());
		userDTO.setRecordVersion(user.getRecordVersion());
		userDTO.setRole(user.getRole());

		return userDTO;

	}

	public static User toEntity(UserDTO userDTO){

		User user = new User();

		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setSurname(userDTO.getSurname());
		user.setAddress(userDTO.getAddress());
		user.setRole(userDTO.getRole());
		user.setRecordVersion(userDTO.getRecordVersion());
		return user;

	}

}
