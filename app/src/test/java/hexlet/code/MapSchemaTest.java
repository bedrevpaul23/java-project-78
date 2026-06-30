package hexlet.code;

import java.util.HashMap;

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
}
