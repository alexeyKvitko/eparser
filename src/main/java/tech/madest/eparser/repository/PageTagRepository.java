package tech.madest.eparser.repository;

import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.PageTagEntity;

import java.util.List;

public interface PageTagRepository extends CrudRepository< PageTagEntity,Integer> {

    List<PageTagEntity> findAllByPageId(Integer pageId);
}
