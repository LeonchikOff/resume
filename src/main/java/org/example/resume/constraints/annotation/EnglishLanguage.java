package org.example.resume.constraints.annotation;

import org.example.resume.constraints.validator.EnglishLanguageConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
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
@Target({ANNOTATION_TYPE, FIELD, CONSTRUCTOR, METHOD, PARAMETER})
@Constraint(validatedBy = {EnglishLanguageConstraintValidator.class})
public @interface EnglishLanguage {
    String message() default "EnglishLanguage";
    boolean withNumbers() default true;
    boolean withPunctuations() default true;
    boolean withSpecialSymbols() default true;
    Class<? extends Payload>[] payload() default {};
    Class<?>[] groups() default {};
}
