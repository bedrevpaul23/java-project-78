package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {
    @Test
    void stringSchemaAllowsNullAndEmptyStringByDefault() {
        var schema = new Validator().string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
    }

    @Test
    void requiredMakesNullAndEmptyStringInvalid() {
        var schema = new Validator().string();

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void minLengthChecksStringLength() {
        var schema = new Validator().string();

        schema.required().minLength(7);

        assertFalse(schema.isValid("hexlet"));
        assertTrue(schema.isValid("what does the fox say"));
    }

    @Test
    void containsChecksSubstring() {
        var schema = new Validator().string();

        schema.required();

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    void latestMinLengthRuleHasPriority() {
        var schema = new Validator().string();

        assertTrue(schema.minLength(10).minLength(4).isValid("Hexlet"));
    }
}
