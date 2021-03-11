package tech.madest.eparser.repository;

import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.shopizer.Category;


public interface CategoryRepository extends CrudRepository< Category, Integer> {

}
