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
        model.addAttribute("form",form);
        return "pages/index";
    }
}
