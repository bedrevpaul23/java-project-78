package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        addCheck("required", value -> value != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> value == null || value.size() == size);
        return this;
    }
}
