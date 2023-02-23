package org.example.resume.repositories;

import org.example.resume.entities.SkillCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;


@RepositoryDefinition(domainClass = SkillCategory.class, idClass = Long.class)
public interface SkillCategoryRepository {
    List<SkillCategory> findAll(Sort sort);
}
