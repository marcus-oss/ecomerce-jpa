package ecommerce.model.Converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class BooleanToSimNaoConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return Boolean.TRUE.equals(attribute) ? "SIM" : "NAO";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "SIM".equals(dbData) ? Boolean.TRUE : Boolean.FALSE;
    }
}
