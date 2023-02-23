package org.example.resume.constraints.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ANNOTATION_TYPE, FIELD, CONSTRUCTOR, METHOD, PARAMETER})
@Constraint(validatedBy = {})
public @interface Adulthood {
    String message() default "Adulthood";

    int adulthoodAge() default 18;

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
