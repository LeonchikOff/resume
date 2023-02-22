package org.example.resume.repositories;

import org.example.resume.entities.ProfileRestore;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRestoreRepository extends PagingAndSortingRepository<ProfileRestore, Long> {
    ProfileRestore findByToken(String token);
}
