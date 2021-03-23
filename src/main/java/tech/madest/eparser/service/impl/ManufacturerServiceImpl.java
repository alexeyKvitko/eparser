package tech.madest.eparser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.madest.eparser.AppConstants;
import tech.madest.eparser.entity.PageManufacturerEntity;
import tech.madest.eparser.entity.shopizer.product.manufacturer.Manufacturer;
import tech.madest.eparser.entity.shopizer.product.manufacturer.ManufacturerDescription;
import tech.madest.eparser.manager.GlobalManager;
import tech.madest.eparser.model.Block;
import tech.madest.eparser.repository.PageManufacturerRepository;
import tech.madest.eparser.repository.ManufacturerDescRepository;
import tech.madest.eparser.repository.ManufacturerRepository;
import tech.madest.eparser.utils.AppUtils;

import java.util.LinkedList;
import java.util.List;

@Service
public class ManufacturerServiceImpl {


    @Autowired
    ManufacturerRepository manufacturerRepo;

    @Autowired
    ManufacturerDescRepository manufacturerDescRepo;

    @Autowired
    PageManufacturerRepository cpmRepo;


    public void addManufacturers( Integer pageId, List< Block >  blocks ){
        List< PageManufacturerEntity > links = new LinkedList<>();
       for( Block block: blocks ){
           if ( AppUtils.isNullOrEmpty( block.getParseItems() ) ){
               continue;
           }
           String name = AppUtils.getFieldValueByName( AppConstants.MANUFACTURED_NAME, block.getParseItems() );
           String descr = AppUtils.getFieldValueByName( AppConstants.MANUFACTURED_DESC, block.getParseItems() );
           String logo = AppUtils.getFieldValueByName( AppConstants.MANUFACTURED_LOGO, block.getParseItems() );
           Manufacturer exist = manufacturerRepo.findOneByCode( name.toUpperCase() );
           if ( exist == null ){
               Manufacturer manufacturer = new Manufacturer();
               manufacturer.setMerchantStore( GlobalManager.getMerchantStore() );
               manufacturer.setCode( name.toUpperCase() );
               manufacturer.setImage( logo );
               manufacturerRepo.save( manufacturer );

               ManufacturerDescription description = new ManufacturerDescription();
               description.setManufacturer( manufacturer );
               description.setName( name );
               description.setTitle( name );
               description.setDescription( descr );
               description.setLanguage( GlobalManager.getMerchantStore().getDefaultLanguage() );
               manufacturerDescRepo.save( description );
               links.add( new PageManufacturerEntity( Long.valueOf( pageId ), manufacturer.getId() ) );
           }
       }
       if ( !AppUtils.isNullOrEmpty( links ) ){
           cpmRepo.saveAll( links );
       }
    }



}
