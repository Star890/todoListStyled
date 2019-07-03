package com.kingpra;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kingpra.Todo.Todo;
//import com.kingpra.Todo.Todo;
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
	private TodoRepository repo;

	// adds Mockito mocks as a bean in Spring ApplicationContext
	@Autowired
	private TodoService service;

	private Todo todo;

	TodoController controller;

	@Disabled
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		controller = new TodoController();

	}

	public void getTaskTest() {
		when(repo.findAll()).thenReturn(Stream.of(new Todo(1L, "task test", false, 1L)).collect(Collectors.toList()));
		assertEquals("task test", todo.getTask());
	}

	@Disabled
	@Test
	public void testMvc() throws Exception {
		@SuppressWarnings("unused")
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		// mockMvc.perform(get("/")).andExpect(status().isOk());
		// mockMvc.perform(get("/")).andExpect(view().name("index"));
		// mockMvc.perform(get("/"));
		// .andExpect(view().name("index"));

	}

	// prepare data and mock behavior
//	@Test
//	public void testAddTask() {
//		Todo task = new Todo(1L, "task 1", false, 19L);
//		when(todoRepo.save(todo)).thenReturn(task);

	// String uri = "/new";

	// MvcResult result =
	// mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8)
	// .content(TestUtils.objectToJson(task))).andReturn();
//	}
}
