package com.kingpra;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.kingpra.Todo.Todo;
import com.kingpra.Todo.TodoRepository;

/*
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.kingpra.Todo.Todo;
import com.kingpra.Todo.TodoController;
import com.kingpra.Todo.TodoRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class ControllerTest {

	@Autowired
	private TodoController todoController;

	@MockBean
	private TodoRepository repo;

	@Before
	public void setUp() {
		Todo workItem = new Todo();
		workItem.setId(100L);
		workItem.setDate(1000L);
		workItem.setTask("My Test Task");
		Mockito.when(repo.findById(workItem.getId()).get()).thenReturn(workItem);
	}

	// write test cases here
	@Test
	public void givenTodo_WhenGetTodo_ThenReturnArray() {
		todoController.
	}
}*/

public class ControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private TodoRepository todoRepo;

	@Autowired
	private WebApplicationContext webAppContext;

	@Before
	public void setUp() {
		Todo workItem = new Todo();
		workItem.setId(100L);
		workItem.setDate(1000L);
		workItem.setTask("My Test Task");
		Optional<Todo> opt = Optional.of(workItem);
		Mockito.when(todoRepo.findById(100L)).thenReturn(opt);
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@Test
	public void MVCTest() throws Exception {
		mockMvc.perform(get("/edit/{id}", 100L)).andExpect(status().isOk());
	}
}