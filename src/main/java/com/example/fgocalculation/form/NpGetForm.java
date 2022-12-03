package com.example.fgocalculation.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class NpGetForm{
    
    /** サーバント名*/
    private String servantName;
    
    /** カードタイプ*/
    private String cardType;
    
    /** 宝具範囲*/
    private String phantasmRange;
    
    /** カードバフ*/
    private Double cardBuf = 0.0;
    
    /** NP獲得量バフ*/
    private Double npGetBuf = 0.0;
    
    /** HIT数*/
    @NotNull(message = "1以上を入力してください。")
    @Min(value = 1, message = "1以上を入力してください。")
    private Integer hit = 0;
    
    /** オーバーキルHIT数*/
    @Min(0)
    private Integer overKillHit = 0;
    
    /** 敵クラス1*/
    private String enemyClass1;
    
    /** 敵クラス2*/
    private String enemyClass2;
    
    /** 敵クラス3*/
    private String enemyClass3;
}
