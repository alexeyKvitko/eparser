package tech.madest.eparser.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tech.madest.eparser.entity.PageManufacturerEntity;
import tech.madest.eparser.entity.shopizer.product.manufacturer.Manufacturer;

import java.util.List;

public interface PageManufacturerRepository extends CrudRepository< PageManufacturerEntity, Long > {

    @Query( value = "select m from Manufacturer m where m.id in " +
                "( select cp.manufacturerId from PageManufacturerEntity cp where cp.pageId = :pageId)"  )
    List< Manufacturer > getAllManufacturersByPageId( @Param("pageId") Long pageId);
}
