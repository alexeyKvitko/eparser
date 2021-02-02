package tech.madest.eparser.repository;

import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.CompanyEntity;

public interface CompanyRepository extends CrudRepository< CompanyEntity, Integer> {
}
