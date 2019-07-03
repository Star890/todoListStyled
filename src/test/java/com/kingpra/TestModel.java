package com.kingpra;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.kingpra.Todo.Todo;

class TestModel {

	@Test
	public void setTask() {
		Todo task = new Todo(1L, "test task", false, 1L);
		assertEquals("task names", "test task", task.getTask());
	}

	@Test
	public void getTask() {
		Todo task = new Todo();
		task.setTask("test");
		assertEquals("task name", "test", task.getTask());
	}

}
