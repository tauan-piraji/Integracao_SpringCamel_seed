package integrador.seed.models.enums.converters;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import integrador.seed.models.enums.EnumColumn;

import static java.util.Objects.nonNull;

@Converter
public class EnumColumnConverter implements AttributeConverter<EnumColumn, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EnumColumn attribute) {
        return nonNull(attribute) ? attribute.toValue() : null;
    }

    @Override
    public EnumColumn convertToEntityAttribute(Integer column) {
        return nonNull(column) ? EnumColumn.fromValue(column) : null;
    }
}
