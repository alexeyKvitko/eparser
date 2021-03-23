package tech.madest.eparser.repository;

import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.shopizer.product.image.ProductImage;

public interface ProductImageRepository extends CrudRepository< ProductImage, Long > {
}
