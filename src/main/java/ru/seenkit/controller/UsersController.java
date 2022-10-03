package ru.seenkit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.seenkit.model.User;
import ru.seenkit.service.UserService;

import javax.validation.Valid;

@Controller
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "allUsers";
    }

    @GetMapping("/add-new-user")
    public String getNewUserForm (Model model) {
        model.addAttribute("newUser", new User());
        return "addUserForm";
    }

    @PostMapping()
    public String addNewUser(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addUserForm";
        }
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit-user")
    public String getEditUserForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("editUser", userService.getUserById(id));
        return "/editUserForm";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("editUser") @Valid User user, BindingResult bindingResult,
                                 @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/editUserForm";
        }
        userService.editUser(user, id);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
