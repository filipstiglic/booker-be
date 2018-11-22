package sk.isdd.workshop.bookerbe.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import sk.isdd.workshop.bookerbe.dto.UserDTO;

/**
 * @Author Filip Stiglic
 */
public interface UserService {

	ResponseEntity<List<UserDTO>> getUsers();

	ResponseEntity<UserDTO> getUser(long id);

	ResponseEntity<UserDTO> createUser(UserDTO userDTO);

	ResponseEntity<Void> deleteUser(long id);

}
