package sk.isdd.workshop.bookerbe.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.isdd.workshop.bookerbe.dto.UserDTO;
import sk.isdd.workshop.bookerbe.service.UserService;

/**
 * @Author Filip Stiglic
 */

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> getUsers(){
		return userService.getUsers();
	}

	@PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
		return userService.createUser(userDTO);
	}

	@GetMapping(path="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id){
		return userService.getUser(id);
	}

	@DeleteMapping(path="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
		return userService.deleteUser(id);
	}




}
