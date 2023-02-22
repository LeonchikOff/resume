package org.example.resume.models;

import javax.persistence.AttributeConverter;

public enum LanguageType {
    ALL, SPOKEN, WRITING;

    public String getDbValue() {
        return name().toLowerCase();
    }

    public LanguageType getReverseType() {
        if (this == SPOKEN) return WRITING;
        else if (this == WRITING) return SPOKEN;
        else throw new IllegalArgumentException(this + " dose not have reverse type");
    }

    public static class PersistJPAConverter implements AttributeConverter<LanguageType, String> {
        @Override
        public String convertToDatabaseColumn(LanguageType attribute) {
            return attribute.getDbValue();
        }

        @Override
        public LanguageType convertToEntityAttribute(String dbValue) {
            return LanguageType.valueOf(dbValue.toUpperCase());
        }
    }
}
