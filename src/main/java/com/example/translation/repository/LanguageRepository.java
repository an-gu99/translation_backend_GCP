package com.example.translation.repository;

import com.example.translation.entity.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(readOnly = true)
public interface LanguageRepository extends CrudRepository<Language, Long> {
    List<Language> findAllByOrderByLanguageNameAsc();

    Language findByLanguageName(String languageName);
}
