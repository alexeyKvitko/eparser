package tech.madest.eparser.repository;

import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.shopizer.product.description.ProductDescription;

public interface ProductDescriptionRepository extends CrudRepository<ProductDescription, Long> {
}
