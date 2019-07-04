package com.kingpra;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.kingpra.Todo.Todo;
import com.kingpra.Todo.TodoController;
import com.kingpra.Todo.TodoRepository;
import com.kingpra.Todo.TodoService;

@WebMvcTest(TodoController.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TodoControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Mock
	Model model;

	@Mock
	private TodoRepository todoRepo;

	@Mock
	private TodoService todoService;

	private TodoController todoController;

	@Test
	public void testMockMVC() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

	@Test
	public void testGetIndex() throws Exception {

		MockitoAnnotations.initMocks(this);
		todoController = new TodoController(todoRepo);

		// given
		long millis = System.currentTimeMillis();
		Date today = new Date(millis);
		Todo taskItem1 = new Todo(1L, "Write Code", false, 1000L);
		Todo taskItem2 = new Todo(2L, "Write Unit Test", false, 1001L);

		when(todoRepo.findAll()).thenReturn(Arrays.asList(taskItem1, taskItem2));

		ArgumentCaptor<List<Todo>> argumentCaptor = ArgumentCaptor.forClass(ArrayList.class);

		// when
		String viewName = todoController.getIndexPage(model);

		// then
		assertEquals("index", viewName);
		verify(todoRepo, times(1)).findAll();
		verify(model, times(1)).addAttribute(eq("todos"), argumentCaptor.capture());
		List<Todo> listInController = argumentCaptor.getValue();
		assertEquals(2, listInController.size());
	}

	@Test
	public void testEditTodoPage() throws Exception {

		MockitoAnnotations.initMocks(this);
		todoController = new TodoController(todoRepo);

		// given
		long millis = System.currentTimeMillis();
		Date today = new Date(millis);
		Todo taskItem1 = new Todo(1L, "Write Code", true, 1000L);
		Optional<Todo> todos = Optional.of(taskItem1);
		when(todoRepo.findById(1L)).thenReturn(todos);

		ArgumentCaptor<Todo> argumentCaptor = ArgumentCaptor.forClass(Todo.class);

		// when
		String viewName = todoController.getUpdatePage(1L, model);

		// then
		assertEquals("edit", viewName);
		verify(todoRepo, times(1)).findById(1L);
		verify(model, times(1)).addAttribute(eq("todo"), argumentCaptor.capture());
		Todo inController = argumentCaptor.getValue();
		assertEquals((long) 1L, (long) inController.getId());
		assertEquals("Write Code", inController.getTask());
		assertEquals(true, inController.isCompleted());
		assertEquals(1000L, inController.getDate());

	}

	@Test
	public void testTodoSubmitUpdate() {

		MockitoAnnotations.initMocks(this);
		todoController = new TodoController(todoRepo);

		// given
		long millis = System.currentTimeMillis();
		Date today = new Date(millis);
		Todo taskItem1 = new Todo(1L, "Write Code", false, 1000L);
		Todo taskItem2 = new Todo(2L, "Write Unit Test", false, 1001L);

		when(todoRepo.findAll()).thenReturn(Arrays.asList(taskItem1, taskItem2));

		ArgumentCaptor<List<Todo>> argumentCaptor = ArgumentCaptor.forClass(ArrayList.class);

		// when
		String viewName = todoController.savePost(taskItem2, model);

		// then
		assertEquals("index", viewName);
		verify(todoRepo, times(1)).findAll();
		verify(model, times(1)).addAttribute(eq("todos"), argumentCaptor.capture());
		List<Todo> inController = argumentCaptor.getValue();
		assertEquals(2, inController.size());
		assertEquals((long) 1L, (long) inController.get(0).getId());
		assertEquals(false, inController.get(0).isCompleted());
		assertEquals("Write Code", inController.get(0).getTask());
		assertEquals(1000L, inController.get(0).getDate());
		assertEquals((long) 2L, (long) inController.get(1).getId());
		assertEquals(false, inController.get(1).isCompleted());
		assertEquals("Write Unit Test", inController.get(1).getTask());
		assertEquals(1001L, inController.get(1).getDate());
	}

	@Test
	public void testGetDeletePage() {

		MockitoAnnotations.initMocks(this);
		todoController = new TodoController(todoRepo);

		// given
		long millis = System.currentTimeMillis();
		Date today = new Date(millis);
		Todo taskItem1 = new Todo(1L, "Write Code", false, 1000L);
		Todo taskItem2 = new Todo(2L, "Write Unit Test", false, 1001L);

		when(todoRepo.findById(1L)).thenReturn(Optional.of(taskItem1));
		when(todoRepo.findAll()).thenReturn(Arrays.asList(taskItem1, taskItem2));

		ArgumentCaptor<List<Todo>> argumentCaptor = ArgumentCaptor.forClass(ArrayList.class);

		// when
		String viewName = todoController.deletePost(1L, taskItem1, model);

		// then
		assertEquals("index", viewName);
		verify(todoRepo, times(1)).findAll();
		verify(model, times(1)).addAttribute(eq("todos"), argumentCaptor.capture());
		List<Todo> inController = argumentCaptor.getValue();
		assertEquals(2, inController.size());
		assertEquals((long) 1L, (long) inController.get(0).getId());
		assertEquals(false, inController.get(0).isCompleted());
		assertEquals("Write Code", inController.get(0).getTask());
		assertEquals(1000L, inController.get(0).getDate());
		assertEquals((long) 2L, (long) inController.get(1).getId());
		assertEquals(false, inController.get(1).isCompleted());
		assertEquals("Write Unit Test", inController.get(1).getTask());
		assertEquals(1001L, inController.get(1).getDate());

	}
}
