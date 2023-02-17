package org.example.resume.models;

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
}