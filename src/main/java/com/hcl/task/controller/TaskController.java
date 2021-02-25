package com.hcl.task.controller;

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
//		User user2=new User();
//		user2.setPassword("2345");
//		user2.setRoles("ROLE_USER");
//		user2.setUsername("alhilo");
//		userRepo.save(user2);
//		
//		m.addAttribute("user",user2);

		Task task = new Task();

		m.addAttribute("task", task);

		return "createTask";
	}

	@PostMapping("/newtask")
	public String postcreateTask(@RequestParam("taksName") String taskname, @RequestParam("sdate") String sdate,
			@RequestParam("edate") String edate, @RequestParam("description") String description,
			@RequestParam("email") String email, @RequestParam("user") String username,
			@RequestParam("severity") String severity, Model m) {

		Optional<User> existuser = userRepo.findByUserName(username);

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
			return "welcome";

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

//	@PostMapping("/newtask")
//	public String postcreateTask(@ModelAttribute Task t,Model m,@RequestParam("user2") User user) {
//		System.out.println("it is here");
//		
//		return "welcom";
//		
//	}

//	@PostMapping("/newtask")
//	public String postcreateTask(@RequestParam("taksName") String taskname, @RequestParam("sdate") String sdate,
//			@RequestParam("edate") String edate, @RequestParam("description") String description,
//			@RequestParam("email") String email, @RequestParam("user") String user,
//			@RequestParam("severity") String severity, Model m) {
//
//		System.out.println("taskname: " + taskname);
//		System.out.println("sdate : " + sdate);
//		System.out.println("edate : " + edate);
//		System.out.println("description: " + description);
//		System.out.println("email : " + email);
//		System.out.println("user : " + user);
//		System.out.println("severity :" + severity);
//		
//		
//		User user2=new User();
//		user2.setPassword("2345");
//		user2.setRoles("ROLE_USER");
//		user2.setUserName("alhilo");
//		
//		
//		Task task = new Task();
//
//		task.setTaksName(taskname);
//		task.setSdate(sdate);
//		task.setEdate(edate);
//		task.setDescription(description);
//		task.setEmail(email);
//		task.setUser(user2);
//		task.setSeverity(severity);
//		
//		taskRepo.save(task);
//		
//
//		return "welcome";
//
//	}

	@GetMapping("/user")
	public String user() {

		return "welcome";

	}

	@GetMapping("/admin")
	public String admin(Model m) {

		System.out.println("in admin");
		List<Task> tasks = taskRepo.findAll();

		m.addAttribute("tasks",tasks);
//
//		return "listalltasks";
		return "admin";
	}

//	@GetMapping("/create")
//	public String create() {
//
//		Task task = new Task();
//		task.setTaksName("task1");
//		task.setDescription("complete task1");
//		task.setEdate("1/2/2021");
//		task.setSdate("2/2/2021");
//		task.setSeverity("important");
//
//		User user1 = new User();
//		user1.setPassword("1234");
//		user1.setRoles("ROLE_USER");
//		user1.setUserName("mustafa");
//		userRepo.save(user1);
//
//		task.setUser(user1);
//		taskRepo.save(task);
//
//		Optional<Task> fromdbTASK = taskRepo.findById(task.getId());
//
//		if (fromdbTASK.isPresent()) {
//			User userfromdb = fromdbTASK.get().getUser();
//
//			System.out.println(userfromdb.getUserName());
//		}
//
//		return "showTask";
//	}

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

		return "welcome";
	}

}
