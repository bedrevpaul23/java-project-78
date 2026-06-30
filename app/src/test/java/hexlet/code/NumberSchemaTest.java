package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {
    @Test
    void numberSchemaAllowsNumberByDefault() {
        var schema = new Validator().number();

        assertTrue(schema.isValid(5));
    }

    @Test
    void numberSchemaAllowsNullByDefault() {
        var schema = new Validator().number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));
    }

    @Test
    void requiredMakesNullInvalid() {
        var schema = new Validator().number();

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    void positiveChecksNumberSign() {
        var schema = new Validator().number();

        schema.positive().required();

        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    void rangeChecksInclusiveBounds() {
        var schema = new Validator().number();

        schema.range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void latestRangeRuleHasPriority() {
        var schema = new Validator().number();

        schema.range(10, 20).range(5, 10);

        assertTrue(schema.isValid(7));
        assertFalse(schema.isValid(15));
    }
}
