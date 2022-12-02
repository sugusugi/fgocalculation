package com.example.fgocalculation.controller;

import org.springframework.stereotype.Controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.annotation.Validated;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.fgocalculation.repository.UserRepository;
import com.example.fgocalculation.form.ServantSearchForm;
import com.example.fgocalculation.form.ToolNameForm;
import com.example.fgocalculation.form.UserForm;
import com.example.fgocalculation.form.UserNameForm;
import com.example.fgocalculation.form.UserEmailForm;
import com.example.fgocalculation.form.UserPasswordForm;
import com.example.fgocalculation.entity.UserEntity;
import com.example.fgocalculation.entity.UserEntity.Authority;
import com.example.fgocalculation.encoder.PasswordHasher;

@Controller
public class UserController {
    
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PasswordHasher passwordHasher;
    
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
            ToolNameForm form = new ToolNameForm();
            form.setToolName("新規登録");
            model.addAttribute("form",form);
            model.addAttribute("create-succes", false);
            return "user/new";
        }
        UserEntity entity = new UserEntity(name, email, passwordHasher.hasher(password), Authority.ROLE_USER);
        repository.saveAndFlush(entity);
        
        ToolNameForm form = new ToolNameForm();
        form.setToolName("新規登録");
        model.addAttribute("form", form);
        model.addAttribute("createSucces", true);
        return "user/new";
    }
    
    @RequestMapping("/mypages")
    public String index(Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserEntity user = (UserEntity) authentication.getPrincipal();
        user = repository.findByEmail(user.getEmail());
        model.addAttribute("user",user);
        ToolNameForm form = new ToolNameForm();
        form.setToolName("マイページ");
        model.addAttribute("form",form);
        return "mypages/index";
    }
    
    @RequestMapping("/mypages/{toolPath}")
    public String userInf(Principal principal, @PathVariable String toolPath,Model model) {
        Authentication authentication = (Authentication) principal;
        UserEntity user = (UserEntity) authentication.getPrincipal();
        model.addAttribute("userForm", new UserForm());
        ToolNameForm form = new ToolNameForm();
        switch(toolPath) {
            case "editName":
                model.addAttribute("userNameForm", new UserNameForm());
                form.setToolName("ユーザー名の変更");
                break ;
            case "editEmail":
                model.addAttribute("userEmailForm", new UserEmailForm());
                form.setToolName("メールアドレスの変更");
                break ;
            case "editPassword":
                model.addAttribute("userPasswordForm", new UserPasswordForm());
                form.setToolName("パスワードの変更");
                break ;
        }
        model.addAttribute("form",form);
        return "mypages/" + toolPath;
    }
    
    @RequestMapping(value = "/mypages/editName", method = RequestMethod.POST)
    public String editName(@Validated @ModelAttribute("userNameForm") UserNameForm userNameForm, BindingResult bindingResult, Principal principal, Model model) {
        ToolNameForm form = new ToolNameForm();
        if(bindingResult.hasErrors()) {
            form.setToolName("ユーザー名の変更");
            model.addAttribute("userNameForm", userNameForm);
            model.addAttribute("form",form);
            return "mypages/editName";
        }
        Authentication authentication = (Authentication) principal;
        UserEntity user = repository.findByEmail(((UserEntity)authentication.getPrincipal()).getEmail());
        user.setName(userNameForm.getName());
        updateSecurityContext(user);
        repository.save(user);
        model.addAttribute("user",user);
        form.setToolName("マイページ");
        model.addAttribute("form",form);
        return "mypages/index";
    }
    
    @RequestMapping(value = "/mypages/editEmail", method = RequestMethod.POST)
    public String editEmail(@Validated @ModelAttribute("userEmailForm") UserEmailForm userEmailForm, BindingResult bindingResult, Principal principal, Model model) {
        ToolNameForm form = new ToolNameForm();
        if(repository.findByEmail(userEmailForm.getEmail()) != null) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "そのEメールはすでに使用されています。");
            bindingResult.addError(fieldError);
        }
        if(bindingResult.hasErrors()) {
            form.setToolName("メールアドレスの変更");
            model.addAttribute("userEmailForm", userEmailForm);
            model.addAttribute("form",form);
            return "mypages/editEmail";
        }
        Authentication authentication = (Authentication) principal;
        UserEntity user = repository.findByEmail(((UserEntity)authentication.getPrincipal()).getEmail());
        user.setEmail(userEmailForm.getEmail());
        updateSecurityContext(user);
        repository.save(user);
        model.addAttribute("user",user);
        form.setToolName("マイページ");
        model.addAttribute("form",form);
        return "mypages/index";
    }
    
    @RequestMapping(value = "/mypages/editPassword", method = RequestMethod.POST)
    public String editPassword(@Validated @ModelAttribute("userPasswordForm") UserPasswordForm userPasswordForm, BindingResult bindingResult, Principal principal, Model model) {
        ToolNameForm form = new ToolNameForm();
        Authentication authentication = (Authentication) principal;
        UserEntity user = repository.findByEmail(((UserEntity)authentication.getPrincipal()).getEmail());
        if (!(user.getPassword().equals(passwordHasher.hasher(userPasswordForm.getNowPassword())))) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "nowPassword", "現在のパスワードと一致しません。");
            bindingResult.addError(fieldError);
        }
        if(bindingResult.hasErrors()) {
            userPasswordForm = new UserPasswordForm();
            form.setToolName("パスワードの変更");
            model.addAttribute("form",form);
            return "mypages/editPassword";
        }
        user.setPassword(passwordHasher.hasher(userPasswordForm.getPassword()));
        updateSecurityContext(user);
        repository.save(user);
        model.addAttribute("user",user);
        form.setToolName("マイページ");
        model.addAttribute("form",form);
        return "mypages/index";
    }
    
    private static void updateSecurityContext(UserEntity userEntity) {
        UserDetails user = (UserDetails) userEntity;
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
    }
    
    @RequestMapping("/mypages/delete")
    private String delete(Model model) {
        ToolNameForm form = new ToolNameForm();
        form.setToolName("アカウント削除");
        model.addAttribute("form", form);
        return "mypages/delete";
    }
    
    @RequestMapping(value = "/mypages/delete", method = RequestMethod.POST)
    private String delete(Principal principal, HttpServletRequest request) {
        Authentication authentication = (Authentication) principal;
        UserEntity user = repository.findByEmail(((UserEntity)authentication.getPrincipal()).getEmail());
        repository.delete(user);
        try {
            request.logout();
          } catch (ServletException e) {
            throw new RuntimeException("logout failure");
          }
        return "redirect:/";
    }
}

