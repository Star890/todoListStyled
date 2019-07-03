package com.kingpra;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.kingpra.Todo.Todo;
//import com.kingpra.Todo.Todo;
import com.kingpra.Todo.TodoController;
import com.kingpra.Todo.TodoRepository;
import com.kingpra.Todo.TodoService;

// registers Spring TestContext into JUnit 5's Jupiter programming model;
@ExtendWith(SpringExtension.class)
// Auto configure Spring Mvc infastructure and Mock Mvc.
@WebMvcTest(TodoController.class)
class TodoControllerTest {
	@Mock
	Model model;
	// provides SpringMVC infastructure without starting the HTTP server;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private TodoRepository repo;
	// adds Mockito mocks as a bean in Spring ApplicationContext
	@Mock
	private TodoService service;
	private Todo todo;
	TodoController controller;
}
