package com.example.fgocalculation.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserEmailForm {
    
    @NotEmpty(message = "メールアドレスを入力してください。")
    @Email(message = "メールアドレスを正しく入力してください。")
    private String email;
}
