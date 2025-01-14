package izh.springbootmvcapp.controller;

import izh.springbootmvcapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import izh.springbootmvcapp.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class UsersController {

    Logger LOGGER = Logger.getLogger(UsersController.class.getName());

    private final UserServiceImpl userService;

    @Autowired
    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // GET LIST REQUEST
    @GetMapping("/")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("list", users);
        return "users";
    }
    // CREATE NEW REQUESTS
    @GetMapping("/new_user")
    public String getNewUserForm(@ModelAttribute("user") User user) {

        return "new_user";
    }
    @PostMapping("/new_user")
    public String setNewServer(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/";
    }
    //UPDATE USER REQUESTS
    @GetMapping("/edit")
    public String editUser(@RequestParam Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit_user";
    }
    @PostMapping("/edit")
    public String setEdit(@RequestParam Long id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/";
    }
    //DELETE USER REQUEST
    @GetMapping("/delete_user")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }


}