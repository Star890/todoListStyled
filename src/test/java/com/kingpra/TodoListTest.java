package com.kingpra;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import static org.junit.Assert.assertNotNull;

import com.kingpra.Todo.Todo;
import com.kingpra.Todo.TodoController;
import com.kingpra.Todo.TodoRepository;
import com.kingpra.Todo.TodoService;

public class TodoListTest {

	@Autowired
	private TodoRepository todoRepo;
	@Autowired
	private TodoController controller;
	@Autowired
	private TodoService todoService;

	private Todo todo;

	private Model model;

	@Test
	public void findById_returnById() {
		// create a new task
		Todo task = new Todo(1L, "this is task one", false, 2019L);

		assertNotNull("should return an index page", task);
	}

}
