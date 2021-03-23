package tech.madest.eparser.repository;

import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.shopizer.product.availability.ProductAvailability;

public interface ProductAvailRepository extends CrudRepository< ProductAvailability, Long > {
}
