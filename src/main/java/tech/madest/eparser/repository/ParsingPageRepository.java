package tech.madest.eparser.repository;

import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.ParsingPageEntity;

import java.util.List;

public interface ParsingPageRepository extends CrudRepository< ParsingPageEntity, Integer > {

    List<ParsingPageEntity> findAllByLinkIdAndPageType(Integer linkId, String pageType);
}
