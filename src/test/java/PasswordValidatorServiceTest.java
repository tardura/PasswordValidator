import annotations.ValidPassword;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;


@ContextConfiguration(classes={PasswordValidatorServiceTest.Config.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordValidatorServiceTest {

    @Autowired
    private PasswordValidatorService service;

    @Autowired
    private Validator validator;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void validatePasswordFailsLengthOfZero(){
        thrown.expect(ConstraintViolationException.class);
        service.passwordValid("");
    }

    @Test
    public void validatePasswordPassesWithLengthOfTwelve(){
        assert service.passwordValid("abcdefghijkl");
    }

    @Test
    public void validatePasswordFailsLengthOfThirteen(){
        thrown.expect(ConstraintViolationException.class);
        service.passwordValid("abcdefghijklm");
    }

    @Test
    public void passwordPassesWithLengthInsideAllowed(){
       assert service.passwordValid("abcdef");
    }

    @Test
    public void passwordFailsWithEasyCycle(){
        thrown.expect(ConstraintViolationException.class);
        service.passwordValid("abcabc");
    }

    @Test
    public void passwordPassesWithNonConsecutiveCycle(){
        assert service.passwordValid("abc9abc");
    }

    @Test
    public void passwordFailsDueToNonAlphaNumeric(){
        thrown.expect(ConstraintViolationException.class);
        service.passwordValid("abcdefgh-");
    }

    @Test
    public void passwordFailsDueToNullValue(){
        thrown.expect(ConstraintViolationException.class);
        service.passwordValid(null);
    }



    @Configuration
    public static class Config{
        @Bean
        public MethodValidationPostProcessor methodValidationPostProcessor() {
            MethodValidationPostProcessor processor =  new MethodValidationPostProcessor();
            processor.setValidator(validator());
            return processor;
        }

        @Bean
        public Validator validator() {
            LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
            return validator;
        }

        @Bean
        public PasswordValidatorService passwordValidatorService(){
            return new PasswordValidatorService();
        }


    }
}
