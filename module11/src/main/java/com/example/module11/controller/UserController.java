package com.example.module11.controller;

import com.example.module11.entity.UserEntity;
import com.example.module11.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserRepository repo;
    @Autowired
    public void setRepo(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping(value = "/all")
    public String all(Model model){
         List<UserEntity> users = repo.findAll();
         model.addAttribute("users", users);

         return "userAll";
    }


    @GetMapping(value = "/create")
    public String create(Model model){
        model.addAttribute("user", new UserEntity("", null));
        return "userCreate";
    }

    @GetMapping(value = "/edit/{userId}")
    public String edit(@PathVariable(name = "userId") Long id, Model model){

        UserEntity user = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        model.addAttribute("user", user);
        return "userEdit";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute("user") UserEntity user){
        repo.findById(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found. Update error"));

        repo.save(user);

        return "redirect:/user/all";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute("user") UserEntity user){
        UserEntity newUser = repo.save(user);
        return "redirect:/user/all";
    }


    @PostMapping(value = "/delete/{userId}")
    public String delete(@PathVariable(name = "userId") Long id){
        repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        repo.deleteById(id);

        return "redirect:/user/all";
    }


}
