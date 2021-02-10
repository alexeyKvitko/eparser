package tech.madest.eparser.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.madest.eparser.dto.CompanyPageDto;
import tech.madest.eparser.dto.PageTagDto;
import tech.madest.eparser.entity.CompanyPageEntity;
import tech.madest.eparser.entity.PageTagEntity;
import tech.madest.eparser.repository.PageTagRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PageTagServiceImpl {

    @Autowired
    PageTagRepository pageTagRepo;

    public List< PageTagDto > getAllTagByPage( Integer pageId ) {
        List< PageTagEntity > pageTagEntities = pageTagRepo.findAllByPageId( pageId );
        ModelMapper mapper = new ModelMapper();
        List< PageTagDto > pageTagDtos = pageTagEntities.stream().map( entity -> mapper.map( entity, PageTagDto.class ) ).collect( Collectors.toList() );
        return pageTagDtos;
    }

    public void upsertPageTag( PageTagDto pageTagDto){
        PageTagEntity pageTagEntity = null;
        if ( pageTagDto.getId() != null ){
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
        } else {
            pageTagEntity = new ModelMapper().map( pageTagDto, PageTagEntity.class );
        }
        pageTagRepo.save( pageTagEntity );
    }

    public void deletePageTag( Integer pageTagId ){
        pageTagRepo.deleteById( pageTagId );
    }

}
