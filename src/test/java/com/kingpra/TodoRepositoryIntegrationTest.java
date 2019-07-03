package com.kingpra;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.kingpra.Todo.Todo;
import com.kingpra.Todo.TodoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TodoRepository todoRepository;

	@Test
	public void whenFindById_thenReturnTodoItem() {
		// given
		Todo workItem = new Todo();
		// workItem.setId(100L);
		workItem.setDate(10000L);
		workItem.setTask("Complete Todo App Unit Test");
		workItem.setCompleted(false);

		entityManager.persist(workItem);
		entityManager.flush();

		// when
		Optional<Todo> found = todoRepository.findById(workItem.getId());

		// then
		assertThat(found.get()).isNotNull();
		assertThat(found.get().getTask()).isEqualTo(workItem.getTask());
	}
}
