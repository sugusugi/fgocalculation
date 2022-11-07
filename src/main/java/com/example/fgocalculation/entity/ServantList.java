package com.example.fgocalculation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "servant_list")
@Entity
@Data
public class ServantList {
    
    /** サーバントNo*/
    @Id
    @Column(name = "number", nullable = false)
    private int number;
    
    /** サーバント名*/
    @Column(name = "servant_name", nullable = false)
    private String servantName;
    
    @Column(name = "servant_name_read", nullable = false)
    private String servantNameRead;
    
    /** クラス*/
    @Column(name = "classtype_name", nullable = false)
    private String classTypeName;
    
    /** レアリティ*/
    @Column(name = "rarity", nullable = false)
    private int rarity;
    
    /** 攻撃力*/
    @Column(name = "atk", nullable = false)
    private int atk;
    
    /** Lv100時の攻撃力*/
    @Column(name = "atk_lv100", nullable = false)
    private int atkLv100;
    
    /** Lv120時の攻撃力*/
    @Column(name = "atk_lv120", nullable = false)
    private int atkLv120;
    
    /** 宝具カードタイプ*/
    @Column(name = "phantasm_card_type", nullable = false)
    private String phantasmCardType;
    
    /** 宝具範囲*/
    @Column(name = "phantasm_range", nullable = false)
    private String phantasmRange;
    
    /** 宝具倍率Lv1*/
    @Column(name = "phantasm_rate_Lv1", nullable = false)
    private double phantasmRateLv1;
    
    /** 宝具倍率Lv2*/
    @Column(name = "phantasm_rate_Lv2", nullable = false)
    private double phantasmRateLv2;
    
    /** 宝具倍率Lv3*/
    @Column(name = "phantasm_rate_Lv3", nullable = false)
    private double phantasmRateLv3;
    
    /** 宝具倍率Lv4*/
    @Column(name = "phantasm_rate_Lv4", nullable = false)
    private double phantasmRateLv4;
    
    /** 宝具倍率Lv5*/
    @Column(name = "phantasm_rate_Lv5", nullable = false)
    private double phantasmRateLv5;
    
    /** 宝具倍率リスト*/
    //private double[] phantasmRateLvList = {phantasmRateLv1,phantasmRateLv2,phantasmRateLv3,phantasmRateLv4,phantasmRateLv5};
    
    /** 宝具HIT数*/
    @Column(name = "phantasm_hit", nullable = false)
    private int phantasmHit;
    
    /** NP獲得量レート*/
    @Column(name = "np_rate_attack", nullable = false)
    private double npRateAttack;
    
    /** １HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_1hit")
    private int phantasmRateFirstHit;
    
    /** 2HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_2hit")
    private int phantasmRateSecondHit;
    
    /** 3HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_3hit")
    private int phantasmRateThirdHit;
    
    /** 4HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_4hit")
    private int phantasmRateFourthHit;
    
    /** 5HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_5hit")
    private int phantasmRateFifthHit;
    
    /** 6HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_6hit")
    private int phantasmRateSixthHit;
    
    /** 7HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_7hit")
    private int phantasmRateSeventhHit;
    
    /** 8HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_8hit")
    private int phantasmRateEightHit;
    
    /** 9HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_9hit")
    private int phantasmRateNinethHit;
    
    /** 10HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_10hit")
    private int phantasmRateTenthHit;
    
    /** 11HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_11hit")
    private int phantasmRateEleventhHit;
    
    /** 12HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_12hit")
    private int phantasmRateTwelfththHit;
    
    /** 13HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_13hit")
    private int phantasmRateThirteenthHit;
    
    /** 14HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_14hit")
    private int phantasmRateFourteenthHit;
    
    /** 15HIT目の宝具ダメージ量レート*/
    @Column(name = "phantasm_rate_15hit")
    private int phantasmRateFifteenthHit;
    
    /** HIT数毎の宝具ダメージ量レート */
    //private int[] phantasmRateHitList = {phantasmRateFirstHit,phantasmRateSecondHit,phantasmRateThirdHit,phantasmRateFourthHit,phantasmRateFifthHit,phantasmRateSixthHit,phantasmRateSeventhHit,phantasmRateEighthHit,phantasmRateNinethHit,phantasmRateTenthHit,phantasmRateEleventhHit,phantasmRateTwelfththHit,phantasmRateThirteenthHit,phantasmRateFourteenthHit,phantasmRateFifteenthHit};
}
