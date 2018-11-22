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
import static sk.isdd.workshop.bookerbe.constants.UserConstants.ADDRESS;
import static sk.isdd.workshop.bookerbe.constants.UserConstants.EMAIL;
import static sk.isdd.workshop.bookerbe.constants.UserConstants.NAME;
import static sk.isdd.workshop.bookerbe.constants.UserConstants.ROLE;
import static sk.isdd.workshop.bookerbe.constants.UserConstants.SURNAME;
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
import sk.isdd.workshop.bookerbe.data.jpa.entity.User;
import sk.isdd.workshop.bookerbe.data.jpa.repository.UserRepository;

/**
 * @Author Filip Stiglic
 */

@Tag("ControllerTest")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	UserRepository userRepository;

	private User createUser() {
		User user = new User();
		user.setAddress(ADDRESS);
		user.setName(NAME);
		user.setSurname(SURNAME);
		user.setEmail(EMAIL);
		user.setRole(ROLE);
		return user;
	}


	@Test
	public void testCreateUser() throws Exception {

		User user = createUser();

		mockMvc.perform(
			post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJsonString(user)))
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$.id", is(notNullValue())))
			.andExpect(jsonPath("$.name", is(user.getName())))
			.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void testgetUserById() throws Exception {

		User user = userRepository.save(createUser());

		mockMvc.perform(
			get("/users/{id}", user.getId()))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$.id", is(user.getId().intValue())))
			.andExpect(jsonPath("$.name", is(user.getName())));


	}

	@Test
	public void testGetUsers() throws Exception {

		User user = userRepository.save(createUser());

		mockMvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].id", is(user.getId().intValue())))
			.andExpect(jsonPath("$[0].name", is(user.getName()))
			);
	}

	@Test
	public void testDeleteUserById() throws Exception {

		User user = userRepository.save(createUser());

		mockMvc.perform(
			delete("/users/{id}", user.getId()))
			.andExpect(status().isAccepted());


	}
}
