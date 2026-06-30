package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {
    @Test
    void validatorCreatesStringSchema() {
        var validator = new Validator();
        var schema = validator.string();

        assertInstanceOf(hexlet.code.schemas.StringSchema.class, schema);
    }

    @Test
    void stringSchemaAllowsNullAndEmptyStringByDefault() {
        var validator = new Validator();
        var schema = validator.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
    }

    @Test
    void requiredMakesNullAndEmptyStringInvalid() {
        var validator = new Validator();
        var schema = validator.string();

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void minLengthChecksStringLength() {
        var validator = new Validator();
        var schema = validator.string();

        schema.required().minLength(7);

        assertFalse(schema.isValid("hexlet"));
        assertTrue(schema.isValid("what does the fox say"));
    }

    @Test
    void containsChecksSubstring() {
        var validator = new Validator();
        var schema = validator.string();

        schema.required();

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    void latestMinLengthRuleHasPriority() {
        var validator = new Validator();
        var schema = validator.string();

        assertTrue(schema.minLength(10).minLength(4).isValid("Hexlet"));
    }
}
