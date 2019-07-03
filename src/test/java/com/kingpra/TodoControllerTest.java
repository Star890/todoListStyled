package com.kingpra;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.kingpra.Todo.Todo;
import com.kingpra.Todo.TodoController;
import com.kingpra.Todo.TodoRepository;
import com.kingpra.Todo.TodoService;

// registers Spring TestContext into JUnit 5's Jupiter programming model;
@ExtendWith(SpringExtension.class)
//Auto configure Spring Mvc infastructure and Mock Mvc.
@WebMvcTest(TodoController.class)
class TodoControllerTest {

	// provides SpringMVC infastructure without starting the HTTP server;
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TodoRepository todoRepo;

	// adds Mockito mocks as a bean in Spring ApplicationContext
	@MockBean
	private TodoService todoService;

	@Before
	public void setup() {
		Todo todo = new Todo(100L, "test task", false, 100L);
	}

	@Test
	public void getByIdTest() {
		Todo todo = todoRepo.findById(100L).orElse(null);
		// assertEquals(100L, todo.getId());
		assertNotNull("should return a task object", todo);

	}

}
