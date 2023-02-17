package org.example.resume.models;

public enum LanguageLevel {
    BEGINNER,
    ELEMENTARY,
    PRE_INTERMEDIATE,
    INTERMEDIATE,
    UPPER_INTERMEDIATE,
    ADVANCED,
    PROFICIENCY;

    public String getDbValue() {
        return name().toLowerCase();
    }
}