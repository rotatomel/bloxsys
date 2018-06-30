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
    String message() default "El CUIT no es válido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
