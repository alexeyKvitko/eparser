package tech.madest.eparser.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tech.madest.eparser.entity.PageProductEntity;
import tech.madest.eparser.entity.shopizer.product.Product;

import java.util.List;

public interface PageProductRepository extends CrudRepository< PageProductEntity, Long > {

    @Query( value = "select p from Product p where p.id in " +
            "( select ppe.productId from PageProductEntity ppe where ppe.pageId = :pageId)"  )
    List< Product > getAllProductsByPageId( @Param("pageId") Long pageId);

}
