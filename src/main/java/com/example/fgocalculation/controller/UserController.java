package com.example.fgocalculation.controller;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.annotation.Validated;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.fgocalculation.repository.UserRepository;
import com.example.fgocalculation.form.ServantSearchForm;
import com.example.fgocalculation.form.ToolNameForm;
import com.example.fgocalculation.form.UserForm;
import com.example.fgocalculation.entity.User;
import com.example.fgocalculation.entity.User.Authority;
import com.example.fgocalculation.encoder.PasswordHasher;

@Controller
public class UserController {
    
    @Autowired
    private UserRepository repository;
    
    @RequestMapping("/user/new")
    public String uerNew(Model model) {
        model.addAttribute("userForm", new UserForm());
        ToolNameForm form = new ServantSearchForm();
        form.setToolName("新規登録");
        model.addAttribute("form",form);
        model.addAttribute("createSucces", false);
        return "user/new";
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String createUser(@Validated @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model model) {
        String name = userForm.getName();
        String email = userForm.getEmail();
        String password = userForm.getPassword();
        if(repository.findByEmail(email) != null) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "そのEメールはすでに使用されています。");
            bindingResult.addError(fieldError);
        }
        if (bindingResult.hasErrors()) {
            ToolNameForm form = new ServantSearchForm();
            form.setToolName("新規登録");
            model.addAttribute("form",form);
            model.addAttribute("create-succes", false);
            return "user/new";
        }
        User entity = new User(name, email, new PasswordHasher().hasher(password), Authority.ROLE_USER);
        repository.saveAndFlush(entity);
        
        ToolNameForm form = new ServantSearchForm();
        form.setToolName("新規登録");
        model.addAttribute("form", form);
        model.addAttribute("createSucces", true);
        return "user/new";
    }
}
