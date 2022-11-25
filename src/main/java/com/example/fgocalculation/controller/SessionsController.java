package com.example.fgocalculation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fgocalculation.form.ServantSearchForm;
import com.example.fgocalculation.form.ToolNameForm;

@Controller
public class SessionsController {

    @RequestMapping("sessions/login")
    public String login(Model model) {
        ToolNameForm form = new ServantSearchForm();
        form.setToolName("ログイン");
        model.addAttribute("form", form);
        return "sessions/login";
    }

    @RequestMapping("/login-failure")
    public String loginFailure(Model model) {
        ToolNameForm form = new ServantSearchForm();
        form.setToolName("ログイン");
        model.addAttribute("form", form);
        model.addAttribute("hasMessage", true);
        model.addAttribute("message", "Emailまたはパスワードに誤りがあります。");

        return "sessions/login";
    }

    @RequestMapping("/logout-complete")
    public String logoutComplete(Model model) {
        ToolNameForm form = new ToolNameForm();
        form.setToolName("ホーム");
        model.addAttribute("form",form);
        model.addAttribute("hasMessage", true);
        model.addAttribute("message", "ログアウトしました。");
        return "pages/index";
    }
}
