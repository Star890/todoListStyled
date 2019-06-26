package com.kingpra.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TodoController {

	@Autowired
	private TodoRepository todoRepo;

	public String getIndexPage() {
		return "index";
	}
}
