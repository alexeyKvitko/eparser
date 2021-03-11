package tech.madest.eparser.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.shopizer.Manufacturer;

public interface ManufacturerRepository extends CrudRepository< Manufacturer, Integer > {

    Manufacturer findOneByCode( String code );

    @Query( value = "select max(MANUFACTURER_ID) from MANUFACTURER", nativeQuery = true )
    Integer getMaxId();

}
