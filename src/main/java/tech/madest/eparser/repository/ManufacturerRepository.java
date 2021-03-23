package tech.madest.eparser.repository;

import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.shopizer.product.manufacturer.Manufacturer;

public interface ManufacturerRepository extends CrudRepository< Manufacturer, Integer > {

    Manufacturer findOneByCode( String code );

}
