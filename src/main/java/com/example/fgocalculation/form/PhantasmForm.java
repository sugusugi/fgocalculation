package com.example.fgocalculation.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PhantasmForm{
    
    /** サーバント名*/
    private String servantName;
    
    /** クラス*/
    public String classTypeName;
    
    /** 攻撃力*/
    @Min(value = 0, message = "0以上を入力してください。")
    private Double atk = 0.0;
    
    /** フォウ*/
    @Min(value = 0, message = "0以上を入力してください。")
    @Max(2000)
    private Double fou = 0.0;
    
    /** 礼装*/
    @Min(value = 0, message = "0以上を入力してください。")
    private Double spiritual = 0.0;
    
    /** 宝具レベル*/
    private int phantasmLv;
    
    /** 宝具カードタイプ*/
    private String phantasmCardType;
    
    /** 宝具範囲*/
    private String phantasmRange;
    
    /** 攻撃バフ*/
    private Double atkBuf = 0.0;
    
    /** カードバフ*/
    private Double cardBuf= 0.0;
    
    /** 宝具バフ*/
    private Double phantasmBuf= 0.0;
    
    /** 特攻バフ*/
    private Double specialBuf= 0.0;
    
    /** 宝具特攻*/
    @Min(value = 100, message = "100以上を入力してください。")
    @NotNull(message = "100以上を入力してください。")
    private Double phantasmSpecial = 100.0;
    
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
