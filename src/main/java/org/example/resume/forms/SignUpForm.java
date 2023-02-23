package org.example.resume.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.resume.constraints.annotation.EnglishLanguage;
import org.example.resume.constraints.annotation.FieldMatch;
import org.example.resume.constraints.annotation.PasswordStrength;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class SignUpForm implements Serializable {

    @NotNull
    @Size(max = 50)
    @EnglishLanguage(withNumbers = false, withSpecialSymbols = false)
    private String firstName;
    @NotNull
    @Size(max = 50)
    @EnglishLanguage(withNumbers = false, withSpecialSymbols = false)
    private String lastName;
    @PasswordStrength
    private String password;
    private String confirmPassword;
}
