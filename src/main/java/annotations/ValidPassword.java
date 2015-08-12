package annotations;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

@NotNull
@PasswordLength
@Cycles
@AlphaNumeric
@Documented
@Constraint(validatedBy={})
@Target({ANNOTATION_TYPE, METHOD, PARAMETER, FIELD, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String PASSWORD_ERROR_MESSAGE = "Not a valid password.";
    String message() default PASSWORD_ERROR_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @OverridesAttribute(constraint = PasswordLength.class, name="min")
    int minPasswordLength() default PasswordLength.DEFAULT_MIN_LENGTH;

    @OverridesAttribute(constraint = PasswordLength.class, name="max")
    int maxPasswordLength() default PasswordLength.DEFAULT_MAX_LENGTH;

    @OverridesAttribute(constraint = Cycles.class, name="allowed")
    boolean cyclesAllowed() default Cycles.DEFAULT_CYCLES_ALLOWED;

    @OverridesAttribute(constraint = AlphaNumeric.class, name="required")
    boolean alphaNumericsRequired() default AlphaNumeric.DEFAULT_ALPHA_NUMERICS_REQUIRED;
}
