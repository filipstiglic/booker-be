package sk.isdd.workshop.bookerbe.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.isdd.workshop.bookerbe.converter.UserConverter;
import sk.isdd.workshop.bookerbe.data.jpa.entity.User;
import sk.isdd.workshop.bookerbe.data.jpa.repository.UserRepository;
import sk.isdd.workshop.bookerbe.dto.UserDTO;

/**
 * @Author Filip Stiglic
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public ResponseEntity<List<UserDTO>> getUsers() {

		List<UserDTO> usersDTO = StreamSupport.stream(userRepository.findAll().spliterator(), false).map(UserConverter::toDto).collect(Collectors.toList());
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);

	}

	@Transactional(readOnly = true)
	@Override
	public ResponseEntity<UserDTO> getUser(long id) {

		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity with was not found"));
		return new ResponseEntity<>(UserConverter.toDto(user), HttpStatus.OK);
	}

	@Transactional
	@Override
	public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
		User savedUser =  userRepository.save(UserConverter.toEntity(userDTO));
		return new ResponseEntity<>(UserConverter.toDto(savedUser),HttpStatus.CREATED);
	}

	@Transactional
	@Override
	public ResponseEntity<Void> deleteUser(long id) {
		userRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
