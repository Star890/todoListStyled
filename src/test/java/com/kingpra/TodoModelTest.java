/**
 * 
 */
package com.kingpra;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.kingpra.Todo.Todo;

/**
 * @author phill
 *
 */
public class TodoModelTest {

	Todo sut;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		sut = Todo.builder().id(100L).date(1000L).completed(true).task("Test Task").build();
	}

	@Test
	public void givenTodoModel_WhenGetIsCalledOnId_ShouldReturnValue() {
		assertEquals((long) sut.getId(), 100L);
	}

	@Test
	public void givenTodoModel_WhenGetIsCalledOnCompleted_ShouldReturnValue() {
		assertEquals((boolean) sut.isCompleted(), true);
	}

	@Test
	public void givenTodoModel_WhenGetIsCalledOnTask_ShouldReturnValue() {
		assertEquals((String) sut.getTask(), "Test Task");
	}

	@Test
	public void givenTodoModel_WhenGetIsCalledOnDate_ShouldReturnValue() {
		assertEquals((long) sut.getDate(), 1000L);
	}
}
