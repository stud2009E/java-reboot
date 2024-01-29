package ru.sberbank.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.edu.dao.entity.UserEntity;
import ru.sberbank.edu.dao.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public String all(Model model){
         List<UserEntity> users = userService.findAll();
         model.addAttribute("users", users);
         model.addAttribute("isLogged", isLogged());

         return "userAll";
    }


    @GetMapping(value = "/create")
    public String createForm(Model model){
        model.addAttribute("user", userService.getEmptyUserEntity());
        return "userCreate";
    }

    @GetMapping(value = "/edit/{userId}")
    public String editForm(@PathVariable(name = "userId") Long id, Model model){
        UserEntity user = userService.findById(id);

        model.addAttribute("user", user);
        return "userEdit";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute("user") UserEntity user, Model model){
        try {
            userService.edit(user);

        }catch ( IllegalArgumentException ex){
            model.addAttribute("message", ex.getMessage());
            model.addAttribute("user", user);

            return "userEdit";
        }

        return "redirect:/user/all";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute("user") UserEntity user, Model model){
        try {
            userService.create(user);

        } catch (IllegalArgumentException ex){
            model.addAttribute("message", ex.getMessage());
            model.addAttribute("user", user);

            return "userCreate";
        }

        return "redirect:/user/all";
    }


    @PostMapping(value = "/delete/{userId}")
    public String delete(@PathVariable(name = "userId") Long id){

        userService.deleteById(id);

        return "redirect:/user/all";
    }


    private boolean isLogged(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

}
