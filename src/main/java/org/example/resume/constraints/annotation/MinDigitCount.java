package org.example.resume.constraints.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ANNOTATION_TYPE, CONSTRUCTOR, FIELD, METHOD, PARAMETER})
@Constraint(validatedBy = {})
public @interface MinDigitCount {
    int value() default 1;
    String message() default "MinDigitCount";
    Class<? extends Payload>[] payload() default {};
    Class<?> [] groups() default {};
}
