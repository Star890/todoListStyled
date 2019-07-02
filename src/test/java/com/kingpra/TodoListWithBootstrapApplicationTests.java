package com.kingpra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.kingpra.Todo.Todo;
import com.kingpra.Todo.TodoController;
import com.kingpra.Todo.TodoRepository;
import com.kingpra.Todo.TodoService;

//@RunWith(SpringRunner.class)

@DataJpaTest
@SpringBootTest
public class TodoListWithBootstrapApplicationTests {

	@Autowired
	private TodoRepository todoRepo;

	private TodoController todoController;

	private TodoService todoService;

	private Todo todo;

//	@Test
//	public void contextLoads() {
//	}

//	@Test
//	public void findById_returnById() {
//		// create a new task
//		Todo task = new Todo(1L, "this is task one", false, 11122019L);
//
//		// when found by id
//		Todo todoFound = todoRepo.findById(1L).orElse(null);
//
//		// then
//		// assertThat(task.equals(task));
//		// assertThat(todoFound.getTask()).equals(task);
//	}

}
