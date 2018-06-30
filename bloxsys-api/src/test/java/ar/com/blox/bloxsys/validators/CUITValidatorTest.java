package ar.com.blox.bloxsys.validators;

import org.apache.commons.collections.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;

public class CUITValidatorTest {

    Validator validator = null;

    @org.junit.Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void isNotValid() {
        DummyTestClass tc = new DummyTestClass(20342577158L);
        Set<ConstraintViolation<DummyTestClass>> violations = validator.validate(tc);
        assertNotNull(violations);
        violations.forEach(x -> assertEquals(x.getMessage(), "El CUIT no es válido."));
    }

    @org.junit.Test
    public void isValid() {
        DummyTestClass tc = new DummyTestClass(20342577157L);
        Set<ConstraintViolation<DummyTestClass>> violations = validator.validate(tc);
        assertTrue(CollectionUtils.isEmpty(violations));
    }

    class DummyTestClass {
        @CUIT(message = "El CUIT no es válido.")
        Long cuit;

        public DummyTestClass(Long cuit) {
            this.cuit = cuit;
        }

        public Long getCuit() {
            return cuit;
        }

        public void setCuit(Long cuit) {
            this.cuit = cuit;
        }
    }
}