package com.example.fgocalculation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.fgocalculation.entity.ServantList;
import com.example.fgocalculation.repository.ServantListRepository;
import com.example.fgocalculation.form.ServantSearchForm;
import com.example.fgocalculation.form.PhantasmForm;
import com.example.fgocalculation.form.NpGetForm;
import com.example.fgocalculation.form.OverKillHitForm;

@Controller
public class ServantSerchController {

    @Autowired
    ServantListRepository repository;

    @RequestMapping(path = "/{toolPath}/search", method = RequestMethod.POST)
    public String search(@Validated @ModelAttribute("form") ServantSearchForm form, BindingResult bindingResult,
            @PathVariable String toolPath, Model model) {
        model.addAttribute("form", form);
        String servantName = form.getServantName();
        ServantList servant = repository.findByServantName(servantName);

        if (servant == null) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "servantName", "指定されたサーヴァントは存在しません。");
            bindingResult.addError(fieldError);
        }

        switch (toolPath) {
        case "phantasm":
            if (bindingResult.hasErrors()) {
                model.addAttribute("servantParameter", new PhantasmForm());
                return "calculator/" + toolPath;
            }
            double atk = 0;
            switch (form.getLevel()) {
            case "atk":
                atk = servant.getAtk();
                break;
            case "atkLv100":
                atk = servant.getAtkLv100();
                break;
            case "atkLv120":
                atk = servant.getAtkLv120();
                break;
            }
            PhantasmForm phantasmForm = new PhantasmForm();
            phantasmForm.setServantName(servantName);
            phantasmForm.setClassTypeName(servant.getClassTypeName());
            phantasmForm.setAtk(atk);
            phantasmForm.setPhantasmCardType(servant.getPhantasmCardType());
            phantasmForm.setPhantasmRange(servant.getPhantasmRange());
            model.addAttribute("servantParameter", phantasmForm);
            break;
        case "npGet":
            model.addAttribute("overKillHitForm", new OverKillHitForm());
            if (bindingResult.hasErrors()) {
                model.addAttribute("servantParameter", new NpGetForm());
                model.addAttribute("overKillHitForm", new OverKillHitForm());
                return "calculator/" + toolPath;
            }
            NpGetForm npGetForm = new NpGetForm();
            npGetForm.setServantName(servantName);
            npGetForm.setCardType(servant.getPhantasmCardType());
            npGetForm.setPhantasmRange(servant.getPhantasmRange());
            npGetForm.setHit(servant.getPhantasmHit());
            model.addAttribute("servantParameter", npGetForm);
            break;
        }
        return "calculator/" + toolPath;
    }
}