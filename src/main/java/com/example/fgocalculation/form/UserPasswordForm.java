package com.example.fgocalculation.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.example.fgocalculation.validation.constraints.PasswordEquals;

import lombok.Data;

@Data
@PasswordEquals
public class UserPasswordForm {

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{8,24}$", message = "半角英数字を8～36文字で入力してください。")
    private String nowPassword;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{8,24}$", message = "半角英数字を8～36文字で入力してください。")
    private String password;

    @NotEmpty(message = "確認用パスワードを入力してください。")
    private String passwordConfirmation;
}
