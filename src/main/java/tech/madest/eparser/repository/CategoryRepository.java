package tech.madest.eparser.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.shopizer.category.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository< Category, Long> {

    @Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cdl.id=4 and cm.id = 1 order by c.sortOrder asc")
    List<Category> findByStoreAndLanguage();

}
