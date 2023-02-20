package org.example.resume.services.impl;

import org.apache.commons.text.WordUtils;
import org.example.resume.services.NameService;
import org.springframework.stereotype.Service;

@Service
public class NameServiceImpl implements NameService {
    @Override
    public String convertName(String name) {
        return WordUtils
                .capitalizeFully(name.contains("-")
                        ? name.replace("-", " ")
                        : name);
    }
}
