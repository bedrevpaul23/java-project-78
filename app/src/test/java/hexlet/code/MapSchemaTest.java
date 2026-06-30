package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {
    @Test
    void mapSchemaAllowsNullByDefault() {
        var schema = new Validator().map();

        assertTrue(schema.isValid(null));
    }

    @Test
    void requiredMakesNullInvalid() {
        var schema = new Validator().map();

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void mapSchemaAllowsNonEmptyMap() {
        var schema = new Validator().map().required();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));
    }

    @Test
    void sizeofChecksMapSize() {
        var schema = new Validator().map().required();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        schema.sizeof(2);

        assertFalse(schema.isValid(data));

        data.put("key2", "value2");

        assertTrue(schema.isValid(data));
    }

    @Test
    void latestSizeofRuleHasPriority() {
        var schema = new Validator().map();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        schema.sizeof(2).sizeof(1);

        assertTrue(schema.isValid(data));
    }

    @Test
    void shapeChecksNestedStringValues() {
        var validator = new Validator();
        var schema = validator.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        var human1 = new HashMap<String, String>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");

        assertTrue(schema.isValid(human1));

        var human2 = new HashMap<String, String>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        assertFalse(schema.isValid(human2));

        var human3 = new HashMap<String, String>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");

        assertFalse(schema.isValid(human3));
    }

    @Test
    void shapeWorksWithAdditionalStringRestrictions() {
        var validator = new Validator();
        var schema = validator.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("email", validator.string().required().contains("@"));

        schema.shape(schemas);

        var validUser = new HashMap<String, String>();
        validUser.put("email", "user@hexlet.io");

        var invalidUser = new HashMap<String, String>();
        invalidUser.put("email", "user.hexlet.io");

        assertTrue(schema.isValid(validUser));
        assertFalse(schema.isValid(invalidUser));
    }
}
