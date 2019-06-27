package com.kingpra.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Controller
public class TodoController {

	@Autowired
	private TodoRepository todoRepo;
	@Autowired
	private TodoService todoService;

	@GetMapping("/")
	public String getIndexPage(Model model) {
		model.addAttribute("todos", todoRepo.findAll());
		return "index";
	}

	@GetMapping("/new")
	public String getNewPage(Todo todo) {
		todoService.completionFalse(todo);
		return "new";
	}

	@PostMapping("/")
	public String savePost(Todo todo, Model model) {
		todoRepo.save(todo);
		model.addAttribute("todos", todoRepo.findAll());
		return "index";
	}

	@DeleteMapping("/delete/{id}")
	public String deletePost(@PathVariable Long id, Todo todo, Model model) {
		todoRepo.deleteById(id);
		model.addAttribute("todos", todoRepo.findAll());
		return "index";
	}

	@GetMapping("/edit/{id}")
	public String getUpdatePage(@PathVariable Long id, Model model) {
		model.addAttribute("todo", todoRepo.findById(id).get());
		return "edit";
	}

	@PutMapping("/edit/{id}")
	public String updateTask(@PathVariable Long id, Todo todo, Model model) {
		Todo task = todoRepo.findById(id).orElse(null);
		task.setTask(todo.getTask());
		todoRepo.save(task);
		model.addAttribute("todos", todoRepo.findAll());
		return "index";
	}

	@PutMapping("/completed/{id}")
	public String completionStatus(@PathVariable Long id, Todo todo, Model model) {
		Todo task = todoRepo.findById(id).orElse(null);
		todoService.completionToggle(task);
		todoRepo.save(task);
		model.addAttribute("todos", todoRepo.findAll());
		return "index";
	}

}
