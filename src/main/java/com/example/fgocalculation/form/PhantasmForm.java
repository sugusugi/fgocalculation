package com.example.fgocalculation.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class PhantasmForm{
    
    /** サーバント名*/
    private String servantName;
    
    /** クラス*/
    public String classTypeName;
    
    /** 攻撃力*/
    private double atk;
    
    /** フォウ*/
    private double fou;
    
    /** 礼装*/
    private double spiritual;
    
    /** 宝具レベル*/
    private int phantasmLv;
    
    /** 宝具カードタイプ*/
    private String phantasmCardType;
    
    /** 宝具範囲*/
    private String phantasmRange;
    
    /** 攻撃バフ*/
    private double atkBuf;
    
    /** カードバフ*/
    private double cardBuf;
    
    /** 宝具バフ*/
    private double phantasmBuf;
    
    /** 特攻バフ*/
    private double specialBuf;
    
    /** 宝具特攻*/
    private double phantasmSpecial = 100;
    
    /** クラス相性1*/
    private double classRate1;
    
    /** クラス相性2*/
    private double classRate2;
    
    /** クラス相性3*/
    private double classRate3;
    
    /** 属性相性1*/
    private double attributeRate1;
    
    /** 属性相性2*/
    private double attributeRate2;
    
    /** 属性相性3*/
    private double attributeRate3;
}
