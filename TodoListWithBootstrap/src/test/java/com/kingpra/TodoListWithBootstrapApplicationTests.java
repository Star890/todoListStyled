package com.kingpra;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kingpra.Todo.Todo;
import com.kingpra.Todo.TodoController;
import com.kingpra.Todo.TodoRepository;
import com.kingpra.Todo.TodoService;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class TodoListWithBootstrapApplicationTests {

	@Autowired
	private TodoRepository todoRepo;

	private TodoController todoController;

	private TodoService todoService;

	private Todo todo;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findById_returnById() {
		Todo task = new Todo();
		task.setTask("this is task 1");
		task.setCompleted(false);
		task.setId(1L);
	}

}
