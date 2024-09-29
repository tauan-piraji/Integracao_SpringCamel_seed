package integrador.seed.models.enums;

public enum EnumColumn {

    OPTION_01(0, "Enum Option 01"),
    OPTION_02(1, "Enum Option 02"),
    OPTION_03(2, "Enum Option 03");

    private final Integer value;
    private final String description;

    EnumColumn(final Integer value, final String description) {
        this.value = value;
        this.description = description;
    }

    public static EnumColumn fromValue(Integer value) {
        for (EnumColumn status : values()) {
            if (status.toValue().equals(value)) return status;
        }
        throw new IllegalArgumentException(String.format("O valor do ENUM é inválido %s", value));
    }

    public String getDescription() {
        return description;
    }

    public Integer toValue() {
        return value;
    }

    public String toString() {
        return value.toString();
    }
}
