package com.example.fgocalculation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "class_type_list")
@Entity
@Data
public class ClassType {
    
    /** クラス名*/
    @Id
    @Column(name = "classtype_name", nullable = false)
    public String classTypeName;
    
    /** クラス別攻撃補正*/
    @Column(name = "atk_rate", nullable = false)
    public double atkRate;
    
    /** 敵クラスNP獲得量補正*/
    @Column(name = "enemy_np_rate", nullable = false)
    public double enemyNpRate;
    
    /** 敵クラス特殊NP獲得量補正*/
    @Column(name = "enemy_np_rate_special", nullable = false)
    private double enemyNpRateSpecial;
}
