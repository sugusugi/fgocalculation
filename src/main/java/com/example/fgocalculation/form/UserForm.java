package com.example.fgocalculation.form;

import com.example.fgocalculation.validation.constraints.PasswordEquals;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@PasswordEquals
public class UserForm{
    
    @NotEmpty(message = "ユーザー名を入力してください。")
    @Size(max = 20, message = "20文字以下で入力してください。")
    private String name;
    
    @NotEmpty(message = "メールアドレスを入力してください。")
    @Email(message = "メールアドレスを正しく入力してください。")
    private String email;
    
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{8,24}$", message = "半角英数字を8～36文字で入力してください。")
    private String password;
    
    @NotEmpty(message = "確認用パスワードを入力してください。")
    private String passwordConfirmation;
}
