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
        return "index";
    }

    @GetMapping("/add-new-user")
    public String getNewUserForm (Model model) {
        model.addAttribute("newUser", new User());
        return "newUser";
    }

    @PostMapping()
    public String addNewUser(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newUser";
        }
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String getEditUserForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("editUser", userService.getUserById(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("editUser") @Valid User user, BindingResult bindingResult,
                                 @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/edit";
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
