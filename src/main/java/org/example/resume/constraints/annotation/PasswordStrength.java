package org.example.resume.constraints.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ANNOTATION_TYPE, CONSTRUCTOR, FIELD, METHOD, PARAMETER})
@Constraint(validatedBy = {})
@NotNull
@Size(min = 8)
@MinDigitCount
@MinUpperCharCount
@MinLowerCharCount
@MinSpecialCharCount
public @interface PasswordStrength {
    String message() default "PasswordStrength";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
