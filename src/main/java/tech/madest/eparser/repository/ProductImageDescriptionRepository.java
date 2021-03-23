package tech.madest.eparser.repository;


import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.shopizer.product.image.ProductImageDescription;

public interface ProductImageDescriptionRepository extends CrudRepository< ProductImageDescription, Long > {
}
