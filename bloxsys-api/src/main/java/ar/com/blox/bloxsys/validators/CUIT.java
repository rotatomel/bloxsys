package ar.com.blox.bloxsys.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CUITValidator.class)
@Documented
public @interface CUIT {
    String message() default "{ar.com.box.bloxsys.cuit.invalid.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
