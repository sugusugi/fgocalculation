package com.example.fgocalculation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.fgocalculation.form.PhantasmForm;
import com.example.fgocalculation.form.ServantSearchForm;
import com.example.fgocalculation.form.ToolNameForm;
import com.example.fgocalculation.entity.ServantList;
import com.example.fgocalculation.entity.CardType;
import com.example.fgocalculation.entity.ClassType;
import com.example.fgocalculation.repository.ServantListRepository;
import com.example.fgocalculation.repository.CardTypeRepository;
import com.example.fgocalculation.repository.ClassTypeRepository;
import com.example.fgocalculation.check.ConsoleOutput;

@Controller
public class PhantasmDamageController {
    
    @Autowired
    ServantListRepository servantListRepository;
    
    @Autowired
    CardTypeRepository cardTypeRepository;
    
    @Autowired
    ClassTypeRepository classTypeRepository;
    
    @RequestMapping("/calculator/phantasm")
    public String index(Model model) {
        ToolNameForm form = new ServantSearchForm();
        form.setToolName("宝具ダメージ");
        model.addAttribute("form",form);
        model.addAttribute("servantParameter", new PhantasmForm());
        return "calculator/phantasm";
    }
    
    
    @RequestMapping(value = "/calculation/phantasm", method = RequestMethod.POST)
    public String calculation(@ModelAttribute("servantParameter") PhantasmForm servantParameter, @ModelAttribute("form") ServantSearchForm form,Model model) {
        model.addAttribute("servantParameter",servantParameter);
        model.addAttribute("form",form);
        
        double answer;
        double answer1Max;
        double answer1Min;
        double answer2Max;
        double answer2Min;
        double answer3Max;
        double answer3Min;
        
        ServantList servant= servantListRepository.findByServantName(servantParameter.getServantName());
        
        double atk = servantParameter.getAtk();
        double fou = servantParameter.getFou();
        double spiritual = servantParameter.getSpiritual();
        
        double atkBuf = 1 + servantParameter.getAtkBuf()/100;
        double cardBuf = 1 + servantParameter.getCardBuf()/100;
        double phantasmBuf = 1 + servantParameter.getPhantasmBuf()/100;
        double specialBuf = servantParameter.getSpecialBuf()/100;
        double phantasmSpecial = servantParameter.getPhantasmSpecial()/100;
        
        double[] phantasmRates = {servant.getPhantasmRateLv1(),servant.getPhantasmRateLv2(),servant.getPhantasmRateLv3(),servant.getPhantasmRateLv4(),servant.getPhantasmRateLv5()};
        int phantasmLv = servantParameter.getPhantasmLv();
        double phantasmRate = 1 + phantasmRates[phantasmLv - 1]/100;
        
        CardType cardType = cardTypeRepository.findByCardTypeName(servant.getPhantasmCardType());
        double cardAtkRate = cardType.getAtkRate();
        
        ClassType classType = classTypeRepository.findByClassTypeName(servant.getClassTypeName());
        double classAtkRate = classType.getAtkRate();
        
        double classRate1 = servantParameter.getClassRate1();
        double classRate2 = servantParameter.getClassRate2();
        double classRate3 = servantParameter.getClassRate3();
        
        double attributeRate1 = servantParameter.getAttributeRate1();
        double attributeRate2 = servantParameter.getAttributeRate2();
        double attributeRate3 = servantParameter.getAttributeRate3();
        
        ConsoleOutput.checkValue("攻撃力は",atk + fou + spiritual);
        ConsoleOutput.checkValue("宝具倍率",phantasmRate);
        ConsoleOutput.checkValue("宝具定数", 0.23);
        ConsoleOutput.checkValue("カード攻撃補正",cardAtkRate);
        ConsoleOutput.checkValue("クラス攻撃補正",classAtkRate);
        ConsoleOutput.checkValue("攻撃力バフは:",atkBuf);
        ConsoleOutput.checkValue("カードバフ",cardBuf);
        ConsoleOutput.checkValue("特殊バフ",phantasmBuf + specialBuf);
        ConsoleOutput.checkValue("宝具特攻",phantasmSpecial);
        ConsoleOutput.checkValue("クラス相性",classRate1);
        ConsoleOutput.checkValue("属性相性",attributeRate1);
        
        answer = (atk + fou + spiritual) * 0.23 * classAtkRate * phantasmRate * (cardAtkRate * cardBuf) * atkBuf * (phantasmBuf + specialBuf) * phantasmSpecial;
        double answer1 = answer * classRate1 * attributeRate1;
        double answer2 = answer * classRate2 * attributeRate2;
        double answer3 = answer * classRate3 * attributeRate3;
        
        answer1Max = answer1 * 1.099;
        answer1Min = answer1 * 0.9;
        answer2Max = answer2 * 1.099;
        answer2Min = answer2 * 0.9;
        answer3Max = answer3 * 1.099;
        answer3Min = answer3 * 0.9;
        
        double answerMaxTotal = answer1Max +answer2Max + answer3Max;
        double answerTotal = answer1 + answer2 + answer3;
        double answerMinTotal = answer1Min +answer2Min + answer3Min;
        
        ConsoleOutput.checkValue("宝具ダメージ1　MAX",answer1Max);
        ConsoleOutput.checkValue("宝具ダメージ1　平均",answer1);
        ConsoleOutput.checkValue("宝具ダメージ1　MIN",answer1Min);
        ConsoleOutput.checkValue("宝具ダメージ2　MAX",answer2Max);
        ConsoleOutput.checkValue("宝具ダメージ2　平均",answer2);
        ConsoleOutput.checkValue("宝具ダメージ2　MIN",answer2Min);
        ConsoleOutput.checkValue("宝具ダメージ3　MAX",answer3Max);
        ConsoleOutput.checkValue("宝具ダメージ3　平均",answer3);
        ConsoleOutput.checkValue("宝具ダメージ3　MIN",answer3Min);
        
        model.addAttribute("answer1Max",Math.round(Math.floor(answer1Max)));
        model.addAttribute("answer1",Math.round(Math.floor(answer1)));
        model.addAttribute("answer1Min",Math.round(Math.floor(answer1Min)));
        model.addAttribute("answer2Max",Math.round(Math.floor(answer2Max)));
        model.addAttribute("answer2",Math.round(Math.floor(answer2)));
        model.addAttribute("answer2Min",Math.round(Math.floor(answer3Min)));
        model.addAttribute("answer3Max",Math.round(Math.floor(answer3Max)));
        model.addAttribute("answer3",Math.round(Math.floor(answer3)));
        model.addAttribute("answer3Min",Math.round(Math.floor(answer3Min)));
        model.addAttribute("answerMaxTotal",Math.round(Math.floor(answerMaxTotal)));
        model.addAttribute("answerTotal",Math.round(Math.floor(answerTotal)));
        model.addAttribute("answerMinTotal",Math.round(Math.floor(answerMinTotal)));

        return "calculator/phantasm";
    }
}
