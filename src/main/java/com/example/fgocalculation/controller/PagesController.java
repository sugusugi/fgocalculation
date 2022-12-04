package com.example.fgocalculation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.example.fgocalculation.form.ToolNameForm;

@Controller
public class PagesController {

    @RequestMapping("/")
    public String index(Model model) {
        ToolNameForm form = new ToolNameForm();
        form.setToolName("ホーム");
        model.addAttribute("form", form);
        return "pages/index";
    }
    
    @RequestMapping("/terms")
    public String terms(Model model) {
        ToolNameForm form = new ToolNameForm();
        form.setToolName("利用規約");
        model.addAttribute("form", form);
        return "pages/terms";
    }
    
    @RequestMapping("/privacy")
    public String privacy(Model model) {
        ToolNameForm form = new ToolNameForm();
        form.setToolName("個人情報保護方針");
        model.addAttribute("form", form);
        return "pages/privacy";
    }
}
