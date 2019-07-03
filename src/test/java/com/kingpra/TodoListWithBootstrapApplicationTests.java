package com.kingpra;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kingpra.Todo.Todo;
import com.kingpra.Todo.TodoController;
import com.kingpra.Todo.TodoRepository;
import com.kingpra.Todo.TodoService;

//@RunWith(SpringRunner.class)

@DataJpaTest
@SpringBootTest
public class TodoListWithBootstrapApplicationTests {

	@MockBean
	private TodoRepository repo;

	private TodoController controller;
	@Autowired
	private TodoService service;

	private Todo todo;

	public void getUsersTest() {
		when(repo.findAll()).thenReturn(Stream.of(new Todo(1L, "task test", false, 1L)).collect(Collectors.toList()));
	}

	@Test
	public void findById_returnById() {
		// create a new task
		Todo task = new Todo(1L, "this is task one", false, 11122019L);
		assertNotNull(task);
		assertFalse("completion status should return false", task.isCompleted());
	}
}
