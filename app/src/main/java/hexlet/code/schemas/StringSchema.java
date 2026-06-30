package hexlet.code.schemas;

public final class StringSchema {
    private boolean required;
    private Integer minLength;
    private String requiredSubstring;

    public StringSchema required() {
        required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        requiredSubstring = substring;
        return this;
    }

    public boolean isValid(String value) {
        if (value == null) {
            return !required;
        }

        if (required && value.isEmpty()) {
            return false;
        }

        if (minLength != null && value.length() < minLength) {
            return false;
        }

        if (requiredSubstring != null && !value.contains(requiredSubstring)) {
            return false;
        }

        return true;
    }
}
