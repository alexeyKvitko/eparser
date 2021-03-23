package tech.madest.eparser.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tech.madest.eparser.entity.shopizer.product.manufacturer.ManufacturerDescription;

public interface ManufacturerDescRepository extends CrudRepository< ManufacturerDescription,Integer > {


}
