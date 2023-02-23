package org.example.resume.constraints.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = {})
public @interface FieldMatch {

    String message() default "FieldMatch";

    String first();

    String second();

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @interface List {
        FieldMatch[] value();
    }
}
