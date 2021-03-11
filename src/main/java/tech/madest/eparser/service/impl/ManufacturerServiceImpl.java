package tech.madest.eparser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.madest.eparser.AppConstants;
import tech.madest.eparser.entity.shopizer.Manufacturer;
import tech.madest.eparser.entity.shopizer.ManufacturerDescription;
import tech.madest.eparser.model.Block;
import tech.madest.eparser.repository.ManufacturerDescRepository;
import tech.madest.eparser.repository.ManufacturerRepository;
import tech.madest.eparser.utils.AppUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ManufacturerServiceImpl {

    @Autowired
    ManufacturerRepository manufacturerRepo;

    @Autowired
    ManufacturerDescRepository manufacturerDescRepo;


    public void addManufacturers( List< Block >  blocks ){
        Timestamp now =  new Timestamp( new Date().getTime() );
       for( Block block: blocks ){
           if ( AppUtils.isNullOrEmpty( block.getParseItems() ) ){
               continue;
           }
           String name = AppUtils.getFieldValueByName( AppConstants.MANUFACTURED_NAME, block.getParseItems() );
           String descr = AppUtils.getFieldValueByName( AppConstants.MANUFACTURED_DESC, block.getParseItems() );
           String logo = AppUtils.getFieldValueByName( AppConstants.MANUFACTURED_LOGO, block.getParseItems() );
           Manufacturer exist = manufacturerRepo.findOneByCode( name.toUpperCase() );
           if ( exist == null ){
               Integer manId = manufacturerRepo.getMaxId()+1;

               Manufacturer manufacturer = new Manufacturer();
               manufacturer.setId( manId );
               manufacturer.setDateCreated( now );
               manufacturer.setDateModified( now );
               manufacturer.setMerchantId( 1 );
               manufacturer.setUpdtId( AppConstants.E_PARSER );
               manufacturer.setCode( name.toUpperCase() );
               manufacturer.setManufacturerImage( logo );
               manufacturerRepo.save( manufacturer );

               ManufacturerDescription description = new ManufacturerDescription();
               description.setId( manufacturerDescRepo.getMaxId()+1 );
               description.setManufacturedId( manId );
               description.setDateCreated( now );
               description.setDateModified( now );
               description.setName( name );
               description.setTitle( name );
               description.setDescription( descr );
               description.setUpdtId(  AppConstants.E_PARSER );
               description.setLanguageId( 5 );
               manufacturerDescRepo.save( description );
           }
       }
    }



}
