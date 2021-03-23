package tech.madest.eparser.repository;

import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.shopizer.product.price.ProductPrice;

public interface ProductPriceRepository extends CrudRepository< ProductPrice, Long > {
}
