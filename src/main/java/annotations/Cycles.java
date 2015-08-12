package annotations;

import validators.CycleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

@Documented
@Constraint(validatedBy={CycleValidator.class})
@Target({ANNOTATION_TYPE, METHOD, PARAMETER, FIELD, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cycles {
    boolean DEFAULT_CYCLES_ALLOWED = false;
    String DEFAULT_CYCLE_ERROR_MESSAGE = "No Cycles allowed";
    String message() default DEFAULT_CYCLE_ERROR_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    boolean allowed() default DEFAULT_CYCLES_ALLOWED;
}
