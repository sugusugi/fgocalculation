package com.example.fgocalculation.form;

import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class OverKillHitForm {

    /** 敵HP1 */
    @Min(value = 0, message = "0以上を入力してください。")
    private Integer enemyHp1 = 0;

    /** ダメージ量１ */
    @Min(value = 0, message = "0以上を入力してください。")
    private Integer damage1 = 0;

    /** 敵HP2 */
    @Min(value = 0, message = "0以上を入力してください。")
    private Integer enemyHp2 = 0;

    /** ダメージ量2 */
    @Min(value = 0, message = "0以上を入力してください。")
    private Integer damage2 = 0;

    /** 敵HP3 */
    @Min(value = 0, message = "0以上を入力してください。")
    private Integer enemyHp3 = 0;

    /** ダメージ量3 */
    @Min(value = 0, message = "0以上を入力してください。")
    private Integer damage3 = 0;
}
