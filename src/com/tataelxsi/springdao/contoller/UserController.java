package com.tataelxsi.springdao.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tataelxsi.springdao.dto.User;
import com.tataelxsi.springdao.service.UserService;
import com.tataelxsi.springdao.exception.UserException;
import com.tataelxsi.springdao.exceptions.DataBaseException;

@Controller
public class UserController {
	private UserService userService;
	@Autowired
	@Qualifier("userValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@ExceptionHandler(UserException.class)
	public String handleResourceNotFoundException() {
		return "notfound";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listUsers(Model model) throws Exception {
		model.addAttribute("user", new User());
		model.addAttribute("listUsers", this.userService.getAllUsers());
		return "user";
	}

	@ExceptionHandler({ DataBaseException.class })
	@RequestMapping(value = "user/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") @Validated User u,
			BindingResult result, Model model) throws DataBaseException {
		if (result.hasErrors()) {
			try {
				model.addAttribute("listUsers", this.userService.getAllUsers());
				return "user";
			} catch (Exception e) {
				DataBaseException dbe = new DataBaseException(
						"Error in get bhgya");
				dbe.printStackTrace();
				throw dbe;
			}

		} else {
			if (u.getId() == 0) {
				// new user, add it
				try {
					this.userService.createUser(u);
				} catch (Exception e) {
					DataBaseException dbe = new DataBaseException(
							"Error in insertion");
					dbe.printStackTrace();
					throw dbe;
				}
			} else {
				try {
					// existing user, call update
					this.userService.updateUser(u);
				} catch (Exception e) {
					DataBaseException dbe = new DataBaseException(
							"Error in updation");
					dbe.printStackTrace();
					throw dbe;
				}
			}
			return "redirect:/";
		}

	}

	@RequestMapping("remove/{id}")
	public String removeUser(@PathVariable("id") int id, Model model) {
		if (id == 0) {
			throw new UserException();
		}
		try {
			this.userService.deleteUser(id);
			return "redirect:/";
		} catch (Exception e) {
			DataBaseException dbe = new DataBaseException(
					"Error in Deleting the user");
			dbe.printStackTrace();
			throw dbe;
		}
	}

	@RequestMapping("edit/{id}")
	public String editUser(@PathVariable("id") int id, Model model)
			throws Exception {
		if (id == 0) {
			throw new UserException();
		}
		try {
			model.addAttribute("user", this.userService.getUserById(id));

			model.addAttribute("listUsers", this.userService.getAllUsers());
		} catch (Exception e) {
			DataBaseException dbe = new DataBaseException(
					"Error in Getting the users");
			dbe.printStackTrace();
			throw dbe;
		}
		return "user";
	}

}
