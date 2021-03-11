package tech.madest.eparser.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.shopizer.ManufacturerDescription;

public interface ManufacturerDescRepository extends CrudRepository< ManufacturerDescription,Integer > {

    @Query( value = "select max(DESCRIPTION_ID) from MANUFACTURER_DESCRIPTION", nativeQuery = true )
    Integer getMaxId();
}
