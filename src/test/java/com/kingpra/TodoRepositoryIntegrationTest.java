package com.kingpra;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Optional;

import org.assertj.core.util.Lists;
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
		assertThat(found.get().getId()).isEqualTo(workItem.getId());
		assertThat(found.get().getTask()).isEqualTo(workItem.getTask());
		assertThat(found.get().isCompleted()).isEqualTo(workItem.isCompleted());
		assertThat(found.get().getDate()).isEqualTo(workItem.getDate());
	}

	@Test
	public void whenFindAll_ShouldReturnArray() {

		Todo item = Todo.builder().task("Write Code").completed(true).date(1000L).build();
		entityManager.persist(item);
		entityManager.flush();
		item = Todo.builder().task("Write Unit Test").completed(true).date(100L).build();
		entityManager.persist(item);
		entityManager.flush();

		ArrayList<Todo> todos = Lists.newArrayList(todoRepository.findAll());

		assertEquals(2, todos.size());
	}
}
