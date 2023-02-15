package org.example.resume.services;

public class NameService {
    private NameService() {
    }

    public static NameService getInstance() {
        return new NameService();
    }

    public String convertName(String name) {
        return name.toUpperCase();
    }
}
