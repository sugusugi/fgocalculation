package com.example.fgocalculation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.example.fgocalculation.repository.CardTypeRepository;
import com.example.fgocalculation.repository.ClassTypeRepository;
import com.example.fgocalculation.repository.ServantListRepository;
import com.example.fgocalculation.entity.ClassType;
import com.example.fgocalculation.entity.ServantList;
import com.example.fgocalculation.form.ToolNameForm;
import com.example.fgocalculation.form.ServantSearchForm;
import com.example.fgocalculation.form.NpGetForm;
import com.example.fgocalculation.form.OverKillHitForm;

@Controller
public class NpGetController {

    @Autowired
    ServantListRepository repository;

    @Autowired
    CardTypeRepository cardTypeRepository;

    @Autowired
    ClassTypeRepository classTypeRepository;

    @RequestMapping("/calculator/npGet")
    public String index(Model model) {
        ToolNameForm form = new ServantSearchForm();
        form.setToolName("NP計算");
        model.addAttribute("form", form);
        model.addAttribute("servantParameter", new NpGetForm());
        model.addAttribute("overKillHitForm", new OverKillHitForm());
        return "calculator/npGet";
    }

    @RequestMapping(value = "/calculation/npGet", method = RequestMethod.POST)
    public String npGetCalculation(
            @ModelAttribute("servantParameter") NpGetForm servantParameter,
            @ModelAttribute("form") ServantSearchForm form,
            @ModelAttribute("overKillHitForm") OverKillHitForm overKillHitForm,
            Model model) {
        model.addAttribute("servantParameter", servantParameter);
        model.addAttribute("form", form);
        model.addAttribute("overKillHitForm", overKillHitForm);
        ServantList servant = repository.findByServantName(servantParameter.getServantName());

        double npRateAttack = servant.getNpRateAttack();
        double cardNpRate = cardTypeRepository.findByCardTypeName(servant.getPhantasmCardType()).getNpRate();
        double cardBuf = servantParameter.getCardBuf() / 100;
        double npGetBuf = servantParameter.getNpGetBuf() / 100;
        double hit = servantParameter.getHit();
        double overKillHit = servantParameter.getOverKillHit();

        double enemyNpRate1 = 0;
        double enemyNpRate2 = 0;
        double enemyNpRate3 = 0;

        if (servantParameter.getEnemyClass1() != "") {
            enemyNpRate1 = classTypeRepository.findByClassTypeName(servantParameter.getEnemyClass1()).getEnemyNpRate();
        }

        if (servantParameter.getEnemyClass2() != "") {
            enemyNpRate2 = classTypeRepository.findByClassTypeName(servantParameter.getEnemyClass2()).getEnemyNpRate();
        }
        if (servantParameter.getEnemyClass3() != "") {
            enemyNpRate3 = classTypeRepository.findByClassTypeName(servantParameter.getEnemyClass3()).getEnemyNpRate();
        }

        double npGet = npRateAttack * cardNpRate * (1 + cardBuf) * (1 + npGetBuf) * hit
                * (enemyNpRate1 + enemyNpRate2 + enemyNpRate3);
        double overKillNpGet = npRateAttack * cardNpRate * (1 + cardBuf) * (1 + npGetBuf)
                * ((hit - overKillHit) + (overKillHit * 1.5)) * (enemyNpRate1 + enemyNpRate2 + enemyNpRate3);

        model.addAttribute("npGet", Math.round(Math.floor(npGet)));
        model.addAttribute("overKillNpGet", Math.round(Math.floor(overKillNpGet)));

        return "calculator/npGet";
    }

    @RequestMapping(value = "/calculation/overKill", method = RequestMethod.POST)
    public String overKillCalculation(
            @ModelAttribute("servantParameter") NpGetForm servantParameter,
            @ModelAttribute("form") ServantSearchForm form,
            @ModelAttribute("overKillHitForm") OverKillHitForm overKillHitForm, Model model) {
        model.addAttribute("form", form);
        model.addAttribute("overKillHitForm", overKillHitForm);
        ServantList servant = repository.findByServantName(servantParameter.getServantName());
        List<Integer> phantasmRateHits = new ArrayList<>();
        phantasmRateHits.add(servant.getPhantasmRateFirstHit());
        phantasmRateHits.add(servant.getPhantasmRateSecondHit());
        phantasmRateHits.add(servant.getPhantasmRateThirdHit());
        phantasmRateHits.add(servant.getPhantasmRateFourthHit());
        phantasmRateHits.add(servant.getPhantasmRateFifthHit());
        phantasmRateHits.add(servant.getPhantasmRateSixthHit());
        phantasmRateHits.add(servant.getPhantasmRateSeventhHit());
        phantasmRateHits.add(servant.getPhantasmRateEightHit());
        phantasmRateHits.add(servant.getPhantasmRateNinethHit());
        phantasmRateHits.add(servant.getPhantasmRateTenthHit());
        phantasmRateHits.add(servant.getPhantasmRateEleventhHit());
        phantasmRateHits.add(servant.getPhantasmRateTwelfththHit());
        phantasmRateHits.add(servant.getPhantasmRateThirteenthHit());
        phantasmRateHits.add(servant.getPhantasmRateFourteenthHit());
        phantasmRateHits.add(servant.getPhantasmRateFifteenthHit());

        int totalPhantasmRateHit = 0;
        for (int phantasmRateHit : phantasmRateHits) {
            totalPhantasmRateHit += phantasmRateHit;
        }

        int overKillHit = 0;
        double damage1 = overKillHitForm.getDamage1();
        if (damage1 != 0) {
            double enemyHp1 = overKillHitForm.getEnemyHp1();
            double hitDamage1 = 0;
            for (int phantasmRateHit : phantasmRateHits) {
                if (phantasmRateHit == 0) {
                    break;
                }
                hitDamage1 += damage1 * phantasmRateHit / totalPhantasmRateHit;
                if (enemyHp1 - hitDamage1 <= 0) {
                    overKillHit++;
                }
            }
        }
        double damage2 = overKillHitForm.getDamage2();
        if (damage2 != 0) {
            double enemyHp2 = overKillHitForm.getEnemyHp2();
            double hitDamage2 = 0;
            for (int phantasmRateHit : phantasmRateHits) {
                if (phantasmRateHit == 0) {
                    break;
                }
                hitDamage2 += damage2 * phantasmRateHit / totalPhantasmRateHit;
                if (enemyHp2 - hitDamage2 <= 0) {
                    overKillHit++;
                }
            }
        }
        double damage3 = overKillHitForm.getDamage3();
        if (damage3 != 0) {
            double enemyHp3 = overKillHitForm.getEnemyHp3();
            double hitDamage3 = 0;
            for (int phantasmRateHit : phantasmRateHits) {
                if (phantasmRateHit == 0) {
                    break;
                }
                hitDamage3 += damage3 * phantasmRateHit / totalPhantasmRateHit;
                if (enemyHp3 - hitDamage3 <= 0) {
                    overKillHit++;
                }
            }
        }
        servantParameter.setOverKillHit(overKillHit);
        model.addAttribute("servantParameter", servantParameter);
        return "calculator/npGet";
    }

}
