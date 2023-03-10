package org.example.resume.constraints.annotation;

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

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy={})
public @interface MinSpecialCharCount  {

    int value() default 1;

    String specSymbols() default "!@~`#$%^&*()_-+=|\\/{}[].,;:/?";

    String message() default "MinSpecCharCount";

    Class<? extends Payload>[] payload() default { };

    Class<?>[] groups() default { };
}

