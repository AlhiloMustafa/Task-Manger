package com.hcl.task.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hcl.task.Dao.Task;
import com.hcl.task.Dao.TaskRepository;
import com.hcl.task.Dao.User;
import com.hcl.task.Dao.UserRepository;

@Controller
public class TaskController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	TaskRepository taskRepo;

	@GetMapping("/")
	public String home() {

		return "index";
	}

	@GetMapping("/createTask")
	public String createTask(Model m) {

		Task task = new Task();

		m.addAttribute("task", task);

		List<String> severityList = Arrays.asList("High", "Medium", "Low");
		m.addAttribute("severityList", severityList);

		return "createTask";
	}

	@PostMapping("/newtask")
	public String postcreateTask(@RequestParam("id") Long id, @RequestParam("taksName") String taskname,
			@RequestParam("sdate") String sdate, @RequestParam("edate") String edate,
			@RequestParam("description") String description, @RequestParam("email") String email,
			@RequestParam("user") String username, @RequestParam("severity") String severity, Model m) {

		Optional<User> existuser = userRepo.findByUserName(username);

		if (id != 0) {
			System.out.println("id not 0 it is : " + id);
			User userfromdb = existuser.get();

			Optional<Task> existTask = taskRepo.findById(id);
			Task theTask = existTask.get();

			theTask.setTaksName(taskname);
			theTask.setSdate(sdate);
			theTask.setEdate(edate);
			theTask.setDescription(description);
			theTask.setEmail(email);
			theTask.setUser(userfromdb);
			theTask.setSeverity(severity);

			taskRepo.save(theTask);

			return "redirect:/admin";

		}

		if (existuser.isPresent()) {
			User userfromdb = existuser.get();

			Task task = new Task();

			task.setTaksName(taskname);
			task.setSdate(sdate);
			task.setEdate(edate);
			task.setDescription(description);
			task.setEmail(email);
			task.setUser(userfromdb);
			task.setSeverity(severity);

			taskRepo.save(task);
			return "redirect:/admin";

		} else {

			User user1 = new User();
			user1.setUserName(username);

			m.addAttribute(user1);
			String message = "No User with the name : " + user1.getUserName()
					+ " found, please create user here to continue:";
			m.addAttribute("errorMessage", message);

			return "newuser";
		}
	}

	@GetMapping("/user")
	public String user() {

		return "welcome";

	}

	@GetMapping("/admin")
	public String admin(Model m) {

		List<Task> tasks = taskRepo.findAll();

		m.addAttribute("tasks", tasks);

		return "admin";
	}

	@GetMapping("/newuser")
	public String newuser(Model m) {

		User user = new User();
		m.addAttribute("user", user);

		return "newuser";
	}

	@PostMapping("/savenewuser")
	public String newuser(@ModelAttribute User user, Model m) {

		System.out.println("save user info");
		System.out.println(user.getId());
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		System.out.println(user.getRoles());

		userRepo.save(user);

		return "admin";
	}

	@GetMapping("/updatetask")
	public String updateform(@RequestParam("taskId") long taskid, Model themodel) {

		Optional<Task> taskFromDb = taskRepo.findById(taskid);

		themodel.addAttribute("task", taskFromDb);

		List<String> severityList = Arrays.asList("High", "Medium", "Low");
		themodel.addAttribute("severityList", severityList);

		return "createTask";

	}

	@GetMapping("/deletetask")
	public String deletetask(@RequestParam("taskId") long taskid, Model themodel) {

		taskRepo.deleteById(taskid);

		return "redirect:/admin";

	}
}
