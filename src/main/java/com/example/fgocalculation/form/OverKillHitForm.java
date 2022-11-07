package com.example.fgocalculation.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class OverKillHitForm {
    
    @NotEmpty
    /** 敵HP1*/
    private int enemyHp1;
    
    @NotEmpty
    /** ダメージ量１*/
    private int damage1;
    
    /** 敵HP2*/
    private int enemyHp2;
    
    /** ダメージ量2*/
    private int damage2;
    
    /** 敵HP3*/
    private int enemyHp3;
    
    /** ダメージ量3*/
    private int damage3;
}
