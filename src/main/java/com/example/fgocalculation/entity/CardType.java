package com.example.fgocalculation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "card_type_list")
@Entity
@Data
public class CardType {
    
    /** カードタイプ名*/
    @Id
    @Column(name = "cardtype_name" ,nullable = false)
    public String cardTypeName;
    
    /** カードタイプ別攻撃補正*/
    @Column(name = "atk_rate" ,nullable = false)
    public double atkRate;
    
    /** カードタイプ別NP獲得量レート*/
    @Column(name = "np_rate" ,nullable = false)
    public double npRate;
}
