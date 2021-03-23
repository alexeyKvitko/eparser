package tech.madest.eparser.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.madest.eparser.AppConstants;
import tech.madest.eparser.dto.PageTagDto;
import tech.madest.eparser.entity.PageTagEntity;
import tech.madest.eparser.entity.shopizer.product.manufacturer.Manufacturer;
import tech.madest.eparser.entity.shopizer.product.manufacturer.ManufacturerDescription;
import tech.madest.eparser.model.Block;
import tech.madest.eparser.model.PageData;
import tech.madest.eparser.model.PageType;
import tech.madest.eparser.model.ParseItem;
import tech.madest.eparser.repository.PageManufacturerRepository;
import tech.madest.eparser.repository.PageTagRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PageTagServiceImpl {

    @Autowired
    PageTagRepository pageTagRepo;

    @Autowired
    PageManufacturerRepository cpmRepo;

    public PageData getAllTagByPage( Integer pageId ) {
        PageData pageData = new PageData();
        List< PageTagEntity > pageTagEntities = pageTagRepo.findAllByPageId( pageId.intValue() );
        ModelMapper mapper = new ModelMapper();
        pageData.setPageTags( pageTagEntities.stream().map( entity -> mapper.map( entity, PageTagDto.class ) ).collect( Collectors.toList() ) );
        List< Block > blocks = new LinkedList<>();
        List< Manufacturer > manufacturers = cpmRepo.getAllByPageId( Long.valueOf( pageId ) );
        for ( Manufacturer manufacturer : manufacturers ) {
            Block block = new Block();
            block.setBlockId( manufacturer.getId().intValue() );
            List< ParseItem > items = new LinkedList<>();
            ManufacturerDescription manufacturerDescription = manufacturer.getDescriptions().iterator().next();
            items.add( new ParseItem( AppConstants.MANUFACTURED_NAME, manufacturerDescription.getName(), false ) );
            items.add( new ParseItem( AppConstants.MANUFACTURED_DESC, manufacturerDescription.getDescription(), false ) );
            items.add( new ParseItem( AppConstants.MANUFACTURED_LOGO, manufacturer.getImage(), true ) );
            block.setParseItems( items );
            blocks.add( block );
        }
        pageData.setBlocks( blocks );
        return pageData;
    }


    public void upsertPageTag( PageTagDto pageTagDto ) {
        PageTagEntity pageTagEntity = null;
        if ( pageTagDto.getId() != null ) {
            pageTagEntity = pageTagRepo.findById( pageTagDto.getId() ).get();
            pageTagEntity.setTagName( pageTagDto.getTagName() );
            pageTagEntity.setStartTag( pageTagDto.getStartTag() );
            pageTagEntity.setEndTag( pageTagDto.getEndTag() );
            pageTagEntity.setReversed( pageTagDto.getReversed() );
            pageTagEntity.setEntryNumber( pageTagDto.getEntryNumber() );
            pageTagEntity.setMapTable( pageTagDto.getMapTable() );
            pageTagEntity.setMapField( pageTagDto.getMapField() );
            pageTagEntity.setIsImage( pageTagDto.getIsImage() );
            pageTagEntity.setNeedTranslate( pageTagDto.getNeedTranslate() );
            pageTagEntity.setInnerSearch( pageTagDto.getInnerSearch() );
        } else {
            pageTagEntity = new ModelMapper().map( pageTagDto, PageTagEntity.class );
        }
        pageTagRepo.save( pageTagEntity );
    }

    public void deletePageTag( Integer pageTagId ) {
        pageTagRepo.deleteById( pageTagId );
    }

    public void addPageTags( PageType pageType, Integer pageId ) {
        List< PageTagDto > tags = null;
        switch (  pageType ){
            case PAGE_MANUFACTURER:
                tags = AppConstants.MANUFACTURER_TAG_NAMES;
                break;
            case PAGE_CATEGORY:
                tags = AppConstants.PRODUCT_TAG_NAMES;
                break;
        }
        List< PageTagEntity > tagsToSave = new LinkedList<>();
        for ( PageTagDto tag : tags ) {
            tag.setPageId( pageId );
            tagsToSave.add( new ModelMapper().map( tag, PageTagEntity.class ) );
        }
        pageTagRepo.saveAll( tagsToSave );
    }

}
