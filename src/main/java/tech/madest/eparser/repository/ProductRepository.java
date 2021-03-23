package tech.madest.eparser.repository;

import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.shopizer.product.Product;

public interface ProductRepository extends CrudRepository< Product, Long > {

    Product findOneByRefSku(String refSku);
    Product findOneBySku(String sku);
}
