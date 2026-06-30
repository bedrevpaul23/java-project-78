package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ValidatorTest {
    @Test
    void validatorCanBeCreated() {
        var validator = new Validator();

        assertNotNull(validator);
    }
}
