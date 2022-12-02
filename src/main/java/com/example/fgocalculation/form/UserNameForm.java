package com.example.fgocalculation.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserNameForm {

    @NotEmpty(message = "ユーザー名を入力してください。")
    @Size(max = 20, message = "20文字以下で入力してください。")
    private String name;
}
