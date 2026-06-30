package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ValidatorTest {
    @Test
    void validatorCreatesStringSchema() {
        var validator = new Validator();
        var schema = validator.string();

        assertInstanceOf(StringSchema.class, schema);
    }

    @Test
    void validatorCreatesNumberSchema() {
        var validator = new Validator();
        var schema = validator.number();

        assertInstanceOf(NumberSchema.class, schema);
    }
}
