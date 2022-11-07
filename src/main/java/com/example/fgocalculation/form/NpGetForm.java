package com.example.fgocalculation.form;

import javax.validation.constraints.NotEmpty;

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
    private double cardBuf = 0;
    
    /** NP獲得量バフ*/
    private double npGetBuf = 0;
    
    /** HIT数*/
    private int hit = 0;
    
    /** オーバーキルHIT数*/
    private int overKillHit = 0;
    
    /** 敵クラス1*/
    private String enemyClass1;
    
    /** 敵クラス2*/
    private String enemyClass2;
    
    /** 敵クラス3*/
    private String enemyClass3;
}
