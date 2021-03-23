package tech.madest.eparser.repository;

import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.shopizer.reference.language.Language;

public interface LanguageRepository extends CrudRepository< Language, Integer > {
}
